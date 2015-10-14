package map.Controller;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Event;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import map.GUI.*;

/********************************************************************************
 * Responsible for class PopMenu
 * which servers as a Menu for a GUI Frame.
 * @author Yan Wang
 * @version 1.0
 ********************************************************************************/
public class PopMenu implements PopupMenuListener {
	
	MapFrame owner;

	static JMenuItem menuMapSave = new JMenuItem("Save");	
	MapPanel panel = null;

	Action[] actions;
	public static JToolBar[] toolbars;
	public static JButton[] buttons;
	public static JButton selectedButton;
	
	public static enum BUTTONMODE
	{//All buttons' functionalities
		NONE,		
		ADD_ROOM,
		ADD_CONNECTION,
		REMOVE,
		ADD_ITEM,
		EDIT_ROOM,
		ADD_PLAYER,
		ADD_CHARACTER,
		NUMBER_OF_TOOLBARS
	}
	
	public static java.util.Map<Integer, String> buttonMap;
	
	public static enum ToolbarCode {									//All toolbars functionalities			
		MAIN,
		EDIT,
		NUMBER_OF_TOOLBARS
	}	

	/**
	 * Constructor
	 * @param mf
	 * 		@see map.GUI.MapFrame 
	 */
	public PopMenu(map.GUI.MapFrame mf){
		
		owner = mf;
		buttonMap = new java.util.HashMap<Integer, String>();
		buttonMap.put(BUTTONMODE.NONE.ordinal(), "Infomation");
		buttonMap.put(BUTTONMODE.ADD_ROOM.ordinal(), "Add Room");
		buttonMap.put(BUTTONMODE.ADD_CONNECTION.ordinal(), "Add Connection");
		buttonMap.put(BUTTONMODE.REMOVE.ordinal(), "Remove");

		buttonMap.put(BUTTONMODE.ADD_ITEM.ordinal(), "Add Item");
		buttonMap.put(BUTTONMODE.EDIT_ROOM.ordinal(), "Edit Room");
		buttonMap.put(BUTTONMODE.ADD_PLAYER.ordinal(), "Add Player");
		buttonMap.put(BUTTONMODE.ADD_CHARACTER.ordinal(), "Add Character");
		
		panel = new MapPanel(owner, null);
	}

	/**
	  * This method is used to create toolbar,add buttons to this toolbar
	  * update buttons status, and create all actions
	  */
	public void initializeToolbar() {
      
		toolbars = new JToolBar[ToolbarCode.NUMBER_OF_TOOLBARS.ordinal()]; //create toolbar
		for(int i=0; i<ToolbarCode.NUMBER_OF_TOOLBARS.ordinal(); i++) {
			toolbars[i] = new JToolBar();
			toolbars[i].setFloatable(false);
   		}
		
		
       createActions();									//create the actions for the buttons
               
       addButtonsToToolbar();								//add all the buttons to all toolbars
       
       panel.updateButtonStatus();
       
       //Initialize edit toolbar
       toolbars[ToolbarCode.EDIT.ordinal()].setOrientation(JToolBar.HORIZONTAL);
       toolbars[ToolbarCode.EDIT.ordinal()].setMargin(new Insets(5, 5, 5, 5));
       toolbars[ToolbarCode.EDIT.ordinal()].setMaximumSize(toolbars[ToolbarCode.EDIT.ordinal()].getPreferredSize());
       
       
       toolbars[ToolbarCode.EDIT.ordinal()].setBackground(Color.green.darker().darker());

       	owner.getContentPane().add(toolbars[ToolbarCode.MAIN.ordinal()], BorderLayout.PAGE_END);
		owner.getContentPane().add(toolbars[ToolbarCode.EDIT.ordinal()], BorderLayout.PAGE_START);
		owner.validate();
		owner.repaint();
	}

	/**
	 * To create menuBar and its corresponding actions.
	 *
	 */
	public void initializeMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuFile = new JMenu("File");  
		menuFile.setMnemonic('F');
     
		JMenuItem menuExit = new JMenuItem("Exit");     
		
		JMenuItem menuNewMap = new JMenuItem("New Map");
		menuNewMap.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
             Event.CTRL_MASK));					//Set the keyboard shortcut
     
		JMenuItem menuMapLoad = new JMenuItem("Load");
		menuMapLoad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
             Event.CTRL_MASK));
     
		menuMapSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
             Event.CTRL_MASK));
     
     // Add action listeners for the menu buttons    
		menuNewMap.addActionListener
		(
			new ActionListener(){
				public void actionPerformed(ActionEvent e){
					if(panel.worldMap == null || panel.justSaved){
						createNewMap();
						panel.updateButtonStatus();
					}
					else {
						if( panel.isJustSaved()){
							createNewMap();
							panel.updateButtonStatus();
						}else if( promptForSave() ){
							createNewMap();
							panel.updateButtonStatus();
						}
					}
				}
			}
		);
     	
		menuMapSave.addActionListener
		(
			new ActionListener(){        		
	 			public void actionPerformed(ActionEvent e) {
	         		save();
	         		panel.updateButtonStatus();
	         	}
			}
		);
     
		menuMapLoad.addActionListener
		(
			new ActionListener(){          
				public void actionPerformed(ActionEvent e) {
					if(panel.worldMap == null || panel.justSaved){
						load();
						panel.updateButtonStatus();
					}
					else {
						if(panel.isJustSaved()){
							load();
							panel.updateButtonStatus();
						}else if(promptForSave()) {
							load();
							panel.updateButtonStatus();
						}
					}
				}
	 	 	}
		);
     
     
	     menuExit.addActionListener
	     (
	         new ActionListener() {
	             public void actionPerformed(ActionEvent e) {
					if(panel.worldMap == null || panel.justSaved)
	     				System.exit(0);
	     			else{
	     				if(panel.isJustSaved())
	     					System.exit(0);
	     				else if(promptForSave()){
	     					System.exit(0);
	     				}
	     			}
	             }
	         }
	     ); 
	     
	     menuFile.add(menuNewMap);		
	     menuFile.add(menuMapSave);
	     menuFile.add(menuMapLoad);
	     menuFile.addSeparator();
	     menuFile.add(menuExit);
	     
	     
	     menuBar.add(menuFile);
	     owner.setJMenuBar(menuBar);
	}

	/**
	 * Add buttons to the toolbar
	 *
	 */
    private void addButtonsToToolbar() {
    	
    	buttons = new JButton[BUTTONMODE.NUMBER_OF_TOOLBARS.ordinal()];
    	
    	buttons[BUTTONMODE.NONE.ordinal()]
    	        = toolbars[ToolbarCode.EDIT.ordinal()].add(actions[BUTTONMODE.NONE.ordinal()]);
        selectButton(buttons[BUTTONMODE.NONE.ordinal()]); 	
    												
        buttons[BUTTONMODE.ADD_ROOM.ordinal()]
                = toolbars[ToolbarCode.EDIT.ordinal()].add(actions[BUTTONMODE.ADD_ROOM.ordinal()]);
        
        buttons[BUTTONMODE.ADD_CONNECTION.ordinal()]
                = toolbars[ToolbarCode.EDIT.ordinal()].add(actions[BUTTONMODE.ADD_CONNECTION.ordinal()]);
        
        buttons[BUTTONMODE.REMOVE.ordinal()]
                = toolbars[ToolbarCode.EDIT.ordinal()].add(actions[BUTTONMODE.REMOVE.ordinal()]);

        buttons[BUTTONMODE.ADD_ITEM.ordinal()]
                = toolbars[ToolbarCode.EDIT.ordinal()].add(actions[BUTTONMODE.ADD_ITEM.ordinal()]);

        buttons[BUTTONMODE.EDIT_ROOM.ordinal()]
                = toolbars[ToolbarCode.EDIT.ordinal()].add(actions[BUTTONMODE.EDIT_ROOM.ordinal()]);

        buttons[BUTTONMODE.ADD_PLAYER.ordinal()]
                = toolbars[ToolbarCode.EDIT.ordinal()].add(actions[BUTTONMODE.ADD_PLAYER.ordinal()]);

        buttons[BUTTONMODE.ADD_CHARACTER.ordinal()]
                = toolbars[ToolbarCode.EDIT.ordinal()].add(actions[BUTTONMODE.ADD_CHARACTER.ordinal()]);
    }

    /**
	 * Create the action for all buttons
	 */	
	private void createActions() {
		//Inforamtion button's action
		actions = new Action[BUTTONMODE.NUMBER_OF_TOOLBARS.ordinal()];
	    
		actions[BUTTONMODE.NONE.ordinal()]
		        = new AbstractAction(buttonMap.get(BUTTONMODE.NONE.ordinal()))
		{
	    	static final long serialVersionUID=8762419586713147L;
        	public void actionPerformed(ActionEvent e) {
        		selectButton(buttons[BUTTONMODE.NONE.ordinal()]);
        		panel.updateButtonStatus();
        	}
        };       

        //Add room button's action
        actions[BUTTONMODE.ADD_ROOM.ordinal()]
                = new AbstractAction(buttonMap.get(BUTTONMODE.ADD_ROOM.ordinal()))
        {
        	static final long serialVersionUID=8762419586713147L;
        	public void actionPerformed(ActionEvent e) {
        		selectButton(buttons[BUTTONMODE.ADD_ROOM.ordinal()]);
        		panel.updateButtonStatus();
        		owner.validate();
        		owner.repaint();
        	}
        };
        //Add connection button's action
        actions[BUTTONMODE.ADD_CONNECTION.ordinal()]
                = new AbstractAction(buttonMap.get(BUTTONMODE.ADD_CONNECTION.ordinal()))
        {
        	static final long serialVersionUID=8762419586713147L;
        	public void actionPerformed(ActionEvent e) {
				selectButton(buttons[BUTTONMODE.ADD_CONNECTION.ordinal()]);
        		panel.updateButtonStatus();
        	}
        };

        //Remove room's action
        actions[BUTTONMODE.REMOVE.ordinal()]
                = new AbstractAction(buttonMap.get(BUTTONMODE.REMOVE.ordinal()))
        {
        	static final long serialVersionUID=8762419586713147L;
        	public void actionPerformed(ActionEvent e) {
				selectButton(buttons[BUTTONMODE.REMOVE.ordinal()]);
				panel.updateButtonStatus();
        	}
        };

        //Add item button's action
        actions[BUTTONMODE.ADD_ITEM.ordinal()]
                = new AbstractAction(buttonMap.get(BUTTONMODE.ADD_ITEM.ordinal()))
        {
        	static final long serialVersionUID=8762419586713147L;
        	public void actionPerformed(ActionEvent e) {
        		selectButton(buttons[BUTTONMODE.ADD_ITEM.ordinal()]);
        		panel.updateButtonStatus();
        		owner.validate();
        		owner.repaint();
        	}
        };

        //Edit room button's action
        actions[BUTTONMODE.EDIT_ROOM.ordinal()]
                = new AbstractAction(buttonMap.get(BUTTONMODE.EDIT_ROOM.ordinal()))
        {
        	static final long serialVersionUID=8762419586713147L;
        	public void actionPerformed(ActionEvent e) {
        		selectButton(buttons[BUTTONMODE.EDIT_ROOM.ordinal()]);
        		panel.updateButtonStatus();
        		owner.validate();
        		owner.repaint();
        	}
        };

        //Add Player button's action
        actions[BUTTONMODE.ADD_PLAYER.ordinal()]
                = new AbstractAction(buttonMap.get(BUTTONMODE.ADD_PLAYER.ordinal()))
        {
        	static final long serialVersionUID=8762419586713147L;
        	public void actionPerformed(ActionEvent e) {
        		selectButton(buttons[BUTTONMODE.ADD_PLAYER.ordinal()]);
//        		panel.editPlayer();
        		panel.updateButtonStatus();
        		owner.validate();
        		owner.repaint();
        	}
        };

        //Add Character button's action
        actions[BUTTONMODE.ADD_CHARACTER.ordinal()]
                = new AbstractAction(buttonMap.get(BUTTONMODE.ADD_CHARACTER.ordinal()))
        {
        	static final long serialVersionUID=8762419586713147L;
        	public void actionPerformed(ActionEvent e) {
        		selectButton(buttons[BUTTONMODE.ADD_CHARACTER.ordinal()]);
        		panel.updateButtonStatus();
        		owner.validate();
        		owner.repaint();
        	}
        };
	}

	/** 
	 * select button
	 * @param b The selected button
	 */
   void selectButton(JButton b) {
	   //selectedButton = b;
	   if(panel != null)
		   panel.setButton(b);
   }    

	/**
	 * Confirm the save file information
	 * @return boolean
	 */
	private boolean promptForSave() {
		switch(
			JOptionPane.showConfirmDialog
				(
					owner,
					"Do you want to save?",
					"Are you sure to exit?",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE
				)
			){
		case JOptionPane.YES_OPTION: save();
		case JOptionPane.NO_OPTION: return true;
		case JOptionPane.CANCEL_OPTION: return false;
		default: return false;
		}
	}


	/**
     * create a new map 
     *
     */
	void createNewMap() {
		
		if(panel == null)
			panel = new MapPanel(owner, null);
		else
			owner.remove(panel);
		
		panel.setupPanel();
        
		selectButton(buttons[BUTTONMODE.NONE.ordinal()]);
		owner.getContentPane().add(panel, BorderLayout.CENTER);
		owner.validate();											
		owner.repaint(); 
	}

	/**
	 * Save the current map information to a file
	 *
	 */	
	void save() {
        int ret = map.GUI.MapFrame.fc.showSaveDialog(owner);
        if (ret == JFileChooser.APPROVE_OPTION) {
            File file = map.GUI.MapFrame.fc.getSelectedFile();
            try{
            	panel.save(file);
            }catch(Exception e){
				JOptionPane.showMessageDialog(owner, "Failed to save!", "Error",
					JOptionPane.ERROR_MESSAGE);
            }
        }
	}

	/**
	 * Load and open an exist map information
	 *
	 */
	void load() {
		int ret =  map.GUI.MapFrame.fc.showOpenDialog(owner);
		
		if ( ret == JFileChooser.APPROVE_OPTION ) {

			File file = map.GUI.MapFrame.fc.getSelectedFile();			
			
			try {
				if(panel != null)
					owner.remove(panel);
				panel = new MapPanel(owner, null);
				panel.setupPanel();
				panel.load(file);

				selectButton(buttons[BUTTONMODE.NONE.ordinal()]);
				owner.getContentPane().add(panel, BorderLayout.CENTER);
				panel.updateButtonStatus();

				owner.validate();											
				owner.repaint(); 
			}
			catch(FileNotFoundException e) {
				JOptionPane.showMessageDialog(owner, "File not found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch(EOFException e) {
				JOptionPane.showMessageDialog(owner, "Corrupted or incompatible file", "Error", JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Override
	 */
	public void popupMenuCanceled(PopupMenuEvent arg0) {

		owner.repaint();
	}

	/**
	 * Override
	 */
	public void popupMenuWillBecomeInvisible(PopupMenuEvent arg0) {

		owner.repaint();
	}

	/**
	 * Override
	 */
	public void popupMenuWillBecomeVisible(PopupMenuEvent arg0) {
		// TODO Auto-generated method stub
	}

}
