package mapManagement;

import junit.framework.TestCase;
import map.Model.Connection;
import map.Model.Room;

import org.junit.Test;

/**
 * Connection class JUnit4 test cases.
 */
public class ConnectionTest extends TestCase{

	/**
         * Unit test for accessor to get the direction.
         */
	@Test
	public void testGetDirection() {
		System.out.println("Test 1");
		Room room1 = new Room();
		Connection connection1 = new Connection (Connection.Direction.UP, room1);
		assertTrue(connection1.getDirection() == Connection.Direction.UP);
	}
	
	/**
         * Unit test for modifier to set the direction.
         */ 
	@Test
	public void testSetDirection() {
		System.out.println("Test 2");
		Room room1 = new Room();
		Connection connection1 = new Connection (Connection.Direction.UP, room1);
		connection1.setDirection(Connection.Direction.DOWN);
		assertTrue(connection1.getDirection() == Connection.Direction.DOWN);
	}


	/**
         * Unit test for accessor to get the room connected to.
	 */
	@Test
	public void testGetConnectsTo() {
		System.out.println("Test 3");
		Room room1 = new Room();
		Connection connection1 = new Connection (Connection.Direction.UP, room1);
		assertTrue(room1 == connection1.getConnectsTo());
	}


	/**
	 * Unit test for modifier to set the room connected to.
	 */
	@Test
	public void testSetConnectsTo() {
		System.out.println("Test 4");
		Room room1 = new Room();
		Connection connection1 = new Connection (Connection.Direction.UP, room1);
		Room room2 = new Room();
		connection1.setConnectsTo(room2);
		assertTrue(room2 == connection1.getConnectsTo());
	}
	
}
