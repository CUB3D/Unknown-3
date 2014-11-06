package call.game.mod.event;

public class CMLEvent extends Event
{
	private ModState curState;
	
	public CMLEvent(ModState state)
	{
		this.curState = state;
	}
	
	public ModState getState()
	{
		return curState;
	}
}
