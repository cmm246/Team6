package map.GUI.Dialog;

import java.util.Vector;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import map.Model.*;
/**
 * GTableModel extends AbstractTableModel.
 * Responsible for serving TableModel for JTable.
 * @author Yan Wang
 * @version 1.3
 */
public class GTableModel extends AbstractTableModel {
	
    protected String[] columnNames;
    protected Vector<DateSet> dataVector;

	Vector<Object> cells;
    Vector<Item> items;
    Vector<Room> rooms;
    boolean editable;

	public static enum ColName{
    	NAME,
    	EDIT,
    	DELETE
    }
	
    private class DateSet{
    	
    	String name;
		JButton edit;
		JButton del;
		
		public JButton getDel() {
			return del;
		}

		public void setDel(JButton del) {
			this.del = del;
		}

		public JButton getEdit() {
			return edit;
		}

		public void setEdit(JButton edit) {
			this.edit = edit;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public DateSet(String name, JButton edit, JButton delete) {
			super();
			this.name = name;
			this.edit = edit;
			del = delete;
		}
		
    }

    /**
     * Constructor
     * @param columnNames
     * @param items : Item Vector
     */
    public GTableModel(String[] columnNames, Vector cells) {
        this.columnNames = columnNames;
        this.cells = cells;
        dataVector = new Vector<DateSet>();
    }

    /**
     * Get columnName for specific column
     * @param column
     */
    public String getColumnName(int column) {
        return columnNames[column];
    }

    /**
     * Check whether to edit for specific cell
     * @param row
     * @param column 
     */
    public boolean isCellEditable(int row, int column) {
       	return false;
    }

    /**
     * Get Class type for specific column
     * @param column
     */
   public Class getColumnClass(int column) {
        switch (column) {
            case 0:
            	return String.class;
            case 1:
            	return JButton.class;
            case 2:
            	return JButton.class;
            default:
               return Object.class;
        }
    }

   /**
    * Get value at specific cell
    * @param row
    * @param column
    * @return Object : value of cell
    */
    public Object getValueAt(int row, int column) {
    	DateSet record = (DateSet)dataVector.get(row);
        switch (column) {
            case 0:
               return record.getName();
            case 1:
                return record.getEdit();
            case 2:
               return record.getDel();
            default:
               return new Object();
        }
    }

    /**
     * Set value at specific cell
     * @param value : value of cell
     * @param row
     * @param column
     */
    public void setValueAt(Object value, int row, int column) {
    	DateSet record = (DateSet)dataVector.get(row);
        switch (column) {
            case 0:
            {
            	record.setName((String)value);
            }
            break;
            case 1:
            {
            	record.setEdit((JButton)value);
            }
            break;
            case 2:
            {
            	record.setDel((JButton)value);
            }
            break;
            default:
               System.out.println("invalid index");
        }
        fireTableCellUpdated(row, column);
    }

    /**
     * Get number of rows
     * @return number of rows
     */
    public int getRowCount() {
        return dataVector.size();
    }

    /**
     * Get number of columns
     * @return number of columns
     */
    public int getColumnCount() {
        return columnNames.length;
    }
    
    /**
     * Check whether to has empty row
     * @return true: has; otherwise has not
     */
    public boolean hasEmptyRow() {
        if (dataVector.size() == 0)
        	return false;
        DateSet Record = (DateSet)dataVector.get(dataVector.size() - 1);
        if (Record.getName().equals(""))
        {
           return true;
        }
        else return false;
    }

    /**
     * Add a empty row
     */
    public void addEmptyRow() {
    	JButton button1, button2;
    	if(editable){
    		button1 = new JButton("EDIT");
    	}
    	else{
    		button1 = new JButton("DETAIL");
    	}
    	button2 = new JButton("DELETE");
    	button2.setEnabled(editable);
		dataVector.add(new DateSet("", button1, button2));
        fireTableRowsInserted
        (
        	dataVector.size() - 1,
        	dataVector.size() - 1
        );
    }

    public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public Vector<Item> getItems() {
		return items;
	}

	public void setItems(Vector<Item> items) {
		this.items = items;
	}

	public Vector<Room> getRooms() {
		return rooms;
	}

	public void setRooms(Vector<Room> rooms) {
		this.rooms = rooms;
	}

	public Vector<Object> getCells() {
		return cells;
	}

	public void setCells(Vector<Object> cells) {
		this.cells = cells;
	}

	/**
	 * @return the dataVector
	 */
	public Vector<DateSet> getDataVector() {
		return dataVector;
	}
}

