package call.game.mod;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ModEntry
{
	private File baseFile;

	private List<Class<?>> classes;

	private Class<?> mainClass;
	private String modID;
	private String name;
	private String version;

	private Object mainClassObj;

	public ModEntry(File f)
	{
		this.baseFile = f;
	}

	public void loadClasses(ClassLoader loader)
	{
		classes = new ArrayList<Class<?>>();

		try
		{	
			ZipFile file = new ZipFile(baseFile);

			Enumeration<? extends ZipEntry> files = file.entries();

			while(files.hasMoreElements())
			{
				ZipEntry entry = files.nextElement();

				if(!entry.isDirectory())
					if(entry.getName().endsWith(".class"))
					{
						String name = entry.getName();

						name = name.replaceAll("/", ".");
						name = name.replaceAll(".class", "");

						try
						{
							Class<?> claz = Class.forName(name, false, loader);
							classes.add(claz);
						}catch(Exception e) {e.printStackTrace();}
					}
			}

			file.close();
		}catch(Exception e) {e.printStackTrace();}
	}

	public void discoverImportantClasses()
	{
		for(Class<?> claz : classes)
		{
			Mod m = claz.getAnnotation(Mod.class);
			if(m != null)
			{
				if(mainClass != null)
				{
					throw new RuntimeException("Mod: " + baseFile.getName() + " has multipull @Mod annotations. This mod cannot continue to load!");
				}
				else
				{
					mainClass = claz;
					modID = m.modID();
					name = m.name();
					version = m.version();
				}
			}
		}

		if(mainClass == null)
			throw new RuntimeException("Mod: " + baseFile.getName() + " has no main class");

		//TODO: check for dupelicated modID

		System.out.println("Mod: " + name + " - " + version + " has loaded");
	}

	public void init()
	{
		try
		{
			mainClassObj = mainClass.newInstance();
		}catch(Exception e) {e.printStackTrace();}
	}

	public Class<?> getClassWithAnnotation(Class<? extends Annotation> ano)
	{
		for(Class<?> claz : classes)
		{
			Annotation a = claz.getAnnotation(ano);

			if(a != null)
				return claz;
		}

		return null;
	}
	
	public List<Class<?>> getClasses()
	{
		return classes;
	}

	public List<Class<?>> getClassesWithAnnotation(Class<? extends Annotation> ano)
	{
		List<Class<?>> classes = new ArrayList<Class<?>>();

		for(Class<?> claz : classes)
		{
			Annotation a = claz.getAnnotation(ano);

			if(a != null)
				classes.add(claz);
		}

		return classes;
	}

	public String getmodID()
	{
		return modID;
	}

	public Class<?> getMainClass()
	{
		return mainClass;
	}

	public Object getMainInstance()
	{
		return mainClassObj;
	}
}
