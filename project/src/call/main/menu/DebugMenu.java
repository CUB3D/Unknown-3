package call.main.menu;
import java.awt.Point;

import call.game.geom.UI2D;
import call.game.input.mouse.Mouse;
import call.game.main.Unknown;


public class DebugMenu extends OverlayMenu
{
	private static DebugMenu instance;
	
	private BasicMenuButton showMouseCoords;
	private BasicMenuButton showImageDebug;
	private BasicMenuButton showFPS;
	
	public DebugMenu()
	{
		super("Debug Settings", 10, (int) (Unknown.getScreenSize().getHeight() - 310), 200, 300);
		
		showMouseCoords = new BasicMenuButton("Show mouse coords", this);
		showImageDebug = new BasicMenuButton("Image Debuger", this);
		showFPS = new BasicMenuButton("Show F/T PS", this);
	}
	
	@Override
	public void render()
	{
		super.render();
		
		if(!isOpen())
			return;
		
		if(canShowMouseCoords())
		{
			Point p = Mouse.getLocation();
			
			UI2D.renderText("X: " + p.getX() + "  Y: " + p.getY(), 10, 10);
		}
		
		if(canShowFPS())
		{
			UI2D.renderText("FPS: " + Unknown.getFPS() + ", TPS: " + Unknown.getTPS(), 2, (int) Unknown.getScreenSize().getHeight() - 14);
		}
	}
	
	public void setShowMouseCoords(boolean b)
	{
		this.showMouseCoords.on = b;
	}
	
	public boolean canShowMouseCoords()
	{
		return showMouseCoords.isEnabled();
	}
	
	public void setShowImageDebug(boolean b)
	{
		this.showImageDebug.on = b;
	}
	
	public boolean canShowImageDebug()
	{
		return showImageDebug.isEnabled();
	}
	
	public void setShowFPS(boolean b)
	{
		this.showFPS.on = b;
	}
	
	public boolean canShowFPS()
	{
		return showFPS.isEnabled();
	}
	
	public static DebugMenu getInstance()
	{
		if(instance == null)
			instance = new DebugMenu();
		
		return instance;
	}
}
