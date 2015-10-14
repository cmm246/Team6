/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Condition.java
 *
 * Created on 2-Dec-2009, 6:55:40 PM
 */

package map.GUI.Dialog;

import java.util.Vector;

import map.Conditions.*;
import map.Model.Item;
import map.Model.Room;

/**
 * ConditionDialog extends JDialog.
 * Responsible for show or Edit Conditions Information.
 * @author Yan Wang
 */
public class ConditionDialog extends javax.swing.JDialog {
	
	Conditions condition;
	Vector<Item> items;
	Vector<Room> rooms;
	boolean retValue;
	boolean editable;

    /** Creates new form Condition */
    public ConditionDialog() {
    	super(new javax.swing.JFrame(), true);
        //initComponents();
    }
    
    /**
     * retValue getter
     * @return retValue
     */
    public boolean isRetValue() {
		return retValue;
	}

    /**
     * retValue setter
     * @param retValue
     */
	public void setRetValue(boolean retValue) {
		this.retValue = retValue;
	}

	/**
	 * condition getter
	 * @return condition
	 */
	public Conditions getCondition() {
		return condition;
	}

	/**
	 * condition setter
	 * @param obj instance of Conditions
	 */
	public void setCondition(Conditions obj) {
    	if(obj instanceof ItemNeedCarried){
    		condition = new ItemNeedCarried(((ItemNeedCarried) obj).getItem());
    	}else if(obj instanceof ItemNotNeedCarried){
    		condition = new ItemNotNeedCarried(((ItemNotNeedCarried) obj).getItem());
    	}else if(obj instanceof RoomVisited){
    		condition = new RoomVisited(((RoomVisited) obj).getRoom());
    	}else if(obj instanceof SpecificMagicWord){
    		condition = new SpecificMagicWord(((SpecificMagicWord) obj).getWord());
    	}else if(obj instanceof SpecificProbality){
    		condition = new SpecificProbality(((SpecificProbality) obj).getProbality());
    	}else
    		condition = null;
	}

	/**
	 * items getter
	 * @return item list
	 */
	public Vector<Item> getItems() {
		return items;
	}

	/**
	 * items setter
	 * @param items
	 */
	public void setItems(Vector<Item> items) {
		this.items = items;
	}

	/**
	 * rooms getter
	 * @return room list
	 */
	public Vector<Room> getRooms() {
		return rooms;
	}

	/**
	 * rooms setter
	 * @param rooms
	 */
	public void setRooms(Vector<Room> rooms) {
		this.rooms = rooms;
	}

	/**
	 * initialise Components
	 */
    public void initComponents() {

    	this.setTitle("Condition Dialog");
        type = new javax.swing.ButtonGroup();
        itemNeedCarried = new javax.swing.JCheckBox();
        itemNotNeedCarried = new javax.swing.JCheckBox();
        probality = new javax.swing.JCheckBox();
        magicWord = new javax.swing.JCheckBox();
        roomVisited = new javax.swing.JCheckBox();
        cbItemNeedCarried = new javax.swing.JComboBox();
        cbItemNotNeedCarried = new javax.swing.JComboBox();
        cbRoomVisited = new javax.swing.JComboBox();
        textMagicWord = new javax.swing.JTextField();
        sliderProbality = new javax.swing.JSlider();
        textProbality = new javax.swing.JLabel();
        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();

        setResizable(false);

        type.add(itemNeedCarried);
        type.add(itemNotNeedCarried);
        type.add(probality);
        type.add(magicWord);
        type.add(roomVisited);
        
        itemNeedCarried.setText("ItemNeedCarried");
        itemNeedCarried.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                itemNeedCarriedStateChanged(evt);
            }
        });
        itemNeedCarried.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNeedCarriedActionPerformed(evt);
            }
        });

        itemNotNeedCarried.setText("ItemNotNeedCarried");
        itemNotNeedCarried.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                itemNotNeedCarriedStateChanged(evt);
            }
        });
        itemNotNeedCarried.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNotNeedCarriedActionPerformed(evt);
            }
        });

        probality.setText("Probability");
        probality.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                probalityStateChanged(evt);
            }
        });
        probality.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                probalityActionPerformed(evt);
            }
        });

        magicWord.setText("MagicWord");
        magicWord.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                magicWordStateChanged(evt);
            }
        });
        magicWord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                magicWordActionPerformed(evt);
            }
        });

        roomVisited.setText("RoomVisited");
        roomVisited.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                roomVisitedStateChanged(evt);
            }
        });
        roomVisited.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roomVisitedActionPerformed(evt);
            }
        });

        sliderProbality.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderProbalityStateChanged(evt);
            }
        });
        sliderProbality.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                sliderProbalityAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        textProbality.setText("jLabel1");

        ok.setText("OK");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        cancel.setText("Cancel");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        
        //TODO set value
        cbItemNeedCarried.addItem("");
        cbItemNotNeedCarried.addItem("");
        cbRoomVisited.addItem("");

        if(items != null){
        	for(Item foo : items){
        		cbItemNeedCarried.addItem(foo.getName());
        		cbItemNotNeedCarried.addItem(foo.getName());
        	}
        }
        if(rooms != null){
        	for(Room foo : rooms){
        		cbRoomVisited.addItem(foo.getName());
        	}
        }
        if(condition instanceof ItemNeedCarried){
        	ItemNeedCarried inc = (ItemNeedCarried)condition;
        	itemNeedCarried.setSelected(true);
        	cbItemNeedCarried.setSelectedItem(inc.getItem().getName());
        }else if(condition instanceof ItemNotNeedCarried){
        	ItemNotNeedCarried inc = (ItemNotNeedCarried)condition;
        	itemNotNeedCarried.setSelected(true);
        	cbItemNotNeedCarried.setSelectedItem(inc.getItem().getName());
        }else if(condition instanceof RoomVisited){
        	RoomVisited inc = (RoomVisited)condition;
        	roomVisited.setSelected(true);
        	cbRoomVisited.setSelectedItem(inc.getRoom().getName());
        }else if(condition instanceof SpecificMagicWord){
        	SpecificMagicWord inc = (SpecificMagicWord)condition;
        	magicWord.setSelected(true);
        	textMagicWord.setText(inc.getWord());
        }else if(condition instanceof SpecificProbality){
        	SpecificProbality inc = (SpecificProbality)condition;
        	probality.setSelected(true);
        	sliderProbality.setValue((int)(inc.getProbality()*100));
        }

        itemNeedCarried.setEnabled(editable);
        itemNotNeedCarried.setEnabled(editable);
        probality.setEnabled(editable);
        magicWord.setEnabled(editable);
        roomVisited.setEnabled(editable);
        sliderProbality.setEnabled(editable);
        textMagicWord.setEnabled(editable);
        cbItemNeedCarried.setEnabled(editable);
        cbItemNotNeedCarried.setEnabled(editable);
        cbRoomVisited.setEnabled(editable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemNeedCarried)
                            .addComponent(itemNotNeedCarried)
                            .addComponent(probality)
                            .addComponent(magicWord)
                            .addComponent(roomVisited))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sliderProbality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(textProbality))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbItemNotNeedCarried, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cbItemNeedCarried, javax.swing.GroupLayout.Alignment.LEADING, 0, 95, Short.MAX_VALUE))
                            .addComponent(textMagicWord, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
                            .addComponent(cbRoomVisited, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(cancel)
                        .addGap(29, 29, 29))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemNeedCarried)
                    .addComponent(cbItemNeedCarried, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemNotNeedCarried)
                    .addComponent(cbItemNotNeedCarried, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(probality)
                    .addComponent(sliderProbality, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textProbality))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(magicWord)
                    .addComponent(textMagicWord, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cancel)
                            .addComponent(ok)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(roomVisited)
                            .addComponent(cbRoomVisited, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * respond to event of Ancestor Moved for sliderProbality
     * @param evt
     */
    private void sliderProbalityAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_sliderProbalityAncestorMoved
        int value = sliderProbality.getValue();
        textProbality.setText(String.valueOf(value)+"%");
    }//GEN-LAST:event_sliderProbalityAncestorMoved

    /**
     * respond to event of State Changed for sliderProbality
     * @param evt
     */
    private void sliderProbalityStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderProbalityStateChanged
        int value = sliderProbality.getValue();
        textProbality.setText(String.valueOf(value)+"%");
    }//GEN-LAST:event_sliderProbalityStateChanged

    /**
     * respond to event of check box for itemNeedCarried
     * @param evt
     */
    private void itemNeedCarriedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNeedCarriedActionPerformed
        if(itemNeedCarried.isSelected()){
            cbItemNeedCarried.setEnabled(true);
            cbItemNotNeedCarried.setEnabled(false);
            cbRoomVisited.setEnabled(false);
            textMagicWord.setEnabled(false);
            sliderProbality.setEnabled(false);
            textProbality.setEnabled(false);
        }
    }//GEN-LAST:event_itemNeedCarriedActionPerformed

    /**
     * respond to event of check box for itemNotNeedCarried
     * @param evt
     */
    private void itemNotNeedCarriedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNotNeedCarriedActionPerformed
        if(itemNotNeedCarried.isSelected()){
            cbItemNotNeedCarried.setEnabled(true);
            cbItemNeedCarried.setEnabled(false);
            cbRoomVisited.setEnabled(false);
            textMagicWord.setEnabled(false);
            sliderProbality.setEnabled(false);
            textProbality.setEnabled(false);
        }
    }//GEN-LAST:event_itemNotNeedCarriedActionPerformed

    /**
     * respond to event of check box for probability
     * @param evt
     */
    private void probalityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_probalityActionPerformed
        if(probality.isSelected()){
            sliderProbality.setEnabled(true);
            textProbality.setEnabled(true);
            cbItemNotNeedCarried.setEnabled(false);
            cbItemNeedCarried.setEnabled(false);
            cbRoomVisited.setEnabled(false);
            textMagicWord.setEnabled(false);
        }
    }//GEN-LAST:event_probalityActionPerformed

    /**
     * respond to event of check box for magicWord
     * @param evt
     */
    private void magicWordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_magicWordActionPerformed
        if(magicWord.isSelected()){
            textMagicWord.setEnabled(true);
            sliderProbality.setEnabled(false);
            textProbality.setEnabled(false);
            cbItemNotNeedCarried.setEnabled(false);
            cbItemNeedCarried.setEnabled(false);
            cbRoomVisited.setEnabled(false);
        }
    }//GEN-LAST:event_magicWordActionPerformed

    /**
     * respond to event of check box for roomVisited
     * @param evt
     */
    private void roomVisitedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roomVisitedActionPerformed
        if(roomVisited.isSelected()){
            cbRoomVisited.setEnabled(true);
            textMagicWord.setEnabled(false);
            sliderProbality.setEnabled(false);
            textProbality.setEnabled(false);
            cbItemNotNeedCarried.setEnabled(false);
            cbItemNeedCarried.setEnabled(false);
        }
    }//GEN-LAST:event_roomVisitedActionPerformed

    /**
     * respond to event of button of OK
     * @param evt
     */
    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
    	if(itemNeedCarried.isSelected()){
    		int idx = cbItemNeedCarried.getSelectedIndex() - 1;
    		if(condition == null && idx >= 0)
    			condition = new ItemNeedCarried(items.elementAt(idx));
    		else
    			((ItemNeedCarried)condition).setItem(items.elementAt(idx));
    	}else if(itemNotNeedCarried.isSelected()){
    		int idx = cbItemNotNeedCarried.getSelectedIndex();
    		if(condition == null && idx >= 0)
    			condition = new ItemNotNeedCarried(items.elementAt(idx));
    		else
    			((ItemNotNeedCarried)condition).setItem(items.elementAt(idx));
    	}else if(roomVisited.isSelected()){
    		int idx = cbRoomVisited.getSelectedIndex() - 1;
    		if(condition == null && idx >= 0)
    			condition = new RoomVisited(rooms.elementAt(idx));
    		else
    			((RoomVisited)condition).setRoom(rooms.elementAt(idx));
    	}else if(magicWord.isSelected()){
    		if(condition == null)
    			condition = new SpecificMagicWord(textMagicWord.getText());
    		else
    			((SpecificMagicWord)condition).setWord(textMagicWord.getText());
    	}else if(probality.isSelected()){
    		String text = textProbality.getText();
    		String[] tokens = text.split("%");
    		if(condition == null)
    			condition = new SpecificProbality(Double.valueOf(tokens[0])/100);
    		else
    			((SpecificProbality)condition).setProbality(Double.valueOf(tokens[0])/100);
    	}else {
        	retValue = false;
            setVisible(false);
            dispose();
            return;
    	}
    	retValue = true;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_okActionPerformed

    /**
     * respond to event of button of cancel
     * @param evt
     */
    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
    	retValue = false;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelActionPerformed

    /**
     * respond to event of State Changed for itemNeedCarried
     * @param evt
     */
    private void itemNeedCarriedStateChanged(javax.swing.event.ChangeEvent evt) {
        // TODO add your handling code here:
        if(itemNeedCarried.isSelected()){
            cbItemNeedCarried.setEnabled(true);
            cbItemNotNeedCarried.setEnabled(false);
            cbRoomVisited.setEnabled(false);
            textMagicWord.setEnabled(false);
            sliderProbality.setEnabled(false);
            textProbality.setEnabled(false);
        }
    }

    private void itemNotNeedCarriedStateChanged(javax.swing.event.ChangeEvent evt) {
        if(itemNotNeedCarried.isSelected()){
            cbItemNotNeedCarried.setEnabled(true);
            cbItemNeedCarried.setEnabled(false);
            cbRoomVisited.setEnabled(false);
            textMagicWord.setEnabled(false);
            sliderProbality.setEnabled(false);
            textProbality.setEnabled(false);
        }
    }

    private void probalityStateChanged(javax.swing.event.ChangeEvent evt) {
        if(probality.isSelected()){
            sliderProbality.setEnabled(true);
            textProbality.setEnabled(true);
            cbItemNotNeedCarried.setEnabled(false);
            cbItemNeedCarried.setEnabled(false);
            cbRoomVisited.setEnabled(false);
            textMagicWord.setEnabled(false);
        }
    }

    private void magicWordStateChanged(javax.swing.event.ChangeEvent evt) {
        if(magicWord.isSelected()){
            textMagicWord.setEnabled(true);
            sliderProbality.setEnabled(false);
            textProbality.setEnabled(false);
            cbItemNotNeedCarried.setEnabled(false);
            cbItemNeedCarried.setEnabled(false);
            cbRoomVisited.setEnabled(false);
        }
    }

    private void roomVisitedStateChanged(javax.swing.event.ChangeEvent evt) {
        if(roomVisited.isSelected()){
            cbRoomVisited.setEnabled(true);
            textMagicWord.setEnabled(false);
            sliderProbality.setEnabled(false);
            textProbality.setEnabled(false);
            cbItemNotNeedCarried.setEnabled(false);
            cbItemNeedCarried.setEnabled(false);
        }
    }
   /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
    	Item item = new Item();
    	item.setName("Key1");
    	Vector<Item> items = new Vector<Item>();
    	items.add(new Item("Apple"));
    	items.add(new Item("Key2"));
    	items.add(item);

    	Room room = new Room();
    	room.setName("Room1");
    	Vector<Room> rooms = new Vector<Room>();
    	rooms.add(room);
    	rooms.add(new Room("Room2"));
    	
    	//Conditions con = new SpecificProbality(0.8);	//NO.1
    	//Conditions con = new ItemNeedCarried(item); 	//NO.2
    	//Conditions con = new ItemNotNeedCarried(item);	//NO.3
    	//Conditions con = new RoomVisited(room);	//NO.4
    	Conditions con = new SpecificMagicWord("Magic");	//NO.5
    	
    	ConditionDialog dialog = new ConditionDialog();
    	dialog.setCondition(con);
    	dialog.setItems(items);
    	dialog.setRooms(rooms);
    	dialog.setEditable(true);
    	
    	dialog.initComponents();
    	dialog.setVisible(true);
    	
    	if(dialog.isRetValue()){
        	Conditions ret = dialog.getCondition();
        	if(ret instanceof ItemNeedCarried){
        		System.out.println(((ItemNeedCarried) ret).getItem().getName());
        	}else if(ret instanceof ItemNotNeedCarried){
        		System.out.println(((ItemNotNeedCarried) ret).getItem().getName());
        	}else if(ret instanceof RoomVisited){
        		System.out.println(((RoomVisited) ret).getRoom().getName());
        	}else if(ret instanceof SpecificMagicWord){
        		System.out.println(((SpecificMagicWord) ret).getWord());
        	}else if(ret instanceof SpecificProbality){
        		System.out.println(((SpecificProbality) ret).getProbality());
        	}
    	}
    }

    /**
	 * editable getter
	 * @return the editable
	 */
	public boolean isEditable() {
		return editable;
	}
	
	/**
	 * editable setter
	 * @param editable the editable to set
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancel;
    private javax.swing.JComboBox cbItemNeedCarried;
    private javax.swing.JComboBox cbItemNotNeedCarried;
    private javax.swing.JComboBox cbRoomVisited;
    private javax.swing.JCheckBox itemNeedCarried;
    private javax.swing.JCheckBox itemNotNeedCarried;
    private javax.swing.JCheckBox magicWord;
    private javax.swing.JButton ok;
    private javax.swing.JCheckBox probality;
    private javax.swing.JCheckBox roomVisited;
    private javax.swing.JSlider sliderProbality;
    private javax.swing.JTextField textMagicWord;
    private javax.swing.JLabel textProbality;
    private javax.swing.ButtonGroup type;
    // End of variables declaration//GEN-END:variables

}
