package call.game.utils;

public class Vec2Double
{
	private double x;
	private double y;

	public Vec2Double(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	public double getLength()
	{
		return Math.sqrt((x * x) + (y * y));
	}
	
	public Vec2Double normalise()
	{
		double len = getLength();
		
		return new Vec2Double(x / len, y / len);
	}
	
	public double getX()
	{
		return x;
	}
	
	public void setX(double x)
	{
		this.x = x;
	}

	public double getY()
	{
		return y;
	}
	
	public void setY(double y)
	{
		this.y = y;
	}
}
