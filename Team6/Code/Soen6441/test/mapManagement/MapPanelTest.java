package mapManagement;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.WindowConstants;

import map.Controller.MapPanel;
import map.Controller.PopMenu;

import org.junit.Test;
import junit.framework.TestCase;

public class MapPanelTest extends TestCase{

	@Test
	public void testMapPanel() {
	    final int width = 900;
	    final int height=700;							//Window's dimension   
		map.GUI.MapFrame owner = new map.GUI.MapFrame();
		owner.setTitle("Adventure Game");		
		owner.setSize(new Dimension(width, height));
		owner.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//set closing behaviour

		owner.getContentPane().setSize(new Dimension(width, height));	//Set frame's size and background
		owner.getContentPane().setBackground(Color.green.darker().darker());
		
		
			
		PopMenu menu = new PopMenu(owner);
		menu.initializeMenuBar();
		menu.initializeToolbar();
		owner.setVisible(true);
		MapPanel panel = new MapPanel(owner, null);
		owner.getContentPane().add(panel, BorderLayout.CENTER);
		owner.validate();											
		owner.repaint(); 
	}

}
