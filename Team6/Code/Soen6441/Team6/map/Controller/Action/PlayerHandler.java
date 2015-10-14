package map.Controller.Action;

import java.util.Vector;

import map.Model.*;

/**This class implements move function, load game function, save game function, inventory function and score function
 * @author yu_pei
 * @version 1.21
 * */

public class PlayerHandler {

	public String err = "";
	String f;
	Item item;
    /** implements move function, load game function, save game function, inventory function and score function
     * @param param Operation
     * @return boolean
     * */
	public boolean update (Operation param)
    {
    	switch(param.act.ordinal()){
    		case 0:	
    		case 1:
    		case 2:
    		case 3:
    		case 4:
    		case 5:
    		case 6:
    		case 7:
    		case 8:
    		case 9:
    		{
    			//move
    			Player player = param.player;
    			player.isEscape = false;
    			Room room = player.currentLocation();
    			if(room == null){
    				err = "No current Room!";
    				return false;
    			}
    			for(Connection foo : room.getConnection()){
    				if(foo.getDirection().name().compareTo(param.act.name()) == 0){
    					Item door = foo.getDoor(); 
						if( door != null && (Boolean)door.getPropValue(Item.Property.LOCKABLE)){
	    						err = "Please unlock Door first!";
	    						return false;
						}
						if(foo.chkCondition(player))
	        			{
							player.setCurrent_location(foo);
	            			Vector<map.Model.Character> vnpc =
	            				player.getOwner().getNpcs(player.currentLocation());
	            			Vector<map.Model.Character> chList = new Vector<map.Model.Character>();
	            			chList.addAll(vnpc);
	            			chList.addAll(player.getVnpc());
	            			player.currentLocation().showInfo(player);
	            			//set current Magic Word to null after a successful moving
	            			player.setMagicWord(null);
	            			
	            			
	            			//talked/attacked by npc
	            			//talking is prior to attacking
	            			for(map.Model.Character c : vnpc){
	            				if(!c.isAttackable()){
	            					//talk
	            					System.out.println(c.getName() + " talk to you:");
	            					c.reciteMessage();
	            					System.out.println();
	            				}
	            			}
	            			//attack
	            			for(map.Model.Character c : vnpc){
	            				//when multi-NPC, attack one by one in turn
	            				if(c.getHealth() > 0 && c.isAttackable()){
	            					//attack if no dead and be able to attack and active attack
	            					if(!player.isEscape){
		            					System.out.println(c.getName() + " attack you:");
		            					c.attackPlayer(player, player.getDamage());
		            					if(player.getHealth() <= 0){
		            						//if player died
		            						System.out.println("You Lose! Game Over!");
		            						param.isOver = true;
		            						return false;
		            					}
		            					else if(c.getHealth() <= 0){
		            						//if npc died
		            						System.out.println("You Win!");
		            						if(player.getVnpc().contains(c))
		            							player.delNpc(c);
		            						continue;	//next npc attack
		            					}else if(!c.isOnce()){
		            						//if player escaped
		            						if(c.isFollow() && !player.containsNPC(c)){
		            							//if npc followed and not contained in npcs of player
		            							player.addNpc(c);
		            							player.getOwner().delNpc(c);
		            						}
		            						player.isEscape = true;
		            						continue;
		            					}else if(c.isOnce()){
		            						//if npc attack once
		            						if(c.isFollow() && !player.containsNPC(c)){
		            							//if npc followed and not contained in npcs of player
		            							player.addNpc(c);
		            							player.getOwner().delNpc(c);
		            						}
		            						continue;
		            					}else{
		            						System.out.println("should not be here, caused by some logic errors!");
		            						param.isOver = true;
		            						return false;
		            					}
	            					}else{
	            						if(c.isFollow() && !player.containsNPC(c)){
	            							//if npc followed and not contained in npcs of player
	            							player.addNpc(c);
	            							player.getOwner().delNpc(c);
	            						}	            	
	            						continue;
	            					}
	            				}//end if
	            			}//end for
	            			return true;
	        			}
						else
						{
							err="You are not allowed to move!";
							return false;
						}
					}
    			}//end for
				err = "No Match Connection!";
				return false;
			}
    		case 10:
    		case 11:
    		{//	take/pick up
    			
    			Item item = param.obj;
    			Player player = param.player;
    			if(item.chkCondition(player))
    			{
                     player.takeItem(item);
                     //set current Magic Word to null after a successful taking
                     player.setMagicWord(null);
                     return true;
    			}else
    			{
    				err="You are not allowed to pick up the item!";
					return false;
    			}
    		}
    		case 12:
    		case 13:
    		{//drop
    			Player player = param.player;
    			Item item = param.obj;
    			player.dropItem(item);
    			break;
    		}
    		case 14:
    		{
    			// show inventory
    			Player player = param.player;
    			player.showInventory();
        		break;
    		}
    		case 15:
    		{
    			//show score
    			Player player = param.player;
				System.out.println("score is..........");
				System.out.print("score is ");
				System.out.println(player.getScore());
        		break;
    		}
    		case 16:{
    			//Save
    			Player player=param.player;
    			String filename = param.getObjName();
				try {
					player.getOwner().save(new java.io.File (filename));
				} catch (Exception e) {
					err = "Saveing Failure!";
					return false;
				}
        		System.out.println("Saving Succeed!");
        		System.out.println("");
				break;
    		}
    		case 17: {
    			//load
    			Player player=param.player;
    			String filename = param.getObjName();
    			try {
					player.getOwner().load(new java.io.File (filename));
				} catch (Exception e) {
					err = "Loading Failure!";
					return false;
				}
        		System.out.println("Loading Succeed!");
        		System.out.println("");
    			player.currentLocation().showInfo(player);
				break;
    		}
    		case 21:
    		{//EAT
    			Item item = param.obj;
    			Player player = param.player;
				int value = (Integer)item.getPropValue(Item.Property.EATABLE);
				int old = player.getHealth();
				player.setHealth(old + value);
				player.getInventory().remove(item);
				System.out.println("eaten!");
				System.out.print("health");
				if(value >= 0){
					System.out.println(" increase from " + old + " to " + player.getHealth());
				}else{
					System.out.println(" decrease from " + old + " to " + player.getHealth());
				}
    			break;
    		}
    		case 22:
    		{//Drink
    			Item item = param.obj;
    			Player player = param.player;
				int value = (Integer)item.getPropValue(Item.Property.DRINKABLE);
				int old = player.getHealth();
				player.setHealth(old + value);
				player.getInventory().remove(item);
				System.out.println("drinked!");
				System.out.print("health");
				if(value >= 0){
					System.out.println(" increase from " + old + " to " + player.getHealth());
				}else{
					System.out.println(" decrease from " + old + " to " + player.getHealth());
				}
				break;
    		}
			default:
			{
				err = "No such a Verb!";
				return false;
			}
    	}//end switch
    	return true;
    }
}
