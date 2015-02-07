package call.game.template;

import call.game.state.StateEngine;
import call.game.state.StateInfo;

public class StateGame
{
	protected StateEngine stateEngine;
	
	public StateGame()
	{
		stateEngine = new StateEngine();
	}
	
	public void addState(Class<?> clas)
	{
		stateEngine.addState(clas);
	}
	
	public void setState(int i, StateInfo info)
	{
		stateEngine.setState(i, info);
	}
	
	public void setState(int i)
	{
		stateEngine.setState(i);
	}
	
	public void render()
	{
		stateEngine.renderState();
	}
	
	public void update()
	{
		stateEngine.updateState();
	}
}
