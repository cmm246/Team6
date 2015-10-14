package map.Conditions;

import map.Model.Player;

/**this class extends condition class.
 * this class defines whether the specific condition for one magic word is satisfied or not 
 * @author yu_pei
 * @version 1.2
 * */
public class SpecificMagicWord extends Conditions{

	String word;
	/**constructor for SpecificMagicWord class
	 * @param w String
	 * */
	public SpecificMagicWord(String w){
		super();
		word = w;
	}
	
	@Override
	/**this function defines whether the specific condition for one magic word is satisfied or not 
	 * @param arg String
	 * @return boolean
	 * */
	public boolean checkCondition(Object arg) {
		if(arg instanceof Player){
			Player player = (Player) arg;
			String in = (String) player.getMagicWord();
			if(word.equals(in))
				return true;
		}
		return false;
	}
    /**return magic word
     * @return word String
     * */
	public String getWord() {
		return word;
	}
    /**set magic word
     * @param word String
     * */
	public void setWord(String word) {
		this.word = word;
	}

}
