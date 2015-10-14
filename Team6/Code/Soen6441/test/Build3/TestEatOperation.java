package Build3;

import junit.framework.Assert;
import junit.framework.TestCase;
import map.Controller.Action.Operation;
import map.Controller.Action.Operation.Action;
import map.Model.Map;
import map.Model.Player;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEatOperation extends TestCase{
 
	static Map worldMap;
	static Player player;
	static map.Model.Item item;
	static Operation op;
	static String word;

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
		
		String name = "apple";
		for(map.Model.Item foo : worldMap.getItems()){
			if(foo.getName().compareTo(name) == 0){
				item = foo;
				break;
			}
		}

		op = new Operation(player);
		Action a = Action.EAT;
		op.setAct(a);
		op.setObjName("apple");
		op.setObj(item);
	}

	@Test
	public void testEat() {
		op.getAct().respond(op);
		System.out.println(op.err);
		//System.out.println(player.getHealth());
		Assert.assertEquals(60, player.getHealth());
	}

}
