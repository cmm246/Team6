package mapManagement;
/*
 * @author: Hong Xian Gao
 * 
 */
import junit.framework.*;

import map.Controller.Frame;

public class TestFrame extends TestCase {
	public void testFrameLoadMapException()
	{
		try{
			Frame frame = new Frame();
			frame.loadMap("map5.dat");
			fail("Map file name is wrong!");
		}
		catch (Exception success){}
	}

	public void testFrameSaveMapException()
	{
		try {
			Frame frame = new Frame();
			frame.saveMap("map1.dat");
			fail("Uncorrect map file name! ");
		}
		catch(Exception success){}
		
	}
	
}
