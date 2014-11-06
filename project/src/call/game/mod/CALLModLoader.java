package call.game.mod;

import java.util.Collections;
import java.util.List;

import call.game.mod.event.CMLInitEvent;
import call.game.mod.event.EventHelper;
import call.game.mod.event.ModState;

public class CALLModLoader
{
	private static CALLModLoader instance;
	
	private static List<ModEntry> mods = null;
	
	private static ModState curState;
	
	private CALLModLoader() {}
	
	public List<ModEntry> getMods()
	{
		return mods;
	}
	
	public ModState getCurrentState()
	{
		return curState;
	}

	public static CALLModLoader getInstance()
	{
		if(instance == null)
		{
			instance = new CALLModLoader();
			
			curState = ModState.CONSTRUCTING;
			
			ModDiscoverer discoverer = new ModDiscoverer("Mods");
			mods = Collections.unmodifiableList(discoverer.discoverMods());
			
			curState = ModState.INIT;
			
			EventHelper.sendEventToAll(new CMLInitEvent());
			
			curState = ModState.RUNNING;
		}
		
		return instance;
	}
}
