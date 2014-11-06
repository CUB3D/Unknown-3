package call.game.mod;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ModDiscoverer
{
	public File modDir;

	public ModDiscoverer(String dir)
	{
		this.modDir = new File(dir);

		if(!modDir.exists())
		{
			try
			{
				modDir.mkdir();
			}catch(Exception e) {}
		}

		this.modDir = new File(dir);
	}

	public List<ModEntry> discoverMods()
	{
		if(modDir == null)
			return null;

		List<ModEntry> mods = new ArrayList<ModEntry>();

		for(File f : modDir.listFiles())	
			if(f.getName().endsWith(".jar"))
			{
				ModEntry e = loadMod(f, modDir);
				
				e.discoverImportantClasses();
				e.init();
				
				mods.add(e);
				
				System.out.println("Mod added: " + f.getName().replaceFirst(".jar", ""));
			}

		return mods;
	}

	public static ModEntry loadMod(File f, File p)
	{

		URL[] urls = null;

		try
		{
			urls = new URL[] {new File(p, f.getName()).toURI().toURL()};
		} catch (Exception e) {e.printStackTrace();}

		URLClassLoader loader = new URLClassLoader(urls, ClassLoader.getSystemClassLoader());


		ModEntry entry = new ModEntry(f);
		entry.loadClasses(loader);

		return entry;
	}
}
