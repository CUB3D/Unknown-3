package call.game.sound;

import java.io.File;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import call.game.main.FileHelper;

public class Music extends Sound
{
	private Sequence sound;
	private Sequencer player;
	
	public Music(String file)
	{
		try
		{
			sound = MidiSystem.getSequence(FileHelper.getStream(file));
			player = MidiSystem.getSequencer();
			
			player.setSequence(sound);
			player.open();

		}catch (Exception e) {e.printStackTrace();}
	}

	public Music(File file)
	{
		try
		{
			sound = MidiSystem.getSequence(file);
			player = MidiSystem.getSequencer();
			
			player.setSequence(sound);
			player.open();

		}catch (Exception e) {e.printStackTrace();}
	}
	
	@Override
	public void play()
	{
		super.play();
		player.start();
	}
	
	@Override
	public void stop()
	{
		super.stop();
		player.stop();
	}
	
	@Override
	public void setLooping(boolean looping)
	{
		super.setLooping(looping);
		
		if(isLooping())
			player.setLoopCount(-1);
		else
			player.setLoopCount(0);
	}
	
	@Override
	public boolean isFinished()
	{
		return !player.isRunning();
	}
}
