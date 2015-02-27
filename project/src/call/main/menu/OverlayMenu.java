package call.main.menu;


import java.util.ArrayList;
import java.util.List;

import call.game.geom.UI2D;
import call.game.input.mouse.MouseListener;
import call.game.main.Unknown;

public class OverlayMenu implements MouseListener
{
	protected int x;
	protected int y;

	protected int width;
	protected int height;
	
	protected String name;

	protected boolean open;
	
	protected List<BasicMenuButton> buttons = new ArrayList<BasicMenuButton>();

	public OverlayMenu(String name, int x, int y, int width, int height)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;

		Unknown.getMouse().registerMouseListener(this);
	}

	public void render()
	{
		if(!isOpen())
			return;

		
		UI2D.rect(x, y, width, height, 0x800000FF);
		
		UI2D.renderText(name, x + 2, y + height - 14);

		UI2D.rect(this.x + this.width - 22, this.y + this.height - 22, 20, 20, 0xFFFF0000);
		
		for(int i = 0; i < buttons.size(); i++)
			buttons.get(i).render(this, i);
	}

	@Override
	public boolean onClick(int x, int y, int state, int button)
	{	
		if(!isOpen())
			return false;
		
		if(x > this.x + width - 22 && x < this.x + width - 2)
			if(y > this.y + height - 22 && y < this.y + height - 2)
			{
				close();
				
				return true;
			}
		
		for(int i = 0; i < buttons.size(); i++)
			if(buttons.get(i).onClick(x, y, this, i))
				return true;
		
		return false;
	}
	
	public void addButton(BasicMenuButton button)
	{
		this.buttons.add(button);
	}

	public void open()
	{
		this.open = true;
	}

	public void close()
	{
		this.open = false;
	}

	public boolean isOpen()
	{
		return this.open;
	}

	@Override
	public void onMove(int arg0, int arg1) {}
	
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public List<BasicMenuButton> getButtons()
	{
		return buttons;
	}
}
