package mapManagement;

import java.util.Vector;
import map.Model.*;

import org.junit.Test;
import junit.framework.TestCase;

public class TestMap extends TestCase{

	@Test
	public void testMap() {
		new Map();
	}

	@Test
	public void testMapVectorOfRoomVectorOfConnection() {
		
		Room r1 = new Room();
		Room r2 = new Room();

		Connection e1 = new Connection(Connection.Direction.DOWN, r2);
		
		r1.addConnection(e1);
		
		Vector<Room> rooms = new Vector<Room>();
		Vector<Connection> cons = new Vector<Connection>();
		rooms.add(r1);
		rooms.add(r2);
		cons.add(e1);
		
		new Map(rooms);
	}

	@Test
	public void testMapMap() {
		
		Map m1 = new Map();
		
		new Map(m1);
	}

	@Test
	public void testIsInRooms() {
		
		int ID = 1;
		//case 1:
		{
			Map m = new Map();
			System.out.print("Room" + String.valueOf(ID));
			assertFalse("exist:", m.isInRooms(ID));
		}
		
		//case 2:
		{
			Vector<Room> r = new Vector<Room>();
			Room room = new Room();
			r.add(room);
			Map m = new Map(r);
			assertTrue("exist:", m.isInRooms(room.getRoomID()));
		}
	}

	@Test
	public void testAddRoom() {

		Map m = new Map();
		Room r = new Room();
		m.addRoom(r);
	}

	@Test
	public void testFindIdx() {
		
		int ID = 1;
		//case 1:
		{
			Room r = new Room();
			Map m = new Map();
			m.addRoom(r);
			assertEquals(m.findIdx(ID), -1);
		}

		//case 2:
		{
			Room r = new Room();
			Map m = new Map();
			m.addRoom(r);
			int idx = m.findIdx(r.getRoomID());
			assertEquals(idx, 0);
		}
}

	@Test
	public void testRemoveRoomInt() {

		int roomID = 2;
		Room r = new Room();
		Map m = new Map();
		m.addRoom(r);
		
		//case 1:
		m.removeRoom(roomID);
		
		//case 2:
		roomID++;
		m.removeRoom(roomID);
		
		//case 3:
		m.removeRoom(null);
	}

	@Test
	public void testRemoveRoomRoom() {
		Room r = new Room();
		Map m = new Map();
		m.addRoom(r);
		
		//case 1:
		m.removeRoom(r);
		
		//case 2:
		Room foo = new Room();
		m.removeRoom(foo);
	}


	@Test
	public void testShowMapInfo() {
		
		int roomID = 1;
		Room r1 = new Room();
		roomID++;
		Room r2 = new Room();

		Connection e1 = new Connection(Connection.Direction.DOWN, r2);
		
		r1.addConnection(e1);
		
		Vector<Room> rooms = new Vector<Room>();
		Vector<Connection> cons = new Vector<Connection>();
		rooms.add(r1);
		rooms.add(r2);
		cons.add(e1);
		
		Map m = new Map(rooms);
		
		m.showMapInfo();
	}
	
}
