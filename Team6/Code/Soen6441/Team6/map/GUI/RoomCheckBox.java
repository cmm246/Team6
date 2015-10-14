package map.GUI;
import java.awt.GridLayout;

import javax.swing.*;

/**
 *RoomCheckBox is used to check wether the room has objects or not
 *@author Pei Yu
 *@version 1.0
 * */
public class RoomCheckBox extends JFrame {
		
		  public RoomCheckBox(){
		    
			setTitle("Check Room Objects");
			setSize(300, 300);
		    setLayout(new GridLayout(2,1));
		    JCheckBox chk1 = new JCheckBox("This Room has objects ");
		    add(chk1);
		    JCheckBox chk2 = new JCheckBox("This Room does not has any objects");
		    add(chk2);
		    	  }
		public static void main(String[] args){
			RoomCheckBox frame=new RoomCheckBox();
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setVisible(true);} 

	}


