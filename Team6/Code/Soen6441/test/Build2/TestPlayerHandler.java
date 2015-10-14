package Build2;


import junit.framework.TestCase;

import map.Controller.Action.Operation;
import map.Controller.Action.PlayerHandler;
import map.Controller.Action.Operation.Action;
import map.Model.Connection;
import map.Model.Map;
import map.Model.Player;

import org.junit.After;
import org.junit.Before;

/**PlayerHander test case
 * @author yu_pei
 * @version 1.0
 * */

public class TestPlayerHandler extends TestCase {

	public void testupdate(){
		String filename = "worldMap.dat";
		java.io.File f = new java.io.File(filename);
		Map worldMap = new Map();
		try{
        	worldMap.load(f);
        	worldMap.showMapInfo();
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
		Operation op=new Operation(player);
		Action a = Action.UP;
		op.setAct(a);
		op.setObjName(null);
		PlayerHandler ph=new PlayerHandler();
		boolean b = ph.update(op);
		assertFalse(b);
	}

}
