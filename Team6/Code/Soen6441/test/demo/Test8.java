package demo;

import java.util.Vector;
import java.awt.Point;

//import map.Conditions.ItemNeedCarried;
//import map.Conditions.SpecificMagicWord;
//import map.Conditions.SpecificProbality;
import map.Conditions.*;
import map.Model.Character;
import map.Model.Connection;
import map.Model.Map;
import map.Model.Player;
import map.Model.Room;
import map.Model.Item;
import map.Model.Item.Property;

import org.junit.Test;
import junit.framework.TestCase;

public class Test8 extends TestCase{
	
	Map worldMap = new Map();


	
	@Test
	public void testSave() {
		Vector<Room> rooms = new Vector<Room>();
		Vector<Connection> cons = new Vector<Connection>();
		Room r1 = new Room();
		r1.setName("Sidewalk");
		r1.setDescription("You are standing on the sidewalk in front of a house to the west.");
		r1.setPos(new Point(282, 199));
		//Character ch=new Character("snake","a big snake",r1,100,0.6,5,true,true);
		//Character ch2=new Character("snake2","a big nice snake",r1,100,0.6,0,false,false);
		//Character ch3=new Character("boy","a big bad boy",r1,100,0.6,5,true,true);
		
		
		Room r2 = new Room();
		r2.setName("Front Porch");
		r2.setDescription("This is the front porch of the house. There are two doors leading inside. The door on the left leads west and the door on the right leads northwest.");
		r2.setPos(new Point(135, 281));
		//Character ch4=new Character("wolf","a big wolf",r2);
		//Character ch4=new Character("wolf","a big wolf",r1,100,0.0,0,false,false);
		//ch4.setMessage("welcome room2, I am a wolf");
		//ch4.setAttackableFlag(false);
		//ch4.setCurrent_location(r2);
		//Character ch5=new Character("nice wolf","a big nice wolf",r1,100,0.0,0,false,false);
		//Character cha1=new Character("snake","a big snake",r2,100,0.6,5,true,false);
		//cha1.setOnce(true);
		//cha1.setFollow(false);
		//cha1.setAttackMessage("I only bite you once! and i will not follow you");
		//cha1.setCurrent_location(r2);
		
		//Character cha2=new Character("dog","a big dog",r2,100,0.6,5,true,true);
		//cha2.setFollow(true);
		//cha2.setOnce(false);
		//cha2.setAttackMessage("I can bite you more than once and i will follow you");
		//cha2.setCurrent_location(r2);
	
		
		Room r3 = new Room();
		r3.setName("Den");
		r3.setDescription("You are in the den of the house. the living room is west of here and the front porch is to the southeast.");
		r3.setPos(new Point(241, 367));
		//Character ch6=new Character("tiger","a big tiger",r1,100,0.6,10,true,true);
		//Character ch7=new Character("nice tiger","a big tiger",r1,100,0.6,10,false,true);
		
		
		Room r4 = new Room();
		r4.setName("Living Room");
		r4.setDescription("This is the living room of the house. The den is to the east.");
		r4.setPos(new Point(495, 285));
		
		
		Room r5 = new Room();
		r5.setName("Foyer");
		r5.setDescription("You are standing in the foyer of the house. It seems as though you can go up a staircase, northwest, or back out the front door to the east.");
		r5.setPos(new Point(400, 427));
		
		
		Room r6 = new Room();
		r6.setName("Hallway");
		r6.setDescription("You are in the hallway on the first floor of the house. The foyer is southeast and the kitchen is west of here..");
		r6.setPos(new Point(447, 98));
		
		
		Room r7 = new Room();
		r7.setName("Kitchen");
		r7.setDescription("YThis is the kitchen of the house. A hallway can be seen to the east and an open doorway to the west leads out to the backyard.");
		r7.setPos(new Point(573, 181));
		
		
		Room r8 = new Room();
		r8.setName("Backyard");
		r8.setDescription("This is the backyard behind the house. There is a pond here.");
		r8.setPos(new Point(202, 83));
		
		
		Room r9 = new Room();
		r9.setName("Upper Hallway");
		r9.setDescription("This is the second floor hallway. Rooms can be seen north and south and a staircase leads down.");
		r9.setPos(new Point(315, 34));
		
		
		Room r10 = new Room();
		r10.setName("North Bedroom");
		r10.setDescription("This is a bedroom on the north side of the house.");
		r10.setPos(new Point(704, 53));

		
		Room r11 = new Room();
		r11.setName("South Bedroom");
		r11.setDescription("This is a bedroom on the south side of the house..");
		r11.setPos(new Point(709, 165));
		
		// DEMO -----------------------------------------------
		Connection e1 = new Connection(Connection.Direction.WEST, r2);
		e1.setStart(r1.getPos());
		e1.setEnd(r2.getPos());
		//e1.addCondition(new SpecificProbality(0.0));
		//e1.addCondition(new SpecificProbality(1.0));
		//e1.addCondition(new SpecificProbality(0.5));
		e1.addCondition(new SpecificMagicWord("go"));//add magic word
//		Item mylamp=new Item();
//		mylamp.addProp(Property.TAKEABLE, true);
//		mylamp.setName("mylamp");
		//e1.addCondition(new ItemNeedCarried(mylamp));
		Item itemDoor=new Item();
		itemDoor.setName("westDoor");
		itemDoor.addProp(Property.LOCKABLE, false);
		Item itemKey=new Item("Room2Key"); //add specific item itemKey
		//itemDoor.setName("door");
		//itemDoor.addProp(Item.Property.LOCKABLE, (Boolean)false);
		itemDoor.setKeyNeed(itemKey);
		//itemDoor.addCondition(new ItemNeedCarried(itemKey));
		e1.setDoor(itemDoor);//add door
		r1.addConnection(e1);
		
		Connection go_up = new Connection(Connection.Direction.UP, r2);
		go_up.setStart(r1.getPos());
		go_up.setEnd(r2.getPos());
		r1.addConnection(go_up);
		
		
		Connection go_up1 = new Connection(Connection.Direction.UP, r3);
		go_up.setStart(r2.getPos());
		go_up.setEnd(r3.getPos());
		r2.addConnection(go_up1);
		
		
		Connection go_down = new Connection(Connection.Direction.DOWN, r2);
		go_down.setStart(r3.getPos());
		go_down.setEnd(r2.getPos());
		r3.addConnection(go_down);
		//r5.addConnection(go_down);

		Connection go_down1 = new Connection(Connection.Direction.DOWN, r1);
		go_down1.setStart(r2.getPos());
		go_down1.setEnd(r1.getPos());
		go_down1.addCondition(new RoomVisited(r3));
		r2.addConnection(go_down1);

		Item item1 = new Item("Light", "A device serving as a source of illumination.", r1);
		item1.addProp(Property.TAKEABLE, true);
		Item item2 = new Item("Mailbox", "There is an open mailbox here.", r1);
		r1.addItem(item2);
		Item item3 = new Item("Letter", "The letter is a simple page of notebook paper.", r1);
		item3.addProp(Item.Property.TAKEABLE, (Boolean)false);
		r1.addItem(item3);
		//item1.addCondition(new SpecificProbality(0.5));
		r1.addItem(item1);
		//item1.addCondition(new SpecificProbality(0.0));
		//item.addCondition(new SpecificProbality(1.0));
		Item mylamp=new Item();
		mylamp.addProp(Property.TAKEABLE, true);
		mylamp.setName("mylamp");
//		mylamp.addCondition(new SpecificMagicWord("magic"));//add magic word
		r1.addItem(mylamp);
		//item1.addCondition(new ItemNeedCarried(mylamp));
		item1.addCondition(new ItemNotNeedCarried(mylamp));
		
		// DEMO -----------------------------------------------
		
		Connection e2 = new Connection(Connection.Direction.WEST, r5);
		e2.setStart(r2.getPos());
		e2.setEnd(r5.getPos());
		
		Item Door2=new Item();
		Item Key2=new Item("Key2");
		Door2.addProp(Property.LOCKABLE, true);
		Door2.addCondition(new ItemNeedCarried(Key2));//add specific item key2
		Door2.setKeyNeed(Key2);
		Door2.setName("door1");
		//e2.setDoor(Door2); //add door
//		e2.addCondition(new SpecificProbality(0.8));//add probality
		e2.addCondition(new RoomVisited(r8));//add probality
		r2.addConnection(e2);
		Connection e3 = new Connection(Connection.Direction.NORTHWEST, r3);
		e3.setStart(r2.getPos());
		e3.setEnd(r3.getPos());
		e3.addCondition(new SpecificProbality(0.9));//add probality
		Item Door3=new Item();
		Item Key3=new Item("Key3");
	
		r2.addConnection(e3);

		item1 = new Item("Light", "A device serving as a source of illumination.", r2);
		r2.addItem(item1);
		
		Connection e4 = new Connection(Connection.Direction.WEST, r4);
		e4.setStart(r3.getPos());
		e4.setEnd(r4.getPos());
		r3.addConnection(e4);
		Connection e5 = new Connection(Connection.Direction.SOUTHEAST, r2);
		e5.setStart(r3.getPos());
		e5.setEnd(r2.getPos());
		r3.addConnection(e5);
		
		item1 = new Item("Light", "A device serving as a source of illumination.", r3);
		r3.addItem(item1);
		item2 = new Item("Rock", "It's smooth and flat, perfect for skipping in a pond.", r3);
		item2.addProp(Item.Property.TAKEABLE, (Boolean) true);
		r3.addItem(item2);
		
		Connection e6 = new Connection(Connection.Direction.EAST, r3);
		e6.setStart(r4.getPos());
		e6.setEnd(r3.getPos());
		r4.addConnection(e6);
		
		item1 = new Item("Light", "A device serving as a source of illumination.", r4);
		r4.addItem(item1);
		
		Connection e7 = new Connection(Connection.Direction.EAST, r2);
		e7.setStart(r5.getPos());
		e7.setEnd(r2.getPos());
		r5.addConnection(e7);
		Connection e8 = new Connection(Connection.Direction.NORTHWEST, r6);
		e8.setStart(r5.getPos());
		e8.setEnd(r6.getPos());
		r5.addConnection(e8);
		Connection e9 = new Connection(Connection.Direction.UP, r9);
		e9.setStart(r5.getPos());
		e9.setEnd(r9.getPos());
		r5.addConnection(e9);

		item1 = new Item("Light", "A device serving as a source of illumination.", r5);
		r5.addItem(item1);
		
		Connection e10 = new Connection(Connection.Direction.SOUTHEAST, r5);
		e10.setStart(r6.getPos());
		e10.setEnd(r5.getPos());
		r6.addConnection(e10);
		Connection e11 = new Connection(Connection.Direction.WEST, r7);
		e11.setStart(r6.getPos());
		e11.setEnd(r7.getPos());
		r6.addConnection(e11);
		
		item1 = new Item("Light", "A device serving as a source of illumination.", r6);
		r6.addItem(item1);

		Connection e12 = new Connection(Connection.Direction.EAST, r6);
		e12.setStart(r7.getPos());
		e12.setEnd(r6.getPos());
		r7.addConnection(e12);
		Connection e13 = new Connection(Connection.Direction.WEST, r8);
		e13.setStart(r7.getPos());
		e13.setEnd(r8.getPos());
		r7.addConnection(e13);
		
		item1 = new Item("Light", "A device serving as a source of illumination.", r7);
		r7.addItem(item1);
		
		Connection e14 = new Connection(Connection.Direction.EAST, r7);
		e14.setStart(r8.getPos());
		e14.setEnd(r7.getPos());
		r8.addConnection(e14);
		
		item1 = new Item("Light", "A device serving as a source of illumination.", r8);
		r8.addItem(item1);
		item2 = new Item("Pond", "It's a small pond, but wide enough to skip rocks.", r8);
		r8.addItem(item2);

		Connection e15 = new Connection(Connection.Direction.NORTH, r10);
		e15.setStart(r9.getPos());
		e15.setEnd(r10.getPos());
		r9.addConnection(e15);
		Connection e16 = new Connection(Connection.Direction.SOUTH, r11);
		e16.setStart(r9.getPos());
		e16.setEnd(r11.getPos());
		r9.addConnection(e16);
		Connection e17 = new Connection(Connection.Direction.DOWN, r5);
		e17.setStart(r9.getPos());
		e17.setEnd(r5.getPos());
		r9.addConnection(e17);

		item1 = new Item("Light", "A device serving as a source of illumination.", r9);
		r9.addItem(item1);
		
		Connection e18 = new Connection(Connection.Direction.SOUTH, r9);
		e18.setStart(r10.getPos());
		e18.setEnd(r9.getPos());
		r10.addConnection(e18);
		
		item1 = new Item("Light", "A device serving as a source of illumination.", r10);
		r10.addItem(item1);
		item2 = new Item("Key", "Something that you are looking for.", r10);
		r10.addItem(item2);
		
		Connection e19 = new Connection(Connection.Direction.NORTH, r9);
		e19.setStart(r11.getPos());
		e19.setEnd(r9.getPos());
		r11.addConnection(e19);
		
		item1 = new Item("Light", "A device serving as a source of illumination.", r11);
		r11.addItem(item1);

		rooms.add(r1);
		rooms.add(r2);
		rooms.add(r3);
		rooms.add(r4);
		rooms.add(r5);
		rooms.add(r6);
		rooms.add(r7);
		rooms.add(r8);
		rooms.add(r9);
		rooms.add(r10);
		rooms.add(r11);

		cons.add(e1);
		cons.add(e2);
		cons.add(e3);
		cons.add(e4);
		cons.add(e5);
		cons.add(e6);
		cons.add(e7);
		cons.add(e8);
		cons.add(e9);
		cons.add(e10);
		cons.add(e11);
		cons.add(e12);
		cons.add(e13);
		cons.add(e14);
		cons.add(e15);
		cons.add(e16);
		cons.add(e17);
		cons.add(e18);
		cons.add(e19);
		//cons.add(go_up);
		
		Map m = new Map(rooms);
		//m.showMapInfo();
		
		Player player = new Player();
		player.setCurrent_location(m.getRooms().firstElement());
		//player.getInventory().add(itemKey);
		//player.setDamage(5);
		player.setHealth(50);
		
		Item apple=new Item();//add apple
		apple.setName("apple");
        apple.addProp(Property.EATABLE, 10);//add apple
        apple.addProp(Property.TAKEABLE, true);//add apple
        
        Item beer=new Item();
        beer.setName("beer");
        beer.addProp(Property.DRINKABLE, -15);//add beer
        beer.addProp(Property.TAKEABLE, true);//add beer
        
        player.getInventory().add(apple);//add to player
        player.getInventory().add(beer);//add to player
        
        Item gun=new Item();
        gun.setName("GUN");
        gun.addProp(Property.TAKEABLE, true);
        gun.addProp(Property.FIRABLE, 20);
        
        Item laptop=new Item();
        laptop.setName("Laptop");
        laptop.addProp(Property.TAKEABLE, true);
        laptop.addProp(Property.THROWABLE, 10);
        
        player.getInventory().add(gun);
        player.getInventory().add(laptop);
        player.getInventory().add(Key2);
        player.setDamage(5);
        //player.getInventory().add(mylamp);

		//Item door = con.getDoor();
		//System.out.println(door.getPropValue(Item.Property.LOCKABLE));

		
		//m.addNpc(ch);
//		m.addNpc(ch4);
//		m.addNpc(cha2);
//		m.addNpc(cha1);
		//m.addNpc(ch3);
		
		m.setPlayer(player);
		player.setOwner(m);
		
		java.io.File f = new java.io.File("m1.dat");
		try{
			m.save(f);
		}catch(Exception e){
			System.err.println(e.getStackTrace());
		}
	}

	@Test
	public void testLoadMap() {
		String filename = "m1.dat";
		java.io.File f = new java.io.File(filename);
		try{
			worldMap.load(f);
			worldMap.showMapInfo();
		}catch(Exception e){
			System.err.println(e.getStackTrace());
		}
	}
}
