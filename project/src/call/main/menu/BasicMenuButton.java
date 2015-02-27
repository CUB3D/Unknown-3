package call.main.menu;
import call.game.geom.UI2D;


public class BasicMenuButton
{	
	private String text;
	
	protected boolean on;

	public BasicMenuButton(String text, OverlayMenu menu)
	{
		this(text);
		
		menu.addButton(this);
	}
	
	public BasicMenuButton(String text)
	{
		this.text = text;
	}

	public void render(OverlayMenu menu, int pos)
	{
		UI2D.renderText(text, menu.getX() + 2, menu.getY() + menu.getHeight() - ((pos + 1) * 14) - 24);
		
		UI2D.rect(menu.getX() + menu.getWidth() - 42, menu.getY() + menu.getHeight() - ((pos + 1) * 14) - 24, 40, 12, 0xFF000000);

		if(on)
			UI2D.rect(menu.getX() + menu.getWidth() - 42, menu.getY() + menu.getHeight() - ((pos + 1) * 14) - 24, 20, 12, 0xFF00FF00);
		else
			UI2D.rect(menu.getX() + menu.getWidth() - 22, menu.getY() + menu.getHeight() - ((pos + 1) * 14) - 24, 20, 12, 0xFFFF0000);
	}
	
	public boolean onClick(int x, int y, OverlayMenu menu, int pos)
	{
		int yy = menu.getY() + menu.getHeight() - ((pos + 1) * 14) - 24;
		
		if(x > menu.getX() + menu.getWidth() - 42 && x < menu.getX() + menu.getWidth() - 2)
			if(y > yy && y < yy + 14)
			{
				on = !on;
				
				return true;
			}
		
		return false;
	}
	
	public boolean isEnabled()
	{
		return this.on;
	}
}
