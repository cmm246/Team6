package map.GUI;

import javax.swing.*;

import map.Model.*;
import java.awt.*;
import java.awt.event.ActionEvent;
/**
 * This class provides graphic user interface.
 * Game developer can use this interface to modify informations of the selected room or the selected connection.
 * 
 * @author Pei Yu
 * @version 2.0
 * */
public class RoomInfoDialog extends JDialog {

	
	private static final long serialVersionUID = 1L;
	public JLabel roomID,roomName,roomDes,conList;
	public JTextField rID;
	public JTextField rName;
	public JTextArea rDes;
	public JList consList;
	public JPanel jpanl;
	public JCheckBox chk;
	
	public Room room;
	public boolean isOk;
	
	boolean editable = true;
	
	/**
	 *  Partial constructor for a RoomInfoDialog
	 * @param r  r is the reference to a room  
	 * @param isEdit isEdit is boolean value 
	 */
	public RoomInfoDialog (Map worldMap, Room r, boolean isEdit){
		
		isOk = false;
		editable = isEdit;
		
		room = r;
		setModal(true);
		setTitle("Room Information");
		
		setSize(400,500);
		setResizable(true);
		
		
		jpanl=new JPanel();
		getContentPane().add(jpanl);
		
		GridBagConstraints c = new GridBagConstraints();
		GridBagLayout g = new GridBagLayout();
		jpanl.setLayout(g); 

		
		roomID=new JLabel("RoomID");
		c.gridx=0;   
		// Specifies the cell containing the leading edge of the component's display area, where the first cell in a row has gridx=0.
		c.gridy=0;  
		// Specifies the cell at the top of the component's display area, where the topmost cell has gridy=0.
		c.gridheight=1; 
		// Specifies the number of cells in a row for the component's display area.
		c.gridwidth=1;
		  // Specifies the number of cells in a column for the component's display area.
		 
		g.setConstraints(roomID,c);
	     jpanl.add(roomID);
		
		/*add JTextField
		 * */
		rID=new JTextField(10);
		c.gridx=1;
		c.gridy=0;
		c.gridheight=1;
		c.gridwidth=1;
	    rID.setText(Integer.toString(r.getRoomID()));
		g.setConstraints(rID,c);
		rID.setEditable(false);
		jpanl.add(rID);
		
		/*add JLabel
		 * */
		roomName =new JLabel ("Room Name");
	
		c.gridx=0;
		c.gridy=1;
		c.gridheight=1;
		c.gridwidth=1;
		g.setConstraints(roomName,c);
		jpanl.add(roomName);
		
		rName=new JTextField(10);
		c.gridx=1;
		c.gridy=1;
		c.gridheight=1;
		c.gridwidth=1;
		rName.setText(r.getName());
		g.setConstraints(rName,c);
		rName.setEditable(editable);
		jpanl.add(rName);

		roomDes =new JLabel ("Description");
		
		c.gridx=0;
		c.gridy=2;
		c.gridheight=1;
		c.gridwidth=1;
		g.setConstraints(roomDes,c);
		jpanl.add(roomDes);

		rDes=new JTextArea(10,20);
		rDes.setLineWrap(true);
		rDes.setWrapStyleWord(true);
		rDes.setText(r.getDescription());
		rDes.setEditable(editable);
		
		JScrollPane jscrlp=new JScrollPane(rDes);
		
		jscrlp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		jscrlp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
		c.gridx=1;
        c.gridy=2;
        c.gridwidth=2;
        c.gridheight=5;
        
        g.setConstraints(jscrlp,c);
		jpanl.add(jscrlp);
	
			if(!isEdit){
			conList=new JLabel("Connection List");
			
			c.gridx=0;
			c.gridy=9;
			c.gridheight=1;
			c.gridwidth=1;
			g.setConstraints(conList,c);
			jpanl.add(conList);

//			JTextArea  conText = new JTextArea();
//			conText.setLineWrap(true);
//			conText.setWrapStyleWord(true);
//			String text = "Direction\tRoom\n";
//			text += "--------------------------------\n";
//			if(r.getConnection() != null){
//				for(Connection foo : r.getConnection()){
//					text += foo.getDirection().toString();
//					text += "\t";
//					text += foo.getConnectsTo().getDescription();
//					text += "\n";
//				}
//			}
//			conText.setText(text);
//			conText.setEditable(editable);
//			JScrollPane js=new JScrollPane(conText);
//			
//			js.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
//			js.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//			
//			c.fill=GridBagConstraints.BOTH;
//			c.gridx=0;
//			c.gridy=11;
//			c.gridheight=4;
//			c.gridwidth=2;
//			
//			
//			g.setConstraints(js,c);
//			jpanl.add(js);
		}
//		/*add JCheckBox
//		 * */
//		chk = new JCheckBox("Has Objects");
//		chk.setSelected(r.isHasObj());
//		chk.setEnabled(isEdit);
//		c.gridx=0;
//		c.gridy=15;
//		c.gridheight=1;
//		c.gridwidth=1;
//		g.setConstraints(chk,c);
//		jpanl.add(chk);
		
		Action ok = new AbstractAction("Ok") {
			static final long serialVersionUID=8762419586713147L;
			public void actionPerformed(ActionEvent e) {
				  room.setName(rName.getText());
				  room.setDescription(rDes.getText());
				  //room.setHasObj(chk.isSelected());
				  setVisible(false); 
				  dispose(); 
				  isOk = true;
			}
		};
		/*add JButton
		 * */
		JButton bok=new JButton(ok);
		c.gridx=0;
		c.gridy=20;
		c.gridheight=1;
		c.gridwidth=1;
		g.setConstraints(bok,c);
		jpanl.add(bok);
		
		
		Action cancel = new AbstractAction("Cancel") {
			static final long serialVersionUID=8762419586713147L;
			public void actionPerformed(ActionEvent e) {
				  setVisible(false); 
				  dispose();
			}
		};
		/*add JButton
		 * */
		JButton bccl=new JButton(cancel);
		c.gridx=1;
		c.gridy=20;
		c.gridheight=1;
		c.gridwidth=1;
		g.setConstraints(bccl,c);
		jpanl.add(bccl,c);
			
		
	}	

}
