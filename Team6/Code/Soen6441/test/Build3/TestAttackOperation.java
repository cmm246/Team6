package Build3;


import junit.framework.TestCase;
import map.Controller.Action.Operation;
import map.Controller.Action.Operation.Action;
import map.Model.Map;
import map.Model.Player;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestAttackOperation extends TestCase{

	static Map worldMap;
	static Player player;
	static map.Model.Character npc;
	static Operation op;
	
	@Before
	public void setUp() throws Exception {
    	//load map
		String filename = "worldMap.dat";
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
		
		//String name = "snake";
		String name = "wolf";
		for(map.Model.Character foo : worldMap.getNpcs()){
			if(foo.getName().compareTo(name) == 0){
				npc = foo;
				break;
			}
		}
		
		op = new Operation(player);
		Action a = Action.ATTACK;
		op.setAct(a);
		op.setObjName(null);
		op.setNpc(npc);

	}
	
	@Test
	public void testAttack(){
		op.getAct().respond(op);
		if(!op.isValid)
			System.out.println(op.err);
	}

}
