/**
 * Test class Room
 * @author Pei Yu
 * @version 1.0
 */

package mapManagement;

import map.Model.Connection;
import map.Model.Room;

import org.junit.Assert;

import junit.framework.TestCase;

public class RoomTest extends TestCase {

	/**
	 * Test addConnection function of the room class.
	 */

	public void testaddConnection(){
		
		Room r= new Room();
		Connection c=new Connection();
		r.addConnection(c);
		Assert.assertEquals(1, r.getConnection().size());
	}
	
	/**
	 * Test deleteConnection function of the room class
	 */
	public void testdeleteConnecttion(){
		Connection c=new Connection();
		Room r=new Room();
		r.addConnection(c);
		r.deleteConnection(c);
		Assert.assertEquals(0, r.getConnection().size());
	}
	
	/**
	 * Test getConnection function of the room class
	 */
	public void testgetConnection(){
		Connection c1=new Connection();
		Connection c2=new Connection();
		Connection c3=new Connection();
		Room r=new Room();
		r.addConnection(c1);
		r.addConnection(c2);
		r.addConnection(c3);
		Assert.assertEquals(3, r.getConnection().size());
	}
}
