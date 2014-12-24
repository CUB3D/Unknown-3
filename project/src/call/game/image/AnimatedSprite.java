package call.game.image;


public class AnimatedSprite extends BaseSprite
{
	protected Animation ani;
	
	public AnimatedSprite(Animation i)
	{
		this(0, 0, i);
	}
	
	public AnimatedSprite(double x, double y)
	{
		this(x, y, null);
	}
	
	public AnimatedSprite(double x, double y, Animation i)
	{
		super(x, y);
		
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
