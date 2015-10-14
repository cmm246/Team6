package map.Controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.util.EventListener;
import java.util.EventObject;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.Shape;


import java.io.File;
import map.GUI.*;
import map.GUI.Dialog.ConnectionDialog;
import map.GUI.Dialog.ItemDialog;
import map.GUI.Dialog.NPCDialog;
import map.GUI.Dialog.PlayerDialog;
import map.Controller.PopMenu.BUTTONMODE;
import map.Model.*;

/********************************************************************************
 * Responsible for class MapPanel
 * MapPanel servers as a Panel for a GUI Frame.
 * @author Yan Wang
 * @version 1.0
 ********************************************************************************/
public class MapPanel extends JPanel{
	
	static final long serialVersionUID=8762419586713147L;
	MapFrame owner;	
	Map worldMap;	

	JComboBox directions;
	JButton selectedButton;
	String message = "None";
	
	boolean justSaved = false;
	
//	boolean stateDrag = false;
//	Room  dragedRoom; 
	
	boolean startPoint = true;		//whether the start point is selected for a connection
	Room startRoom;					//for add a connection(start point of the connection)
	Connection createCon;			//current connection creating
	
	/**
	 * Constructor
	 * @param mf
	 * 		@see MapFrame
	 * @param map
	 * 		@see Map
	 */
	public MapPanel(MapFrame mf, Map map){
		
		owner = mf;
		addMouseEvents();
		if(map != null)
			worldMap = new Map(map);
	}
	
    /**
     *  Add Mouse Events listeners for the Panel  
     */
	private void addMouseEvents() {
		
		addMouseListener(new MouseAdapter() {
			
	        public void mouseReleased(MouseEvent e) {

	        	if(e.getModifiers()==MouseEvent.BUTTON1_MASK) {
		        	String command=selectedButton.getActionCommand();
		        	if(command.equals
		        		(
		        			PopMenu.buttonMap.get(PopMenu.BUTTONMODE.ADD_ROOM.ordinal()))
		        		)
		        	{
		        		addRoom(e.getPoint());
		        	}
		        	else if(command.equals
		        		(
		        			PopMenu.buttonMap.get(PopMenu.BUTTONMODE.ADD_CONNECTION.ordinal()))
		        		)
		        	{
		        		addConnection(e.getPoint());
		        	}
//		        	if(command.equals
//			        		(
//			        			PopMenu.buttonMap.get(PopMenu.BUTTONMODE.ADD_PLAYER.ordinal()))
//			        		)
//			        	{
//			        		editPlayer();
//			        	}
		        	justSaved = false;
		    		owner.validate();
					owner.repaint();
					
				} else if(e.getModifiers()==MouseEvent.BUTTON3_MASK) {
					startPoint = true;
		    		owner.validate();
					owner.repaint();
				}
	        }
	        
	        public void mouseClicked(MouseEvent e) {
	        	
	        	if(e.getModifiers()==MouseEvent.BUTTON1_MASK) {
		        	String command=selectedButton.getActionCommand();
			        if(command.equals
			        	(
			        		PopMenu.buttonMap.get(PopMenu.BUTTONMODE.REMOVE.ordinal()))
			        	)
			        {
		        		removeObject(e.getPoint());
		        		justSaved=false;
			    		owner.validate();
						owner.repaint();
		        	}
		        	else if(command.equals
				       	(
				        		PopMenu.buttonMap.get(PopMenu.BUTTONMODE.NONE.ordinal()))
				    	)
		        	{
		        		getInfo(e.getPoint());
		        	}
		        	else if(command.equals
			        		(
			        			PopMenu.buttonMap.get(PopMenu.BUTTONMODE.ADD_ITEM.ordinal()))
			        		)
		        	{
		        		addItem(e.getPoint());
		        		justSaved=false;
			    		owner.validate();
						owner.repaint();
		        	}
		        	else if(command.equals
			        		(
			        			PopMenu.buttonMap.get(PopMenu.BUTTONMODE.EDIT_ROOM.ordinal()))
			        		)
			        {
			        	editRoom(e.getPoint());
		        		justSaved=false;
			    		owner.validate();
						owner.repaint();
			        }
		        	else if(command.equals
			        		(
			        			PopMenu.buttonMap.get(PopMenu.BUTTONMODE.ADD_PLAYER.ordinal()))
			        		)
		        	{
		        		//TODO add player
		        		if(worldMap.getPlayer() == null){
			        		addPlayer(e.getPoint());
		        		}else{
			        		editPlayer();
		        		}
		        		
		        		justSaved=false;
			    		owner.validate();
						owner.repaint();
		        	}else if(command.equals("Edit Player")){
		        		editPlayer();
		        		justSaved=false;
			    		owner.validate();
						owner.repaint();
		        	}
		        	else if(command.equals
			        		(
			        			PopMenu.buttonMap.get(PopMenu.BUTTONMODE.ADD_CHARACTER.ordinal()))
			        		)
		        	{
		        		//TODO add Charater
		        		addCharacter(e.getPoint());
		        		justSaved=false;
			    		owner.validate();
						owner.repaint();
		        	}
	        	}
	        }
	    });
        
	}
	
	/**
	 * edit Player
	 */
	protected void editPlayer() {
		// TODO Auto-generated method stub
		Player player = new Player();
		if(worldMap.getPlayer() != null)
			player = new Player(worldMap.getPlayer());
		else
			player = new Player();
		if(player == null){
			return;
		}

		PlayerDialog dialog = new PlayerDialog(player);
		dialog.setEnabled(true);
		dialog.setRooms(worldMap.getRooms());
		dialog.setItems(player.getInventory());
		dialog.initComponents();
		dialog.setVisible(true);
		if(dialog.getReturnStatus() == PlayerDialog.RET_OK){
			player = dialog.getPlayer();
			worldMap.setPlayer(player);
			player.setOwner(worldMap);
			//change button name to Edit
			updateButtonStatus();
		}
	}

	/**
	 * add Character
	 * @param point
	 */
	protected void addCharacter(Point point) {
		// TODO Auto-generated method stub
		if(getClosedRoom(point) == null)
			return;
		Room room = new Room(getClosedRoom(point));
		if(room == null)
			return;
		
		map.Model.Character character = new map.Model.Character("name", "desc", room);

		NPCDialog dialog = new NPCDialog(character);
		dialog.setRooms(worldMap.getRooms());
		dialog.initComponents();
		dialog.setVisible(true);
		if(dialog.getReturnStatus() == NPCInfoDialog.RET_OK){
			//character = dialog.getCharacter();
			worldMap.addNpc(character);
		}
	}
	
	/**
	 *add Player 
	 * @param point
	 */
	protected void addPlayer(Point point) {
		// TODO Auto-generated method stub
		if(getClosedRoom(point) == null)
			return;
		Room room = new Room(getClosedRoom(point));
		if(room == null)
			return;
		
		Player player;
		if(worldMap.getPlayer() == null){
			player = new Player();
		}
		else
			player = worldMap.getPlayer();
		player.setCurrent_location(room);
		PlayerDialog dialog = new PlayerDialog(player);
		dialog.setEnabled(true);
		dialog.setRooms(worldMap.getRooms());
		dialog.setItems(player.getInventory());
		dialog.initComponents();
		dialog.setVisible(true);
		if(dialog.getReturnStatus() == PlayerDialog.RET_OK){
			player = dialog.getPlayer();
			worldMap.setPlayer(player);

			//change button name to Edit
			updateButtonStatus();
		}
		
	}

	/**
	 * Add a Item
	 * @param p
	 * 		position of the room containing a Item
	 */
	private void addItem(Point p){
		if(getClosedRoom(p) == null)
			return;
		Room room = new Room(getClosedRoom(p));
		if(room == null)
			return;
		Item item = new Item("Name", "Description", room);
		
//		
//		ItemInfoDialog dlg = new ItemInfoDialog(item, true);
//		dlg.setVisible(true);
//
//		if(dlg.getReturnStatus() == ItemInfoDialog.RET_OK){
//			room.addItem(dlg.item);
//			int idx = worldMap.findIdx(room.getRoomID());
//			worldMap.getRooms().setElementAt(room, idx);
////			worldMap.removeRoom(room.getRoomID());
////			worldMap.addRoom(room);
//		}

    	ItemDialog dialog = new ItemDialog();
    	dialog.setItem(item);
    	dialog.setItems(worldMap.getItems());
    	dialog.setRooms(worldMap.getRooms());
    	dialog.setEditable(true);
    	
    	dialog.initComponents();
    	dialog.setVisible(true);
    	if(dialog.isRetValue()){
    		room.addItem(dialog.getItem());
			int idx = worldMap.findIdx(room.getRoomID());
			worldMap.getRooms().setElementAt(room, idx);
    	}

    	fireObjectChangeEvent(new ObjectChangeEvent(this));
		justSaved=false;
	}

	/**
	 * Edit a room information
	 * @param p
	 * 		position of the room
	 */
	private void editRoom(Point p){
		Room room = getClosedRoom(p);
		if(room != null){
			//RoomInfoDialog dlg = new RoomInfoDialog(worldMap, room, true);
			map.GUI.Dialog.RoomInformation dlg
				= new map.GUI.Dialog.RoomInformation(worldMap.getRooms(), room, true);
			dlg.setChList(worldMap.getNpcs(room));
			dlg.setItemList(worldMap.getItems());

			dlg.initialize();
			dlg.setVisible(true);
			if(dlg.isOk){
				room = new Room(dlg.getRoom());
				int idx = worldMap.findIdx(room.getRoomID());
				worldMap.getRooms().setElementAt(room, idx);
				worldMap.delNpcs(room);
				worldMap.getNpcs().addAll(dlg.getChList());
				if(worldMap.getPlayer() != null && room.getRoomID() == 
					worldMap.getPlayer().getCurrent_location().getRoomID()){
					worldMap.getPlayer().path.clear();
					worldMap.getPlayer().setScore(0);
					worldMap.getPlayer().setCurrent_location(room);
				}
			}
		}
	}

	/**
	 * get a room information
	 * @param p
	 * 		position of the room
	 */
	private void getInfo(Point p){
		Room r = getClosedRoom(p);
		if(r != null){
//			RoomInfoDialog dlg = new RoomInfoDialog(worldMap, r, false);
			map.GUI.Dialog.RoomInformation dlg
				= new map.GUI.Dialog.RoomInformation(worldMap.getRooms(), r, false);
			dlg.setChList(worldMap.getNpcs(r));
			
			dlg.initialize();
			dlg.setVisible(true);
		}
	}
	
	/**
	 * remove a room and related connections (from and to)
	 * @param p
	 * 		postion of the room
	 */
	private void removeObject(Point p){

		fireObjectChangeEvent(new ObjectChangeEvent(this));

		Room r = getClosedRoom(p);
		if(r != null){
			
			Room room = new Room(r);
			//delete its connection
			if(room.getConnection() != null){
				Vector<Connection> vcon = new Vector<Connection>(); 
				for(Connection foo : vcon){
					room.deleteConnection(foo);
				}
			}
			
			//delete connections connected to it
			for(Room foo : worldMap.getRooms()){
				if(foo.getConnection() != null){
					Vector<Connection> vcon = new Vector<Connection>(foo.getConnection());
					for(Connection con : vcon){
						if(con.getConnectsTo() != null 
								&& con.getConnectsTo().getRoomID() == room.getRoomID())
						{
							foo.deleteConnection(con);
						}
					}
				}
			}
			worldMap.removeRoom(room.getRoomID());
		}
	}
	
	/**
	 *  add a connection for a Map
	 *  @param p
	 *  	position of the connection
	 */
	private void addConnection(Point p) {
		if(startPoint){
			
			if( addConStart(p) )
				startPoint = false;
		}
		else{
			if( addConEnd(p) )
			startPoint = true;
		}
		fireObjectChangeEvent(new ObjectChangeEvent(this));
	}

	/**
	 * draw a Map
	 * @param gs
	 * 		@see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	public void paint(Graphics gs) {
		
		Graphics2D g=(Graphics2D)gs;
		
		g.setColor(Color.green.darker().darker());
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		paintRooms(g); 

		//   paint connection
//		if(startPoint)
			paintCons(g);
		
		int w=getWidth()-g.getFontMetrics().stringWidth(message)-10;
		int h=getHeight()-g.getFontMetrics().getHeight();
		
		g.setColor(Color.black);
		g.drawString(message, w, h);
	}
	
	/**
	 * Draw all rooms for a map
	 * @param g
	 * 		@see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	private void paintRooms(Graphics g) {
		
		if(worldMap == null)
			return;
		Graphics2D g2d = (Graphics2D)g;
		for (Room foo : worldMap.getRooms()) {
        	Point p = foo.getPos();
        	int r = Room.RADIUS;
        	
        	if(foo.getItems().size() > 0)
        		g2d.setColor(Color.red);//???
        	else
        		g2d.setColor(Color.white);//???
        	
        	g2d.fillOval(p.x-r, p.y-r, 2*r, 2*r);
        	String text = foo.getName();
        	if(text == null){
        		text = "";
        	}
        	g2d.drawString(text, p.x-(g2d.getFontMetrics().stringWidth(text)/2),
        			p.y-r-(g2d.getFontMetrics().getAscent()/2));
		}
	}

	/**
	 * add start point for a connection
	 * @param p
	 * 		position of the start point
	 */
	public boolean addConStart(Point p) {
		if(getClosedRoom(p) == null)
			return false;
		startRoom = new Room(getClosedRoom(p));
		if(startRoom != null) {
			createCon = new Connection();
			createCon.setStart(startRoom.getPos());
			return true;
		}else
			return false;
		}
	
		Connection.Direction getDirection(){
		return null;
	}

	/**
	 * add end point for a connection
	 * @param p
	 * 		postion of the end point
	 */
	public boolean addConEnd(Point p) {
		Room end = getClosedRoom(p);
		if(startRoom != null && end != null) {

//handle Direction
//			java.util.Vector<String> dir = new java.util.Vector<String>();
//			for(Connection.Direction d : Connection.Direction.values()){
//				if(startRoom.getConnection() != null
//						&& startRoom.getConnection().size() > 0)
//				{
//					boolean match = false;
//					for(Connection c : startRoom.getConnection()){
//						if(d.compareTo(c.getDirection()) == 0){
//							match = true;
//							break;
//						}
//					}
//					if(!match)
//						dir.add(d.toString());
//				}
//				else{
//					dir.add(d.toString());
//				}
//			}

//			JComboBox combox = new JComboBox(dir);
//			DirectionCombox dlg = new DirectionCombox(owner, "Direction:", combox);
//			dlg.setVisible(true);
//			String foo = dlg.getValue();
//			if(foo == null)
//				return false;
//			Connection.Direction direction = Connection.Direction.valueOf(foo);
//			if(direction == null)
//				return false;
//			
//			//handle map data
//			//worldMap.removeRoom(startRoom.getRoomID());
//			createCon.setDirection(direction);
//			
			createCon.setConnectsTo(end);
			createCon.setEnd(end.getPos());

			ConnectionDialog dialog = new ConnectionDialog();
			dialog.setCon(createCon);
			dialog.setItems(worldMap.getItems());
			dialog.setRooms(worldMap.getRooms());
			dialog.setEditable(true);
			
			dialog.initComponents();
			dialog.setVisible(true);
			
			if(!dialog.isRetValue()){
				return false;
			}
			
			createCon = dialog.getCon();

			startRoom.addConnection(createCon);
			//worldMap.addRoom(startRoom);
			int idx = worldMap.findIdx(startRoom.getRoomID());
			worldMap.getRooms().setElementAt(startRoom, idx);
			
			justSaved=false;
			return true;
		}else
			return false;
	} 

	/**
	 * Draw all connections for a map
	 * @param gs
	 * 		@see javax.swing.JComponent#paint(java.awt.Graphics)
	 */
	private void paintCons(Graphics gs) {
		if(worldMap == null)
			return;
		Graphics2D g = (Graphics2D)gs;
		for(Room room : worldMap.getRooms()){
			for(Connection foo : room.getConnection()) {
				
				Point start = foo.getStart();
				Point end = foo.getEnd();

				g.setColor(Color.blue.brighter());//???

	            AffineTransform at = AffineTransform.getTranslateInstance(start.x, start.y);
	            double len = (Math.sqrt(((double)end.y-start.y)*(end.y-start.y) + (end.x-start.x)*(end.x-start.x)));
	            int barb = 10;
	            double angle = Math.toRadians(20);
	            Path2D.Double path = new Path2D.Double();
	            path.moveTo(0, 0);
	            path.lineTo(len, 0);
	            double x = len - barb*Math.cos(angle);
	            double y = barb*Math.sin(angle);
	            path.lineTo(x,  y);

	            path.moveTo(len, 0);
	            x = len - barb*Math.cos(-angle);
	            y = barb*Math.sin(-angle);
	            path.lineTo(x, y);
	            
	            at.rotate(end.x - start.x, end.y - start.y);
	            Shape shape = at.createTransformedShape(path);
	            g.draw(shape);

				int mid_x = start.x + (end.x - start.x) / 2;
				int mid_y = start.y + (end.y - start.y) / 2;
	        	g.drawString(foo.getDirection().toString(), mid_x, mid_y);

			} 
			
		}
	} 

	/**
	 * Get the closed room to the position
	 * @param p
	 * 		position to choose 
	 */
	private Room getClosedRoom(Point p) {
		
		if(worldMap == null || worldMap.sizeRooms() <= 0)
			return null;
		
		return worldMap.getClosedRoom(p);
	} 

	/**
	 * add a room to the Map
	 * @param p
	 * 		position of the adding room
	 */
	public void addRoom(Point p) {
		
		Room foo = new Room();
		foo.setPos( resetOutBoundPoint(p, Room.RADIUS) );
		foo.setName("Room" + foo.getRoomID());
		
		RoomInfoDialog dlg = new RoomInfoDialog(worldMap, foo, true);
		dlg.setVisible(true);

		if(dlg.isOk)
			worldMap.addRoom(foo);


		fireObjectChangeEvent(new ObjectChangeEvent(this));
		justSaved=false;
	}

    /**
     * reset out bound point
     * @param point
     * 		position to choose
     * @param radius
     * 		radius of a room painting
     * @return Point
     * 		reset position
     */
	private Point resetOutBoundPoint(Point point, int radius) {

		int x = point.x;
		int y = point.y;
		
		if(x < radius )
			x = radius;
		else if( x + radius > getWidth() )
			x = getWidth() - radius ;

		if(y < radius)
			y = radius;
		else if( y + radius > getHeight())
			y = getHeight() - radius;
		
		return new Point(x, y);
	}
    
	/**
	 * Set up each button status and position on the map
	 *
	 */
	public void setupPanel() {
        
		worldMap = new Map();

//		directions.addItemListener(this);
		
		this.addObjectChangeEventListener
		(
			new ObjectChangeEventListener()
			{
				public void myEventOccurred(ObjectChangeEvent evt)
				{
					updateButtonStatus();
				}
			}
		);
        
	}

	/**
	 *  Create the listener list
	 */
    protected javax.swing.event.EventListenerList listenerList =
        new javax.swing.event.EventListenerList();
	
    // This methods allows classes to register for MyEvents
    public void addObjectChangeEventListener(ObjectChangeEventListener listener) {
        listenerList.add(ObjectChangeEventListener.class, listener);
    }

    /**
     * This methods allows classes to unregister for MyEvents
     * @param listener
     * 		@see ObjectChangeEventListener 
     */
    public void removeObjectChangeEventListener(ObjectChangeEventListener listener) {
        listenerList.remove(ObjectChangeEventListener.class, listener);
    }

    /**
     *  This private class is used to fire MyEvents
     * @param evt
     * 		@see ObjectChangeEvent
     */
    void fireObjectChangeEvent(ObjectChangeEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i=0; i<listeners.length; i+=2) {
            if (listeners[i]==ObjectChangeEventListener.class) {
                ((ObjectChangeEventListener)listeners[i+1]).myEventOccurred(evt);
            }
        }
    }

    /**
     * set the selected button
     * @param b
     * 		button selected
     */
    public void setButton(JButton b) {
		selectedButton = b;
		message = selectedButton.getActionCommand();
//		System.out.println(message);
		
		//end paint connection
		startPoint = true;
		owner.validate();
		owner.repaint();
	}


    /**
     * The preparations for the future builds.
     */
    public class ObjectChangeEvent extends EventObject {
    	static final long serialVersionUID=8762419586713147L;
        public ObjectChangeEvent(Object source) {
            super(source);
        }
    }
    
    /**
     * The preparations for the future builds.
     */
    public interface ObjectChangeEventListener extends EventListener {
    	public void myEventOccurred(ObjectChangeEvent evt);
    }
    
    /**
     * Set the enable/disable status for each button
     */
	void updateButtonStatus() {
    	
		if(worldMap != null) {    		
    		PopMenu.menuMapSave.setEnabled(true);
    		
    		if(worldMap.sizeRooms() > 0){
    			PopMenu.buttons[BUTTONMODE.REMOVE.ordinal()].setEnabled(true);
    			PopMenu.buttons[BUTTONMODE.ADD_CONNECTION.ordinal()].setEnabled(true);
        		PopMenu.buttons[BUTTONMODE.NONE.ordinal()].setEnabled(true);
    			PopMenu.buttons[BUTTONMODE.ADD_ITEM.ordinal()].setEnabled(true);
    			PopMenu.buttons[BUTTONMODE.EDIT_ROOM.ordinal()].setEnabled(true);
    			PopMenu.buttons[BUTTONMODE.ADD_PLAYER.ordinal()].setEnabled(true);
    			PopMenu.buttons[BUTTONMODE.ADD_CHARACTER.ordinal()].setEnabled(true);
    		}
    		else{
    			PopMenu.buttons[BUTTONMODE.REMOVE.ordinal()].setEnabled(false);
    			PopMenu.buttons[BUTTONMODE.ADD_CONNECTION.ordinal()].setEnabled(false);
        		PopMenu.buttons[BUTTONMODE.NONE.ordinal()].setEnabled(false);
    			PopMenu.buttons[BUTTONMODE.ADD_ITEM.ordinal()].setEnabled(false);
    			PopMenu.buttons[BUTTONMODE.EDIT_ROOM.ordinal()].setEnabled(false);
    			PopMenu.buttons[BUTTONMODE.ADD_PLAYER.ordinal()].setEnabled(false);
    			PopMenu.buttons[BUTTONMODE.ADD_CHARACTER.ordinal()].setEnabled(false);
    		}
    		if(worldMap.getPlayer() != null){
    			PopMenu.buttons[BUTTONMODE.ADD_PLAYER.ordinal()].setText("Edit Player");
    		}

    		PopMenu.buttons[BUTTONMODE.ADD_ROOM.ordinal()].setEnabled(true);
	    }
		else {
			PopMenu.menuMapSave.setEnabled(false);
			PopMenu.buttons[BUTTONMODE.NONE.ordinal()].setEnabled(false);
			PopMenu.buttons[BUTTONMODE.ADD_ROOM.ordinal()].setEnabled(false);
			PopMenu.buttons[BUTTONMODE.ADD_CONNECTION.ordinal()].setEnabled(false);
			PopMenu.buttons[BUTTONMODE.REMOVE.ordinal()].setEnabled(false);
			PopMenu.buttons[BUTTONMODE.ADD_ITEM.ordinal()].setEnabled(false);
			PopMenu.buttons[BUTTONMODE.EDIT_ROOM.ordinal()].setEnabled(false);
			PopMenu.buttons[BUTTONMODE.ADD_PLAYER.ordinal()].setEnabled(false);
			PopMenu.buttons[BUTTONMODE.ADD_CHARACTER.ordinal()].setEnabled(false);
	    }
    }

	/**
	 * return Map
	 * @return map
	 */
	public Map getWorldMap() {
		return worldMap;
	}
	
	/**
	 * save a map to a file
	 * @param f
	 * 		@see java.io.File
	 */
	public void save(File f) throws Exception{
		if(worldMap != null){
			worldMap.save(f);
			justSaved = true;
		}
	}

	/**
	 * read a map from a file
	 * @param f
	 * 		@see java.io.File
	 */
	public void load(File f) throws Exception{
		worldMap = new Map();
		worldMap.load(f);
	}

	/**
	 * confirm whether has just saved or not
	 * @return boolean
	 * 		flag for whether just save or not
	 */
	public boolean isJustSaved() {
		return justSaved;
	}
}
