package call.game.entitys;

import call.game.image.BaseSprite;
import call.game.image.Sprite;

public class BaseEntity
{
	public BaseSprite sprite;

	protected int health;
	
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
	
	public void damage(int dmg)
	{
		setHealth(getHeath() - dmg);
	}

	public String getEntityID()
	{
		return null;
	}
	
	public String[] getCollidableEntitys()
	{
		return null;
	}

	public int getMaxHealth()
	{
		return 0;
	}

	public int getHeath()
	{
		return this.health;
	}

	public void setHealth(int newHealth)
	{
		this.health = newHealth;
	}

	public boolean isDead()
	{
		return this.health <= 0;
	}

	public void kill()
	{
		setHealth(0);
	}

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
