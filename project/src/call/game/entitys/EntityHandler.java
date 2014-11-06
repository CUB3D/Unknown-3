package call.game.entitys;

import java.util.ArrayList;
import java.util.List;

import call.game.physicx.Physicx;
import call.utils.ArrayUtils;

public class EntityHandler
{
	private static List<BasicEntity> entitys = new ArrayList<BasicEntity>();

	private EntityHandler() {}

	public static void registerEntity(BasicEntity e)
	{
		entitys.add(e);
		e.setHealth(e.getMaxHealth());
	}

	public static void updateEntitys()
	{
		for(int i = 0; i < entitys.size(); i++)
		{
			BasicEntity e = entitys.get(i);

			if(!e.isDead())
				e.update();
		}

		for(int i = 0; i < entitys.size(); i++)
		{
			BasicEntity e = entitys.get(i);

			if(e.isDead())
				entitys.remove(i);
		}

		// check collisons

		for(BasicEntity e : entitys)
			if(!e.isDead())
				for(BasicEntity ee : entitys)
					if(e.getCollidableEntitys() != null)
						if(ArrayUtils.contains(e.getCollidableEntitys(), ee.getEntityTypeID()))
							if(!ee.isDead())
								if(Physicx.isIntersecting(e.getSprite().getImage().getBounds(), ee.getSprite().getImage().getBounds()))
									e.handleCollision(ee);
	}

	public static void renderEntitys()
	{
		for(BasicEntity e : entitys)
			if(!e.isDead())
				e.render();
	}

	public static List<BasicEntity> getEntitys()
	{
		return entitys;
	}
}
