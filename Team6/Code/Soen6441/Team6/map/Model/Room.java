package map.Model;
import java.awt.Point;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;

import map.Conditions.Conditions;
import map.Conditions.ItemNeedCarried;
import map.Conditions.ItemNotNeedCarried;
import map.Conditions.RoomVisited;
import map.Conditions.SpecificMagicWord;
import map.Conditions.SpecificProbality;
import map.Model.Item.Property;
/**
 * This class defines the rooms that can be connected by connections and be added in a map in Game Engine.
 * This class uses composite pattern to describe the relationships between each rooms in a map.
 * 
 * @author Pei Yu
 * @version 1.31
 * 
 */
public class Room implements java.io.Serializable
{
	// Member variables
	public static int RADIUS = 15;
	private static int count = 1;
	private Point pos;
	private String name;
	
	private int RoomID;                     // Room Identifier
	private String Description;             // Room Description
	private Vector<Connection> connections; // List of Connections of the room

	private Vector<Item> Items = new Vector<Item>();    
	// List of Item list
	
	Character npc;
	/**
	 *  Blank constructor for a room
	 */
	public Room()
	{
		setRoomID(count++);
		setName(" ");
		setDescription(new String());
		connections = new Vector<Connection>();
		Items=new Vector<Item>();
	}

	/**
	 *  Partial constructor for a room
	 * @param n the identifier of the room
	 */
	public Room(String n)
	{
		setRoomID(count++);
		setName(n);
		setDescription(new String());
		connections = new Vector<Connection>();
		Items=new Vector<Item>();
	}
	/**
	 * partial constructor for a room
	 * 
	 * @param n  room name
	 * @param des room description
	 * */
	public Room (String n,String des){
		setRoomID(count++);
		setName(n);
		setDescription(des);
		connections = new Vector<Connection>();
		Items=new Vector<Item>();
	}
    /**
     *  Full constructor for a room
     *
     * @param n   room name
     * @param RDescription room description
     * @param connections the connections of the room
     * @param items  items in a room
     */
	public Room(String n,String RDescription, Vector<Connection> connections,Vector<Item> items) {
		super();
		setRoomID(count++);
		setName(n);
		setDescription(RDescription);
		this.connections = connections;
		this.Items=items;
	}
	/**
	 *  Partial constructor for a room 
	 * @param src
	 */
	public Room(Room src){
		if(src == null){
			setRoomID(0);
			setDescription(null);
			connections=null;
			Items = null;
		}
		else{
			setRoomID(src.RoomID);
			this.setName(src.name);
			setDescription(src.Description);
			if(src.getPos() != null)
				pos = new Point(src.getPos());
			if(src.getConnection() != null)
				connections = new Vector<Connection>(src.getConnection());
			if(src.getItems() != null)
				Items = new Vector<Item>(src.getItems());
		}
	}

	/**
	 * Adds a connection to this room
	 * @param connect the connection of the room
	 */
	public void addConnection ( Connection connect )
	{
		connections.addElement(connect);
	}

	/**
	 *  Deletes a connection from this room
	 * @param connect the connection of the room
	 */
	public void deleteConnection ( Connection connect )
	{
		if (connections.contains(connect))
            connections.removeElement(connect);    		
	}
	/**
	 * Returns a vector of connection
	 * @return a list of connections of the room
	 */
	
	public Vector<Connection> getConnection(){
		//Vector<Connection> clone = (Vector<Connection>)connections.clone();
		//Return a clone, since we don't want an external object to modify our original vector
		return connections;
		
	}
    /**
     * Assigns room description
     * @param description the description of the room
     */
	public void setDescription(String description) {
		Description = description;
	}
    /**
     * Returns room description
     * @return the description of the room
     */
	public String getDescription() {
		return Description;
	}
    /**
     * Assigns roomID
     * @param roomID the room identifier
     */
	public void setRoomID(int roomID) {
		RoomID = roomID;
	}
    /**
     * Return roomID
     * @return the room identifier
     */
	public int getRoomID() {
		return RoomID;
	}
	/**
     * Return Room Position
     * @return pos the room position
     */
	public Point getPos() {
		return pos;
	}
	/**
     * Set Room Position
     */
	public void setPos(Point pos) {
		this.pos = pos;
	}

	/**add items
	 * */
	public void addItem(Item item){
		Items.addElement(item);
	}
	/**delete items
	 * */
	public void deleteItem(Item item){
		if(Items.contains(item)){
			Items.remove(item);
		}
	}
	/**get items
	 * @return vector items
	 * */
	public Vector<Item> getItems(){
		//Vector<Item> vclone=(Vector<Item>)Items.clone();
		return Items;
	}
	/**get room name
	 * */
	public String getName() {
		return name;
	}
    /**set room name
     * @param name String
     * */
	public void setName(String name) {
		this.name = name;
	}
    /**set connections vector
     * @param connections Vector<Connection>
     * */
	public void setConnections(Vector<Connection> connections) {
		this.connections = new Vector<Connection>(connections);
	}
    /**set Item vector
     * @param items Vector<Item>
     * */
	public void setItems(Vector<Item> items) {
		Items = items;
	}
	
	/**show items in a room
	 * */
	public void showItems(){
		for(Enumeration e=Items.elements();e.hasMoreElements();){
			Item it=(Item)e.nextElement();
			System.out.println(it.getName());
			System.out.println(it.getDescription());
			
		}
		
	}
	
	
	/*public void showInfo(){
		
		System.out.println("Name: " + this.name + "\nDescription: " + this.Description);
		System.out.println();
		System.out.println("Valid Connections:");
		for(Connection foo : connections){
			System.out.println("\t" + foo.getDirection().toString());
		}
		System.out.println("");
		System.out.println("Items:");
		for(Item foo : Items){
			System.out.println("\t" + foo.getName());
		}
	}*/
    /**show room information
     * @param player Player Class
     * */
	public void showInfo(Player player){
		
		Vector<map.Model.Character> vnpc =
			player.getOwner().getNpcs(player.currentLocation());
		
		Vector<map.Model.Character> chList = player.getVnpc();
		
		System.out.println("Name: " + this.name + "\nDescription: " + this.Description);
		System.out.println();
		System.out.println("Connections:");
		for(Connection foo : connections){
			System.out.println("\t" + foo.getDirection().toString());
			Item door = foo.getDoor();
			if(door != null){
				boolean lock = (Boolean)door.getPropValue(Property.LOCKABLE);
				if(lock){
					System.out.println("\t\tWhose " + door.getName() + " is locked!"
							+ " Need " + door.getKeyNeed().getName() + " to unlock!");
				}else{
					System.out.println("\t\tWhose " + door.getName() + " is unlocked!"
							+ " Need " + door.getKeyNeed().getName() + " to lock!");
				}
			}
			for(Conditions obj : foo.getVcon()){
		    	if(obj instanceof ItemNeedCarried){
		    		ItemNeedCarried con = (ItemNeedCarried)obj;
		    		System.out.println("\t\t" + con.getItem().getName() + " need be carried!");
		    	}else if(obj instanceof ItemNotNeedCarried){
		    		ItemNotNeedCarried con = (ItemNotNeedCarried)obj;
		    		System.out.println("\t\t" + con.getItem().getName() + " need not be carried!");
		    	}else if(obj instanceof RoomVisited){
		    		RoomVisited con = (RoomVisited)obj;
		    		System.out.println("\t\t" + con.getRoom().getName() + " need be visited!");
		    	}else if(obj instanceof SpecificMagicWord){
		    		System.out.println("\t\t" + "Need input a magic word!");
		    	}else if(obj instanceof SpecificProbality){
		    		SpecificProbality con = (SpecificProbality)obj;
		    		System.out.println("\t\t" + "Probability = " + (int)(con.getProbality()*100) + "%");
		    	}
			}
		}
		
		if(Items != null && Items.size() > 0){
			System.out.println();
			System.out.println("Items:");
			for(Item foo : Items){
				System.out.println("\t" + foo.getName());
				for(java.util.Map.Entry<Property, Object> prop : foo.getProps().entrySet()){
					Property key = prop.getKey();
					Object value = prop.getValue();
					if(key.compareTo(Property.DRINKABLE) == 0){
						System.out.println("\t\t" + key.name() + "\tcure = " + (Integer)value);
					}else if(key.compareTo(Property.EATABLE) == 0){
						System.out.println("\t\t" + key.name() + "\t\tcure = " + (Integer)value);
					}else if(key.compareTo(Property.FIRABLE) == 0){
						System.out.println("\t\t" + key.name() + "\t\tdamage = " + (Integer)value);
					}else if(key.compareTo(Property.THROWABLE) == 0){
						System.out.println("\t\t" + key.name() + "\tdamage = " + (Integer)value);
					}else if(key.compareTo(Property.TAKEABLE) == 0){
						System.out.println("\t\t" + key.name());
						for(Conditions obj : foo.getVcon()){
					    	if(obj instanceof ItemNeedCarried){
					    		ItemNeedCarried con = (ItemNeedCarried)obj;
					    		System.out.println("\t\t" + con.getItem().getName() + " need be carried!");
					    	}else if(obj instanceof ItemNotNeedCarried){
					    		ItemNotNeedCarried con = (ItemNotNeedCarried)obj;
					    		System.out.println("\t\t" + con.getItem().getName() + " need not be carried!");
					    	}else if(obj instanceof RoomVisited){
					    		RoomVisited con = (RoomVisited)obj;
					    		System.out.println("\t\t" + con.getRoom().getName() + " need be visited!");
					    	}else if(obj instanceof SpecificMagicWord){
					    		System.out.println("\t\t" + "Need input a magic word!");
					    	}else if(obj instanceof SpecificProbality){
					    		SpecificProbality con = (SpecificProbality)obj;
					    		System.out.println("\t\t" + "Probability = " + (int)(con.getProbality()*100) + "%");
					    	}
						}
					}else if(key.compareTo(Property.LOCKABLE) == 0){
						boolean v = (Boolean)value;
						if(v){
							System.out.println("\t\t" + key.name() +
									"\t" + foo.getName() + " is locked, " + 
									"\tneed " + foo.getKeyNeed().getName() + " to unlock!");
						}else{
							System.out.println("\t\t" + key.name() +
									"\t" + foo.getName() + " is unlocked, " + 
									"\tneed " + foo.getKeyNeed().getName() + " to lock!");
						}
					}
				}
			}
		}

		if(vnpc != null && vnpc.size() > 0){
			System.out.println();
			System.out.println("Characters:");
			for(Character c : vnpc)
			{
				System.out.println("\t" + c.getName());
				if(c.isAttackable()){
					System.out.println("\t\t" + "damage = " + c.getDamage());
					System.out.println("\t\t" + "accuracy = " + (int)(c.getAttackProbability()*100) + "%");

					if(c.isOnce()){
						System.out.println("\t\t" + "Attack Once!");
					}else{
						System.out.println("\t\t" + "Attack Till Die!");
					}
					
					if(c.isFollow()){
						System.out.println("\t\t" + "Follow Player!");
					}else{
						System.out.println("\t\t" + "Not Follow Player!");
					}
				}else{
					System.out.println("\t\t" + "Messager!");
				}
			}
		}
		
		if(chList != null && chList.size() > 0){
			System.out.println();
			System.out.println("Characters following:");
			for(Character c : chList)
			{
				System.out.println("\t" + c.getName());
				if(c.isAttackable()){
					System.out.println("\t\t" + "damage = " + c.getDamage());
					System.out.println("\t\t" + "accuracy = " + (int)(c.getAttackProbability()*100) + "%");
				}
				
				if(c.isOnce()){
					System.out.println("\t\t" + "Attack Once!");
				}else{
					System.out.println("\t\t" + "Attack Till Die!");
				}
				
				if(c.isFollow()){
					System.out.println("\t\t" + "Follow Player");
				}else{
					System.out.println("\t\t" + "Not Follow Player");
				}
			}//end for
		}//end if
		System.out.println();
	}
};