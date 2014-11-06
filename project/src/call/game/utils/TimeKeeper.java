package call.game.utils;

public class TimeKeeper
{
	private int nanoSecs;
	private long lastTime = -1;
	
	public TimeKeeper(double seconds)
	{
		this.nanoSecs = (int) (1000000000 * seconds);
		
		this.lastTime = System.nanoTime();
	}
	
	public boolean isTickCompleted()
	{
		if(System.nanoTime() - lastTime > nanoSecs)
		{
			lastTime = System.nanoTime();
			
			return true;
		}
		
		return false;
	}

	public double getDuration() 
	{
		return nanoSecs / 1000000000;
	}
}
