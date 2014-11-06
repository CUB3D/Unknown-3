package call.game.mod;

import java.util.HashMap;

import call.game.mod.annotations.Overridable;

public class Overrider
{
	private static Overrider instance;

	private Overrider() {}

	private HashMap<String, Class<?>> remapping = new HashMap<String, Class<?>>();

	public void addRemaping(String name, Class<?> newClass)
	{
		remapping.put(name, newClass);
	}

	public Object createObject(Class<?> clazz, Class<?>[] paramTypes, Object[] params)
	{
		Overridable ov = clazz.getAnnotation(Overridable.class);

		if(ov != null)
		{
			String val = ov.value();

			try
			{
				if(remapping.containsKey(val))
					return remapping.get(val).getConstructor(paramTypes).newInstance(params);
				else
					return clazz.getConstructor(paramTypes).newInstance(params);
			}catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("Unable to create instance for: " + val + " (Note: this could be caused by a mod made incorrectly)");
				return null;
			}
		}
		else
			return null;
	}

	public static Overrider getInstance()
	{
		if(instance == null)
			instance = new Overrider();

		return instance;
	}
}
