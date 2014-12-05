package call.game.entitys.particle;

import call.game.entitys.HealthEntity;
import call.game.image.BaseSprite;

public class BaseParticle extends HealthEntity
{	
	public BaseParticle(double maxHealth, BaseSprite sprite)
	{
		super(maxHealth, sprite);
	}
	
	@Override
	public void update()
	{
		this.damage(1);
	}
}
