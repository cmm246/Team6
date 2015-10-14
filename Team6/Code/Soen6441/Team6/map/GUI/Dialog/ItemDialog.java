/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemDialog.java
 *
 * Created on 2009-12-3, 1:05:19
 */

package map.GUI.Dialog;

import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.table.TableCellRenderer;

import map.Conditions.*;
import map.Model.Item;
import map.Model.Room;
import map.Model.Item.Property;

/**
 * ItemDialog extends JDialog.
 * Responsible for show or Edit Item Information.
 * @author Yan Wang
 */
public class ItemDialog extends javax.swing.JDialog {
	
	private Item item;
	private Vector<Item> items;
	private Vector<Room> rooms;
	private boolean retValue;
	private boolean editable;
	private GTableModel tableModel;
	private static String[] columnNames = {
        "Number", "Detail", "Delete"
    };

    /** Creates new form ItemDialog */
    public ItemDialog() {
    	super(new javax.swing.JFrame(), true);
    }

    /**
     * initialize the form.
     */
    public void initComponents() {

    	this.setTitle("Item Dialog");
        jLabel1 = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textDesc = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        bProp = new javax.swing.JButton();
        bAdd = new javax.swing.JButton();
        cbKey = new javax.swing.JCheckBox();

        setResizable(false);

        jLabel1.setText("Name:");

        jLabel2.setText("Description:");

        textDesc.setColumns(20);
        textDesc.setRows(5);
        jScrollPane1.setViewportView(textDesc);

        jLabel4.setText("Conditions:");

        tableModel = new GTableModel(columnNames, item.getVcon());
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
		Vector<Conditions> cons = item.getVcon();
		if(cons != null){
			int idx = 0;
			for(Conditions foo : cons){
				tableModel.addEmptyRow();
				tableModel.setValueAt(String.valueOf(foo.getConditionId()), idx, 0);
				idx++;
			}
		}
        jScrollPane4.setViewportView(table);

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

        bProp.setText("Property");
        bProp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPropActionPerformed(evt);
            }
        });

        bAdd.setText("ADD Condition");
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });
        bAdd.setEnabled(editable);
        
        cbKey.setText("ISKEY");
        cbKey.setEnabled(editable);
        cbKey.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cbKeyStateChanged(evt);
            }
        });
        cbKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKeyActionPerformed(evt);
            }
        });

        //TODO set value
        
        textName.setText(item.getName());
        textName.setEditable(editable);
        textDesc.setText(item.getDescription());
        textDesc.setEditable(editable);
        cbKey.setSelected(item.isKey());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(bAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(57, 57, 57)
                .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cancel)
                .addContainerGap(48, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                        .addComponent(cbKey)
                        .addGap(42, 42, 42)
                        .addComponent(bProp))
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bProp)
                    .addComponent(cbKey))
                .addGap(10, 10, 10)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAdd)
                    .addComponent(ok)
                    .addComponent(cancel))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
	 * @return the retValue
	 */
	public boolean isRetValue() {
		return retValue;
	}

	/**
	 * @param retValue the retValue to set
	 */
	public void setRetValue(boolean retValue) {
		this.retValue = retValue;
	}

	/**
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * @return the item
	 */
	public Item getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(Item item) {
		if(item != null)
			this.item = new Item(item);
	}

	/**
	 * @return the rooms
	 */
	public Vector<Room> getRooms() {
		return rooms;
	}

	/**
	 * @param rooms the rooms to set
	 */
	public void setRooms(Vector<Room> rooms) {
		this.rooms = rooms;
	}

	private void bPropActionPerformed(java.awt.event.ActionEvent evt) {
	        // TODO add your handling code here:
    	ItemPropertyDialog dialog = new ItemPropertyDialog();
    	dialog.setItem(item);
    	dialog.setItems(items);
    	dialog.setEditable(editable);
    	dialog.initComponents();
    	dialog.setVisible(true);
    	if(dialog.isRetValue()){
    		item = dialog.getItem();
    	}
	}
	
	private void bAddActionPerformed(java.awt.event.ActionEvent evt) {
	    // TODO add your handling code here:
    	ConditionDialog dialog = new ConditionDialog();
    	dialog.setItems(items);
    	dialog.setRooms(rooms);
    	dialog.setEditable(true);
    	
    	dialog.initComponents();
    	dialog.setVisible(true);
    	if(dialog.isRetValue()){
    		Conditions con = dialog.getCondition();
			tableModel.addEmptyRow();
			int idx = tableModel.getDataVector().size() - 1;
			tableModel.setValueAt(String.valueOf(con.getConditionId()), idx, 0);
			
			item.addCondition(con);
    	}
		
	}
	
	private void okActionPerformed(java.awt.event.ActionEvent evt) {
	    // TODO add your handling code here:
		item.setName(textName.getText());
		item.setDescription(textDesc.getText());
		item.setKey(cbKey.isSelected());
		if(cbKey.isSelected()){
			if(item.getProps() != null)
				item.getProps().clear();
			item.addProp(Property.TAKEABLE, null);
		}
    	retValue = true;
        setVisible(false);
        dispose();
	}
	
	private void cancelActionPerformed(java.awt.event.ActionEvent evt) {
	    // TODO add your handling code here:
    	retValue = false;
        setVisible(false);
        dispose();
	}

	/**
	 * @return the items
	 */
	public Vector<Item> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(Vector<Item> items) {
		this.items = items;
	}
	
    private void cbKeyActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(cbKey.isSelected()){
            bProp.setEnabled(false);
        }
        else{
            bProp.setEnabled(true);
        }
    }

    private void cbKeyStateChanged(javax.swing.event.ChangeEvent evt) {
        // TODO add your handling code here:
        if(cbKey.isSelected()){
            bProp.setEnabled(false);
        }
        else{
            bProp.setEnabled(true);
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
            	
            	ItemDialog dialog = new ItemDialog();
            	dialog.setItem(item);
            	dialog.setItems(items);
            	dialog.setRooms(rooms);
            	dialog.setEditable(true);
            	
            	dialog.initComponents();
            	dialog.setVisible(true);
            }
        });
    }

	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bProp;
    private javax.swing.JButton cancel;
    private javax.swing.JButton bAdd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable table;
    private javax.swing.JButton ok;
    private javax.swing.JTextArea textDesc;
    private javax.swing.JTextField textName;
    private javax.swing.JCheckBox cbKey;
    // End of variables declaration//GEN-END:variables

}
