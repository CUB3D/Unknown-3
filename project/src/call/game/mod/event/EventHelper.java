package call.game.mod.event;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import call.game.mod.CALLModLoader;
import call.game.mod.ModEntry;
import call.game.mod.annotations.RegisterEvent;

public class EventHelper
{
	public static void sendEventTo(ModEntry entry, Event event)
	{
		Class<?> claz = entry.getMainClass();

		for(Method m : claz.getDeclaredMethods())
			for(Annotation a : m.getAnnotations())
				if(a instanceof RegisterEvent)
					if(m.getParameterTypes()[0].isInstance(event))
					{
						try
						{
							m.invoke(entry.getMainInstance(), event);
						}catch(Exception e) {e.printStackTrace();}
					}
	}
	
	public static void sendEventToAll(Event e)
	{
		for(ModEntry entry : CALLModLoader.getInstance().getMods())
			sendEventTo(entry, e);
	}
	
}
