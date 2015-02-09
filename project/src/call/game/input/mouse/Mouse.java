package call.game.input.mouse;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import call.game.main.Unknown;
import call.game.physicx.BoundingBox;
import call.game.physicx.Physicx;

import com.jogamp.newt.event.MouseEvent;

public class Mouse implements com.jogamp.newt.event.MouseListener
{
	public static final int STATE_CLICKED = 0;
	public static final int STATE_MOUSE_DOWN = 1;
	public static final int STATE_MOUSE_UP = 2;

	private static int x;
	private static int y;
	private List<MouseListener> listeners = new ArrayList<MouseListener>();


	public void registerMouseListener(MouseListener ml)
	{
		listeners.add(ml);
	}

	public void clearListeners()
	{
		listeners.clear();
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		
		for(int i = 0; i < listeners.size(); i++)
			listeners.get(i).onClick(x, y, STATE_CLICKED, e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();
		
		for(int i = 0; i < listeners.size(); i++)
			listeners.get(i).onClick(x, y, STATE_MOUSE_DOWN, e.getButton());
	}


	@Override
	public void mouseReleased(MouseEvent e)
	{
		x = e.getX();
		y = e.getY();

		for(int i = 0; i < listeners.size(); i++)
			listeners.get(i).onClick(x, y, STATE_MOUSE_UP, e.getButton());
	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		x = arg0.getX();
		y = arg0.getY();

		for(int i = 0; i < listeners.size(); i++)
			listeners.get(i).onMove(x, y);
	}

	public static Point getLocation()
	{
		return new Point(x, -y + Unknown.getScreenSize().height);
	}
	
	public static Point getTrueLocation()
	{
		return new Point(x, y);
	}

	public static BoundingBox getBounds()
	{
		return new BoundingBox(x, y, 5, 5);
	}

	public static boolean isTouching(Rectangle b)
	{
		return Physicx.isIntersecting(b, getBounds());
	}

	public static boolean isTouching(BoundingBox b)
	{
		return isTouching(b.getBounds());
	}

	@Override
	public void mouseWheelMoved(MouseEvent arg0) {}
}
