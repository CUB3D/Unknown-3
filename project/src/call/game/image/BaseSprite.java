package call.game.image;

import call.game.utils.Translate;

public class BaseSprite
{
	private int x;
	private int y;
	
	public BaseSprite(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public void translate(Translate t)
	{
		this.x += t.getDifX();
		this.y += t.getDifY();
	}
	
	public void render() {}
	public Image getImage() { return null; }
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	@Override
	public Object clone() {return null;}
}
