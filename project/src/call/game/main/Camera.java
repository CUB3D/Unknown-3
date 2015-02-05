package call.game.main;

import call.game.physicx.BoundingBox;
import call.game.physicx.IBounded;

public class Camera implements IBounded
{
	private double cameraX;
	private double cameraY;
	private double cameraWidth;
	private double cameraHeight;
	private double cameraScale = 1.0;
	
	public Camera()
	{
		this(0, 0, Unknown.getScreenSize().getWidth(), Unknown.getScreenSize().getHeight(), 1);
	}
	
	public Camera(double x, double y, double width, double height, double scale)
	{
		this.cameraX = x;
		this.cameraY = y;
		this.cameraWidth = width;
		this.cameraHeight = height;
		this.cameraScale = scale;
	}
	
	public void setCameraX(double cameraX)
	{
		this.cameraX = cameraX;
	}
	
	public double getCameraX()
	{
		return cameraX * cameraScale;
	}
	
	public void setCameraY(double cameraY)
	{
		this.cameraY = cameraY;
	}
	
	public double getCameraY()
	{
		return cameraY * cameraScale;
	}
	public double getCameraHeight()
	{
		return cameraHeight * cameraScale;
	}
	public double getCameraWidth()
	{
		return cameraWidth * cameraScale;
	}
	
	public void setCameraScale(double cameraScale)
	{
		this.cameraScale = cameraScale;
	}
	
	public double getCameraScale()
	{
		return cameraScale;
	}
	
	
	public double toRealX(double x)
	{
		return x + getCameraX();
	}
	
	public double toRealY(double y)
	{
		return y + getCameraY();
	}
	
	public boolean isVisable(double rx, double ry)
	{
		if(rx >= getCameraX() && rx <= getCameraX() + getCameraWidth())
			if(ry >= getCameraY() && ry <= getCameraY() + getCameraHeight())
				return true;
		
		return false;
	}
	
	public boolean isVisable(IBounded b)
	{
		return isVisable(b.getBounds().getX(), b.getBounds().getY());
	}

	@Override
	public BoundingBox getBounds()
	{
		return new BoundingBox(getCameraX(), getCameraY(), getCameraWidth(), getCameraHeight());
	}
}
