package map.Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import map.Model.Connection;
import map.Model.Map;
import map.Model.Room;
import map.Model.Connection.Direction;

/**
 * Frame class, methods are loadMap, saveMap
 * @author  Hongxian Gao
 * @version 1.5
 */
public class Frame {
	
	private Map map;
	
	void createMap(){		
		map = new Map();
		
		Room room1 = new Room();
		Room room2 = new Room();
		Room room3 = new Room();
		Room room4 = new Room();
		Room room5 = new Room();
		Room room6 = new Room();
		Room room7 = new Room();

		Connection connection1 = new Connection ( Connection.Direction.UP, room2 );
		Connection connection2 = new Connection ( Connection.Direction.DOWN, room1 );
		Connection connection3 = new Connection ( Connection.Direction.NORTH, room3 );
		Connection connection4 = new Connection ( Connection.Direction.SOUTH, room2 );
		Connection connection5 = new Connection ( Connection.Direction.NORTH, room4 );
		Connection connection6 = new Connection ( Connection.Direction.SOUTH, room3 );
		Connection connection7 = new Connection ( Connection.Direction.EAST, room5 );
		Connection connection8 = new Connection ( Connection.Direction.WEST, room2 );
		Connection connection9 = new Connection ( Connection.Direction.EAST, room6 );
		Connection connection10 = new Connection ( Connection.Direction.WEST, room5 );
		Connection connection11 = new Connection ( Connection.Direction.UP, room7 );
		Connection connection12 = new Connection ( Connection.Direction.DOWN, room6 );
		
		room1.addConnection ( connection1 );
		room2.addConnection ( connection2 );
		room2.addConnection ( connection3 );
		room2.addConnection ( connection7 );
		room3.addConnection ( connection4 );
		room3.addConnection ( connection5 );
		room4.addConnection ( connection6 );
		room5.addConnection ( connection8 );
		room5.addConnection ( connection9 );
		room6.addConnection ( connection10 );
		room6.addConnection ( connection11 );
		room7.addConnection ( connection12 );

		/**
		 *  Add locations/Connections to our game lists
		*/
		map.addRoom(room1);
		map.addRoom(room2);
		map.addRoom(room3);
		map.addRoom(room4);
		
	}
	/**
	 * Load a Map
	 * @param filename
	 * @throws Exception
	 */
	public void loadMap(String filename) throws Exception{
		
		/**
		 *  Create a file input stream
		 */		
		FileInputStream fin = new FileInputStream(filename);

		/**
		 * Create an object input stream
		 */		
		ObjectInputStream objectIn = new ObjectInputStream(fin);

		/**
		 * Read an object in from object store, and cast it to a GameWorld
		 */		
		map = (Map) objectIn.readObject();
		
		/**
		 * Close object input stream
		 */		
		objectIn.close();
	}
	/**
	 * Save a Map
	 * @param filename
	 * @throws Exception
	 */
	public void saveMap(String filename) throws Exception{
		/**
		 * Create a file to write game system
		 */		
		FileOutputStream out = new FileOutputStream (filename);
        
		/**
		 * Create an object output stream, linked to out
		 */		
		ObjectOutputStream objectOut = new ObjectOutputStream (out);

		/**
		 * Write game system to object store
		 */		
		objectOut.writeObject (map);

		/**
		 * Close object output stream
		 */		
		objectOut.close();

	}
	
	public static void main(String[] args) {
		Frame f = new Frame();
		f.createMap();
		
		try{
			f.saveMap("map1.dat");
			System.out.println("save ok!");
		}catch(Exception e)
		{
			return;
		}
		
		try{
			f.loadMap("map1.dat");
			System.out.println("load ok!");
		}catch(Exception e)
		{
			return;
		}
	}
}
