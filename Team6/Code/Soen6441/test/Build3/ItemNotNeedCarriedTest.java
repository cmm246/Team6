package Build3;

import map.Conditions.ItemNeedCarried;
import map.Model.Item;
import map.Model.Player;

import org.junit.Assert;

import junit.framework.TestCase;

public class ItemNotNeedCarriedTest extends TestCase{
    
	public void testcheckCondition(){
		Player player=new Player();
		Item it=new Item();
		it.setName("item");
		player.getInventory().add(it);
		ItemNeedCarried itNeed =new ItemNeedCarried(it);
		Assert.assertTrue(itNeed.checkCondition(player));
		
		}
}
