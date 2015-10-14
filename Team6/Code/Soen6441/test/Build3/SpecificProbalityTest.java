package Build3;

import junit.framework.TestCase;

import org.junit.Assert;

import map.Conditions.SpecificProbality;


public class SpecificProbalityTest extends TestCase{
	
	public void testcheckCondition(){
		double pro=1.0;
		double pro1=0.0;
		Object arg=null;
		SpecificProbality sp=new SpecificProbality(pro);
		SpecificProbality spc=new SpecificProbality(pro1);
		Assert.assertTrue(sp.checkCondition(arg));
		Assert.assertFalse(spc.checkCondition(arg));
	}

}
