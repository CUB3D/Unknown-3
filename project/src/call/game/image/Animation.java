package call.game.image;

import java.util.ArrayList;
import java.util.List;

import call.game.utils.TimeKeeper;

public class Animation
{
	private List<Image> frames = new ArrayList<Image>();
	private TimeKeeper timer;
	private int index = 0;
	private int maxindex;
	private boolean loop;

	public Animation(double duration)
	{
		this(duration, false);
	}

	public Animation(double duration, boolean loop)
	{
		this(null, duration, loop);
	}

	public Animation(Image[] frames, double duration)
	{
		this(frames, duration, false);
	}

	public Animation(Image[] frames, double duration, boolean loop)
	{
		this.timer = new TimeKeeper(duration);
		this.loop = loop;

		if(frames != null)
		{
			for(Image i : frames)
				addFrame(i);
		}
	}

	public void advance()
	{
		if(timer.isTickCompleted())
		{
			index++;

			if(willLoop())
				if(index >= maxindex)
					index = 0;
		}
	}

	public void render(int x, int y)
	{
		if(index < maxindex)
			getFrame(index).render(x, y);
	}

	public void renderAndAdvance(int x, int y)
	{
		render(x, y);
		advance();
	}

	public void addFrame(Image i)
	{
		frames.add(i);
		this.maxindex++;
	}

	public Image getFrame(int i)
	{
		return frames.get(i);
	}

	public int getIndex()
	{
		return index;
	}

	public List<Image> getFrames()
	{
		return frames;
	}

	public int getMaxindex()
	{
		return maxindex;
	}
	
	public boolean willLoop()
	{
		return this.loop;
	}
	
	public TimeKeeper getTimer()
	{
		return timer;
	}
}
