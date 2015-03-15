package call.game.main;


public class GameSettings
{
	public static final int DISPLAY_NONE = 1;
	public static final int DISPLAY_VSYNC = 2;
	public static final int DISPLAY_AUTOCLEAN = 4;
	
	private int width;
	private int height;
	private String title;
	private int tps;
	private int fps;
	private int displaySettings;
	
	private int requiredVBOSpace;
	
	public GameSettings()
	{
		this(10, 10);
	}
	
	public GameSettings(int width, int heigth)
	{
		this(width, heigth, "");
	}
	
	public GameSettings(int width, int height, String title)
	{
		this(width, height, title, 120, 60, 0);
	}
	
	public GameSettings(int width, int height, String title, int fps, int tps, int displaySettings)
	{
		this(width, height, title, fps, tps, displaySettings, 100);
	}
	
	public GameSettings(int width, int height, String title, int fps, int tps, int displaySettings, int VBOSpace)
	{
		this.width = width;
		this.height = height;
		this.title = title;
		this.fps = fps;
		this.tps = tps;
		this.displaySettings = displaySettings;
		this.requiredVBOSpace = VBOSpace;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setFps(int fps)
	{
		this.fps = fps;
	}
	
	public int getFps()
	{
		return fps;
	}
	
	public void setTps(int tps)
	{
		this.tps = tps;
	}
	
	public int getTps()
	{
		return tps;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setDisplaySettings(int displaySettings)
	{
		this.displaySettings = displaySettings;
	}
	
	public int getDisplaySettings()
	{
		return displaySettings;
	}
	
	public void setRequiredVBOSpace(int requiredVBOSpace)
	{
		this.requiredVBOSpace = requiredVBOSpace;
	}
	
	public int getRequiredVBOSpace()
	{
		return requiredVBOSpace;
	}
}
