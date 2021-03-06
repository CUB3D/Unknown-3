package call.game.entitys;

import java.util.ArrayList;
import java.util.List;

import call.game.physicx.Physicx;
import call.utils.ArrayUtils;

public class EntityHandler
{
	private static List<BaseEntity> entitys = new ArrayList<BaseEntity>();

	private EntityHandler() {}

	public static void registerEntity(BaseEntity e)
	{
		entitys.add(e);
	}

	public static void updateEntitys()
	{
		for(int i = 0; i < entitys.size(); i++)
		{
			BaseEntity e = entitys.get(i);

			if(!e.isDead())
				e.update();
		}

		for(int i = 0; i < entitys.size(); i++) // go through all entities
		{
			BaseEntity e = entitys.get(i);
			
			if(!e.isDead()) // make sure entity is not dead
				for(int ii = 0; ii < entitys.size(); ii++) // go through all entities again
				{
					BaseEntity ee = entitys.get(ii);
					
					if(!ee.isDead()) // make sure entity is not dead
						if(!ee.equals(e)) // don't check for collision with self
							if(e.getCollidableEntitys() != null) // check to see if the first entity can actually collide with anything
								if(ArrayUtils.contains(e.getCollidableEntitys(), ee.getEntityID()))
									if(Physicx.isPPIntersecting(e.getSprite().getImage(), ee.getSprite().getImage()))
										e.handleCollision(ee);
				}
		}


		for(int i = 0; i < entitys.size(); i++)
		{
			BaseEntity e = entitys.get(i);

			if(e.isDead())
				entitys.remove(i);
		}
	}

	public static void renderEntitys()
	{
		for(int i = 0; i < entitys.size(); i++)
		{
			BaseEntity e = entitys.get(i);

			if(!e.isDead())
				e.render();
		}
	}

	public static List<BaseEntity> getEntitys()
	{
		return entitys;
	}
}
