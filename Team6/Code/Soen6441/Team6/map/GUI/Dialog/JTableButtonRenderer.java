package map.GUI.Dialog;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.TableCellRenderer;

/**
 * JTableButtonRenderer extends TableCellRenderer.
 * Responsible for serving as a TableCellRenderer.
 * @author Yan Wang
 * @version 1.1
 */
public class JTableButtonRenderer implements TableCellRenderer {
  private TableCellRenderer __defaultRenderer;

  public JTableButtonRenderer(TableCellRenderer renderer) {
    
	  __defaultRenderer = renderer;
	  
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
		  	boolean isSelected, boolean hasFocus, int row, int column)
  {
    
	  if(value instanceof Component)
		  return (Component)value;
    
	  return __defaultRenderer.getTableCellRendererComponent
    		(
    			table, value, isSelected, hasFocus, row, column
    		);
  }
}

