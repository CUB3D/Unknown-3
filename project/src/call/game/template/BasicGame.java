package call.game.template;

import java.awt.Dimension;

import call.game.main.Camera;
import call.game.main.Unknown;

public class BasicGame
{
	protected Camera camera;
	
	public BasicGame()
	{
		Dimension dim = Unknown.getScreenSize();
		
		this.camera = new Camera(0, 0, dim.getWidth(), dim.getHeight(), 1);
	}
}
