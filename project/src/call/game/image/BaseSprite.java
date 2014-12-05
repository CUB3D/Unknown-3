package call.game.image;

import call.game.utils.Translate;
import call.game.utils.Vec2Double;

public class BaseSprite
{
	private double x;
	private double y;
	private double angle;
	private Vec2Double direction = new Vec2Double(0, 0);
	
	public BaseSprite(double x, double y)
	{
		this.x = x;
		this.y = y;
		
		setAngle(0);
	}
	
	public void translate(Translate t)
	{
		this.x += t.getDifX();
		this.y += t.getDifY();
	}
	
	public void move(int speedX, int speedY)
	{
		Vec2Double velocity = new Vec2Double(direction.getX() * speedX, direction.getY() * -speedY);
		
		
		if(direction.getX() == 0 && direction.getY() == 0)
			velocity = new Vec2Double(speedX, speedY);
		
		this.x += velocity.getX();
		this.y += velocity.getY();
	}
	
	public void render() {}
	public Image getImage() { return null; }
	
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
	
	public double getAngle()
	{
		return angle;
	}
	
	public void setAngle(double angle)
	{
		this.angle = angle;
		
		direction.setX(Math.cos(Math.toRadians(this.angle - 90)));
		direction.setY(Math.sin(Math.toRadians(this.angle - 90)));
		
		if (direction.getLength() < 0)
		    direction = direction.normalise();
	}
	
	@Override
	public Object clone() {return null;}
}
