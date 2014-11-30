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
	
	public double getAngleDegrees(Vec2Double v2d)
	{
		return (Math.atan2(this.getX() - v2d.getX(), this.getY() - v2d.getY()) * 180 / Math.PI) + 90;
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
