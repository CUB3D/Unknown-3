package call.game.sound;

public interface IPlayable
{
	void play();
	void stop();
	boolean isPlaying();
	boolean isFinished();
	void setLooping(boolean looping);
	boolean isLooping();
	void reset();
}
