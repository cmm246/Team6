package map.Conditions;

import java.io.Serializable;

/**this is an abstract class to define some conditions.
 * @author yu_pei
 * @version 1.8
 * */
public class Conditions implements Serializable{
	
	static int nextId = 1;
	int conditionId;
	
	/**Constructor for Condition Class
	 * */
	public Conditions(){
		conditionId = nextId;
		nextId++;
	}
	
	/**this is a function to define whether the specific condition is satisfied or not.
	 * @param arg Object getting from game desinger
	 * @return boolean
	 * */
	public boolean checkCondition(Object arg){return true;};
	/**Return ConditionID
	 * @return conditionId
	 * */
	public int getConditionId() {
		return conditionId;
	}
}
