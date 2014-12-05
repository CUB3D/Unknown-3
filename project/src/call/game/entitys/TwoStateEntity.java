package call.game.entitys;

import call.game.image.Sprite;

public class TwoStateEntity extends BaseEntity
{
	protected boolean alive = true;
	
	public TwoStateEntity(Sprite s)
	{
		super(s);
	}
	
	@Override
	public boolean isDead()
	{
		return !this.alive;
	}
	
	@Override
	public double getHealth()
	{
		return alive ? 1 : 0;
	}
	
	@Override
	public void setHealth(double health)
	{
		alive = (health == 1 ? true : false);
	}
}
