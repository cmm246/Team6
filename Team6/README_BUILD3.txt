Group Members:

6176526  Pei Yu
9092994	 Hong Xian Gao
6137687	 Yan Wang 
2393336	 Chung Mak

     
Project name is Soen6441, there are four main packages:
1. Model - Game Data Model.
2. GUI - User Interaction for data and option selections.
3. Controller - Manipulation of the Data Model.
4. Conditions - Conditions related to items and movement.


Changes/Additions were made to the following classes for Build 3:

Model Related Additions/Changes: 
Connection.java -- defines the connection to a room in the game engine.
Map.java -- as Graph data structure, and the representation is an adjacency list.
Room.java -- defines the rooms that can be connected by connections and be added in a map in Game Engine. This class uses composite pattern to describe the relationships between each rooms in a map.  
Item.java--Define the item that can be in a player or a room.
Player.java--This class defines the game player used to play the game designed using the game engine.
Character.java--This class defines the non-player character which interacts with the player which the game engine can provide to the game designer.

Controller Related Additions/Changes:
Frame.java -- as the game frame. 
MapPanel.java -- as a Panel for a GUI Frame. This class can be used to create map by game desinger.
PopMenu.java -- as a Menu for a GUI Frame. This class can be used to create map by game desinger.
Operation.java--Operation extends a Observable Class in Observer Pattern.Responsible for trigger the update of Concrete Observer PlayerHandler and ItemHandler. 
ItemHandler.java--Operation implements a Observable Interface in Observer Pattern. Implement take item function drop item function.
PlayerHandler.java--Operation implements a Observable Interface in Observer Pattern. Implement move function, inventory function, score function
CharacterHandler.java--Responsible for update the state of Class Character.

GUI Related Additions/Changes:
DirectionCombox.java--Responsible for class DirectionCombox which servers as Dialog for choosing Direction.
ItemInfoDialog.java -- This class provides graphic user interface.Game developer can use this interface to modify item informations in the selected room.
MapFrame.java--Responsible for class MapFrame.MapFrame servers as a GUI Frame.
RoomCheckBox.java -- RoomCheckBox is used to check whether the room has objects or not.
RoomInfoDialog.java -- This class provides graphic user interface.Game developer can use this interface to modify informations of the selected room or the selected connection.
Game.java--Game class get game player's command and parse these commands.
ConnectionForm.java--ConnectionForm extends JPanel.Responsible for serving as a Connection JTable Panel for RoomInformation Dialog.
ConnectionTableModel--ConnectionTableModel extends AbstractTableModel.Responsible for serving TableModel for ConnectionForm.
ItemForm.java--ItemForm extends JPanel.Responsible for serving as Item JTable Panel for RoomInformation Dialog.
ItemTableModel.java--ItemTableModel extends AbstractTableModel.Responsible for serving TableModel for ItemForm.
JTableButtonModel.java--JTableButtonModel extends AbstractTableModel.Responsible for serving as a JButton TableModel.
JTableButtonMouseListener.java--JTableButtonMouseListener extends MouseListener.Responsible for serving as a MouseListener.
JTableButtonRenderer.java--JTableButtonRenderer extends TableCellRenderer.Responsible for serving as a TableCellRenderer.
RoomInformation.java--RoomInformation extends JDialog.Responsible for show or Edit Room Information.

New classes related to the movement and item conditions:
There are six java class file in Conditions
Conditions.java--this is an abstract class to define some conditions.
ItemNeedCarried.java--this class extends condition class.this class defines whether the specific condition for one specific item is satisfied or not.
ItemNotNeedCarried.java-- this class extends condition class. this class defines whether the specific condition for one specific item is satisfied or not.
RoomVisited.java--this class extends condition class. this class defines whether the specific condition for one room is satisfied or not.
SpecificMagicWord.java--this class extends condition class.this class defines whether the specific condition for one magic word is satisfied or not.
SpecificProbality.java--this class extends condition class.this class defines whether the specific condition for one probability is satisfied or not.

Game Player Command Line Interface related classes in game play:
Game.java


JUnit test cases for Build 3:
-In directory /test are all the JUnit test cases for Build 3.

AllTests.java - Test suite all the main Actions(follow function for character,lock connection function, magic world, eat function, drink function,attack function)
ItemNeedCarriedTest.java-test cItemNeedCarried condition
ItemNotNeedCarriedTest.java-test ItemNotNeedCarried condition
RoomVisitedTest.java-test RoomVisited condition
SpecificProbalityTest.java-test SpecificProbality condition
TestAttackOperation.java-test Attack function
TestDrinkOperation.java-test Drink function
TestEatOperation.java-test eat function
TestFollowOperation.java-test follow function
TestLockOperation.java-test lock function
TestMagicWordOperation.java-test magic word function
Player.java-Test the main player class
TestConnectionForm.java - Test the Connection
RoomTest.java - Test the main Room class
TestItem.java - Test the main Item class
TestItemForm.java - Test ItemForm
TestItemHander.java - Test the main ItemHandler class.
TestPlayerHander.java - Test the main Player class.
TestItemInfoDialog.java - Test ItemInfoDialog class
TestOperation.java-Test the main Operation class.
TestRoomInfomation.java - Test RoomInfomation class
ConnectionTest.java-test connection class
PlayerTest.java-test player class
RoomTest.java-test room class
TestMap.java-test Map class
TestItem.java-test java class
MapPanelTest.java-test MapPane class



To view JavaDoc online documentation, click on /doc/index.html as part of this electronic submission.


Instructions for how to compile, build and run code: 
Eclipse SDK Version is 3.4.1(GANYMEDE) Build M20080911-1700.

For game desinger, from the Package Explorer, open map.GUI.MapFrame.java file. Open the Building project using the Project -> Building project menu.  or click run using Run menu.
   
For game desinger, from the Package Explorer, open map.GUI.MapFrame.java file. Open the Building project using the Project -> Building project menu.  or click run using Run menu.

For game player,from the Package Explorer, open map.CommandLineInterface.GUI..CommandLineInterface.Game.java file. Open the Building project using the Project -> Building project menu.  or click run using Run menu.

NOTE 1: Refer to the Architecture Document to refer to specifics on the individual files.