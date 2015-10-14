package map.Conditions;
import map.Model.Item;
import map.Model.Player;
/** this class extends condition class.
 * this class defines whether the specific condition for one specific item is satisfied or not 
 * @author yu_pei
 * @version 1.4
 * */
public class ItemNotNeedCarried extends Conditions{ 
	
	Item item;
   /**Constructor for SpecificItem class
    * @param element Item class
    * */
   public ItemNotNeedCarried(Item element){
	   super();
	   item = element;
   }
  
	@Override
	/**this function defines whether the specific condition for one specific item is satisfied or not 
	 * @param arg Object class
	 * @return boolean
	 * */
	public boolean checkCondition(Object arg) {

		if(arg instanceof Player){
			Player player = (Player) arg;
			for(Item i : player.getInventory())
			{
				if(i.getName().equals(item.getName())){
					return false;
				}
			}
		}
		return true;

	}
	/**return Item
	    * @return item
	    * */
	public Item getItem() {
		return item;
	}
	/**set Item value
     * @param item Item Class
     * 
     * */
	public void setItem(Item item) {
		this.item = item;
	}

}
