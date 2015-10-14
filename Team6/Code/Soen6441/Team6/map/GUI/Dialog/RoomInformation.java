package map.GUI.Dialog;

import javax.swing.JPanel;
import javax.swing.UIManager;

import java.awt.Frame;
import javax.swing.JDialog;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.table.TableCellRenderer;

import java.awt.Dimension;
import java.util.Vector;

import map.Model.*;
/**
 * RoomInformation extends JDialog.
 * Responsible for show or Edit Room Information.
 * @author Yan Wang
 * @version 1.4
 */
public class RoomInformation extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JTextField textID = null;
	private JTextField textName = null;
	private JScrollPane jScrollPane = null;
	private JTextArea textDesc = null;
	private JPanel jPanel = null;
	private JPanel jSplitPane = null;
	private JButton Ok = null;
	private JButton Cancel = null;
	private JScrollPane jScrollPane1 = null;
	private JScrollPane jScrollPane2 = null;
	private JScrollPane jScrollPane3 = null;
	private JTable table1 = null;
	private JTable table2 = null;
	private JTable table3 = null;
	
	
	Room room;
	Vector<Room> roomList;
	Vector<Item> itemList;
	Vector<map.Model.Character> chList;
	boolean editable;
	public boolean isOk;
	
	/**
	 * Constructor
	 * @param owner
	 */
	public RoomInformation(Frame owner) {
		super(owner);
		initialize();
	}

	/**
	 * Constructor with fields
	 * @param roomList : Room List in Map
	 * @param r : Room
	 * @param Editable : whether to edit or not. true edit; false show
	 */
	public RoomInformation(Vector<Room> roomList, Room r, boolean Editable) {
    	super(new javax.swing.JFrame(), true);
		this.roomList = new Vector<Room>(roomList);
		this.room = new Room(r);
		this.editable = Editable;
		//initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	public void initialize() {
    	this.setTitle("Room Dialog");
		this.setSize(400, 500);
		this.setContentPane(getJContentPane());
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
			gridBagConstraints81.fill = GridBagConstraints.BOTH;
			gridBagConstraints81.gridy = 5;
			gridBagConstraints81.weightx = 1.0;
			gridBagConstraints81.weighty = 1.0;
			gridBagConstraints81.gridx = 1;
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.fill = GridBagConstraints.BOTH;
			gridBagConstraints51.gridy = 4;
			gridBagConstraints51.weightx = 1.0;
			gridBagConstraints51.weighty = 1.0;
			gridBagConstraints51.gridx = 1;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.fill = GridBagConstraints.BOTH;
			gridBagConstraints41.gridy = 3;
			gridBagConstraints41.weightx = 1.0;
			gridBagConstraints41.weighty = 1.0;
			gridBagConstraints41.gridx = 1;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.gridy = 4;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 3;
			gridBagConstraints11.fill = GridBagConstraints.BOTH;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = GridBagConstraints.NONE;
			gridBagConstraints31.gridy = 6;
			gridBagConstraints31.weightx = 1.0;
			gridBagConstraints31.weighty = 1.0;
			gridBagConstraints31.gridheight = 1;
			gridBagConstraints31.anchor = GridBagConstraints.CENTER;
			gridBagConstraints31.gridx = 1;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.gridy = 10;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.BOTH;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.weighty = 1.0;
			gridBagConstraints7.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.gridx = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints5.gridy = 0;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.NORTH;
			gridBagConstraints4.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints4.gridy = 4;
			jLabel5 = new JLabel();
			jLabel5.setText("Character: ");
			jLabel5.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel5.setHorizontalTextPosition(SwingConstants.LEFT);
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.anchor = GridBagConstraints.NORTH;
			gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints8.gridy = 5;
			jLabel4 = new JLabel();
			jLabel4.setText("Item: ");
			jLabel4.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel4.setHorizontalTextPosition(SwingConstants.LEFT);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 0;
			gridBagConstraints3.anchor = GridBagConstraints.NORTH;
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 3;
			jLabel3 = new JLabel();
			jLabel3.setText("Connection: ");
			jLabel3.setVerticalAlignment(SwingConstants.TOP);
			jLabel3.setVerticalTextPosition(SwingConstants.TOP);
			jLabel3.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel3.setHorizontalTextPosition(SwingConstants.LEFT);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.NORTH;
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 2;
			jLabel2 = new JLabel();
			jLabel2.setText("Description: ");
			jLabel2.setVerticalAlignment(SwingConstants.TOP);
			jLabel2.setVerticalTextPosition(SwingConstants.TOP);
			jLabel2.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel2.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel2.setHorizontalTextPosition(SwingConstants.LEFT);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 1;
			jLabel1 = new JLabel();
			jLabel1.setText("Name: ");
			jLabel1.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel1.setHorizontalTextPosition(SwingConstants.LEFT);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			jLabel = new JLabel();
			jLabel.setText("Number: ");
			jLabel.setHorizontalAlignment(SwingConstants.TRAILING);
			jLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(jLabel, gridBagConstraints);
			jContentPane.add(jLabel1, gridBagConstraints1);
			jContentPane.add(jLabel2, gridBagConstraints2);
			jContentPane.add(jLabel3, gridBagConstraints3);
			jContentPane.add(jLabel4, gridBagConstraints4);
			jContentPane.add(jLabel5, gridBagConstraints8);
			jContentPane.add(getTextID(), gridBagConstraints5);
			jContentPane.add(getTextName(), gridBagConstraints6);
			jContentPane.add(getJScrollPane(), gridBagConstraints7);
			jContentPane.add(getJPanel(), gridBagConstraints10);
			if(this.editable)
				jContentPane.add(getJSplitPane(), gridBagConstraints31);


			if(room != null){
				//Connection Table
				table1 = new javax.swing.JTable();
				String[] columnNames = {
			        "Direction", "Detail", "Delete"
			    };
				if(editable){
					columnNames[1] = "Edit";
				}
		        GTableModel tableModel = new GTableModel(columnNames, room.getConnection());
		        tableModel.setItems(itemList);
		        tableModel.setRooms(roomList);
		        tableModel.setEditable(editable);
		        table1.setModel(tableModel);
		        table1.setSurrendersFocusOnKeystroke(true);

		        TableCellRenderer defaultRenderer;
			    defaultRenderer = table1.getDefaultRenderer(JButton.class);
			    table1.setDefaultRenderer(JButton.class,
						       new JTableButtonRenderer(defaultRenderer));
			    table1.addMouseListener(new JTableButtonMouseListener(table1));
		        table1.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
				table1.setRowHeight(30);
				Vector<Connection> cons = room.getConnection();
				if(cons != null){
					int idx = 0;
					for(Connection foo : cons){
						tableModel.addEmptyRow();
						tableModel.setValueAt(String.valueOf(foo.getDirection().name()), idx, 0);
						idx++;
					}
				}

		        jScrollPane1 = new javax.swing.JScrollPane();
		        jScrollPane1.setViewportView(table1);
				jContentPane.add(jScrollPane1, gridBagConstraints41);
				
				//Item Table
				table2 = new javax.swing.JTable();
				columnNames[0] = "Name";
				if(editable){
					columnNames[1] = "Edit";
				}
		        tableModel = new GTableModel(columnNames, room.getItems());
		        tableModel.setItems(itemList);
		        tableModel.setRooms(roomList);
		        tableModel.setEditable(editable);
		        table2.setModel(tableModel);
		        table2.setSurrendersFocusOnKeystroke(true);

			    defaultRenderer = table2.getDefaultRenderer(JButton.class);
			    table2.setDefaultRenderer(JButton.class,
						       new JTableButtonRenderer(defaultRenderer));
			    table2.addMouseListener(new JTableButtonMouseListener(table2));
		        table2.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
				table2.setRowHeight(30);
				Vector<Item> items = room.getItems();
				if(items != null){
					int idx = 0;
					for(Item foo : items){
						tableModel.addEmptyRow();
						tableModel.setValueAt(String.valueOf(foo.getName()), idx, 0);
						idx++;
					}
				}

				jScrollPane2 = new javax.swing.JScrollPane();
		        jScrollPane2.setViewportView(table2);
				jContentPane.add(jScrollPane2, gridBagConstraints51);
				
				//Character Table
				table3 = new javax.swing.JTable();
				columnNames[0] = "Name";
				if(editable){
					columnNames[1] = "Edit";
				}
				Vector<map.Model.Character> characters = chList;
				if(characters != null){
			        tableModel = new GTableModel(columnNames, chList);
			        tableModel.setItems(itemList);
			        tableModel.setRooms(roomList);
			        tableModel.setEditable(editable);
			        table3.setModel(tableModel);
			        table3.setSurrendersFocusOnKeystroke(true);

				    defaultRenderer = table3.getDefaultRenderer(JButton.class);
				    table3.setDefaultRenderer(JButton.class,
							       new JTableButtonRenderer(defaultRenderer));
				    table3.addMouseListener(new JTableButtonMouseListener(table3));
			        table3.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
					table3.setRowHeight(30);

					int idx = 0;
					for(map.Model.Character foo : characters){
						tableModel.addEmptyRow();
						tableModel.setValueAt(String.valueOf(foo.getName()), idx, 0);
						idx++;
					}
				}

				jScrollPane3 = new javax.swing.JScrollPane();
		        jScrollPane3.setViewportView(table3);
				jContentPane.add(jScrollPane3, gridBagConstraints81);

				textID.setText(String.valueOf(room.getRoomID()));
				textID.setEditable(false);
				textName.setText(room.getName());
				textName.setEditable(editable);
				textDesc.setText(room.getDescription());
				textDesc.setEditable(editable);
			}
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes textID	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextID() {
		if (textID == null) {
			textID = new JTextField();
		}
		return textID;
	}

	/**
	 * This method initializes textName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getTextName() {
		if (textName == null) {
			textName = new JTextField();
		}
		return textName;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getTextDesc());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes textDesc	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getTextDesc() {
		if (textDesc == null) {
			textDesc = new JTextArea();
			textDesc.setSize(10, 40);
			textDesc.setWrapStyleWord(true);
			textDesc.setLineWrap(true);
		}
		return textDesc;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(new GridBagLayout());
		}
		return jPanel;
	}

	/**
	 * This method initializes jSplitPane	
	 * 	
	 * @return javax.swing.JSplitPane	
	 */
	private JPanel getJSplitPane() {
		if (jSplitPane == null) {

			jSplitPane = new JPanel();
			jSplitPane.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();

			c.gridx=30;   
			c.gridy=0;  
			c.gridheight=1; 
			c.gridwidth=1;
			
			jSplitPane.add(getOk(), c);
			
			jSplitPane.add(new JLabel("                   "));

			c.gridx=40;   
			c.gridy=0;  
			c.gridheight=1; 
			c.gridwidth=1;
			
			jSplitPane.add(getCancel(), c);
}
		return jSplitPane;
	}

	/**
	 * This method initializes Ok	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOk() {
		if (Ok == null) {
			isOk = false;
			Action ok = new AbstractAction("Ok") {
				static final long serialVersionUID=8762419586713147L;
				public void actionPerformed(ActionEvent e) {
					//TODO
					Room r = room;
					r.setName(textName.getText());
					r.setDescription(textDesc.getText());
					GTableModel tableModel;
					tableModel = (GTableModel)table1.getModel();
					Vector<Connection> cons = new Vector<Connection>();
					for(Object obj : tableModel.getCells()){
						if(obj instanceof Connection)
							cons.add((Connection)obj);
					}
					r.setConnections(cons);

					tableModel = (GTableModel)table2.getModel();
					Vector<Item> items = new Vector<Item>();
					for(Object obj : tableModel.getCells()){
						if(obj instanceof Item)
							items.add((Item)obj);
					}
					r.setItems(items);
					
					tableModel = (GTableModel)table3.getModel();
					Vector<map.Model.Character> chs = new Vector<map.Model.Character>();
					for(Object obj : tableModel.getCells()){
						if(obj instanceof map.Model.Character)
							chs.add((map.Model.Character)obj);
					}
					chList = chs;

					isOk = true;
					setVisible(false); 
					dispose();
				}
			};
			Ok = new JButton(ok);
			Ok.setMnemonic(KeyEvent.VK_UNDEFINED);
			Ok.setText("OK");
			Ok.setPreferredSize(new Dimension(60, 30));
		}
		return Ok;
	}

	/**
	 * This method initializes Cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getCancel() {
		if (Cancel == null) {
			isOk = false;
			Action cancel = new AbstractAction("Cancel") {
				static final long serialVersionUID=8762419586713147L;
				public void actionPerformed(ActionEvent e) {
					  setVisible(false); 
					  dispose();
				}
			};
			Cancel = new JButton(cancel);
			Cancel.setMnemonic(KeyEvent.VK_UNDEFINED);
			Cancel.setText("CANCEL");
			Ok.setPreferredSize(new Dimension(60, 30));
			//Cancel.setHorizontalTextPosition(SwingConstants.CENTER);
		}
		return Cancel;
	}

	public Room getRoom() {
		return room;
	}

	public static void main(String[] args) {
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
			item.addProp(Item.Property.TAKEABLE, (Boolean)false);
			r1.addItem(item);
			item = new Item("Item2", "Item2", r1);
			r1.addItem(item);
			RoomInformation dlg = new RoomInformation(rooms, r1, true); 
		    dlg.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * @return the itemList
	 */
	public Vector<Item> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(Vector<Item> itemList) {
		this.itemList = new Vector<Item>(itemList);
	}

	/**
	 * @return the chList
	 */
	public Vector<map.Model.Character> getChList() {
		return chList;
	}

	/**
	 * @param chList the chList to set
	 */
	public void setChList(Vector<map.Model.Character> chList) {
		this.chList = new Vector<map.Model.Character>(chList);
	}
}
