package call.game.input.keyboard;

import call.game.mod.event.CMLEvent;
import call.game.mod.event.ModState;

public class KeyTypedEvent extends CMLEvent
{
	private int key;
	private int state;
	
	public KeyTypedEvent(int key, int state)
	{
		super(ModState.RUNNING);
		this.key = key;
		this.state = state;
	}

	public int getKey()
	{
		return key;
	}
	
	public int getKeyState()
	{
		return state;
	}
}
