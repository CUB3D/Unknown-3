package call.game.entitys.particle;

import java.util.ArrayList;
import java.util.List;

import call.game.image.BaseSprite;

public class ParticleHandler
{
	private static List<BaseParticle> particles = new ArrayList<BaseParticle>();

	public static BaseParticle spawnParticle(BaseSprite sprite, int lifeSpan)
	{
		BaseParticle bp = new BaseParticle(lifeSpan, sprite);

		particles.add(bp);

		return bp;
	}

	public static void updateParticles()
	{
		for(int i = 0; i < particles.size(); i++)
		{
			BaseParticle p = particles.get(i);

			if(!p.isDead())
				p.update();
			else
				particles.remove(i);
		}
	}

	public static void renderParticles()
	{
		for(int i = 0; i < particles.size(); i++)
		{
			BaseParticle p = particles.get(i);

			if(!p.isDead())
				p.render();
		}
	}

	public static List<BaseParticle> getParticles()
	{
		return particles;
	}
}
