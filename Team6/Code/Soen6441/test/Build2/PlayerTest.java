package Build2;

import java.util.Enumeration;
import java.util.Vector;

import junit.framework.TestCase;
import map.Model.Connection;
import map.Model.Map;
import map.Model.Room;
import map.Model.Player;
import map.Model.Item;

import org.junit.Test;

/**
 * Player class JUnit4 test cases.
 */
public class PlayerTest extends TestCase{

	/**
     * Unit test for accessor function to get the score.
     */
	@Test
	public void testGetScore() {
		Player player1 = new Player();
		assertTrue("Failure getting the player score.", player1.getScore() >= 0);
	}
	
	/**
	 * Unit test to get the current location of a player.
	 */
	@Test
	public void testGetCurrent_Location() {
		Player player1 = new Player();
		Room room1 = new Room();
		Connection connection1 = new Connection (Connection.Direction.UP, room1);
		player1.setCurrent_location(connection1);
		assertTrue("Failure getting the current player location.", room1 == player1.getCurrent_location());
	}
	
	/**
	 * Unit test to set the current location of player.
	 */
	@Test
	public void testSetCurrent_location() {
		Player player1 = new Player();
		Room room1 = new Room();
		Connection connection1 = new Connection (Connection.Direction.UP, room1);
		player1.setCurrent_location(connection1);
		Room getRoom = player1.getCurrent_location();
		assertTrue("Failure setting the current player location.", room1 == getRoom);
	}
	
	/**
	 * Unit test to take an item from the room.
	 */
	@Test
	public void testTakeItem() {
		Player player1 = new Player();
		Room room1 = new Room();
		Item item1 = new Item("item1", "this is my item", room1);
		Vector<Item> inventory;
		Vector<Item> room_items;
		Boolean conditionFlag = Boolean.FALSE;
		
		player1.setCurrent_location(room1);
		player1.takeItem(item1);
		
		// make sure items is in the inventory
		inventory = player1.getInventory();
		for (Enumeration<Item> inv = inventory.elements(); inv.hasMoreElements();)
		{
			Item it=(Item)inv.nextElement();
			if (it == item1) {
				conditionFlag = Boolean.TRUE;
				break;
			}
		}
		
		// make sure item was removed from the room
		room_items = room1.getItems();
		for (Enumeration<Item> inv = room_items.elements(); inv.hasMoreElements();)
		{
			Item it=(Item)inv.nextElement();
			if (it == item1) {
				conditionFlag = Boolean.FALSE;
				break;
			}
		}
		
		assertTrue("Failure taking item from room.", conditionFlag);
	}
	
	/**
	 * Unit test to drop item in room.
	 */
	@Test 
	public void testDropItem() {
		Player player1 = new Player();
		Room room1 = new Room();
		Item item1 = new Item("item1", "this is my item", room1);
		Vector<Item> room_items;
		Vector<Item> inventory;
		Boolean conditionFlag = Boolean.FALSE;
		
		player1.setCurrent_location(room1);
		player1.takeItem(item1);
		player1.dropItem(item1);
		room_items = room1.getItems();
		for (Enumeration<Item> inv = room_items.elements(); inv.hasMoreElements();)
		{
			Item it=(Item)inv.nextElement();
			if (it == item1) {
				conditionFlag = Boolean.TRUE;
				break;
			}
		}
		
		// make sure items is not in the inventory
		inventory = player1.getInventory();
		for (Enumeration<Item> inv = inventory.elements(); inv.hasMoreElements();)
		{
			Item it=(Item)inv.nextElement();
			if (it == item1) {
				conditionFlag = Boolean.FALSE;
				break;
			}
		}
		
		assertTrue("Failure dropping item in room.", conditionFlag);
	}
}