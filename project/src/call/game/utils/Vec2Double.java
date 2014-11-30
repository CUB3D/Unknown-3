package call.game.utils;

import java.awt.Point;

public class Vec2Double
{
	private double x;
	private double y;

	public Vec2Double(Point p)
	{
		this(p.x, p.y);
	}
	
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
	
	public double getAngleRadians(Vec2Double v2d)
	{
	    double angle = Math.atan2(v2d.y - y, v2d.x - x);

	    return angle;
	}
	
	public double getAngleDegrees(Vec2Double v2d)
	{
		double degs = Math.toDegrees(getAngleRadians(v2d));
		
		if(degs < 0)
			degs += 360;
		
		return degs;
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
