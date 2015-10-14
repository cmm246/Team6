package map.Controller.Action;


import java.util.Random;
import java.util.Vector;
import map.Model.*;
import map.Model.Character;
import map.Model.Item.Property;

/**
 * Responsible for update the state of Class Character
 * @author Yan Wang
 * @version 1.5
 */
public class CharacterHandler {

	public String err = "";

	/**
	 * make operation for Character
	 * @param param Operation
	 * @return true: action succeed; false: otherwise
	 */
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
    			Player player = param.player;
    			//follow 
    			for(map.Model.Character c : player.getVnpc()){
    				if(c.isFollow()){
	        			Room room = c.currentLocation();
	        			if(room == null){
	        				err = "No current Room!";
	        				return false;
	        			}
	        			boolean match = false;
	        			for(Connection foo : room.getConnection()){
	        				if(foo.getDirection().name().compareTo(param.act.name()) == 0){
	                			c.setCurrent_location(foo);
	                			match = true;
	                			break;
	        				}
	        			}
	        			if(!match){
    	    				err = "No Match Connection!";
    	    				return false;
	        			}
					}
					else{
						//not run here
	    				err = "Should not run to here!";
	    				return false;
					}
    			}
    			if(player.isEscape){
    				return true;
    			}
    			Vector<Character> npcs = new Vector<Character>(player.getVnpc());
    			//attack
    			for(map.Model.Character c : npcs){
    				if(c.isAttackable()){
    					//if be able to attack and active attack
        				c.attackPlayer(player, player.getDamage());
    					if(player.getHealth() <= 0){
    						//if player died
    						System.out.println("You Lose!");
    						param.isOver = true;
    						return false;
    					}
    					if(c.getHealth() <= 0){
    						//if npc died
    	    				System.out.println("Health = " + player.getHealth());
    						System.out.println("You Win!");
    						if(player.getVnpc().contains(c))
    							player.delNpc(c);
    						continue;
    					}else{
    						//if player escaped or npc attack once
    						if(c.isFollow() && !player.containsNPC(c)){
    							//if npc followed and not contained in npcs of player
    							player.addNpc(c);
    							player.getOwner().getNpcs().remove(c);
    						}
							return true;
    					}
    				}
    			}
				break;
			}
    		case 18:
    		case 19:
    		case 20:
    		{
    			// FIRE/THROW/ATTACK
    			Player player = param.player;
    			Item item = param.obj;
    			Vector<map.Model.Character> vnpc = new Vector<Character>();
				if(param.npc != null){
					vnpc.add(param.npc);
				}else{
					vnpc =
	    				player.getOwner().getNpcs(player.currentLocation());					
				}
    			if(vnpc == null || vnpc.size() == 0){
					err = "Cannot find Character to Attack!";
					return false;
    			}
				int harm = 0;
				//get item harm
				if(param.act.name().equals(Operation.Action.FIRE.name())){
					if(item.chkProp(Property.FIRABLE)){
						Random r = new Random();
						if(r.nextDouble() <= item.getProbFire())
							harm = (Integer)item.getPropValue(Property.FIRABLE);
						else
							harm = 0;
					}
				}
				else if(param.act.name().equals(Operation.Action.THROW.name())){
					if(item.chkProp(Property.THROWABLE)){
						Random r = new Random();
						if(r.nextDouble() <= item.getProbThrow())
							harm = (Integer)item.getPropValue(Property.THROWABLE);
						else
							harm = 0;
						player.getInventory().remove(item);
					}
					
				}
				else if(param.act.name().equals(Operation.Action.ATTACK.name())){
					harm = player.getDamage();
				}

				if(harm == 0){
					//attack/fire/throw invalidly
					err = "You missed!";
					System.out.println(err);
				}
				boolean first = true;
    			for(map.Model.Character c : vnpc){
    				//when multi-NPCs, attack one by one in turn
    				if(first){
    					first = false;
        				if(c.hurt(harm) <= 0){
        					//attack NPC and check health
    	    				System.out.println("Health = " + player.getHealth());
        					System.out.println("You Win!");
        					continue;	//attack next npc
        				}
    				}
    				//if(c.isAttackable() && !c.isPassive()){
    				if(c.isAttackable()){
        				System.out.println("Attack " + c.getName() +" :");
    					//if be able to attack and active attack
        				c.attackPlayer(player, player.getDamage());
    					if(player.getHealth() <= 0){
    						//if player died
    						System.out.println("You Lose!");
    						return true;
    					}
    					if(c.getHealth() <= 0){
    						//if npc died
    	    				System.out.println("Health = " + player.getHealth());
    						System.out.println("You Win!");
   						if(player.getVnpc().contains(c))
    							player.delNpc(c);
    						continue;
    					}else{
    						//if player escaping or attacked once
    						if(c.isFollow() && !player.containsNPC(c)){
    							//if npc followed and not contained in npcs of player
    							player.addNpc(c);
    							player.getOwner().getNpcs().remove(c);
    						}
							return true;
    					}
    				}
    			}
    			break;
    		}
			default:{
				err = "No such a Verb!";
				return false;
			}
    	}//end switch
    	return true;
    }
}