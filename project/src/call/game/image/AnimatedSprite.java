package call.game.image;


public class AnimatedSprite extends BaseSprite
{
	protected Animation ani;
	
	public AnimatedSprite(Animation i)
	{
		this(0, 0, i);
	}
	
	public AnimatedSprite(int x, int y)
	{
		this(x, y, null);
	}
	
	public AnimatedSprite(int x, int y, Animation i)
	{
		super(x, y);
		
		this.ani = i;
	}
	
	@Override
	public void render()
	{
		ani.render(getX(), getY());
	}
	
	public void renderAndAdvance()
	{
		ani.renderAndAdvance(getX(), getY());
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
