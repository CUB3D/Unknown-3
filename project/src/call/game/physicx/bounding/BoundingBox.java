package call.game.physicx.bounding;

import java.awt.Rectangle;

public class BoundingBox
{
	public double x;
	public double y;
	public double width;
	public double height;

	public BoundingBox(double x, double y, double f, double g)
	{
		this.x = x;
		this.y = y;
		this.width = f;
		this.height = g;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getWidth()
	{
		return width;
	}

	public double getHeight()
	{
		return height;
	}

	public Rectangle getBounds()
	{
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
}