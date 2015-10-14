package map.Model;

import java.awt.Point;
import java.util.Vector;
import map.Conditions.*;

/**
 * This class defines the connection to a room in the game engine.
 * 
 * @author Chung Mak
 * @since 1.0.0
 */
public class Connection implements java.io.Serializable {
	/**
	 * Global variable.
	 */
	public static final int radius = 3;

	/**
	 * Start position of connection in GUI.
	 */
	private Point start;

	/**
	 * End position of connection in GUI.
	 */
	private Point end;

	/**
	 * Defines a Direction enumerated type to represent the direction.
	 */
	public enum Direction {
		UNDEFINED, NORTH, SOUTH, EAST, WEST, UP, DOWN, NORTHEAST, NORTHWEST, SOUTHEAST, SOUTHWEST;
	}

	/**
	 * Holds the room connected to by connection.
	 */
	private Room connects_to;

	/**
	 * Holds the direction of the connection.
	 */
	private Direction direction;
	
	//add for build3
	Vector<Conditions> vcon = new Vector<Conditions>();
	
	Item door;

	/**
	 * Default constructor for direction.
	 */
	public Connection() {
		this.direction = Direction.UNDEFINED;
		this.connects_to = null;
	}

	/**
	 * Constructor for connection (NON GUI VERSION)
	 * 
	 * @param direction
	 *            The direction leading to the connected room.
	 * @param connects_to
	 *            The room connected to.
	 */
	public Connection(Direction direction, Room connects_to) {
		this.direction = direction;
		this.connects_to = connects_to;
	}

	/**
	 * Constructor to initialize the initial position of the connection (GUI
	 * VERSION).
	 * 
	 * @param src
	 *            From an existing connection to a room.
	 */
	public Connection(Connection src) {
		direction = src.getDirection();
		connects_to = new Room(src.getConnectsTo());
		if (src.getStart() != null)
			start = new Point(src.getStart());
		if (src.getEnd() != null)
			end = new Point(src.getEnd());
		if (src.vcon != null)
			vcon = new Vector<Conditions>(src.vcon);
		if(src.door != null)
			this.door = new Item(src.door);
	}

	/**
	 * Set the direction.
	 * 
	 * @param direction
	 */
	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	/**
	 * Return the direction of connection.
	 * 
	 * @return direction.
	 */
	public Direction getDirection() {
		return this.direction;
	}

	/**
	 * Set the room connected to.
	 * 
	 * @param connects_to
	 *            Room connected to by connection class.
	 */
	public void setConnectsTo(Room connects_to) {
		this.connects_to = connects_to;
		if(connects_to.getPos() != null)
			this.end = new Point(connects_to.getPos());
	}

	/**
	 * Return the room connected to.
	 * 
	 * @return connection to room.
	 */
	public Room getConnectsTo() {
		return this.connects_to;
	}

	/**
	 * Get the start position of the connection in the GUI.
	 * 
	 * @return start position of connection in GUI.
	 */
	public Point getStart() {
		return start;
	}

	/**
	 * Set the start position of the connection in the GUI
	 * 
	 * @param start
	 *            Start position of connection in GUI.
	 */
	public void setStart(Point start) {
		this.start = start;
	}

	/**
	 * Get the end position of the connection in the GUI.
	 * 
	 * @return end position of connection in GUI.
	 */
	public Point getEnd() {
		return end;
	}

	/**
	 * Set the end position of the connection in the GUI
	 * 
	 * @param end
	 *            End position of connection in GUI.
	 */
	public void setEnd(Point end) {
		this.end = end;
	}

	public Vector<Conditions> getVcon() {
		return vcon;
	}

	public void setVcon(Vector<Conditions> vcon) {
		this.vcon = new Vector<Conditions>(vcon);
	}
	
	public void addCondition(Conditions con){
		if(vcon == null)
			vcon = new Vector<Conditions>();
		vcon.add(con);
	}
	
	public void delCondition(Conditions con){
		vcon.remove(con);
	}
	
	public boolean chkCondition(Player player){
		for(Conditions foo : vcon){
			if(!foo.checkCondition(player))
				return false;
		}
		return true;
	}

	public Item getDoor() {
		return door;
	}

	public void setDoor(Item door) {
		this.door = door;
	}
	
}