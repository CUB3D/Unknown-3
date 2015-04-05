package call.game.image;

import call.game.main.EnumCallTime;

public class BlankSprite extends BaseSprite
{
	public BlankSprite(int x, int y, EnumCallTime callTime)
	{
		super(x, y, callTime);
	}
	
	@Override
	public void render() {}
}
