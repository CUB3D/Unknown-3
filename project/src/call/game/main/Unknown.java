package call.game.main;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.logging.Logger;

import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import call.game.input.keyboard.KeyBind;
import call.game.input.keyboard.Keyboard;
import call.game.input.mouse.Mouse;
import call.game.mod.CALLModLoader;
import call.game.mod.ModDiscoverer;
import call.game.mod.ModEntry;

import com.jogamp.newt.event.WindowEvent;
import com.jogamp.newt.event.WindowListener;
import com.jogamp.newt.event.WindowUpdateEvent;
import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.util.FPSAnimator;

import cub3d.file.main.Element;
import cub3d.file.main.FileAPI;
import cub3d.file.main.ParseException;
import cub3d.file.reader.BasicReader;
import cub3d.file.reader.CallReader;

public class Unknown
{
	private static GLProfile profile;

	private static Dimension screenSize;

	private static GL2 gl;

	private static Keyboard keyboard;
	private static Mouse mouse;

	private static int FPS;
	private static int TPS;

	private static Logger logger = Logger.getLogger("UNKNOWN-CORE");

	public static void main(String[] args)
	{
		ModEntry game = ModDiscoverer.loadMod(new File("core/Game.jar"), new File("core"));

		Class<?> clazz = null;

		for(Class<?> claz : game.getClasses())
		{
			Annotation[] anos = claz.getAnnotations();

			for(int i = 0;i < anos.length;i++)
			{
				Class<?>[] interfaces = anos[i].getClass().getInterfaces();

				for(int i2 = 0; i2 < interfaces.length; i2++)
				{
					System.out.println(interfaces[i2].getSimpleName());
					if(interfaces[i2].getSimpleName().equals("Define"))
						clazz = claz;
				}
			}
		}

		if(clazz == null)
			clazz = game.getClassWithAnnotation(Define.class);

		if(clazz == null)
		{
			logger.warning("No Game.jar present");
			System.exit(-1);
		}

		init(clazz);
	}

	public static void init(Class<?> clazz)
	{
		GameSettings settings = new GameSettings();

		File f = new File("GameInfo.call");

		FileAPI api = new FileAPI(f);

		if(f.exists())
		{
			CallReader reader = null;

			int height = 0;
			int width = 0;
			String title = "A Unknown 3.0 game";
			
			int maxFPS = 120;
			int maxTPS = 60;
			boolean vSync = false;

			try
			{
				reader = new CallReader(new BasicReader(api.getReader()));

				Element window = reader.getElementByName("Window");

				if(window != null)
				{
					height = window.getValue("Height").getInt();
					width = window.getValue("Width").getInt();
					title = window.getValue("Title").getString("A Unknown 3.0 game");
				}
				
				Element game = reader.getElementByName("Game");
				
				if(game != null)
				{
					maxFPS = game.getValue("MaxFPS").getInt(120);
					maxTPS = game.getValue("MaxTPS").getInt(60);
					vSync = game.getValue("vSync").getBoolean(false);
				}

			}catch(IOException e) {
				e.printStackTrace();
				logger.warning("Failed to read GameInfo.call");
				System.exit(-3);
			}catch(ParseException e) {
				e.printStackTrace();
				logger.warning("Failed to pharse value from GameInfo.call");
				System.exit(-4);
			}

			settings.setWidth(width);
			settings.setHeight(height);
			settings.setTitle(title);

			

			settings.setFps(maxFPS);
			settings.setTps(maxTPS);
			settings.setvSync(vSync);
		}
		else
		{
			logger.warning("No GameInfo.call, game cannot be loaded");
			System.exit(-2);
		}

		init(clazz, settings);
	}

	public static void init(Class<?> clazz, GameSettings settings)
	{
		// load mods
		CALLModLoader.getInstance();



		int width = settings.getWidth();
		int height = settings.getHeight();
		String title = settings.getTitle();
		int fps = settings.getFps();

		//System.out.println("width" + width);

		setScreenSize(new Dimension(width, height));

		profile = GLProfile.get(GLProfile.GL2);
		GLProfile.initSingleton();
		GLCapabilities caps = new GLCapabilities(profile);
		// create JOGL frame
		GLWindow window = GLWindow.create(caps);
		window.setSize(width, height);

		window.addKeyListener(Unknown.getKeyboard());
		window.addMouseListener(Unknown.getMouse());

		window.setTitle(title);
		window.setVisible(true);

		window.addWindowListener(new WindowListener() {

			@Override
			public void windowResized(WindowEvent arg0) {}

			@Override
			public void windowRepaint(WindowUpdateEvent arg0) {}

			@Override
			public void windowMoved(WindowEvent arg0) {}

			@Override
			public void windowLostFocus(WindowEvent arg0) {}

			@Override
			public void windowGainedFocus(WindowEvent arg0) {}

			@Override
			public void windowDestroyed(WindowEvent arg0) {}

			@Override
			public void windowDestroyNotify(WindowEvent arg0)
			{
				KeyBind.saveBinds();
				System.exit(0);
			}
		});

		window.addGLEventListener(new RenderHelper(width, height, clazz, settings));

		//create animator
		FPSAnimator ani = new FPSAnimator(fps);
		ani.add(window);
		ani.start();
	}

	public static void setTPS(int tPS)
	{
		TPS = tPS;
	}

	public static int getTPS()
	{
		return TPS;
	}

	public static void setFPS(int fPS)
	{
		FPS = fPS;
	}

	public static int getFPS()
	{
		return FPS;
	}

	public static void setScreenSize(Dimension screenSize)
	{
		Unknown.screenSize = screenSize;
	}

	public static Dimension getScreenSize()
	{
		return screenSize;
	}

	public static void setGL(GL2 gl)
	{
		Unknown.gl = gl;
	}

	public static GL2 getGL()
	{
		return gl;
	}

	public static GLProfile getGLProfile()
	{
		return profile;
	}

	public static Keyboard getKeyboard()
	{
		if(keyboard == null)
			Unknown.keyboard = new Keyboard();

		return keyboard;
	}

	public static Mouse getMouse()
	{
		if(mouse == null)
			Unknown.mouse = new Mouse();

		return mouse;
	}
}
