package call.game.input.keyboard;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import call.file.api.CFile;
import call.file.api.FileAPI;
import call.file.layout.Element;
import call.file.layout.Value;
import call.game.main.Unknown;

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
		down = (state == Keyboard.KEY_DOWN || state == Keyboard.KEY_REPEAT);
		
		System.out.println(down);

		for(KeyBindListener kbl : listeners)
			if(down)
				kbl.onKeyPressed();
			else
				kbl.onKeyRealeased();
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
		File f = new File("KeyBindings.call");

		try
		{
			f.createNewFile();
		}catch(Exception e) {e.printStackTrace();}

		CFile binds = new CFile(new FileAPI(f));

		for(KeyBind bind : keybindings.values())
		{
			Element e = new Element(bind.getName());

			e.addValue(new Value("realKey", "" + bind.realKey));

			binds.addElement(e);
		}

		binds.save();
	}

	public static void loadBinds()
	{
		File f = new File("KeyBindings.call");

		if(!f.exists())
			return;

		CFile binds = new CFile(new FileAPI(f));

		binds.load();

		for(Element e : binds.getElements())
		{
			String name = e.getName();
			int realKey = e.getValue("realKey").getInt();

			KeyBind bind = keybindings.get(name);

			if(bind != null)
				bind.setRealKey(realKey);
		}
	}
}
