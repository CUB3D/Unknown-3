package call.game.console;

import call.main.menu.DebugMenu;

public class DebugCommand implements ICommandListener
{
	@Override
	public boolean onCommand(Console console, String command)
	{
		DebugMenu menu = DebugMenu.getInstance();

		String[] s = command.split(" ");

		if(s[0].equalsIgnoreCase("debug"))
		{
			if(s.length == 1)
			{
				if(menu.isOpen())
					menu.close();
				else
					menu.open();
				
				return true;
			}

			if(s[1].equalsIgnoreCase("coords"))
			{
				menu.setShowMouseCoords(!menu.canShowMouseCoords());
				
				return true;
			}

			if(s[1].equalsIgnoreCase("fps"))
			{
				menu.setShowFPS(!menu.canShowFPS());
				
				return true;
			}

			if(s[1].equalsIgnoreCase("image"))
			{
				menu.setShowImageDebug(!menu.canShowImageDebug());
				
				return true;
			}
		}

		return false;
	}

}
