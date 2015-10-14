package Build3;


import junit.framework.TestCase;
import map.Controller.Action.Operation;
import map.Controller.Action.Operation.Action;
import map.Model.Map;
import map.Model.Player;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestMagicWordOperation extends TestCase{

	static Map worldMap;
	static Player player;
	static map.Model.Character npc;
	static Operation op;
	static String word;

	@Before
	public void setUp() throws Exception {
    	//load map
		String filename = "m15.dat";
		java.io.File f = new java.io.File(filename);
		worldMap = new Map();
		try{
        	worldMap.load(f);
		}catch(Exception e){
			System.err.println(e.getStackTrace());
			return;
		}
		player = worldMap.getPlayer();
		player.setOwner(worldMap);
		
		String name = "snake";
		for(map.Model.Character foo : worldMap.getNpcs()){
			if(foo.getName().compareTo(name) == 0){
				npc = foo;
				break;
			}
		}

		op = new Operation(player);
	}

	@Test
	public void testItemMagicWord(){
		Action a = Action.PICK_UP;
		op.setAct(a);
		op.setObjName("key1");
		word = "Magic";
		player.setMagicWord(word);
		op.getAct().respond(op);
		if(!op.isValid){
			
			System.out.println(op.getPlayerHandler().err);
		}
	}

	@Test
	public void testConMagicWord(){
		Action a = Action.WEST;
		op.setAct(a);
		op.setObjName(null);
		word = "Room";
		player.setMagicWord(word);
		op.getAct().respond(op);
		if(!op.isValid){
			
			System.out.println(op.getPlayerHandler().err);
		}
	}
}
