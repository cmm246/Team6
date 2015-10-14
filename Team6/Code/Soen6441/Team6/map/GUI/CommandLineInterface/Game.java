package map.GUI.CommandLineInterface;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Vector;

import map.Controller.Action.Operation;
import map.Controller.Action.Operation.Action;
import map.Model.*;
import map.Model.Character;
/**Game class get game player's command and parse these commands.  
 * @author yu_Pei
 * @version 1.18
 * */

public class Game {
	
	Operation op;  
	Player player;
	Action act;
	String objName;
	//String NPCName;
	String err;
	/**Map structure to represent the association between the two String value
	 * */
	java.util.Map<String, String> abbre = new java.util.HashMap<String, String>();
	
	/**Item list 
	 * */
	Map worldMap = new Map();
	
     /** constructor for game class
     * */
	public Game(){
	
		abbre.put("NORTH", "N");
		abbre.put("SOUTH", "S");
		abbre.put("EAST", "E");
		abbre.put("WEST", "W");
		abbre.put("NORTHEAST", "NE");
		abbre.put("NORTHWEST", "NW");
		abbre.put("SOUTHEAST", "SE");
		abbre.put("SOUTHWEST", "SW");
		abbre.put("UP", "U");
		abbre.put("DOWN", "D");
		abbre.put("INVENTORY", "I");
	}
	
	/** Parse command from user
	 * @param command String 
	 * */
	public boolean parseCommand(String command){
		
		String[] tokens = command.split("\\s");
		if(tokens.length < 1 || tokens.length > 3){
			//if more than three words
			err = "Wrong Grammar! Verb[ Prep][ Noun] is valid!([ Prep][ Noun] means Optional)";
			return false;
		}
		
		Vector<Item> items = new Vector<Item>();
		if(player.getInventory() != null)
			items.addAll(player.getInventory());
		if(player.currentLocation() != null 
				&& player.currentLocation().getItems()!= null)
			items.addAll(player.currentLocation().getItems());
		
		if(tokens.length == 1){
			//one word
			act = null;
			objName = null;
			boolean match = false;
			for(java.util.Map.Entry<String, String> foo : abbre.entrySet()){
				if(tokens[0].compareToIgnoreCase(foo.getKey()) == 0
						|| tokens[0].compareToIgnoreCase(foo.getValue()) == 0){
					//if verb is in abbre
					for(Operation.Action a : Operation.Action.values()){
						if(a.name().compareTo(foo.getKey()) == 0){
							//found action
							act = a;
							match = true;
							break;
						}
					}
					if(match){
						return match;
					}
				}
			}
			//verb is not in abbre
			if(tokens[0].compareToIgnoreCase(Operation.Action.SCORE.name()) == 0){
				act = Operation.Action.SCORE;
				return true;
			}
			
			//retrieve Connection with specific magic word
			Connection con = player.getConWithMW(tokens[0]);
			if(con == null){
				err = "Wrong Verb!";	
				return false;
			}
			
			//set magic word for checking condition later
			player.setMagicWord(tokens[0]);
			//translate magic word into Action
			for(Operation.Action foo : Operation.Action.values()){
				if(con.getDirection().name().compareToIgnoreCase(foo.name()) == 0){
					act = foo;
					return true;
				}
			}
			err = "Wrong Verb!";	
			return false;
		}
		
		else if(tokens.length == 2){
			//two words
			act = null;
			objName = null;
			boolean match = false;
			//parse action
			for(Operation.Action foo : Operation.Action.values()){
				if(tokens[0].compareToIgnoreCase(foo.name()) == 0){
					//found action
					act = foo;
					match = true;
					break;
				}
			}
			if(!match){
				//set Operation value to magic word
				//retrieve Connection with specific magic word
				Item item = player.getItemWithMW(tokens[0]);
				if(item != null){
					//set magic word for checking condition later
					player.setMagicWord(tokens[0]);
					//translate magic word into Action
					act = Operation.Action.PICK_UP;
					objName = item.getName();
					match = true;
				}
			}
			if(!match){
				//if verb is not in Action
				err = "Wrong Verb!";	
				return false;
			}
			//parse item
			match = false;
			for(Item foo : items){
				if(tokens[1].compareToIgnoreCase(foo.getName()) == 0){
					objName = foo.getName();
					match = true;
					break;
				}
			}
			if(!match){
				if(tokens[0].compareToIgnoreCase(Operation.Action.SAVE.name()) == 0){
					act = Operation.Action.SAVE;
					objName = tokens[1];
					return true;
				}
				if(tokens[0].compareToIgnoreCase(Operation.Action.LOAD.name()) == 0){
					act = Operation.Action.LOAD;
					objName = tokens[1];
					return true;
				}
				if(tokens[0].compareToIgnoreCase(Operation.Action.LOCK.name()) == 0
						|| tokens[0].compareToIgnoreCase(Operation.Action.UNLOCK.name()) == 0){
					objName = tokens[1];
					return true;
				}
				if(tokens[0].compareToIgnoreCase(Operation.Action.ATTACK.name()) == 0){
					for(Character foo : worldMap.getNpcs(player.currentLocation())){
						if(foo.getName().compareTo(tokens[1]) == 0){
							//objName = tokens[1];
							op.setNpc(foo);
							return true;
						}
					}
					for(Character foo : player.getVnpc()){
						if(foo.getName().compareTo(tokens[1]) == 0){
							//objName = tokens[1];
							op.setNpc(foo);
							return true;
						}
					}
				}
				err = "No such a Item!";	
				return false;
			}
		}
		else if(tokens.length == 3){
			//three words
			act = null;
			objName = null;
			boolean match = false;
			String action = tokens[0] + "_" + tokens[1];
			//parse action
			if(tokens[0].compareToIgnoreCase(Operation.Action.FIRE.name())==0)
			{
				act=Operation.Action.FIRE;
				for(Item foo:player.getInventory()){
					if(foo.getName().compareToIgnoreCase(tokens[1])==0){
						objName=tokens[1];
						match=true;
						//return true;
						break;
					}
					
				}
				for(Character foo:worldMap.getNpcs(player.currentLocation()))
				{
					if(foo.getName().compareToIgnoreCase(tokens[2])==0)
					{
						op.setNpc(foo);
						return true;
					}
				}
				for(Character foo:player.getVnpc())
				{
					if(foo.getName().compareToIgnoreCase(tokens[2])==0)
					{
						op.setNpc(foo);
						return true;
					}
				}
				
			}else if(tokens[0].compareToIgnoreCase(Operation.Action.THROW.name())==0)
			{
				act=Operation.Action.THROW;
				for(Item foo:player.getInventory()){
					if(foo.getName().compareToIgnoreCase(tokens[1])==0){
						objName=tokens[1];
						match=true;
						break;
					}
					
				}
				for(Character foo:worldMap.getNpcs(player.currentLocation()))
				{
					if(foo.getName().compareToIgnoreCase(tokens[2])==0)
					{
						op.setNpc(foo);
						return true;
					}
				}
				for(Character foo:player.getVnpc())
				{
					if(foo.getName().compareToIgnoreCase(tokens[2])==0)
					{
						op.setNpc(foo);
						return true;
					}
				}
			}
			else {
				for(Operation.Action foo : Operation.Action.values()){
				 if(action.compareToIgnoreCase(foo.name()) == 0){
					act = foo;
					match = true;
					break;
				  }
				
			     }
			    if(!match){
				  err = "Wrong Verb!";	
				  return false;
			   }
			   //parse item
			   match = false;
			  for(Item foo : items){
				if(tokens[2].compareToIgnoreCase(foo.getName()) == 0){
					objName = foo.getName();
					match = true;
					break;
				 }
			  }
			  if(!match){
				err = "No such a Item!";	
				return false;
			 }
		  }
	     }
		return true;
	}
	/**  initialise the game, run the game and get game player's command
	 * */
	public void play() throws Exception
	{
		String command = null;
		boolean validCommand = false;

		BufferedReader din = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			System.out.println("Welcome to Adventure Game");
			System.out.println("\t" + ">New");
			System.out.println("\t" + ">Load");
			System.out.println("\t" + ">Quit");

			command = din.readLine();
			
			// Print a new line
			System.out.println();
			
			//load saved game
			if (command.compareToIgnoreCase("load") == 0){
				System.out.println("Please Input a Game File to Play: ");
				
				command = din.readLine();
				System.out.println();

				String filename;
				if(command.length() < 3){
					filename = "player.dat";
				}
				else{
					filename = command;
				}
				Map worldMap = new Map();
				try{
					worldMap.load(new File(filename));
				}catch(Exception e){
					System.err.println("Game Loading Failure!");
					return;
				}
				player = worldMap.getPlayer();
				player.setOwner(worldMap);
				for(Room foo : worldMap.getRooms()){
					if(player.getCurrent_location().getRoomID() == foo.getRoomID()){
						player.setScore(0);
						player.setCurrent_location(foo);
						break;
					}
				}
				op = new Operation(player);
				break;
			}
			//new game
			else if (command.compareToIgnoreCase("new")==0){

				System.out.println("Please Input a Map File to Play: ");
				
				command = din.readLine();
				System.out.println();
				
				String filename;
				if(command.length() < 3){
					filename = "worldMap.dat";
				}
				else{
					filename = command;
				}
					
				java.io.File f = new java.io.File(filename);
				try{
		        	worldMap.load(f);
				}catch(Exception e){
					System.out.println("Map Loading Failure!");
					return;
				}
				
				//TODO create Player
				worldMap.setCurrentRoom(worldMap.getRooms().firstElement());
				
				player = worldMap.getPlayer();
				player.setCurrent_location(worldMap.getRooms().firstElement());
				player.setOwner(worldMap);
				op = new Operation(player);
				break;
			}else if (command.compareToIgnoreCase("quit")==0){
				System.out.println ("Okay. Bye!");
				System.exit(0);
			}else{
				System.out.println ("Huh? Invalid Command - Try again!");
				System.out.println ();
			}
		}
		
		validCommand = true;
		player.currentLocation().showInfo(player);
		//player.getOwner().showNPC();//show NPC
		//player.showNPC();//SHOW NPC
		
		
		for (;;)
		{
			
			System.out.println();

			System.out.println("Please Type Command: ");
			
			try
			{
				command = din.readLine();
				
				// Print a new line
				System.out.println();
			}
			catch (IOException e)
			{
				System.out.println ("Huh? Command Failure!");
				System.out.println ();
			}
			
			// Check to see if user wants to quit
			if (command.compareToIgnoreCase( "quit" ) == 0)
			{
				System.out.println ("Okay. Bye!");
				System.exit(0);
			}
			
			validCommand = parseCommand(command);
			if(validCommand){
				op.setAct(act);
				op.setObj(null);
				op.getPlayer().isEscape = false;
				op.setObjName(objName);
				act.respond(op);
				
				//error handler
				if(!op.isValid){
					if(op.isOver)
						return;
					System.out.println("Huh? " + op.err);
					System.out.println ();
		        	op.isValid = true;
		        	op.err = null;
				}
			}
			// If no valid commands, warn the user is invalid
			else
			{
				if(err == null || err.length() < 1)
					System.out.println ("Huh? Invalid Command!");
				else
					System.out.println ("Huh? " + err);
				System.out.println ();
			}
		}
	}

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		Game g=new Game();
		g.play();
	}

	public Action getAct() {
		return act;
	}

	public void setAct(Action act) {
		this.act = act;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}
}
