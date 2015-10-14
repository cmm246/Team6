package map.Controller.Action;

import java.util.Observable;
import java.util.Observer;

import map.Model.*;

/**
 * Responsible for update the state of Class Item
 * @author Yan Wang
 * @version 1.5
 */
public class ItemHandler {

	public String err = "";

	/**
	 * make operation for Item
	 * @param param Operation
	 * @return true: action succeed; false: otherwise
	 */
	public boolean update (Operation param)
    {
    	switch(param.act.ordinal()){
    		case 23:
    		{
    			//lock
    			Item item = param.obj;
    			if(!item.chkCondition(param.player)){
    				err = "Need a key!";
    				return false;
    			}
    			if(item.getKeyNeed() != null){
    				for(Item foo: param.player.getInventory()){
    					if(foo.getId() == item.getKeyNeed().getId()){
    	        			item.lock();
    	        			return true;
    					}
    				}
    				err = "Need a key!";
    				return false;
    			}
    			item.lock();
    			break;
    		}
    		case 24:
    		{
    			//unlock
    			Item item = param.obj;
    			if(!item.chkCondition(param.player)){
    				err = "Need a key!";
    				return false;
    			}
    			if(item.getKeyNeed() != null){
    				for(Item foo: param.player.getInventory()){
    					if(foo.getId() == item.getKeyNeed().getId()){
    	        			item.unlock();
    	        			return true;
    					}
    				}
    				err = "Need a key!";
    				return false;
    			}
    			item.unlock();
    			break;
    		}
			default:
			{
				err = "No such a Verb!";
				return false;
			}
    	}//end switch
    	return true;
    }
}
