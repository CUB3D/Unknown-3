package call.game.main;

import java.lang.reflect.Method;

public class ClassUtils
{
	public static Method getDefinedMethod(String name, Class<?> claz)
	{
		for(Method m : claz.getDeclaredMethods())
		{
			Define a = m.getAnnotation(Define.class);

			if(a != null)
				if(a.value().equals(name))
					return m;
		}

		return null;
	}
}
