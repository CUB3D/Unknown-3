package call.game.image;

import call.game.main.EnumCallTime;

public class BlankSprite extends BaseSprite
{
	public BlankSprite(double x, double y, EnumCallTime callTime)
	{
		super(x, y, callTime);
	}
	
	public BlankSprite(double x, double y)
	{
		this(x, y, EnumCallTime.NEVER);
	}
	
	@Override
	public void render() {}
}
