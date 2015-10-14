package map.Model;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Vector;

import map.Conditions.Conditions;
import map.Conditions.SpecificMagicWord;
import map.Model.Connection.Direction;
import map.Model.Item.Property;

/**
 * This class defines the game player used to play the game designed using the game engine.
 * 
 * @author Chung Mak
 * @since 1.0
 */
public class Player implements Serializable{
	
	public static int MAX_ITEM = 10;
	public static int MAX_HEALTH = 100;

	/** every time a command is executed by the player the counter is incremented */
	private int score;
	private int health;
	private int damage;
	private Vector<Item> inventory;
	private Room current_location;
	
	public Vector<PathNode> path;
	public boolean isEscape;
	private Vector<Character> vnpc;	//npcs followed 

	private Map owner;
	private String magicWord;
	
	public class PathNode implements Serializable {
		Connection.Direction inComing;
		Room room;
		
		public PathNode(Direction direction, Room room) {
			super();
			this.inComing = direction;
			this.room = room;
		}

		public Room getRoom() {
			return room;
		}
		
	}
	
	/**
	 * Constructor to initialise the inventory, current player location, player health,
	 * and player score.
	 */
	public Player() {
		this.score = 0;
		this.health = MAX_HEALTH;
		this.damage = 0;
		this.inventory = new Vector<Item>();
		this.current_location = null;
		this.path = new Vector<PathNode>();
		this.vnpc = new Vector<Character>();
		this.owner = null;
		this.magicWord = null;
	}
	
	/**
	 * Another constructor for player with a player's score, inventory, room, hearth, and damage
	 */
	public Player(int score, Vector<Item> inventory, Room room, int health, int damage) {
		this.score = score;
		this.health = health;
		this.damage = damage;
		this.inventory = inventory;
		this.current_location = room;
		this.path = new Vector<PathNode>();
		this.vnpc = new Vector<Character>();
		this.owner = null;
		this.magicWord = null;
	}
	
	/**
	 * and player score for a saved game.
	 * 
	 * @param src The current player state.
	 */
	public Player(Player src){
		deepCopy(src);
	}

	/**
	 * Deep copy the current state of the game (current state of player)
	 * 
	 * @param src Current player state.
	 */
	private void deepCopy(Player src){

		score = src.score;
		current_location = src.current_location;
		health = src.health;
		this.damage = src.damage;
		
		this.owner = new Map(src.owner);
		this.magicWord = src.magicWord;

		if(src.path != null)
			path = new Vector<PathNode>(src.path);
		else
			path = new Vector<PathNode>();
		
		if(src.inventory != null)
			inventory = new Vector<Item>(src.inventory);
		else
			inventory = new Vector<Item>();
		
		if(src.vnpc != null)
			vnpc = new Vector<Character>(src.vnpc);
		else
			this.vnpc = new Vector<Character>();
	}

	/**
	 * Add the item to the inventory and remove the item from the room.
	 * NOTE: same action as verb phrase pick up item.
	 * 
	 * @param add_item Item to be added to player inventory and removed from room.
	 */
	public void takeItem(Item add_item) {
		current_location.deleteItem(add_item);
		if(inventory == null)
			inventory = new Vector<Item>();
		inventory.addElement(add_item);
	}
	
	/**
	 * Iterate through the inventory list and remove the item from the list and update
	 * the room where the item is left.
	 * NOTE: same action as verb phrase put down item.
	 * 
	 * @param drop_item Item to be dropped in room and removed from player inventory.
	 */
	public void dropItem(Item drop_item) {
		inventory.remove(drop_item);
		current_location.addItem(drop_item);
	}
	
	/**
	 * Get the current location of the player (must point to a Room).
	 * 
	 * @return Current location of player.
	 */
	public Room currentLocation() {
		return current_location;
	}
	
	/**
	 * Prints a list of items currently collected by the player.
	 */
	public void showInventory() {
		// Traverse entire list, printing them all out
		if(inventory == null || inventory.size() == 0){
			System.out.println("No Items Carried with!");
			System.out.println();
			return;
		}
		System.out.println("Inventory: ");
		for (Enumeration<Item> inv = inventory.elements(); inv.hasMoreElements();)
		{
			Item it=(Item)inv.nextElement();
			System.out.println("\t" + it.getName());
			if(it.getProps() == null)
				continue;
			for(java.util.Map.Entry<Property, Object> foo : it.getProps().entrySet()){
				System.out.print("\n\t\t" + foo.getKey().name() + "\t");
				if(foo.getKey().name().equals(Property.DRINKABLE.name())){

					System.out.println("cure = " + (Integer)foo.getValue());
				
				}else if(foo.getKey().name().equals(Property.EATABLE.name())){

						System.out.println("\tcure = " + (Integer)foo.getValue());
					
				}else if(foo.getKey().name().equals(Property.FIRABLE.name())){
					
					System.out.println("\tdamage = " + (Integer)foo.getValue());
					
				}else if(foo.getKey().name().equals(Property.THROWABLE.name())){
					
					System.out.println("damage = " + (Integer)foo.getValue());
				
				}

			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Return the current player score.
	 * 
	 * @return the current player score.
	 */
	public int getScore() {
		return score;		
	}
	
	/**
	 * Return the current player health.
	 * 
	 * @return The current player health.
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * Get the current location of the player (must point to a Room).
	 * 
	 * @return Current location of player.
	 */
	public Room getCurrent_location() {
		return current_location;
	}

	/**
	 * Return the current player inventory.
	 * 
	 * @return The current player inventory.
	 */
	public Vector<Item> getInventory() {
		return inventory;
	}
	
	public void setInventory(Vector<Item> inventory) {
		this.inventory = inventory;
	}

	public boolean isCarried(Item obj){
		for(Item foo : inventory){
			if(foo.getName().compareTo(obj.getName()) == 0)
				return true;
		}
		return false;
	}
	
	/**
	 * Set the current location of the player based on the room location.
	 * 
	 * @param current_location The room where player will be located.
	 */
	public void setCurrent_location(Room current_location) {
		this.current_location = current_location;
		path.add(new PathNode(Connection.Direction.UNDEFINED, current_location));
		score++;
	}

	/**
	 * Set the current location of the player based on the connection to a room location.
	 * 
	 * @param con The connection leading to the room where the player will be located.
	 */
	public void setCurrent_location(Connection con) {
		current_location = con.getConnectsTo();
		path.add(new PathNode(con.getDirection(), current_location));
		score++;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setHealth(int health) {
		this.health = health >= MAX_HEALTH ? MAX_HEALTH : health;
	}

	public void addNpc(Character c){
		if(vnpc == null){
			vnpc = new Vector<Character>();
		}
		vnpc.add(c);
	}
	
	public void delNpc(Character c){
		vnpc.remove(c);
	}
	
	public Vector<Character> getVnpc() {
		return vnpc;
	}

	public void setVnpc(Vector<Character> vnpc) {
		this.vnpc = vnpc;
	}

	public boolean containsNPC(Character c){
		if(vnpc != null){
			return vnpc.contains(c);
		}
		return false;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int hurt(int harm){
		health -= harm;
		return health;
	}

	public String getMagicWord() {
		return magicWord;
	}

	public void setMagicWord(String magicWord) {
		this.magicWord = magicWord;
	}
	
	public Vector<String> getMWordsConnection() {
		
		Vector<String> magicWords = new Vector<String>();
		for(Connection con : this.current_location.getConnection()){
			for(Conditions foo : con.getVcon()){
				if(foo instanceof SpecificMagicWord){
					SpecificMagicWord mw = (SpecificMagicWord) foo;
					magicWords.add(mw.getWord());
				}
			}
		}
		return magicWords;
	}
	
	public Connection getConWithMW(String word){

		for(Connection con : this.current_location.getConnection()){
			for(Conditions foo : con.getVcon()){
				if(foo instanceof SpecificMagicWord){
					SpecificMagicWord mw = (SpecificMagicWord) foo;
					if(mw.getWord().equals(word)){
						return con;
					}
						
				}
			}
		}
		return null;
	}

	public Vector<String> getMWordsItem() {

		Vector<String> magicWords = new Vector<String>();
		for(Item item : this.current_location.getItems()){
			for(Conditions foo : item.getVcon()){
				if(foo instanceof SpecificMagicWord){
					SpecificMagicWord mw = (SpecificMagicWord) foo;
					magicWords.add(mw.getWord());
				}
			}
		}
		return magicWords;
	}
	
	public Item getItemWithMW(String word){
		
		for(Item item : this.current_location.getItems()){
			for(Conditions foo : item.getVcon()){
				if(foo instanceof SpecificMagicWord){
					SpecificMagicWord mw = (SpecificMagicWord) foo;
					if(mw.getWord().equals(word)){
						return item;
					}
				}
			}
		}
		return null;
	}

	public Map getOwner() {
		return owner;
	}

	public void setOwner(Map owner) {
		this.owner = new Map(owner);
	}
    public void showNPC(){
		
		for(Character foo : vnpc){
			System.out.println("\t" + foo.getName());
		}
		
	}
	
}
