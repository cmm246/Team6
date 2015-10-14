package map.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Vector;
import java.util.Random;

import map.Controller.Action.Operation;
import map.Model.Item.Property;

/**
 * This class defines the non-player character which interacts with the player
 * which the game engine can provide to the game designer.
 * 
 * @author mak_96
 * @since 1.0
 */
public class Character extends Player{

	private boolean atRoom;
	private Random random;

	private int id;	//ID of Character
	private static int count = 1;
	private boolean hint;

	private boolean once; 	//true: once;	false: always
	private boolean follow;	//true: follow;	false: stay
	private Item weapon;
	
	// Game Designer configurable states.
	private String name;
	private String description;
	private Room location;
	private int health;
	private int damage;
	private String message;
	private double attackProbability; // probability from 0 to 1
	private boolean attackableFlag;

	private boolean multipleAttacksFlag;
	private String attackMessage;
	private int round;

	/**
	 * @param weapon the weapon to set
	 */
	public void setWeapon(Item weapon) {
		this.weapon = weapon;
	}

	/**
	 * @return the weapon
	 */
	public Item getWeapon() {
		return weapon;
	}

	public void deepCopy(Character src) {
		name = src.name;
		description = src.description;
		message = src.message;
		attackMessage = src.attackMessage;
		health = src.health;
		attackProbability = src.attackProbability;
		damage = src.damage;
		once = src.once;
		follow = src.follow;
		multipleAttacksFlag = src.multipleAttacksFlag;
		if (src.location != null)
			location = new Room(src.location);
		atRoom = src.atRoom;
		attackableFlag = src.attackableFlag;
		if (src.path != null)
			path = new Vector<PathNode>(src.path);
	}

	/**
	 * Constructor:
	 * 
	 * @param name
	 * @param description
	 * @param location
	 */
	public Character(String name, String description, Room location){
		this.name = name;
		this.description = description;
		this.location = location;
		id = count++;
	}
	
	public Character(String name, String description, Room location,
			int health, double attackProbability, int damage,
			boolean attackableFlag, boolean multipleAttacksFlag) {
		
		this.message = null;
		this.attackMessage = null;
		this.name = name;
		this.description = description;
		this.location = location;
		this.health = health;
		this.attackProbability = attackProbability;
		this.damage = damage;
		this.attackableFlag = attackableFlag;
		this.multipleAttacksFlag = multipleAttacksFlag;
		atRoom = true;
		path = new Vector<PathNode>();
		path.add(new PathNode(Connection.Direction.UNDEFINED, location));
		id = count++;
	}

	/**
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Print out the message from the character to the player.
	 */
	public void reciteMessage() {
		System.out.println(message);
	}

	/**
	 * 
	 * @param attackMessage
	 */
	public void setAttackMessage(String attackMessage) {
		this.attackMessage = attackMessage;
	}

	/**
	 * 
	 * @return
	 */
	public String getAttackMessage() {
		return attackMessage;
	}

	/**
	 * Print out the message after an attack from the character.
	 */
	public void reciteAttackMessage() {
		System.out.println(attackMessage);
	}

	/**
	 * 
	 * @param attackProbability
	 */
	public void setAttackProbability(double attackProbability) {
		this.attackProbability = attackProbability;
	}

	/**
	 * 
	 * @return
	 */
	public double getAttackProbability() {
		return attackProbability;
	}

	/**
	 * 
	 * @param damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * 
	 * @return
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * 
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health >= 100 ? 100 : health;
	}

	/**
	 * 
	 * @return
	 */
	public int getHealth() {
		return health;
	}

	public void setLocation(Room location) {
		this.location = location;
	}

	public Room getLocation() {
		return location;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isAttackable() {
		return attackableFlag;
	}

	public boolean isMultipleAttack() {
		return multipleAttacksFlag;
	}

	/**
	 * ATTACK CONDITIONS.
	 * 1. character will attack once when player enters the room.
	 * 2. character will attack after every turn until the player dies or character dies (same room).
	 * 3. character will attack after every turn until the player dies or character dies (follow player)
	 */

//	/**
//	 * Attack player.
//	 * Check of character in player's current location done outside;
//	 * Otherwise character is attacking player from another room ???
//	 */
//	public void attackPlayer(Player player) {
//		if (isAttackable()) {
//			if (random.nextDouble() > getAttackProbability()) {
//				player.setHealth(player.getHealth() - getDamage());
//				reciteAttackMessage(true);
//			} else {
//				reciteAttackMessage(false);
//			}
//		} else {
//			reciteMessage();
//		}
//	}
	
//<<<<<< Character.java
	
	//add 2 methods for NPCInfoDialog
	public String getName(){
		return this.name;
	}
	
	public String getDiscription(){
		return this.description;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * show hint and respond to it between rounds
	 * @param player
	 * @param harm
	 * @return true : attack continue;  false : attack terminate 
	 */
	private boolean showHint(Player player, int harm){
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true){
			try {
				System.out.println("Make a Choice: ");
				System.out.println("\t" + ">1: Attack");
				System.out.println("\t" + ">2: Use Item");
				System.out.println("\t" + ">3: Auto Attack");
				System.out.println("\t" + ">4: Escape");
				System.out.print(">");

				String command = br.readLine();
				int choice = Integer.parseInt(command);
				switch(choice){
					case 1:
					{//attack
						if(this.hurt(harm) <= 0)
							//npc died then terminate
							return false;
						return true;	//continue for next round
					}
					case 2:
					{//use item to increase health or to attack
						player.showInventory();
						while(true){
							System.out.println("Please type item to use(or 'r' for return): ");
							command = br.readLine();
							if(command.compareToIgnoreCase("r") == 0)
								break;
							for(Item foo : player.getInventory()){
								if(foo.getName().compareToIgnoreCase(command) == 0){
									//find item
									int value = 0;
									if(foo.chkProp(Item.Property.DRINKABLE)){
										int addHeath=(Integer)foo.getPropValue(Item.Property.DRINKABLE);
										player.setHealth(player.getHealth()+addHeath);
										player.getInventory().remove(foo);
										System.out.println("PLayer Health = " + player.getHealth());
										return true;	//continue for next round
									}else if(foo.chkProp(Item.Property.EATABLE)){
										int addHeath = (Integer)foo.getPropValue(Item.Property.EATABLE);
										player.setHealth(player.getHealth() + addHeath);
										player.getInventory().remove(foo);
										System.out.println("PLayer Health = " + player.getHealth());
										return true;	//continue for next round
									}else if(foo.chkProp(Item.Property.THROWABLE)){
										Random r = new Random();
										if(r.nextDouble() <= foo.getProbThrow()){
											value = (Integer)foo.getPropValue(Item.Property.THROWABLE);
										}else
											value = 0;

										player.getInventory().remove(foo);
										if(value == 0){
											System.out.println("Bad lucky!, You missed!");
										}else{
											System.out.println("Action Succeed!");
										}
										if(this.hurt(value) <= 0)
											return false;	//npc died then terminate
										return true;	//continue for next round
									}else if(foo.chkProp(Item.Property.FIRABLE)){
										Random r = new Random();
										if(r.nextDouble() <= foo.getProbFire()){
											value = (Integer)foo.getPropValue(Item.Property.FIRABLE);
										}else
											value = 0;

										if(value == 0){
											System.out.println("Bad lucky!, You missed!");
										}else{
											System.out.println("Action Succeed!");
										}

										if(this.hurt(value) <= 0)
											return false;	//npc died then terminate
										return true;	//continue for next round
									}else{
										System.out.println("Cannot use the item, Try another one!");
										break;
									}
								}//end if
							}//end for
						}//end while
						continue;
					}
					case 3:
					{//attack without hint
						hint = false;
						if(this.hurt(player.getDamage()) <= 0)
							return false;	//npc died then terminate
						return true;	//continue for next round
					}
					case 4:
					{//escape
						return false;	//escape then terminate
					}
					default:
					{
						System.out.println("Wrong choice, Try again!");
						continue;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException nfe) {
				System.out.println("Wrong input, Try again!");
				continue;
			}
			return false;	//should not run to here, some error then terminate
		}
	}
	
	/**
	 * attack in one round
	 * @param player
	 * @param harm
	 * @return true : continue;  false : terminate due to dying or escaping or only one round attacking
	 */
	private boolean attackOneRound(Player player, int harm){
		
		round++;
		System.out.println("Round " + round + ": ");
		//decide damage
		int damage = this.damage;
		random = new Random();
		double value = random.nextDouble();
		if(weapon != null){
			Random r = new Random();
			if(r.nextDouble() <= weapon.getProbFire()){
				damage = (Integer)weapon.getPropValue(Property.FIRABLE);
			}else
				damage = 0;
		}else if (value <= attackProbability) {
			damage = 0;
		}
		
		if(damage == 0){
			System.out.println("So lucky! " + this.name + " missed.");
		}
		
		else if(player.hurt(damage) <= 0){
			//player died then terminate
			return false;
		}
		
		System.out.println("Player health left: " + player.getHealth());
		System.out.println(this.name + " heather left: " + this.health);
		System.out.println();

		if(hint){
			if(!showHint(player, harm)){
				//one died or escaped
				return false;
			}

			if(isOnce()){
				return false;
			}
		}else{
			if(this.hurt(player.getDamage()) <= 0)
				return false;	//npc died then terminate
		}
		return true;	//continue for next round
	}
	
	/**
	 * attack player
	 * @param player
	 * @param harm
	 */
	public void attackPlayer(Player player, int harm){

		hint = true;
		boolean ret = true;
		round = 0;
		do{
			ret = attackOneRound(player, harm); 
		} while(ret);
	}

	/**
	 * @return the follow
	 */
	public boolean isFollow() {
		return follow;
	}
	
	/**
	 * @param follow the follow to set
	 */
	public void setFollow(boolean follow) {
		this.follow = follow;
	}
	
	/**
	 * @param once the once to set
	 */
	public void setOnce(boolean once) {
		this.once = once;
	}
	
	/**
	 * @return the once
	 */
	public boolean isOnce() {
		return once;
	}
	   
	public int hurt(int harm){
		health -= harm;
		return health;
	}

	public void setAttackableFlag(boolean attackableFlag) {
		this.attackableFlag = attackableFlag;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description){
		this.description = description;
	}
	
} // Character
