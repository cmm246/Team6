package map.Conditions;
import map.Model.Item;
import map.Model.Player;
/** this class extends condition class.
 * this class defines whether the specific condition for one specific item is satisfied or not 
 * @author yu_pei
 * @version 1.4
 * */
public class ItemNeedCarried extends Conditions{ 
	
	Item item;
	
   /**Constructor for SpecificItem class
    * @param element Item class
    * */
   public ItemNeedCarried(Item element){
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
					return true;
				}
			}
		}
		return false;

	}
    /**set Item value
     * @param item Item Class
     * 
     * */
	public void setItem(Item item) {
		this.item = item;
	}
   /**return Item
    * @return item
    * */
	public Item getItem() {
		return item;
	}

}
