package call.game.state;

import call.game.geom.UI2D;
import call.game.main.Define;
import call.game.main.Unknown;

public class OptionsState
{
	public static final int OPTIONS_STATE_ID = -1;

	@Define("Render")
	public void render()
	{
		if(Unknown.isDebugging())
		{
			UI2D.renderText("Debug Mode", 2, 14);
		}
	}
}
