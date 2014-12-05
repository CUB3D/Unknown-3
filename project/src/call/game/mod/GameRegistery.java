package call.game.mod;

import java.util.ArrayList;
import java.util.List;

public class GameRegistery
{
	private static GameRegistery instance;
	
	private List<ITickHandler> tickHandlers = new ArrayList<ITickHandler>();
	private List<IRenderHandler> renderHandlers = new ArrayList<IRenderHandler>();
	
	private GameRegistery() {}
	
	public void registerTickHandler(ITickHandler handler)
	{
		tickHandlers.add(handler);
	}
	
	public void registerRenderHandler(IRenderHandler handler)
	{
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
