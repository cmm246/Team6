package Build2;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;

import javax.swing.UIManager;

import junit.framework.TestCase;

import map.GUI.Dialog.*;
import map.Model.Connection;
import map.Model.Item;
import map.Model.Room;
import org.junit.Test;

public class TestRoomInformation extends TestCase {

	@Test
	public void testDriver(){
		try{
	        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	        javax.swing.JFrame frame = new javax.swing.JFrame();
			frame.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent evt) {
	                System.exit(0);
	            }
	        });
			Room r1 = new Room();
			r1.setName("ROOM1");
			r1.setDescription("ROOM1");
			Room foo;
			Connection con;
			Vector<Room> rooms = new Vector<Room>();
			for(int i=2; i < 5; i++){
				foo = new Room();
				foo.setName("ROOM" + String.valueOf(i));
				foo.setDescription("ROOM" + String.valueOf(i));
				rooms.add(foo);
				con = new Connection(Connection.Direction.NORTH, foo);
				r1.addConnection(con);
			}
			Item item;
			item = new Item("Item1", "Item1", r1);
			item.addProp(Item.Property.TAKEABLE, (Boolean)true);
			r1.addItem(item);
			item = new Item("Item2", "Item2", r1);
			r1.addItem(item);
			RoomInformation dlg = new RoomInformation(rooms, r1, true);
			dlg.initialize();
		    dlg.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
