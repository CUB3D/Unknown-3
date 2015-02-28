package call.game.state;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import call.game.main.ClassUtils;
import call.game.main.EnumCallTime;
import call.game.main.IRenderable;
import call.game.main.IUpdateable;
import call.game.main.Unknown;

/**
 * A state system for managing states and passing inner-state data
 * 
 * @author Callum
 */
public class StateEngine implements IRenderable, IUpdateable
{
	/**
	 * The list of states stored in class form
	 */
	private List<Class<?>> states = new ArrayList<Class<?>>();
	
	
	/**
	 * The class of the current state
	 */
	private Class<?> curState;
	
	/**
	 * The instance of the current state
	 */
	private Object curStateInstance;
	
	/**
	 * The render method of the current state (may be null)
	 */
	private Method render;
	
	/**
	 * The update method of the current state (may be null)
	 */
	private Method update;

	public StateEngine(EnumCallTime callTime)
	{
		Unknown.registerRenderable(this, callTime);
		Unknown.registerUpdateable(this, callTime);
	}
	
	/**
	 * Add's a state to the engine
	 * 
	 * @param state The class of the state to be added
	 */
	public void addState(Class<?> state)
	{
		states.add(state);
	}

	/**
	 * Sets the current state with no inner-state data
	 * 
	 * @param i The id number of the state
	 */
	public void setState(int i)
	{
		setState(i, null);
	}
	
	/**
	 * Sets the current state with inner-state data
	 * 
	 * @param i The id number of the state
	 * @param data The inner-state data or null
	 */
	public void setState(int i, StateInfo data)
	{
		curState = states.get(i);
		
		render = ClassUtils.getDefinedMethod("Render", curState);
		update = ClassUtils.getDefinedMethod("Update", curState);
		
		try
		{
			curStateInstance = curState.newInstance();
		} catch(Exception e) {e.printStackTrace();}

		if(data != null)
			for(Field f : curState.getDeclaredFields())
			{
				Annotation stateData = f.getAnnotation(StateData.class);

				if(stateData != null)
				{
					try
					{
						f.set(curStateInstance, data);
					}catch(Exception e) {e.printStackTrace();}
				}
			}
	}

	/**
	 * Get the current state instance
	 * 
	 * @return The instance of the current state
	 */
	public Object getState()
	{
		return curStateInstance;
	}

	@Override
	public void update()
	{
		if(update != null)
		{
			try
			{
				update.invoke(curStateInstance, (Object[]) null);
			}catch(Exception e) {e.printStackTrace();}
		}
	}

	@Override
	public void render()
	{
		if(render != null)
		{
			try
			{
				render.invoke(curStateInstance, (Object[]) null);
			}catch(Exception e) {e.printStackTrace();}
		}
	}
}
