package Build3;

import map.Conditions.RoomVisited;
import map.Model.Player;
import map.Model.Room;
import junit.framework.Assert;
import junit.framework.TestCase;

public class RoomVisitedTest extends TestCase {

	public void testcheckCondition(){
		Room r=new Room();
		r.setRoomID(1);
		r.setName("room");
		Player player=new Player();
		player.setCurrent_location(r);
		RoomVisited roomv=new RoomVisited(r);
		Assert.assertEquals(true,roomv.checkCondition(player) );
	}

}
