package map.Model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;
import java.util.Iterator;
import java.awt.Point;

import map.Controller.Action.Operation.Action;

/**
 * Responsible for class Map.
 * Represent class Map as Graph data structure, and the representation is an adjacency list.
 * @author Yan Wang
 * @version 1.0
 */

public class Map implements Serializable{

	private Vector<Room> rooms;				//list of Rooms
	//private Vector<Connection> connections;	//list of Connections
	private Room currentRoom;
	
	private Player player;
	private Vector<Character> npcs;
	private Vector<Item> items;
	
	public final HashMap<Action, String> verbMsg = new HashMap<Action, String>();
	/********************************************************************************
	* Default Constructor
	*/
	public Map(){
		rooms = new Vector<Room>();
		currentRoom = null;
		npcs = new Vector<Character>();
		player = null;
	}
	
	/********************************************************************************
	* Constructor using fields
	* @param srcRooms
	* 		a list of Room Objects
	* @param srcConnections
	* 	 	a list of Connection Objects
	*/
	public Map( Vector<Room> srcRooms){
		
		if(srcRooms != null){
			rooms = new Vector<Room>(srcRooms);
		}

		// The default location of a player isn't known
		currentRoom=null;
		npcs = new Vector<Character>();
	}

	
	/********************************************************************************
	* Copy Constructor
	* @param srcMap
	* 		an Object of Map
	*/
	public Map( Map srcMap ){
		
		deepCopy(srcMap);
		
	}
	
	private void deepCopy(Map srcMap){
		
		if(srcMap == null){
			rooms = new Vector<Room>();
			// The default location of a player isn't known
			currentRoom=null;
			
			npcs = new Vector<Character>();
			player = new Player();
			return;
		}
		
		if(srcMap.rooms != null)
			rooms = new Vector<Room>(srcMap.rooms);
		else
			rooms = new Vector<Room>();
		
		if(srcMap.npcs != null)
			npcs = new Vector<Character>(srcMap.npcs);
		else
			npcs = new Vector<Character>();
		
		if(srcMap.player != null)
			player = new Player(srcMap.player);
		else
			player = new Player();
		// The default location of a player isn't known
		currentRoom = srcMap.currentRoom;
	}
	
	/********************************************************************************
	* Check whether a room LocationNumber is exist
	* @param roomID
	* @return true:  in room list
	* @return false: otherwise
	*/
	public boolean isInRooms( int roomID ){
		int idx = findIdx(roomID);
		if(idx < 0)
			return false;
		return true;
	}

	/********************************************************************************
	* Add a new Room to the Map
	* @param r
	* 		an Object of Room
	* @return true:  success
	* @return false: failure
	*/
	public boolean addRoom( Room r ){
		
		if( isInRooms(r.getRoomID()))
			return false;		//room exists
		return rooms.add(r);
	}
	
	/********************************************************************************
	* Find index from the room list
	* @param roomID
	* 		an ID number of a Room
	* @return >=0: index in Vector
	* @return <0:  room not exist
	*/
	public int findIdx( int roomID ){
		
		Iterator<Room> iter = rooms.iterator();
		for(int i=0; iter.hasNext(); i++)
			if( (iter.next()).getRoomID() == roomID )
				return i;
		return -1;
	}

	/********************************************************************************
	* Remove a Room from the Map
	* @param roomID 
	* 		an ID number of a Room
	* @return true:  success
	* @return false: room not exist
	*/
	public boolean removeRoom( int roomID ){
		
		int idx = findIdx(roomID);
		if(idx >= 0){
			Room tmp = rooms.remove(idx);
			if(tmp != null)
				return true;
		}
		return false;
	}

	/********************************************************************************
	* Remove a Room from the Map
	* @param r
	* 		an Object of Room
	* @return true:  success
	* @return false: room not exist
	*/
	public boolean removeRoom( Room r ){
		
		if(r == null)
			return false;
		int roomID = r.getRoomID();
		return removeRoom(roomID);
	}
	

	/********************************************************************************
	* Show map information
	*/
	public void showMapInfo(){
		
		System.out.println("Map Information: ");
		
		Iterator<Room> itRoom = rooms.iterator();
		while( itRoom.hasNext() ){
			Room foo = itRoom.next();
			System.out.print("Room: ");
			System.out.print(foo.getRoomID());
			System.out.print("\n");
			Iterator<Connection> itCon = foo.getConnection().iterator();
			while( itCon.hasNext() ){
				System.out.print("\tConnection: ");
				Connection con = itCon.next();
				System.out.print("\t");
				System.out.print(con.getDirection());
				System.out.print("\t");
				System.out.print(con.getConnectsTo().getRoomID());
				System.out.print("\n");
			}
		}
	}
	/**
	 * return number of the rooms in a Map
	 */
	public int sizeRooms(){
		return rooms.size();
	}
	
	/**
	 * save to a file
	 * @param f
	 * @see java.io.File
	 * @exception Exception
	 */
	public void save(java.io.File f) throws Exception{
		
		// Create a file to write game system
		FileOutputStream out = new FileOutputStream(f);

		// Create an object output stream, linked to out
		ObjectOutputStream objectOut = new ObjectOutputStream (out);

		// Write game system to object store
		objectOut.writeObject (this);

		// Close object output stream
		objectOut.close();
	}
	
	/**
	 * load from a file
	 * @param f
	 * @see java.io.File
	 * @throws Exception
	 */
	public void load(java.io.File f) throws Exception{
		
		// Create a file input stream
		FileInputStream fin = new FileInputStream(f);

		// Create an object input stream
		ObjectInputStream objectIn = new ObjectInputStream(fin);

		// Read an object in from object store, and cast it to a GameWorld
		Map foo = (Map) objectIn.readObject();
		
		deepCopy(foo);
		
		// Close object input stream
		objectIn.close();
	}
	
	/**
	 * get closed room near a point
	 * @param p
	 * 		@see java.awt.Point
	 * @return Room
	 */
	public Room getClosedRoom(Point p){
		
		for(Room foo : rooms){
			Point position = foo.getPos();
			if(Math.abs(position.x - p.x) < Room.RADIUS
				&& Math.abs(position.y - p.y)< Room.RADIUS)
				return foo; 
		}
		return null;
	}
	
	/**
	 * return room list
	 * @return rooms
	 */
	public Vector<Room> getRooms() {
		return rooms;
	}

	/**return current Room
	 * @return currentRoom
	 * */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	/**set current Room
	 * */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}
	/**
	 * show room information
	 * */
	public void showRoom(){
		
		System.out.println(String.valueOf(currentRoom.getRoomID()));
		System.out.println(currentRoom.getName());
		System.out.println(currentRoom.getDescription());
		System.out.println();
		System.out.println("Availble connection is...");
		
		for(Enumeration e=currentRoom.getConnection().elements();e.hasMoreElements();){
			Connection con=(Connection)e.nextElement();
			
			System.out.println(con.getDirection().name());
		}
	}

	public void addNpc(Character c){
		if(npcs == null){
			npcs = new Vector<Character>();
		}
		npcs.add(c);
	}
	
	public void delNpc(Character c){
		npcs.remove(c);
	}

	public void delNpcs(Room room){
		if(npcs == null || npcs.size() <= 0)
			return;
		Vector<Character> vnpc = new Vector<Character>(npcs);
		for(Character foo : vnpc){
			if(foo.getLocation().getRoomID() == room.getRoomID())
				npcs.remove(foo);
		}
	}

	public Vector<Character> getNpcs(Room location) {
		Vector<Character> vnpc = new Vector<Character>();
		if(npcs == null)
			npcs = new Vector<Character>();
		for(Character foo : npcs){
			if(foo.getLocation().getRoomID() == location.getRoomID())
				vnpc.add(foo);
		}
		return vnpc;
	}

	public Vector<Character> getNpcs() {
		return npcs;
	}

	public void setNpcs(Vector<Character> npcs) {
		this.npcs = npcs;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the items
	 */
	public Vector<Item> getItems() {
		if(items == null)
			items = new Vector<Item>();
		else
			items.clear();
		if(player != null && player.getInventory() != null){
			items.addAll(player.getInventory());
		}
		if(rooms != null){
			for(Room foo : rooms){
				items.addAll(foo.getItems());
			}
		}
		return items;
	}
	public void showNPC(){
		
		for(Character foo : npcs){
			System.out.println("\t" + foo.getName());
		}
		
	}
}

