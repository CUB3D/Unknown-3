/**
 * Copyright 2015 Callum A. D. Thomson
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package call.game.main;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.media.opengl.GL2;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLProfile;

import call.game.image.ImageBulkRenderer;
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

/**
 * @author Callum, AKA: CUB3D
 */
public class Unknown
{
	private static GLProfile profile;
	
	/**
	 * The internal size of the window in pixels
	 */
	private static Dimension screenSize;

	/**
	 * The OpenGL instance currently in use
	 */
	private static GL2 gl;

	/**
	 * The keyboard instance
	 */
	private static Keyboard keyboard;
	
	/**
	 * The mouse instance
	 */
	private static Mouse mouse;

	/**
	 * The last recorded FPS
	 */
	private static int FPS;
	
	/**
	 * The last recorded TPS
	 */
	private static int TPS;
	
	private static ImageBulkRenderer imageBulkRenderer;
	
	private static GameSettings settings;
	
	private static boolean isDebuging = true;
	
	private static Map<EnumCallTime, Set<IUpdateable>> updateables = new HashMap<EnumCallTime, Set<IUpdateable>>();
	private static Map<EnumCallTime, Set<IRenderable>> renderables = new HashMap<EnumCallTime, Set<IRenderable>>();

	private static Logger logger = Logger.getLogger("UNKNOWN-CORE");

	public static void main(String[] args)
	{
		isDebuging = false;
		
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
			boolean autoClear = true;

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
					autoClear = game.getValue("autoClear").getBoolean(true);
				}

			}catch(IOException e) {
				e.printStackTrace();
				logger.warning("Failed to read GameInfo.call");
				System.exit(-3);
			}catch(ParseException e) {
				e.printStackTrace();
				logger.warning("Failed to parse value from GameInfo.call");
				System.exit(-4);
			}

			settings.setWidth(width);
			settings.setHeight(height);
			settings.setTitle(title);

			

			settings.setFps(maxFPS);
			settings.setTps(maxTPS);
			
			settings.setDisplaySettings(GameSettings.DISPLAY_NONE);
			
			if(vSync)
				settings.setDisplaySettings(settings.getDisplaySettings() | GameSettings.DISPLAY_VSYNC);
			
			if(autoClear)
				settings.setDisplaySettings(settings.getDisplaySettings() | GameSettings.DISPLAY_AUTOCLEAN);
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

		screenSize = new Dimension(width, height);

		profile = GLProfile.get(GLProfile.GL2);
		
		GLProfile.initSingleton();
		
		GLCapabilities caps = new GLCapabilities(profile);
		
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

		Unknown.settings = settings;
		
		window.addGLEventListener(new RenderHelper(width, height, clazz, settings));

		//create animator
		FPSAnimator ani = new FPSAnimator(fps);
		ani.add(window);
		ani.start();
	}
	
	public static boolean isDebugging()
	{
		return isDebuging;
	}

	public static void setTPS(int tPS)
	{
		TPS = tPS;
	}

	/**
	 * @return The last recorded TPS
	 */
	public static int getTPS()
	{
		return TPS;
	}

	public static void setFPS(int fPS)
	{
		FPS = fPS;
	}

	/**
	 * @return The last recorded FPS
	 */
	public static int getFPS()
	{
		return FPS;
	}

	/**
	 * @return A Dimension containing the internal screen size in pixels
	 */
	public static Dimension getScreenSize()
	{
		return screenSize;
	}

	public static void setGL(GL2 gl)
	{
		Unknown.gl = gl;
	}

	/**
	 * @return The current GL instance
	 */
	public static GL2 getGL()
	{
		return gl;
	}

	public static GameSettings getSettings()
	{
		return settings;
	}
	
	public static void registerUpdateable(IUpdateable update, EnumCallTime callTime)
	{
		if(callTime == EnumCallTime.NEVER)
			return;
		
		Set<IUpdateable> iUpdateables = updateables.get(callTime);
		
		if(iUpdateables == null)
			iUpdateables = new HashSet<IUpdateable>();
		
		iUpdateables.add(update);
		
		updateables.put(callTime, iUpdateables);
	}
	
	public static Map<EnumCallTime, Set<IUpdateable>> getUpdateables()
	{
		return updateables;
	}
	
	public static void registerRenderable(IRenderable render, EnumCallTime callTime)
	{
		if(callTime == EnumCallTime.NEVER)
			return;
		
		Set<IRenderable> iRenderables = renderables.get(callTime);
		
		if(iRenderables == null)
			iRenderables = new HashSet<IRenderable>();
		
		iRenderables.add(render);
		
		renderables.put(callTime, iRenderables);
	}
	
	public static Map<EnumCallTime, Set<IRenderable>> getRenderables()
	{
		return renderables;
	}

	/**
	 * @return The current GL profile
	 */
	public static GLProfile getGLProfile()
	{
		return profile;
	}

	/**
	 * @return The keyboard instance
	 */
	public static Keyboard getKeyboard()
	{
		if(keyboard == null)
			Unknown.keyboard = new Keyboard();

		return keyboard;
	}

	/**
	 * @return The mouse instance
	 */
	public static Mouse getMouse()
	{
		if(mouse == null)
			Unknown.mouse = new Mouse();

		return mouse;
	}
	
	public static ImageBulkRenderer getImageBulkRenderer()
	{
		if(imageBulkRenderer == null)
			Unknown.imageBulkRenderer = new ImageBulkRenderer();
		
		return imageBulkRenderer;
	}
}
