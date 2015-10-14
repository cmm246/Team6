package map.Controller.Action;

import java.util.Observable;
import java.util.Vector;

import map.Model.*;
import map.Model.Character;
import map.Model.Item.Property;
/**
 * Responsible for trigger the update of PlayerHandler, ItemHandler and CharacterHandler
 * @author Yan Wang
 */
public class Operation {

	PlayerHandler playerHandler;
	CharacterHandler characterHandler;
	ItemHandler itemHandler;
	Player player;
	Action act;
	String objName;
	Item obj;
	Character npc;
	
	public boolean isValid = true;
	public boolean isOver  = false;
	public String err;
	
	/**
	 * Constructor
	 * @param p : Player
	 */
	public Operation(Player p){
		player = p;
		playerHandler = new PlayerHandler();
		characterHandler = new CharacterHandler();
		itemHandler = new ItemHandler();
	}
	
	public enum Action {
	    NORTH {	
	    	//0
	        public void respond(Operation op){

	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        	
	        }
	    },
	    SOUTH {	
	    	//1
	        public void respond(Operation op){
	        	
	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        }
	    },
	    EAST {
	    	//2
	        public void respond(Operation op){
	        	
	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        }
	    },
	    WEST {
	    	//3
	        public void respond(Operation op){
	        	
	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        }
	    },
	    NORTHEAST {
	    	//4
	        public void respond(Operation op){
	        	
	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        }
	    },
	    NORTHWEST {
	    	//5
	        public void respond(Operation op){
	        	
	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        }
	    },
	    SOUTHEAST {
	    	//6
	        public void respond(Operation op){
	        	
	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        }
	    },
	    SOUTHWEST {
	    	//7
	        public void respond(Operation op){
	        	
	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        }
	    },
	    UP {
	    	//8
	        public void respond(Operation op){
	        	
	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        }
	    },
	    DOWN {
	    	//9
	        public void respond(Operation op){
	        	
	        	if( op.playerHandler.update(op) ){
	        		op.isValid = op.characterHandler.update(op);
	        		if(!op.isValid)
	        			op.err = op.characterHandler.err;
	        	}else{
	        		op.isValid = false;
	        		op.err = op.playerHandler.err;
	        		return;
	        	}
	        }
	    },
	    TAKE {
	    	//10
	        public void respond(Operation op){

	        	if(op.player == null
    					|| op.player.currentLocation() == null
    					|| op.player.currentLocation().getItems() == null){
    				op.isValid = false;
    				op.err = "Null Argument!";
    				return ;
    			}
    			if(op.getObj() == null){
    	    		//find Item
    	    		boolean match = false;
        			for(Item foo: op.player.currentLocation().getItems()){
        				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
                			//find item
        					match = true;
        					op.obj = foo;
        					break;
        				}
        			}
        			if(!match){
            			//not in current room
            			op.isValid = false;
        				op.err = "No such Items!";
        				return;
        			}
    			}
    			if(op.player.getInventory() != null
    					&& op.player.getInventory().size() + 1 > Player.MAX_ITEM){
        			//exceed amount
        			op.isValid = false;
    				op.err = "Exceed maximum amount of Items carried!";
    				return;
    			}
    			if(!op.obj.chkProp(Item.Property.TAKEABLE)){
        			//cannot take
        			op.isValid = false;
    				op.err = "Cannot take!";
    				return;
    			}
    			
    			if( op.playerHandler.update(op) ){
    	        	//action succeeds
            		System.out.println("Taken!");
    			}else{
    				op.isValid = false;
    				op.err = op.playerHandler.err;
    			}
	        }
	    },
	    PICK_UP {
	    	//11
	        public void respond(Operation op){

	        	op.setAct(Action.TAKE);
	        	op.act.respond(op);
	        
	        }
	    },
	    DROP {
	    	//12
	        public void respond(Operation op){

	        	if(op.player == null
    					|| op.player.getInventory() == null
    					|| op.player.currentLocation() == null){
    				op.isValid = false;
    				op.err = "Null Argument!";
    				return ;
    			}
    			if(op.getObj() == null){
    	    		boolean match = false;
        			for(Item foo : op.player.getInventory()){
        				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
                			//find item
        					match = true;
        					op.obj = foo;
        					break;
        				}
        			}
        			if(!match){
            			//not carry with
            			op.isValid = false;
        				op.err = "No such Items Carried!";
        				return;
        			}
    			}
    			if( op.playerHandler.update(op) ){
    	        	//action succeeds
            		System.out.println("Dropped!");
    			}else{
    				op.isValid = false;
    				op.err = op.playerHandler.err;
    			}
	        }
	    },
	    PUT_DOWN {
	    	//13
	        public void respond(Operation op){

	        	op.setAct(Action.DROP);
	        	op.act.respond(op);
	        }
	    },
	    INVENTORY {
	    	//14
	        public void respond(Operation op){
    			if( !op.playerHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.playerHandler.err;
    			}
	        }
	    },
	    SCORE {
	    	//15
	        public void respond(Operation op){
    			if( !op.playerHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.playerHandler.err;
    			}
	        }
	    },
	    SAVE {
	    	//16
	        public void respond(Operation op){
    			if( !op.playerHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.playerHandler.err;
    			}
	        }
	    },
	    LOAD {
	    	//17
	        public void respond(Operation op){
    			if( !op.playerHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.playerHandler.err;
    			}
	        }
	    },
	    //TODO new verbs for build3
	    FIRE {
	    	//18
	        public void respond(Operation op){

	        	//find item
	    		boolean match = false;
    			for(Item foo : op.player.getInventory()){
    				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
            			//find item
    					match = true;
    					op.obj = foo;
    					break;
    				}
    			}
    			if(!match){
        			//not carry with
        			op.isValid = false;
    				op.err = "No such Items Carried!";
    				return;
    			}
    			//check property
    			if(!op.obj.chkProp(Item.Property.FIRABLE)){
        			//cannot fire
        			op.isValid = false;
    				op.err = "Cannot fire!";
    				return;
    			}
    			
    			//attack by items
    			if( !op.characterHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.characterHandler.err;
    			}

	        }
	    },
	    THROW {
	    	//19
	        public void respond(Operation op){

	        	//find item
	    		boolean match = false;
    			for(Item foo : op.player.getInventory()){
    				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
            			//find item
    					match = true;
    					op.obj = foo;
    					break;
    				}
    			}
    			if(!match){
        			//not carry with
        			op.isValid = false;
    				op.err = "No such Items Carried!";
    				return;
    			}

    			//check property
    			if(!op.obj.chkProp(Item.Property.THROWABLE)){
        			//cannot throw
        			op.isValid = false;
    				op.err = "Cannot throw!";
    				return;
    			}
    			
    			//check any npc exists at current location
    			Vector<Character> chList = op.player.getOwner().getNpcs(op.player.currentLocation());
    			if(chList == null || chList.size() == 0){
    				op.act = Operation.Action.DROP;
    				op.act.respond(op);
    				return;
    			}
    			
    			//attack by items
    			if( !op.characterHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.characterHandler.err;
    			}
	        }
	    },
	    ATTACK {
	    	//20
	        public void respond(Operation op){
	        	
	        	//attack by hands
    			if( !op.characterHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.characterHandler.err;
    			}

	        }
	    },
	    EAT {
	    	//21
	        public void respond(Operation op){

	        	//find item
	    		boolean match = false;
    			for(Item foo : op.player.getInventory()){
    				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
            			//find item
    					match = true;
    					op.obj = foo;
    					break;
    				}
    			}
    			if(!match){
        			//not carry with
        			op.isValid = false;
    				op.err = "No such Items Carried!";
    				return;
    			}

    			//check property
    			if(!op.obj.chkProp(Item.Property.EATABLE)){
        			//cannot fire
        			op.isValid = false;
    				op.err = "Cannot eat!";
    				return;
    			}
    			
    			if( !op.playerHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.playerHandler.err;
    			}

	        }
	    },
	    DRINK {
	    	//22
	        public void respond(Operation op){

	        	//find item
	    		boolean match = false;
    			for(Item foo : op.player.getInventory()){
    				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
            			//find item
    					match = true;
    					op.obj = foo;
    					break;
    				}
    			}
    			if(!match){
        			//not carry with
        			op.isValid = false;
    				op.err = "No such Items Carried!";
    				return;
    			}

    			//check property
    			if(!op.obj.chkProp(Item.Property.DRINKABLE)){
        			//cannot fire
        			op.isValid = false;
    				op.err = "Cannot drink!";
    				return;
    			}

    			if( !op.playerHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.playerHandler.err;
    			}

	        }
	    },
	    LOCK {
	    	//23
	        public void respond(Operation op){

	        	//find item
	        	boolean match = false;
    			for(Item foo : op.player.getInventory()){
    				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
            			//find item
    					match = true;
    					op.obj = foo;
    					break;
    				}
    			}
    			if(!match){
    	        	for(Connection con : op.player.currentLocation().getConnection()){
    	        		if(con.getDoor().getName().compareToIgnoreCase(op.objName) == 0){
    	        			op.obj = con.getDoor();
    	        			match = true;
    	        			break;
    	        		}
    	        	}
    	        	if(!match){
            			for(Item foo : op.player.currentLocation().getItems()){
            				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
                    			//find item
            					match = true;
            					op.obj = foo;
            					break;
            				}
            			}
            			if(!match){
                			op.isValid = false;
            				op.err = "Cannot find " + op.objName;
            				return;
            			}
        			}
    			}
    			
    			//check Proerty
    			if(!op.obj.chkProp(Property.LOCKABLE)){
        			op.isValid = false;
    				op.err = op.objName + " cannot lock/unlock!";
    				return;
    			}else{
    				boolean value = (Boolean)op.obj.getPropValue(Property.LOCKABLE);
    				if(value){
            			op.isValid = false;
        				op.err = op.objName + " has already locked!";
        				return;
    				}
    			}

    			if( !op.itemHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.itemHandler.err;
    			}else
    				System.out.println("Lock!");

	        }
	    },
	    UNLOCK {
	    	//24
	        public void respond(Operation op){

	        	//find item
	        	boolean match = false;
    			for(Item foo : op.player.getInventory()){
    				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
            			//find item
    					match = true;
    					op.obj = foo;
    					break;
    				}
    			}
    			if(!match){
    	        	for(Connection con : op.player.currentLocation().getConnection()){
    	        		if(con.getDoor().getName().compareToIgnoreCase(op.objName) == 0){
    	        			op.obj = con.getDoor();
    	        			match = true;
    	        			break;
    	        		}
    	        	}
    	        	if(!match){
            			for(Item foo : op.player.currentLocation().getItems()){
            				if(foo.getName().compareToIgnoreCase(op.getObjName()) == 0){
                    			//find item
            					match = true;
            					op.obj = foo;
            					break;
            				}
            			}
            			if(!match){
                			op.isValid = false;
            				op.err = "Cannot find " + op.objName;
            				return;
            			}
        			}
    			}
	        	
    			//check Proerty
    			if(!op.obj.chkProp(Property.LOCKABLE)){
        			op.isValid = false;
    				op.err = op.objName + " cannot lock/unlock!";
    				return;
    			}else{
    				boolean value = (Boolean)op.obj.getPropValue(Property.LOCKABLE);
    				if(!value){
            			op.isValid = false;
        				op.err = op.objName + " has already unlocked!";
        				return;
    				}
    			}

    			if( !op.itemHandler.update(op) ){
    				op.isValid = false;
    				op.err = op.itemHandler.err;
    			}else
	        		System.out.println("Unlock!");
	        	
	        }
	    };
	    
	    public abstract void respond(Operation op);
	}
	
	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public PlayerHandler getPlayerHandler() {
		return playerHandler;
	}

	public void setPlayerHandler(PlayerHandler playerHandler) {
		this.playerHandler = playerHandler;
	}
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Action getAct() {
		return act;
	}

	public void setAct(Action act) {
		this.act = act;
	}

	public void setObj(Item obj) {
		this.obj = obj;
	}

	public CharacterHandler getCharacterHandler() {
		return characterHandler;
	}

	public void setCharacterHandler(CharacterHandler characterHandler) {
		this.characterHandler = characterHandler;
	}

	public ItemHandler getItemHandler() {
		return itemHandler;
	}

	public void setItemHandler(ItemHandler itemHandler) {
		this.itemHandler = itemHandler;
	}

	public Character getNpc() {
		return npc;
	}

	public void setNpc(Character npc) {
		this.npc = npc;
	}

	public Item getObj() {
		return obj;
	}

}
