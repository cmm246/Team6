package Build3;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for Build3");
		//$JUnit-BEGIN$
		suite.addTestSuite(TestFollowOperation.class);
		suite.addTestSuite(TestLockOperation.class);
		suite.addTestSuite(TestMagicWordOperation.class);
		suite.addTestSuite(TestEatOperation.class);
		suite.addTestSuite(TestDrinkOperation.class);
		suite.addTestSuite(TestAttackOperation.class);
		//$JUnit-END$
		return suite;
	}

}
