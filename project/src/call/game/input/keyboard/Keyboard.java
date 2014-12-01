package call.game.input.keyboard;

import java.util.ArrayList;
import java.util.List;

import call.game.mod.event.EventHelper;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.KeyListener;

public class Keyboard implements KeyListener
{
	public static int KEY_DOWN = 0;
	public static int KEY_UP = 1;
	public static int KEY_REPEAT = 2;

	private List<KeyboardListener> listeners = new ArrayList<KeyboardListener>();

	public void registerKeyboardListener(KeyboardListener listener)
	{
		listeners.add(listener);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.isAutoRepeat())
		{
			for(int i = 0; i < listeners.size(); i++)
				listeners.get(i).onKey(KEY_REPEAT, e.getKeyCode());
		}
		
		EventHelper.sendEventToAll(new KeyTypedEvent(e.getKeyCode(), KEY_DOWN));
		
		for(int i = 0; i < listeners.size(); i++)
			listeners.get(i).onKey(KEY_DOWN, e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(!e.isPrintableKey() && e.isAutoRepeat())
			return;
		
		EventHelper.sendEventToAll(new KeyTypedEvent(e.getKeyCode(), KEY_UP));

		for(int i = 0; i < listeners.size(); i++)
			listeners.get(i).onKey(KEY_UP, e.getKeyCode());
	}
}
