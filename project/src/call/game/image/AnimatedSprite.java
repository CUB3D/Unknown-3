package call.game.image;

import call.game.main.EnumCallTime;


public class AnimatedSprite extends BaseSprite
{
	protected Animation ani;
	
	public AnimatedSprite(Animation i, EnumCallTime callTime)
	{
		this(0, 0, i, callTime);
	}
	
	public AnimatedSprite(double x, double y)
	{
		this(x, y, EnumCallTime.NEVER);
	}
	
	public AnimatedSprite(double x, double y, EnumCallTime callTime)
	{
		this(x, y, null, callTime);
	}
	
	public AnimatedSprite(double x, double y, Animation i, EnumCallTime callTime)
	{
		super(x, y, callTime);
		
		this.ani = i;
	}
	
	@Override
	public void render()
	{
		renderAndAdvance();
	}
	
	public void renderAndAdvance()
	{
		ani.renderAndAdvance((int) getX(), (int)getY());
	}
	
	public void advance()
	{
		ani.advance();
	}
	
	public Animation getAnimation()
	{
		return ani;
	}
	
	@Override
	public Image getImage()
	{
		return ani.getFrames().get(ani.getIndex());
	}
}
