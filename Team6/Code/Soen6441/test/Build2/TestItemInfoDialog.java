package Build2;

import junit.framework.*;
import map.Model.*;
import map.GUI.*;

/**
 * Test case for ItemInfoDialog
 * @author ho_gao
 * @version 1.0
 */
public class TestItemInfoDialog extends TestCase{
	public void testItemInfoDialog(){
		Room room1 = new Room("Room 1");
    	Item item1 = new Item("key","for Room1 and player1",room1);
        ItemInfoDialog dialog1 = new ItemInfoDialog(new javax.swing.JFrame(), true, item1);
		
        Room room2 = new Room("Room 2");
    	Item item2 = new Item("key","for Room1 and player1",room2);
        ItemInfoDialog dialog2 = new ItemInfoDialog(new javax.swing.JFrame(), true, item2);
		
        Assert.assertNotSame(dialog1, dialog2);
	}

}
