package call.game.main;

import java.awt.Dimension;
import java.io.File;
import java.lang.annotation.Annotation;
import java.util.logging.Logger;

import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import call.file.api.CFile;
import call.file.layout.Element;
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
		
		if(f.exists())
		{
			CFile cf = new CFile(f);
			
			Element window = cf.getElementByName("Window");
			
			settings.setWidth(window.getValue("Width").getInt(0));
			settings.setHeight(window.getValue("Height").getInt(0));
			settings.setTitle(window.getValue("Title").getValue("A Unknown 3.0 game"));
			
			Element game = cf.getElementByName("Game");
			
			settings.setFps(game.getValue("MaxFPS").getInt(120));
			settings.setTps(game.getValue("MaxTPS").getInt(60));
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
		int tps = settings.getTps();
		int fps = settings.getFps();
		
		//System.out.println("width" + width);

		setScreenSize(new Dimension(width, width));

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

		window.addGLEventListener(new RenderHelper(width, height, clazz, tps));

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
