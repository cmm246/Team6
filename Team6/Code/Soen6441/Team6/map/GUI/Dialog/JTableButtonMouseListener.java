package map.GUI.Dialog;


import javax.swing.*;
import javax.swing.table.*;
import java.awt.event.*;
import java.util.Map.Entry;

import map.Conditions.Conditions;
import map.Conditions.ItemNeedCarried;
import map.Conditions.ItemNotNeedCarried;
import map.Conditions.RoomVisited;
import map.Conditions.SpecificMagicWord;
import map.Conditions.SpecificProbality;
import map.GUI.*;
import map.Model.*;
import map.Model.Item.Property;

/**
 * JTableButtonMouseListener extends MouseListener.
 * Responsible for serving as a MouseListener.
 * @author Yan Wang
 * @version 1.1
 */
public class JTableButtonMouseListener implements MouseListener {
	private JTable __table;

	  private void __forwardEventToButton(MouseEvent e) {
	    TableColumnModel columnModel = __table.getColumnModel();
	    int column = columnModel.getColumnIndexAtX(e.getX());
	    int row    = e.getY() / __table.getRowHeight();
	    Object value;
	    JButton button;
	    MouseEvent buttonEvent;

	    if(row >= __table.getRowCount() || row < 0 ||
	       column >= __table.getColumnCount() || column < 0)
	      return;

	    value = __table.getValueAt(row, column);

	    if(!(value instanceof JButton))
	      return;

	    
	    button = (JButton)value;

	    buttonEvent =
	      (MouseEvent)SwingUtilities.convertMouseEvent(__table, e, button);
	    if(button.isEnabled()){
		    TableModel model = __table.getModel(); 
		    if(buttonEvent.getID() == MouseEvent.MOUSE_CLICKED
		    		&& buttonEvent.getModifiers() == MouseEvent.BUTTON1_MASK){
			    //TODO Edit/Detail/Delete
			    if(button.getText().compareTo("EDIT") == 0){
				    if(model instanceof GTableModel){
				    	GTableModel tableModel = (GTableModel)model;
				    	Object obj = tableModel.cells.get(row);
				    	if(obj instanceof Item){
				    		Item item = new Item((Item)obj);
			            	ItemDialog dialog = new ItemDialog();
			            	dialog.setItem(item);
				        	dialog.setItems(tableModel.getItems());
				        	dialog.setRooms(tableModel.getRooms());
			            	dialog.setEditable(true);
			            	
			            	dialog.initComponents();
			            	dialog.setVisible(true);
			            	if(dialog.isRetValue()){
				        		tableModel.cells.set(row, dialog.getItem());
				        		tableModel.setValueAt(dialog.getItem().getName(), row, 0);
			            	}
				    	}else if(obj instanceof Connection){
				    		Connection connection = (Connection)obj;
			            	ConnectionDialog dialog = new ConnectionDialog();
			            	dialog.setCon(connection);
				        	dialog.setItems(tableModel.getItems());
				        	dialog.setRooms(tableModel.getRooms());
			            	dialog.setEditable(true);
			            	
			            	dialog.initComponents();
			            	dialog.setVisible(true);
			            	if(dialog.isRetValue()){
				        		tableModel.cells.set(row, dialog.getCon());
				        		tableModel.setValueAt(dialog.getCon().getDirection().name(), row, 0);
			            	}
				    	}else if(obj instanceof Conditions){
				    		Conditions con = (Conditions) obj;
				        	ConditionDialog dialog = new ConditionDialog();
				        	dialog.setCondition(con);
				        	dialog.setItems(tableModel.getItems());
				        	dialog.setRooms(tableModel.getRooms());
				        	dialog.setEditable(true);
				        	
				        	dialog.initComponents();
				        	dialog.setVisible(true);
				        	
				        	if(dialog.isRetValue()){
				        		tableModel.cells.set(row, dialog.getCondition());
				        	}
				    	}else if(obj instanceof map.Model.Character){
				    		
				    		//TODO edit character
				    		map.Model.Character ch = (map.Model.Character) obj;
				    		NPCDialog dialog = new NPCDialog(ch);
				    		dialog.setRooms(tableModel.getRooms());
				    		dialog.initComponents();
				    		dialog.setVisible(true);
				    		if(dialog.getReturnStatus() == NPCInfoDialog.RET_OK){
				        		tableModel.cells.set(row, ch);
				    		}
				    	}
				    }
			    }else if(button.getText().compareTo("DETAIL") == 0){
				    if(model instanceof GTableModel){
				    	GTableModel tableModel = (GTableModel)model;
				    	Object obj = tableModel.cells.get(row);
				    	if(obj instanceof Item){
				    		Item item = (Item)obj;
			            	ItemDialog dialog = new ItemDialog();
			            	dialog.setItem(item);
				        	dialog.setItems(tableModel.getItems());
				        	dialog.setRooms(tableModel.getRooms());
			            	dialog.setEditable(false);
			            	
			            	dialog.initComponents();
			            	dialog.setVisible(true);
				    	}else if(obj instanceof Connection){
				    		Connection connection = (Connection)obj;
			            	ConnectionDialog dialog = new ConnectionDialog();
			            	dialog.setCon(connection);
				        	dialog.setItems(tableModel.getItems());
				        	dialog.setRooms(tableModel.getRooms());
			            	dialog.setEditable(false);
			            	
			            	dialog.initComponents();
			            	dialog.setVisible(true);
				    	}else if(obj instanceof Conditions){
				    		Conditions con = (Conditions)obj;
				        	ConditionDialog dialog = new ConditionDialog();
				        	dialog.setCondition(con);
				        	dialog.setItems(tableModel.getItems());
				        	dialog.setRooms(tableModel.getRooms());
				        	dialog.setEditable(false);
				        	
				        	dialog.initComponents();
				        	dialog.setVisible(true);
				    	}else if(obj instanceof map.Model.Character){
				    		map.Model.Character ch = (map.Model.Character) obj;
				    		NPCInfoDialog dialog = new NPCInfoDialog(ch);
				    		dialog.setVisible(true);
				    	}
				    }
			    }else if(button.getText().compareTo("DELETE") == 0){
				    if(model instanceof GTableModel){
				    	((GTableModel) model).dataVector.remove(row);
				    	((GTableModel) model).getCells().remove(row) ;
				    }
			    }
		    	
		    }
	    }
	    button.dispatchEvent(buttonEvent);
	    // This is necessary so that when a button is pressed and released
	    // it gets rendered properly.  Otherwise, the button may still appear
	    // pressed down when it has been released.
	    __table.repaint();
	  }

	  public JTableButtonMouseListener(JTable table) {
	    __table = table;
	  }

	  public void mouseClicked(MouseEvent e) {
	    __forwardEventToButton(e);
	  }

	  public void mouseEntered(MouseEvent e) {
	    __forwardEventToButton(e);
	  }

	  public void mouseExited(MouseEvent e) {
	    __forwardEventToButton(e);
	  }

	  public void mousePressed(MouseEvent e) {
	    __forwardEventToButton(e);
	  }

	  public void mouseReleased(MouseEvent e) {
	    __forwardEventToButton(e);
	  }
}

