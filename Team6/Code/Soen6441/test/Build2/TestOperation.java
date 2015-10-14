package Build2;

import junit.framework.TestCase;
import map.Controller.Action.*;
import map.Controller.Action.Operation.Action;
import map.Model.Connection;
import map.Model.Map;
import map.Model.Player;

import org.junit.Test;


public class TestOperation extends TestCase {
	
	@Test
	public void testMove() {
    	//load map
		String filename = "worldMap.dat";
		java.io.File f = new java.io.File(filename);
		Map worldMap = new Map();
		try{
        	worldMap.load(f);
		}catch(Exception e){
			System.err.println(e.getStackTrace());
			return;
		}
		
		//create Player
		Player player = new Player();
		player.setOwner(worldMap);
		Connection con = new Connection(Connection.Direction.UNDEFINED,
				worldMap.getRooms().firstElement());
		player.setCurrent_location(con);

		//perform actions
		Operation op = new Operation(player);
		Action a = Action.EAST;
		op.setAct(a);
		op.setObjName(null);
		
		a.respond(op);
		assertFalse((op.isValid));
		
		a = Action.WEST;
		op.setAct(a);
		op.setObjName(null);
		a.respond(op);
//<<<<<<< TestOperation.java
		assertFalse((op.isValid));
//=======
		assertTrue((op.isValid));
//>>>>>>> 1.6
	}

	@Test
	public void testTake() {
    	//load map
		String filename = "worldMap.dat";
		java.io.File f = new java.io.File(filename);
		Map worldMap = new Map();
		try{
        	worldMap.load(f);
		}catch(Exception e){
			System.err.println(e.getStackTrace());
			return;
		}
		
		//create Player
		Player player = new Player();
		player.setOwner(worldMap);
		Connection con = new Connection(Connection.Direction.UNDEFINED,
				worldMap.getRooms().firstElement());
		player.setCurrent_location(con);

		//perform actions
		Operation op = new Operation(player);
		Action a = Action.TAKE;
		op.setAct(a);
		op.setObjName("light");
		
		a.respond(op);
		assertFalse((op.isValid));
		
		a = Action.TAKE;
		op.setAct(a);
		op.setObjName("letter");
		a.respond(op);
		assertFalse((op.isValid));
	}
}
