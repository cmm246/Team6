/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ItemProperty.java
 *
 * Created on 2-Dec-2009, 7:23:11 PM
 */

package map.GUI.Dialog;

import java.util.HashMap;
import java.util.Vector;
import java.util.Map.Entry;

import map.Model.Item;
import map.Model.Item.Property;

/**
 * ItemPropertyDialog extends JDialog.
 * Responsible for show or Edit Items Property Information.
 * @author Yan Wang
 */
public class ItemPropertyDialog extends javax.swing.JDialog {

    HashMap<Property, Object> props = new HashMap<Property, Object>();
    Item item;

	Vector<Item> items = new Vector<Item>();
    boolean retValue;
    boolean editable;
    
	/** Creates new form ItemProperty */
    public ItemPropertyDialog() {
    	super(new javax.swing.JFrame(), true);
        //initComponents();
    }

    public HashMap<Property, Object> getProps() {
		return props;
	}

	public void setProps(HashMap<Property, Object> props) {
		this.props = new HashMap<Property, Object>(props);
	}

	public Vector<Item> getItems() {
		return items;
	}

	public void setItems(Vector<Item> items) {
		this.items = new Vector<Item>(items);
	}

    public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = new Item(item);
	}

	public boolean isRetValue() {
		return retValue;
	}

	public void setRetValue(boolean retValue) {
		this.retValue = retValue;
	}

	/**
	 * initialize Form
	 */
	public void initComponents() {

    	this.setTitle("Property Dialog");
        bgLock = new javax.swing.ButtonGroup();
        takeable = new javax.swing.JCheckBox();
        drinkable = new javax.swing.JCheckBox();
        eatable = new javax.swing.JCheckBox();
        fireable = new javax.swing.JCheckBox();
        throwable = new javax.swing.JCheckBox();
        lockable = new javax.swing.JCheckBox();
        ok = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        sliderDrinkable = new javax.swing.JSlider();
        textDrinkable = new javax.swing.JLabel();
        sliderEatable = new javax.swing.JSlider();
        textEatable = new javax.swing.JLabel();
        sliderFireable = new javax.swing.JSlider();
        textFireable = new javax.swing.JLabel();
        sliderThrowable = new javax.swing.JSlider();
        textThrowable = new javax.swing.JLabel();
        rbLock = new javax.swing.JRadioButton();
        rbUnLock = new javax.swing.JRadioButton();
        cbLock = new javax.swing.JComboBox();
        rbEatable = new javax.swing.JRadioButton();
        rbDrinkable = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        sliderProFireable = new javax.swing.JSlider();
        proFireable = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        sliderProThrowable = new javax.swing.JSlider();
        proThrowable = new javax.swing.JLabel();

        setResizable(false);

        bgLock.add(rbLock);
        bgLock.add(rbUnLock);
        
        rbLock.setEnabled(editable);
        rbUnLock.setEnabled(editable);
        cbLock.setEnabled(editable);
        takeable.setEnabled(editable);
        drinkable.setEnabled(editable);
        eatable.setEnabled(editable);
        fireable.setEnabled(editable);
        throwable.setEnabled(editable);
        lockable.setEnabled(editable);
        sliderDrinkable.setEnabled(editable);
        sliderEatable.setEnabled(editable);
        sliderFireable.setEnabled(editable);
        sliderThrowable.setEnabled(editable);
        
        
        takeable.setText("Takeable");

        drinkable.setText("Drinkable");

        eatable.setText("Eatable");

        fireable.setText("Fireable");

        throwable.setText("Throwable");

        lockable.setText("Lockable");

        rbEatable.setText("Negative");

        rbDrinkable.setText("Negative");

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

        sliderDrinkable.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderDrinkableStateChanged(evt);
            }
        });
        sliderDrinkable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                sliderDrinkableAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        textDrinkable.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        textDrinkable.setText("jLabel1");

        sliderEatable.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderEatableStateChanged(evt);
            }
        });
        sliderEatable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                sliderEatableAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        textEatable.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        textEatable.setText("jLabel1");

        sliderFireable.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderFireableStateChanged(evt);
            }
        });
        sliderFireable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                sliderFireableAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        textFireable.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        textFireable.setText("jLabel1");

        sliderThrowable.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderThrowableStateChanged(evt);
            }
        });
        sliderThrowable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                sliderThrowableAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        textThrowable.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        textThrowable.setText("jLabel1");

        rbLock.setText("Lock");

        rbUnLock.setText("UnLock");
        
        jLabel1.setText("Damage:");

        jLabel2.setText("Probability:");

        sliderProFireable.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderProFireableStateChanged(evt);
            }
        });
        sliderProFireable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                sliderProFireableAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        proFireable.setText("jLabel3");

        jLabel3.setText("Damage:");

        jLabel4.setText("Probability:");

        sliderProThrowable.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sliderProThrowableStateChanged(evt);
            }
        });
        sliderProThrowable.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                sliderProThrowableAncestorMoved(evt);
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        proThrowable.setText("jLabel5");

        //TODO set value
        cbLock.addItem("");
        if(items != null){
        	for(Item foo : items){
        		if(foo.isKey())
        			cbLock.addItem(foo.getName());
        	}
        }
        props = item.getProps();
        if(props != null){
            for(Entry<Property, Object> foo : props.entrySet()){
            	Property prop = foo.getKey();
            	Object value = foo.getValue();
            	if(prop.name().equals(Property.DRINKABLE.name())){
            		drinkable.setSelected(true);
            		sliderDrinkable.setValue((Integer)value);
            	}else if(prop.name().equals(Property.EATABLE.name())){
            		eatable.setSelected(true);
            		sliderEatable.setValue((Integer)value);
            	}else if(prop.name().equals(Property.FIRABLE.name())){
            		fireable.setSelected(true);
            		sliderFireable.setValue((Integer)value);
            		int p = (int)(item.getProbFire() * 100);
            		sliderProFireable.setValue(p);
            	}else if(prop.name().equals(Property.THROWABLE.name())){
            		throwable.setSelected(true);
            		sliderThrowable.setValue((Integer)value);
            		int p = (int)(item.getProbThrow() * 100);
            		sliderProThrowable.setValue(p);
            	}else if(prop.name().equals(Property.TAKEABLE.name())){
            		takeable.setSelected(true);
            	}else if(prop.name().equals(Property.LOCKABLE.name())){
            		lockable.setSelected(true);
            		if((Boolean)value){
            			rbLock.setSelected(true);
            		}else{
            			rbUnLock.setSelected(true);
            		}
            		if(item.getKeyNeed() != null)
            			cbLock.setSelectedItem(item.getKeyNeed().getName());
            	}
            }
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(drinkable)
                            .addComponent(takeable)
                            .addComponent(throwable)
                            .addComponent(fireable)
                            .addComponent(eatable, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbEatable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(rbDrinkable, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(sliderThrowable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderFireable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderEatable, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
                            .addComponent(sliderProFireable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderDrinkable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(sliderProThrowable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textThrowable, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textFireable, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(textEatable, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                            .addComponent(textDrinkable, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(proFireable, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(proThrowable, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(43, 43, 43))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lockable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbLock))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(196, 196, 196)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ok, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(41, 41, 41)
                                        .addComponent(cancel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(rbUnLock)
                                        .addGap(55, 55, 55)
                                        .addComponent(cbLock, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(49, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(takeable)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textDrinkable, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(rbDrinkable, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(drinkable, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                    .addComponent(sliderDrinkable, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textEatable)
                    .addComponent(sliderEatable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbEatable, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                    .addComponent(eatable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textFireable, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sliderFireable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(fireable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(sliderProFireable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proFireable))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(textThrowable)
                    .addComponent(sliderThrowable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(throwable))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel4)
                    .addComponent(sliderProThrowable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(proThrowable))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(rbUnLock)
                    .addComponent(rbLock)
                    .addComponent(cbLock, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lockable))
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cancel)
                    .addComponent(ok))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sliderDrinkableAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_sliderDrinkableAncestorMoved
        int value = sliderDrinkable.getValue();
        textDrinkable.setText(String.valueOf(value));
    }//GEN-LAST:event_sliderDrinkableAncestorMoved

    private void sliderDrinkableStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderDrinkableStateChanged
        int value = sliderDrinkable.getValue();
        textDrinkable.setText(String.valueOf(value));
    }//GEN-LAST:event_sliderDrinkableStateChanged

    private void sliderEatableAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_sliderEatableAncestorMoved
        int value = sliderEatable.getValue();
        textEatable.setText(String.valueOf(value));
    }//GEN-LAST:event_sliderEatableAncestorMoved

    private void sliderEatableStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderEatableStateChanged
        int value = sliderEatable.getValue();
        textEatable.setText(String.valueOf(value));
    }//GEN-LAST:event_sliderEatableStateChanged

    private void sliderFireableAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_sliderFireableAncestorMoved
        int value = sliderFireable.getValue();
        textFireable.setText(String.valueOf(value));
    }//GEN-LAST:event_sliderFireableAncestorMoved

    private void sliderFireableStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderFireableStateChanged
        int value = sliderFireable.getValue();
        textFireable.setText(String.valueOf(value));
    }//GEN-LAST:event_sliderFireableStateChanged

    private void sliderThrowableAncestorMoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_sliderThrowableAncestorMoved
        int value = sliderThrowable.getValue();
        textThrowable.setText(String.valueOf(value));
    }//GEN-LAST:event_sliderThrowableAncestorMoved

    private void sliderThrowableStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sliderThrowableStateChanged
        int value = sliderThrowable.getValue();
        textThrowable.setText(String.valueOf(value));
    }//GEN-LAST:event_sliderThrowableStateChanged

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        // TODO add your handling code here:
    	if(drinkable.isSelected()){
    		int value = sliderDrinkable.getValue();
    		if(rbDrinkable.isSelected()){
    			value *= -1;
    		}
    		props.put(Property.DRINKABLE, Integer.valueOf(value));
    	}else if(eatable.isSelected()){
    		int value = sliderEatable.getValue();
    		if(rbEatable.isSelected()){
    			value *= -1;
    		}
    		props.put(Property.EATABLE, Integer.valueOf(value));
    	}else if(fireable.isSelected()){
    		props.put(Property.FIRABLE, Integer.valueOf(sliderFireable.getValue()));
    		String[] tokens = proFireable.getText().split("%");
    		double p = Double.valueOf(tokens[0]) / 100;
    		item.setProbFire(p);
    	}else if(throwable.isSelected()){
    		props.put(Property.THROWABLE, Integer.valueOf(sliderThrowable.getValue()));
    		String[] tokens = proThrowable.getText().split("%");
    		double p = Double.valueOf(tokens[0]) / 100;
    		item.setProbThrow(p);
    	}else if(takeable.isSelected()){
    		props.put(Property.TAKEABLE, null);
    	}else if(lockable.isSelected()){
    		Object value = (Boolean)false;
    		if(rbLock.isSelected()){
    			value = (Boolean)true;
    		}
    		props.put(Property.LOCKABLE, value);
    		Vector<Item> keys = new Vector<Item>();
    		for(Item foo : items){
    			if(foo.isKey()){
    				keys.add(foo);
    			}
    		}
    		int idx = cbLock.getSelectedIndex() - 1;
    		if(idx >= 0)
    			item.setKeyNeed(keys.elementAt(idx));
    	}
    	retValue = true;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_okActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
    	retValue = false;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelActionPerformed

    private void sliderProFireableAncestorMoved(javax.swing.event.AncestorEvent evt) {

        int value = sliderProFireable.getValue();
        proFireable.setText(String.valueOf(value) + "%");

    }

    private void sliderProFireableStateChanged(javax.swing.event.ChangeEvent evt) {
        
        int value = sliderProFireable.getValue();
        proFireable.setText(String.valueOf(value) + "%");

    }

    private void sliderProThrowableAncestorMoved(javax.swing.event.AncestorEvent evt) {

    	int value = sliderProThrowable.getValue();
        proThrowable.setText(String.valueOf(value) + "%");

    }

    private void sliderProThrowableStateChanged(javax.swing.event.ChangeEvent evt) {

    	int value = sliderProThrowable.getValue();
        proThrowable.setText(String.valueOf(value) + "%");

    }

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//            }
//        });
    	
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
    	item.setProbThrow(0.6);
    	
    	Vector<Item> items = new Vector<Item>();
    	items.add(item);
    	items.add(new Item("Apple"));
    	Item foo = new Item("Key2");
    	foo.setKey(true);
    	items.add(foo);
    	items.add(carried);

    	ItemPropertyDialog dialog = new ItemPropertyDialog();
    	dialog.setItem(item);
    	dialog.setItems(items);
    	dialog.setEditable(true);
    	dialog.initComponents();
    	dialog.setVisible(true);
    	
    	Item out = dialog.getItem();
    	System.out.println(out.getPropValue(Property.DRINKABLE));
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

	// Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgLock;
    private javax.swing.JButton cancel;
    private javax.swing.JComboBox cbLock;
    private javax.swing.JCheckBox drinkable;
    private javax.swing.JCheckBox eatable;
    private javax.swing.JCheckBox fireable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JCheckBox lockable;
    private javax.swing.JButton ok;
    private javax.swing.JLabel proFireable;
    private javax.swing.JLabel proThrowable;
    private javax.swing.JRadioButton rbDrinkable;
    private javax.swing.JRadioButton rbEatable;
    private javax.swing.JRadioButton rbLock;
    private javax.swing.JRadioButton rbUnLock;
    private javax.swing.JSlider sliderDrinkable;
    private javax.swing.JSlider sliderEatable;
    private javax.swing.JSlider sliderFireable;
    private javax.swing.JSlider sliderProFireable;
    private javax.swing.JSlider sliderProThrowable;
    private javax.swing.JSlider sliderThrowable;
    private javax.swing.JCheckBox takeable;
    private javax.swing.JLabel textDrinkable;
    private javax.swing.JLabel textEatable;
    private javax.swing.JLabel textFireable;
    private javax.swing.JLabel textThrowable;
    private javax.swing.JCheckBox throwable;
    // End of variables declaration//GEN-END:variables

}
