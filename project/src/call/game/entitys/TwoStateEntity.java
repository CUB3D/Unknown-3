package call.game.entitys;

import call.game.image.Sprite;

public class TwoStateEntity extends BaseEntity
{
	protected boolean alive;
	
	public TwoStateEntity(Sprite s)
	{
		super(s);
	}
	
	@Override
	public int getHeath()
	{
		return this.alive ? 1 : 0;
	}
	
	@Override
	public int getMaxHealth()
	{
		return 1;
	}
	
	@Override
	public void setHealth(int newHealth)
	{
		this.alive = (newHealth == 1 ? true : false);
	}
	
	@Override
	public boolean isDead()
	{
		return !this.alive;
	}
}
