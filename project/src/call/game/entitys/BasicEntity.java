package call.game.entitys;

import call.game.image.BaseSprite;
import call.game.image.Sprite;

public class BasicEntity
{
	public BaseSprite sprite;

	protected int health;

	public int id;

	public BasicEntity(BaseSprite s, int id)
	{
		this.sprite = s;
		this.id = id;
	}

	public void update()
	{
	}

	public void render()
	{
		sprite.render();
	}
	
	public void handleCollision(BasicEntity e)
	{
		
	}
	
	public void damage(int dmg)
	{
		setHealth(getHeath() - dmg);
	}

	public int getEntityTypeID()
	{
		return this.id;
	}
	
	public int[] getCollidableEntitys()
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

	public int getX()
	{
		return this.sprite.getX();
	}

	public int getY()
	{
		return this.sprite.getY();
	}

	public BaseSprite getSprite()
	{
		return this.sprite;
	}
	
	@Override
	public Object clone()
	{
		return new BasicEntity((Sprite) getSprite().clone(), getEntityTypeID());
	}
}
