package call.game.entitys;

import call.game.image.BaseSprite;
import call.game.image.Sprite;

public class BaseEntity
{
	public BaseSprite sprite;
	
	public BaseEntity() {}
	
	public BaseEntity(BaseSprite s)
	{
		this.sprite = s;
	}

	public void update()
	{
	}

	public void render()
	{
		sprite.render();
	}
	
	public void handleCollision(BaseEntity e)
	{
		
	}
	
	public void damage(int dmg) {}

	public String getEntityID()
	{
		return null;
	}
	
	public String[] getCollidableEntitys()
	{
		return null;
	}

	public boolean isDead()
	{
		return true;
	}

	public void kill() {}
	
	public double getHealth() {return 0;}
	
	public void setHealth(double health) {}

	public double getX()
	{
		return this.sprite.getX();
	}

	public double getY()
	{
		return this.sprite.getY();
	}

	public BaseSprite getSprite()
	{
		return this.sprite;
	}
	
	public void setSprite(BaseSprite sprite)
	{
		this.sprite = sprite;
	}
	
	@Override
	public Object clone()
	{
		return new BaseEntity((Sprite) getSprite().clone());
	}
}
