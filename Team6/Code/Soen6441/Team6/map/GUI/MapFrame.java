package map.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileFilter;
import javax.swing.WindowConstants;

import map.Controller.*;

/********************************************************************************
 * Responsible for class MapFrame
 * MapFrame servers as a GUI Frame.
 * * @author Yan Wang
 * @version 1.0
 ********************************************************************************/
public class MapFrame extends JFrame {

    static final int width = 900;
    static final int height=700;							//Window's dimension   
    
    public static JFileChooser fc;
    
    /**
	 * Default Constructor 
	 */
	public MapFrame() {
		
		setTitle("Map Editor");		
		setSize(new Dimension(width, height));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//set closing behaviour

		getContentPane().setSize(new Dimension(width, height));	//Set frame's size and background
		getContentPane().setBackground(Color.green.darker().darker());
		
		
		//choose file
		fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);					//initialize the file chooser
		
		class MyFileFilter extends FileFilter{
			/**
			 * @Override
			 */
			public boolean accept(File f) {
				if (f.isDirectory()) return true;
				
		        String ext = null;
		        String s = f.getName();
		        int i = s.lastIndexOf('.');

		        if (i > 0 &&  i < s.length() - 1) {
		            ext = s.substring(i+1).toLowerCase();
		        }
		        
			    if(ext!=null&&ext.equals("dat")) return true;
			    else return false;
			}

			/**
			 * @Override
			 */
			public String getDescription() {
				return "Adventure Game Save Files";
			}
		}
		fc.addChoosableFileFilter(new MyFileFilter());
			
		PopMenu menu = new PopMenu(this);
		menu.initializeMenuBar();
		menu.initializeToolbar();
	}
	
	

    /**
	 * @param args
	 */
	public static void main(String[] args) {
		
		MapFrame frame = new MapFrame();
		frame.setVisible(true);
	}

}
