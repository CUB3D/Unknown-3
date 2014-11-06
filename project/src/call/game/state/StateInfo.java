	package call.game.state;

import java.util.HashMap;
import java.util.Map;


/**
 * Used for storing extra data to pass from state to state
 * 
 * @author Callum
 */
public class StateInfo
{
	/**
	 * Mapping of names as strings to data as objects
	 */
	protected Map<String, Object> extras = new HashMap<String, Object>();
	
	/**
	 * Stores extra data
	 * 
	 * @param name The name for the data
	 * @param obj The extra data
	 */
	public void putExtra(String name, Object obj)
	{
		extras.put(name, obj);
	}
	
	/**
	 * Stores extra integers
	 * 
	 * @param name The name for the data
	 * @param i The extra integer
	 */
	public void putExtraInt(String name, int i)
	{
		putExtra(name, "" + i);
	}
	
	/**
	 * Gets extra data
	 * 
	 * @param name The name for the data
	 * @return The extra data
	 */
	public Object getExtra(String name)
	{
		return extras.get(name);
	}
	
	/**
	 * Gets extra integers
	 * 
	 * @param name The name for the data
	 * @return The extra integer
	 */
	public int getExtraInt(String name)
	{
		return Integer.parseInt(extras.get(name).toString());
	}
}
