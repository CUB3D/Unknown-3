package call.game.input.keyboard;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import call.game.main.Unknown;
import cub3d.file.main.Element;
import cub3d.file.main.FileAPI;
import cub3d.file.main.Value;
import cub3d.file.reader.BasicReader;
import cub3d.file.reader.CallReader;
import cub3d.file.writer.BasicWriter;
import cub3d.file.writer.CallWriter;

public class KeyBind implements KeyboardListener
{
	public static Map<String, KeyBind> keybindings = new HashMap<String, KeyBind>();

	private String bindName;
	private int defaultKey = -1;
	private int realKey = -1;
	private boolean down = false;

	private List<KeyBindListener> listeners = new ArrayList<KeyBindListener>();

	public KeyBind(String name, int defaultKey)
	{
		this.bindName = name;
		this.defaultKey = defaultKey;

		Unknown.getKeyboard().registerKeyboardListener(this);

		keybindings.put(name, this);
	}


	@Override
	public void onKey(int state, int keycode)
	{
		if(keycode == this.defaultKey && this.realKey == -1)
			onKeyPress(state, keycode);

		if(this.realKey != -1 && keycode == this.realKey)
			onKeyPress(state, keycode);
	}

	private void onKeyPress(int state, int keycode)
	{
		if(state == Keyboard.KEY_DOWN)
		{
			for(KeyBindListener kbl : listeners)
				kbl.onKeyPressed();

			down = true;
		}

		if(state == Keyboard.KEY_UP)
		{
			for(KeyBindListener kbl : listeners)
				kbl.onKeyRealeased();

			down = false;
		}
	}

	public void setRealKey(int realKey)
	{
		this.realKey = realKey;
	}

	public boolean isDown()
	{
		return down;
	}

	public String getName()
	{
		return bindName;
	}

	public int getRealKey()
	{
		return realKey;
	}

	public void registerKeybindListener(KeyBindListener kbl)
	{
		this.listeners.add(kbl);
	}


	public static void saveBinds()
	{
		FileAPI api = new FileAPI("KeyBindings.call");

		try
		{
			api.createFile();
		}catch(IOException ioe) {
			System.out.println("Failed to create KeyBindings.call: ");
			ioe.printStackTrace();
		}


		CallWriter cw = null;

		try
		{
			cw = new CallWriter(new BasicWriter(api.getWriter()));


			for(KeyBind bind : keybindings.values())
			{
				Element e = new Element(bind.getName());

				e.addValue(new Value("realKey", "" + bind.realKey));

				cw.writeElement(e);
			}

		}catch(Exception e) {
			System.out.println("Error saving keybinds: ");
			e.printStackTrace();
		}finally {
				cw.cleanup();
		}
	}

	public static void loadBinds()
	{
		File f = new File("KeyBindings.call");

		if(!f.exists())
			return;

		FileAPI api = new FileAPI(f);

		try
		{
			CallReader binds = new CallReader(new BasicReader(api.getReader()));

			for(Element e : binds.getElements())
			{
				String name = e.getName();
				int realKey = e.getValue("realKey").getInt();

				KeyBind bind = keybindings.get(name);

				if(bind != null)
					bind.setRealKey(realKey);
			}

		}catch(Exception e) {
			System.out.println("Coulden't read KeyBinds.call");
			e.printStackTrace();
		}
	}
}
