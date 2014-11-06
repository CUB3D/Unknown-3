package call.game.input;


import java.util.HashMap;

import call.game.input.keyboard.Keyboard;
import call.game.input.keyboard.KeyboardListener;

public class Input implements KeyboardListener
{
	private static HashMap<Integer, Boolean> states = new HashMap<Integer, Boolean>();
	
	@Override
	public void onKey(int state, int keycode)
	{
		states.put(keycode, state == Keyboard.KEY_DOWN);
	}

	public static boolean isPressed(int code)
	{
		boolean b = (states.get(code) == null ? false : states.get(code));
		return b;
	}
}
