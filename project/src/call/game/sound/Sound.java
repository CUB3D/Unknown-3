package call.game.sound;


public class Sound implements IPlayable
{
	private boolean isPlaying;
	private boolean isLooping;

	@Override
	public void play()
	{
		isPlaying = true;
	}

	@Override
	public void stop()
	{
		isPlaying = false;
	}

	@Override
	public boolean isPlaying()
	{
		return isPlaying;
	}

	@Override
	public void setLooping(boolean looping)
	{
		this.isLooping = looping;
	}

	@Override
	public boolean isLooping()
	{
		return isLooping;
	}

	@Override
	public boolean isFinished()
	{
		return !isPlaying;
	}

	@Override
	public void reset() {}
}
