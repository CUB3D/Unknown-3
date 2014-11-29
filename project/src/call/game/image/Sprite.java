package call.game.image;


public class Sprite extends BaseSprite
{	
	protected Image img;
	
	public Sprite(Image i)
	{
		this(0, 0, i);
	}
	
	public Sprite(double x, double y)
	{
		this(x, y, null);
	}
	
	public Sprite(double x, double y, Image i)
	{
		super(x, y);
		
		this.img = i;
	}
	
	@Override
	public void render()
	{
		img.render((int) getX(), (int) getY(), getAngle());
	}
	
	@Override
	public Image getImage()
	{
		return img;
	}
	
	@Override
	public Object clone()
	{
		Image i = (Image) getImage().clone();
		
		return new Sprite(getX(), getY(), i);
	}
}
