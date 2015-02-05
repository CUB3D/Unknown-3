package call.game.main;

import java.lang.reflect.Method;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import call.game.entitys.EntityHandler;
import call.game.entitys.particle.ParticleHandler;
import call.game.input.keyboard.KeyBind;
import call.game.mod.GameRegistery;

public class RenderHelper implements GLEventListener
{
	private int width;
	private int height;

	private Method update;
	private Method render;
	private Method init;
	private Object mainClassInstance;

	private long startTime = 0;
	private double unprocesed = 0;
	private double tickSpeed = 0;
	private int frames = 0;
	private int ticks = 0;
	private long timer = 0;
	
	private GameSettings settings;

	public RenderHelper(int width, int height, Class<?> claz, GameSettings settings)
	{
		this.settings = settings;
		
		this.width = width;
		this.height = height;

		this.startTime = System.nanoTime();
		this.timer = System.currentTimeMillis();
		this.tickSpeed = 1000000000.0 / settings.getTps();

		render = ClassUtils.getDefinedMethod("Render", claz);
		update = ClassUtils.getDefinedMethod("Update", claz);
		init = ClassUtils.getDefinedMethod("Init", claz);

		try
		{
			mainClassInstance = claz.newInstance();
		}catch(Exception e) {e.printStackTrace();}

		KeyBind.loadBinds();
	}

	@Override
	public void display(GLAutoDrawable draw)
	{
		long time = System.nanoTime();
		unprocesed += (time - startTime) / tickSpeed;
		startTime = time;

		while(unprocesed >= 1)
		{
			ticks++;
			tick();

			unprocesed--;
		}

		frames++;
		render(draw);

		if(System.currentTimeMillis() - timer > 1000)
		{
			timer = System.currentTimeMillis();

			Unknown.setFPS(frames);
			Unknown.setTPS(ticks);

			System.out.println("FPS: " + frames + ", TPS: " + ticks);
			frames = 0;
			ticks = 0;
		}
	}

	public void tick()
	{
		GameRegistery.getInstance().onTickStart();

		if(update != null)
		{
			try
			{
				update.invoke(mainClassInstance, (Object[]) null);
			}catch(Exception e) {e.printStackTrace();}
		}

		EntityHandler.updateEntitys();
		ParticleHandler.updateParticles();

		GameRegistery.getInstance().onTickEnd();
	}

	public void render(GLAutoDrawable draw)
	{
		GL2 gl = draw.getGL().getGL2();

		if(gl == null)
			return;

		Unknown.setGL(gl);

		if(render != null)
		{
			try
			{
				render.invoke(mainClassInstance, (Object[]) null);
			}catch(Exception e) {e.printStackTrace();}
		}

		EntityHandler.renderEntitys();
		ParticleHandler.renderParticles();

		GameRegistery.getInstance().onRender();
	}

	public void initTextures()
	{
		if(init != null)
		{
			try
			{
				init.invoke(mainClassInstance, (Object[]) null);
			}catch(Exception e) {e.printStackTrace();}
		}
	}

	@Override
	public void dispose(GLAutoDrawable arg0) {}

	@Override
	public void init(GLAutoDrawable auto)
	{
		GL2 gl = auto.getGL().getGL2();

		Unknown.setGL(gl);

		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST); // best perspective correction
		gl.glShadeModel(GL2.GL_SMOOTH);

		initTextures();
	}

	@Override
	public void reshape(GLAutoDrawable draw, int arg1, int arg2, int arg3, int arg4)
	{
		//create orthographic view
		GL2 gl = draw.getGL().getGL2();

		gl.glViewport(0, 0, width, height);

		gl.glOrtho(0, width, 0, height, 0, 1);

		if(settings.getvSync())
			gl.setSwapInterval(1); // enable v-sync
	}
}
