/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ConnectionDialog.java
 *
 * Created on 2009-12-3, 0:44:17
 */

package map.GUI.Dialog;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.TableCellRenderer;

import map.Conditions.Conditions;
import map.Conditions.ItemNeedCarried;
import map.Conditions.ItemNotNeedCarried;
import map.Conditions.RoomVisited;
import map.Conditions.SpecificMagicWord;
import map.Conditions.SpecificProbality;
import map.Model.Connection;
import map.Model.Item;
import map.Model.Room;
import map.Model.Item.Property;

/**
 * ConnectionDialog extends JDialog.
 * Responsible for show or Edit Connection Information.
 * @author Yan Wang
 */
public class ConnectionDialog extends javax.swing.JDialog {

    private boolean retValue;
	private Connection con;
	private Vector<Item> items;
	private Vector<Room> rooms;
	private boolean editable;
	private GTableModel tableModel;
	private static String[] columnNames = {
        "Number", "Detail", "Delete"
    };
	
	/** Creates new form ConnectionDialog */
    public ConnectionDialog() {
    	super(new javax.swing.JFrame(), true);
//        initComponents();
    }

    /**
     * initialise Components
     */
    public void initComponents() {

    	this.setTitle("Connection Dialog");
    	cbName = new javax.swing.JComboBox();
        Direction = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cbRoom = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        bAdd = new javax.swing.JButton();
        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        bEdit = new javax.swing.JButton();
        bDel = new javax.swing.JButton();
        bAddCon = new javax.swing.JButton();

        Direction.setText("Direction:");

        jLabel2.setText("Outgoing:");

        setResizable(false);
//        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableModel = new GTableModel(columnNames, con.getVcon());
        tableModel.setItems(items);
        tableModel.setRooms(rooms);
        tableModel.setEditable(editable);
        table.setModel(tableModel);
        table.setSurrendersFocusOnKeystroke(true);

        TableCellRenderer defaultRenderer;
	    defaultRenderer = table.getDefaultRenderer(JButton.class);
	    table.setDefaultRenderer(JButton.class,
				       new JTableButtonRenderer(defaultRenderer));
	    table.addMouseListener(new JTableButtonMouseListener(table));
        table.setPreferredScrollableViewportSize(new java.awt.Dimension(500, 300));
		table.setRowHeight(30);
		Vector<Conditions> cons = con.getVcon();
		if(cons != null){
			int idx = 0;
			for(Conditions foo : cons){
				tableModel.addEmptyRow();
				tableModel.setValueAt(String.valueOf(foo.getConditionId()), idx, 0);
				idx++;
			}
		}
        jScrollPane1.setViewportView(table);

        jLabel3.setText("Conditions:");

        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        cancel.setText("CANCEL");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        bAdd.setText("ADD DOOR");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });

        bAddCon.setText("ADD Condition");
        bAddCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddConActionPerformed(evt);
            }
        });

        bEdit.setText("EDIT DOOR");
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });

        bDel.setText("DELETE DOOR");
        bDel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDelActionPerformed(evt);
            }
        });
        
        bAddCon.setEnabled(editable);
        cbName.setEnabled(editable);
        cbRoom.setEnabled(editable);
        
        if(editable){
        	if(con.getDoor() == null){
        		bAdd.setEnabled(true);
            	bEdit.setEnabled(false);
            	bDel.setEnabled(false);
        	}
        	else{
        		bAdd.setEnabled(false);
            	bEdit.setEnabled(true);
            	bDel.setEnabled(true);
        	}
        }else{
            bEdit.setText("DOOR");
            if(con.getDoor() == null)
            	bEdit.setEnabled(false);
            else	
            	bEdit.setEnabled(true);
        	bAdd.setEnabled(false);
        	bDel.setEnabled(false);
        }

        //TODO set value
        cbName.addItem("");
        cbRoom.addItem("");
        for(Connection.Direction foo : Connection.Direction.values()){
        	cbName.addItem(foo.name());
        }
        for(Room foo : rooms){
        	cbRoom.addItem(foo.getName());
        }
        cbName.setSelectedItem(con.getDirection().name());
        cbRoom.setSelectedItem(con.getConnectsTo().getName());
        
        
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(bAddCon, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addGap(62, 62, 62)
                        .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancel))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbRoom, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Direction)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(cbName, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bDel))))
                .addGap(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Direction))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cbRoom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(bAdd)
                        .addGap(7, 7, 7)
                        .addComponent(bEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bDel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAddCon)
                    .addComponent(cancel)
                    .addComponent(ok))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    	ItemDialog dialog = new ItemDialog();
    	dialog.setItem(new Item());
    	dialog.setItems(items);
    	dialog.setRooms(rooms);
    	dialog.setEditable(true);
    	
    	dialog.initComponents();
    	dialog.setVisible(true);
    	if(dialog.isRetValue()){
    		con.setDoor(dialog.getItem());
    		bAdd.setEnabled(false);
    		bEdit.setEnabled(true);
    		bDel.setEnabled(true);
    	}
    }

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    	ItemDialog dialog = new ItemDialog();
    	dialog.setItem(con.getDoor());
    	dialog.setItems(items);
    	dialog.setRooms(rooms);
    	dialog.setEditable(true);
    	
    	dialog.initComponents();
    	dialog.setVisible(true);
    	if(dialog.isRetValue()){
    		con.setDoor(dialog.getItem());
    	}
    }

    private void bDelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    	con.setDoor(null);
		bAdd.setEnabled(true);
    	bEdit.setEnabled(false);
    	bDel.setEnabled(false);
    }

    private void okActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    	for(Connection.Direction foo : Connection.Direction.values()){
    		if(foo.name().equals(cbName.getSelectedItem())){
    	    	con.setDirection(foo);
    	    	break;
    		}
    	}
    	int idx = cbRoom.getSelectedIndex() - 1;
    	if(idx >= 0){
        	Room end = rooms.elementAt(idx);
        	con.setConnectsTo(end);
        	con.setEnd(end.getPos());
    	}
    	
    	retValue = true;
        setVisible(false);
        dispose();
    }

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    	setRetValue(false);
        setVisible(false);
        dispose();
    }
    
    /**
	 * retValue setter
	 * @param retValue the retValue to set
	 */
	public void setRetValue(boolean retValue) {
		this.retValue = retValue;
	}

	/**
	 * retValue getter
	 * @return the retValue
	 */
	public boolean isRetValue() {
		return retValue;
	}

	/**
	 * connection getter
	 * @return the con
	 */
	public Connection getCon() {
		return con;
	}

	/**
	 * connection setter
	 * @param con the con to set
	 */
	public void setCon(Connection con) {
		this.con = new Connection(con);
	}

	/**
	 * editable setter
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * editable getter
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}
	
    private void bAddConActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
    	ConditionDialog dialog = new ConditionDialog();
    	dialog.setItems(items);
    	dialog.setRooms(rooms);
    	dialog.setEditable(true);
    	
    	dialog.initComponents();
    	dialog.setVisible(true);
    	if(dialog.isRetValue()){
    		Conditions condition = dialog.getCondition();
			tableModel.addEmptyRow();
			int idx = tableModel.getDataVector().size() - 1;
			tableModel.setValueAt(String.valueOf(condition.getConditionId()), idx, 0);
			
			con.addCondition(condition);
    	}
    }                                       

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	HashMap<Property, Object> props = new HashMap<Property, Object>();
            	props.put(Property.DRINKABLE, (Integer)10);
            	props.put(Property.THROWABLE, (Integer)10);
            	props.put(Property.LOCKABLE, (Boolean)true);
            	
            	Item carried = new Item();
            	carried.setName("Key1");
            	carried.setKey(true);

            	Item item = new Item();
            	item.setName("Box");
            	item.setProps(props);
            	item.setKeyNeed(carried);
            	
            	Vector<Item> items = new Vector<Item>();
            	items.add(item);
            	items.add(new Item("Apple"));
            	Item foo = new Item("Key2");
            	foo.setKey(true);
            	items.add(foo);
            	items.add(carried);
            	
            	Room room = new Room();
            	room.setName("Room1");
            	Vector<Room> rooms = new Vector<Room>();
            	rooms.add(room);
            	rooms.add(new Room("Room2"));

            	Vector<Conditions> cons = new Vector<Conditions>();
            	cons.add(new SpecificProbality(0.8));
            	cons.add(new ItemNeedCarried(carried));
            	cons.add(new ItemNotNeedCarried(carried));
            	cons.add(new RoomVisited(room));
            	cons.add(new SpecificMagicWord("Magic"));
            	
            	item.setVcon(cons);
            	
            	Connection con = new Connection(Connection.Direction.WEST, room);
            	con.setVcon(cons);
            	
            	ConnectionDialog dialog = new ConnectionDialog();
            	dialog.setCon(con);
            	dialog.setItems(items);
            	dialog.setRooms(rooms);
            	dialog.setEditable(true);
            	
            	dialog.initComponents();
            	dialog.setVisible(true);
            }
        });
    }

	/**
	 * tableMode setter
	 * @param tableModel the tableModel to set
	 */
	public void setTableModel(GTableModel tableModel) {
		this.tableModel = tableModel;
	}

	/**
	 * tableModel getter
	 * @return the tableModel
	 */
	public GTableModel getTableModel() {
		return tableModel;
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAdd;
    private javax.swing.JButton bAddCon;
    private javax.swing.JButton bDel;
    private javax.swing.JButton bEdit;
    private javax.swing.JComboBox cbName;
    private javax.swing.JButton ok;
    private javax.swing.JButton cancel;
    private javax.swing.JComboBox cbRoom;
    private javax.swing.JLabel Direction;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables

	/**
	 * items getter
	 * @return the items
	 */
	public Vector<Item> getItems() {
		return items;
	}

	/**
	 * items setter
	 * @param items the items to set
	 */
	public void setItems(Vector<Item> items) {
		this.items = items;
	}

	/**
	 * rooms getter
	 * @return the rooms
	 */
	public Vector<Room> getRooms() {
		return rooms;
	}

	/**
	 * rooms setter
	 * @param rooms the rooms to set
	 */
	public void setRooms(Vector<Room> rooms) {
		this.rooms = rooms;
	}

}
