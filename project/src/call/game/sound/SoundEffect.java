package call.game.sound;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import call.game.main.FileHelper;

public class SoundEffect extends Sound
{
	private Clip clip;
	private AudioInputStream stream;
	private AudioFormat form;
	private byte[] data;

	public SoundEffect(String file)
	{
		try
		{	
			stream = AudioSystem.getAudioInputStream(FileHelper.getURL(file));

			form = stream.getFormat();

			data = new byte[(int)stream.getFrameLength() * form.getFrameSize()];

			byte[] buf = new byte[1024];
			
			for (int i=0; i < data.length; i += 1024)
			{
				int r = stream.read(buf, 0, buf.length);
				
				if (i + r >= data.length)
					r = i + r - data.length;

				System.arraycopy(buf, 0, data, i, r);
			}
			
			stream.close();
			
		}catch (Exception e) {e.printStackTrace();}
	}

	@Override
	public void play()
	{
		super.play();
		
		try
		{
			clip = AudioSystem.getClip();
			clip.open(form, data, 0, data.length);
		}catch(Exception e) {e.printStackTrace();}

		clip.start();
	}

	@Override
	public void stop()
	{
		super.stop();
		clip.stop();
	}

	@Override
	public void setLooping(boolean looping)
	{
		super.setLooping(looping);
		if(isLooping())
			clip.loop(-1);
		else
			clip.loop(0);
	}

	@Override
	public boolean isFinished()
	{
		return !clip.isRunning();
	}

	@Override
	public void reset()
	{
		super.reset();
	}
}
