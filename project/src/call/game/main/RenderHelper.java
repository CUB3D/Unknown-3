package call.game.main;

import java.lang.reflect.Method;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;

import call.game.entitys.EntityHandler;

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

	public RenderHelper(int width, int height, Class<?> claz, int tps)
	{
		this.width = width;
		this.height = height;

		this.startTime = System.nanoTime();
		this.timer = System.currentTimeMillis();
		this.tickSpeed = 1000000000.0 / tps;

		render = ClassUtils.getDefinedMethod("Render", claz);
		update = ClassUtils.getDefinedMethod("Update", claz);
		init = ClassUtils.getDefinedMethod("Init", claz);

		try
		{
			mainClassInstance = claz.newInstance();
		}catch(Exception e) {e.printStackTrace();}
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
			System.out.println("FPS: " + frames + ", TPS: " + ticks);
			frames = 0;
			ticks = 0;
		}
	}

	public void tick()
	{
		if(update != null)
		{
			try
			{
				update.invoke(mainClassInstance, (Object[]) null);
			}catch(Exception e) {e.printStackTrace();}
		}
		
		EntityHandler.updateEntitys();
	}

	public void render(GLAutoDrawable draw)
	{
		if(render != null)
		{
			GL2 gl = draw.getGL().getGL2();

			Unknown.setGL(gl);

			try
			{
				render.invoke(mainClassInstance, (Object[]) null);
			}catch(Exception e) {e.printStackTrace();}
		}
		
		EntityHandler.renderEntitys();
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
	public void init(GLAutoDrawable arg0)
	{
		initTextures();
	}

	@Override
	public void reshape(GLAutoDrawable draw, int arg1, int arg2, int arg3, int arg4)
	{
		//create orthographic view
		GL2 gl = draw.getGL().getGL2();

		gl.glOrtho(0, width, 0, height, 0, 1);

		//	gl.setSwapInterval(1); // enable v-sync
	}
}
