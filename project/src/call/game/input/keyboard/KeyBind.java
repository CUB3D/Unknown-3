package call.game.input.keyboard;

import call.game.main.Unknown;

public class KeyBind implements KeyboardListener
{
	private int keycode = -1;
	private boolean down = false;

	public KeyBind(int key)
	{
		this.keycode = key;

		Unknown.getKeyboard().registerKeyboardListener(this);
	}


	@Override
	public void onKey(int state, int keycode)
	{
		if(keycode == this.keycode)
			down = (state == Keyboard.KEY_DOWN || state == Keyboard.KEY_REPEAT);
	}

	public boolean isDown()
	{
		return down;
	}
}
