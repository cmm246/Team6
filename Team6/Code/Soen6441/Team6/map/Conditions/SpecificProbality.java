package map.Conditions;

import java.util.Random;

/**this class extends condition class.
 * this class defines whether the specific condition for probability is satisfied or not 
 * @author yu_pei
 * @version 1.6
 * */
public class SpecificProbality extends Conditions{

	double probality;
	/**constructor of SpecificProbality class
	 * @param p Float
	 * */
	public SpecificProbality(double p){
		super();
		probality = p;
	}

	@Override
	/**this function defines whether the specific condition for one magic word is satisfied or not
	 * @param arg Object getting from game designer
	 * @return boolean  
	 * */
	public boolean checkCondition(Object arg) {

		Random ra=new Random();
		if(probality>ra.nextDouble()){
			return true;
		}
		return false;
	}
    /**return Probability
     * @return probality double
     * */
	public double getProbality() {
		return probality;
	}
    /**set Probability
     * @param probality double
     * */
	public void setProbality(double probality) {
		this.probality = probality;
	}

}
