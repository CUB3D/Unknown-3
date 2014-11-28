package call.game.main;

import java.awt.Dimension;

import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import call.game.input.keyboard.KeyBind;
import call.game.input.keyboard.Keyboard;
import call.game.input.mouse.Mouse;
import call.game.mod.CALLModLoader;

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
	
	public static void main(String[] args)
	{
		//TODO load jar file
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
