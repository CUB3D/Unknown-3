package call.game.mod;

import java.util.ArrayList;
import java.util.List;

import call.game.mod.event.ModState;

public class GameRegistery
{
	private static GameRegistery instance;
	
	private List<ITickHandler> tickHandlers = new ArrayList<ITickHandler>();
	private List<IRenderHandler> renderHandlers = new ArrayList<IRenderHandler>();
	
	private GameRegistery() {}
	
	public void registerTickHandler(ITickHandler handler)
	{
		if(CALLModLoader.getInstance().getCurrentState() == ModState.CONSTRUCTING)
			System.out.println("A mod is atempting to register a tick handler while constructing\nThis is bad practise");
		tickHandlers.add(handler);
	}
	
	public void registerRenderHandler(IRenderHandler handler)
	{
		if(CALLModLoader.getInstance().getCurrentState() == ModState.CONSTRUCTING)
			System.out.println("A mod is atempting to register a render handler while constructing\nThis is bad practise");
		renderHandlers.add(handler);
	}
	
	public void onTickStart()
	{
		for(ITickHandler handler : tickHandlers)
			handler.onTickStart();
	}
	
	public void onTickEnd()
	{
		for(ITickHandler handler : tickHandlers)
			handler.onTickEnd();
	}
	
	public void onRender()
	{
		for(IRenderHandler handler : renderHandlers)
			handler.onRender();
	}
	
	public static GameRegistery getInstance()
	{
		if(instance == null)
			instance = new GameRegistery();
		
		return instance;
	}
}
