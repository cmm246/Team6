package mapManagement;

/**
 * Test class map.Model.Item
 * @author ho_gao
 * @version 1.0
 */
import junit.framework.*;
import map.Model.Item;
import map.Model.Room;

public class TestItem extends TestCase{
	public void testItemException()
	{
		try{
		Room room =new Room("Room2","it's at up-left");
		Item item = new Item("key1", "First key for room 1", room);
		fail("Should raise an exception");			
		}
		catch (Exception success){success.printStackTrace();}
	}

	public void testGetSetItemName()
	{
		Room room =new Room("Room2","it's at up-left");
		Item item = new Item("key1", "First key for room 1", room);
	    item.setName("key01");
		Assert.assertEquals("Item name for testing is key01","key01", item.getName());
	}
	
}
