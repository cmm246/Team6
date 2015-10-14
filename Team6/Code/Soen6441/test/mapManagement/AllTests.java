package mapManagement;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for mapManagement");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestMap.class);
		suite.addTestSuite(RoomTest.class);
		suite.addTestSuite(ConnectionTest.class);
		suite.addTestSuite(MapTest.class);
		//$JUnit-END$
		return suite;
	}

}
