package map.Model;

import java.io.*;
import java.util.HashMap;
import java.util.Vector;
import map.Conditions.*;

/**
 * Define the item that can be in a player or a room 
 * add 7 attributes for build 3, such as fired, thrown, attackable, eatable, drinkable, lockable, unlockable.
 * @author ho_gao
 * @version 1.4
 */

public class Item implements Serializable {

	private static int count = 1;
	
	String name;
	String description;
	int id;
	
	//add for build3
	Vector<Conditions> vcon = new Vector<Conditions>();
	Item keyNeed;
	boolean isKey;
	double probFire = 1;
	double probThrow = 1;
    
	HashMap<Property, Object> props = new HashMap<Property, Object>();
	public enum Property {
		
		TAKEABLE,
		DRINKABLE,
		EATABLE,
		FIRABLE,
		THROWABLE,
		LOCKABLE
		;
	}
	
	
	public void deepCopy(Item src){
    	
    	name = src.name;
    	description = src.description;
    	id = src.id;
    	isKey = src.isKey;
    	probFire = src.probFire;
    	probThrow = src.probThrow;
    	
    	if(src.props != null)
    		props = new HashMap<Property, Object>(src.props);
    	else
    		props = new HashMap<Property, Object>();
    	
    	if(src.vcon != null)
    		vcon = new Vector<Conditions>(src.vcon);
    	else
    		vcon = new Vector<Conditions>();
    	
    	if(src.keyNeed != null)
    		keyNeed = new Item(src.keyNeed);
    }
	
	/**
     * constructor for Item
     * @param name
     * @param description
     * @param location
     */
	public Item(String name, String description, Room location)
	{
		this.name = name;
		this.description = description;
		props = new HashMap<Property, Object>();
		vcon = new Vector<Conditions>();
		id = count++;
	}
	/**default constructor for Item
	 * */
	public Item(){
		id = count++;
	}
	/**constructor for Item
	 * @param name
	 * */
	public Item(String name){
		this.name=name;
		id = count++;
	}
	
	/**
	 * constructor for player
	 * atRoom = false
	 * @param name
	 * @param description
	 * @param player	 * 
	 */
	public Item(String name, String description, Player player)
	{
		this.name = name;
		this.description = description;
		props = new HashMap<Property, Object>();
		vcon = new Vector<Conditions>();
		id = count++;
	}
	
	/**
	 * Copy Constructor
	 * @param src
	 */
	public Item(Item src){
		deepCopy(src);
	}
	
	/**
	 * whether Property exists or not
	 * @param p
	 * @return
	 */
	public boolean chkProp(Property p){
		if(props == null)
			props = new HashMap<Property, Object>();
		return props.containsKey(p);
		
	}
	
	/**
	 * add a new Property
	 * @param p
	 */
	public void addProp(Property key, Object value){
		if(props == null)
			props = new HashMap<Property, Object>();
		props.put(key, value);
	}
	
	public Object getPropValue(Property p){
		if(props == null)
			props = new HashMap<Property, Object>();
		return props.get(p);
	}

	/**
	 * remove a Property if exists
	 * @param p
	 */
	public boolean delProp(Property p){
		if(props == null)
			return false;
		if(props.remove(p) == null)
			return false;
		return true;
	}
	
	/**
	 * check conditions
	 * @param player
	 * @return 
	 */
	public boolean chkCondition(Player player){
		for(Conditions foo : vcon){
			if(!foo.checkCondition(player))
				return false;
		}
		return true;
	}
	
	public void lock(){
		if(props.containsKey(Property.LOCKABLE))
			props.put(Property.LOCKABLE, (Boolean)true);
	}
	
	public void unlock(){
		if(props.containsKey(Property.LOCKABLE))
			props.put(Property.LOCKABLE, (Boolean)false);
	}

	public void addCondition(Conditions con){
		if(vcon == null)
			vcon = new Vector<Conditions>();
		vcon.add(con);
	}
	
	public void delCondition(Conditions con){
		vcon.remove(con);
	}

	/**
	 * getter and setter.
	 * @return different value, such as name by String.
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Vector<Conditions> getVcon() {
		return vcon;
	}

	public void setVcon(Vector<Conditions> vcon) {
		this.vcon = new Vector<Conditions>(vcon);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public HashMap<Property, Object> getProps() {
		return props;
	}

	public void setProps(HashMap<Property, Object> props) {
		this.props = props;
	}

	/**
	 * @return the keyNeed
	 */
	public Item getKeyNeed() {
		return keyNeed;
	}

	/**
	 * @param keyNeed the keyNeed to set
	 */
	public void setKeyNeed(Item keyNeed) {
		this.keyNeed = keyNeed;
	}

	/**
	 * @return the isKey
	 */
	public boolean isKey() {
		return isKey;
	}

	/**
	 * @param isKey the isKey to set
	 */
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}

	/**
	 * @return the probFire
	 */
	public double getProbFire() {
		return probFire;
	}

	/**
	 * @param probFire the probFire to set
	 */
	public void setProbFire(double probFire) {
		this.probFire = probFire;
	}

	/**
	 * @return the probThrow
	 */
	public double getProbThrow() {
		return probThrow;
	}

	/**
	 * @param probThrow the probThrow to set
	 */
	public void setProbThrow(double probThrow) {
		this.probThrow = probThrow;
	}

}