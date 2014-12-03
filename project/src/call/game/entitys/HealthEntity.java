package call.game.entitys;

import call.game.image.BaseSprite;

public class HealthEntity extends BaseEntity
{
	private double health;
	private double maxHealth;
	
	public HealthEntity(double maxHealth, BaseSprite sprite)
	{	
		this(maxHealth, maxHealth, sprite);
	}
	
	public HealthEntity(double health, double maxHealth, BaseSprite sprite)
	{
		super(sprite);
		
		this.health = health;
		this.maxHealth = maxHealth;
	}
	
	@Override
	public void setHealth(double health)
	{
		this.health = health;
	}
	
	@Override
	public double getHealth()
	{
		return this.health;
	}
	
	public double getMaxHealth()
	{
		return this.maxHealth;
	}
	
	@Override
	public void damage(int dmg)
	{
		this.setHealth(getHealth() - dmg);
	}
	
	@Override
	public boolean isDead()
	{
		return health < 0;
	}
}
