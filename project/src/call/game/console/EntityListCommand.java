package call.game.console;

import call.game.entitys.BaseEntity;
import call.game.entitys.EntityHandler;

public class EntityListCommand implements ICommandListener
{
	@Override
	public boolean onCommand(Console console, String command)
	{
		if(command.equalsIgnoreCase("entitys"))
		{
			Console.printLine("format: ID|HP|X|Y|Dead");
			Console.printLine("-------------------");
			
			for(BaseEntity be : EntityHandler.getEntitys())
			{
				Console.printLine(be.getEntityID() + "|" + be.getHealth() + "|" + be.getX() + "|" + be.getY() + "|" + be.isDead());
			}
			
			return true;
		}
		
		return false;
	}

}
