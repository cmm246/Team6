package map.Conditions;
import map.Model.Player;
import map.Model.Room;
/**this class extends condition class.
 * this class defines whether the specific condition for one room is satisfied or not
 * @author yu_pei
 * @version 1.3
 * */
public class RoomVisited extends Conditions{
	
	Room room;
	/**constructor of SpecificRoom class
	 * @param r Room class
	 * */
    public RoomVisited(Room r){
 	   	super();
    	room = r;
    }
    
	@Override
	/**this function defines whether the specific condition for one room is satisfied or not
	 * @param arg Object class getting from game desinger
	 * @return boolean
	 * */
	public boolean checkCondition(Object arg) {
		
		if(arg instanceof Player){
			Player player = (Player) arg;
			for(Player.PathNode foo : player.path){
				if(foo.getRoom().getRoomID() == room.getRoomID()){
					return true;
				}
			}
		}
		return false;
	}
	/**get Room value
     * @return room Room Class
     * 
     * */
	public Room getRoom() {
		return room;
	}
	/**set Room value
     * @param room Room Class
     * 
     * */
	public void setRoom(Room room) {
		this.room = room;
	}

}
