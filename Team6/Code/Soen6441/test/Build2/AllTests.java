package Build2;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test Suite for Build 2.
 * 
 * @author mak_96
 *
 */
public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test Suite for Build2");
		//$JUnit-BEGIN$
		// Unit Test for Player class
		suite.addTestSuite(PlayerTest.class);
		// Unit Test for Item class
		suite.addTestSuite(TestItem.class);
		// Unit Test for Operation
		suite.addTestSuite(TestOperation.class);
		// Unit Test for PlayerHandler
		suite.addTestSuite(TestPlayerHandler.class);
		
		// Unit Test for GUI
		suite.addTestSuite(TestItemInfoDialog.class);
		suite.addTestSuite(TestRoomInformation.class);
		//$JUnit-END$
		return suite;
	}

}
