package call.game.image;

import call.game.main.EnumCallTime;


public class Sprite extends BaseSprite
{	
	protected Image img;

	public Sprite(String image, EnumCallTime callTime)
	{
		this(new Image(image), callTime);
	}
	
	public Sprite(Image i, EnumCallTime callTime)
	{
		this(0, 0, i, callTime);
	}

	public Sprite(double x, double y, EnumCallTime callTime)
	{
		this(x, y, (Image) null, callTime);
	}

	public Sprite(double x, double  y, String image, EnumCallTime callTime)
	{
		this(x, y, new Image(image), callTime);
	}
	
	public Sprite(double x, double y, Image i, EnumCallTime callTime)
	{
		super(x, y, callTime);

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

		return new Sprite(getX(), getY(), i, callTime);
	}
}
