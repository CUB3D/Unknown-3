package call.game.image;

import java.util.HashMap;
import java.util.Map;

import call.game.utils.Translate;

public class GroupedSprite extends BaseSprite
{
	private Map<String, BaseSprite> sprites = new HashMap<String, BaseSprite>();
	
	public GroupedSprite(double x, double y)
	{
		super(x, y);
	}
	
	@Override
	public void render()
	{
		for(BaseSprite bs : sprites.values())
			bs.render();
	}
	
	@Override
	public void translate(Translate t)
	{
		super.translate(t);
		
		for(BaseSprite bs : sprites.values())
		{
			bs.setX(getX());
			bs.setY(getY());
		}
	}
	
	@Override
	public void move(int speedX, int speedY)
	{
		super.move(speedX, speedY);
		
		for(BaseSprite bs : sprites.values())
		{
			bs.setX(getX());
			bs.setY(getY());
		}
	}
	
	@Override
	public void setX(double x)
	{
		super.setX(x);
		
		for(BaseSprite bs : sprites.values())
			bs.setX(getX());
	}
	
	@Override
	public void setY(double y)
	{
		super.setY(y);
		
		for(BaseSprite bs : sprites.values())
			bs.setY(getY());
	}
	
	
	
	public void addSprite(String name, BaseSprite s)
	{
		this.sprites.put(name, s);
		
		s.setX(getX());
		s.setY(getY());
		s.setAngle(getAngle());
	}
	
	public BaseSprite getSprite(String name)
	{
		return this.sprites.get(name);
	}

	public Map<String, BaseSprite> getSprites()
	{
		return sprites;
	}
	
	@Override
	public Image getImage()
	{
		return ((BaseSprite) getSprites().values().toArray()[0]).getImage();
	}
}
