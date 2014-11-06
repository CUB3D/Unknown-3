package call.game.utils;

public class Translate
{
	public static final Translate UP = new Translate(0, 1);
	public static final Translate DOWN = new Translate(0, -1);
	public static final Translate LEFT = new Translate(-1, 0);
	public static final Translate RIGHT = new Translate(1, 0);
	public static final Translate NONE = new Translate(0, 0);
	
	private double difX;
	private double difY;
	
	public Translate(double difX, double difY)
	{
		this.difX = difX;
		this.difY = difY;
	}
	
	public Translate multiply(double d)
	{
		return getScaledInstance(d, d);
	}
	
	public Translate getScaledInstance(double a, double b)
	{
		return new Translate(getDifX() * a, getDifY() * b);
	}
	
	public double getDifX()
	{
		return difX;
	}
	
	public double getDifY()
	{
		return difY;
	}
}
