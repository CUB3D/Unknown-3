package call.game.geom;

import javax.media.opengl.GL2;

import call.game.main.Unknown;

public class GLRect
{
	private int width;
	private int height;
	
	public GLRect(int width, int height)
	{
		this.width = width;
		this.height = height;
	}
	
	public void render(int x, int y)
	{
		GL2 gl = Unknown.getGL();
		
		gl.glBegin(GL2.GL_QUADS);
		
		gl.glColor3f(1, 1, 0);
		
		gl.glVertex2f(x, y);
		gl.glVertex2f(x, y + height);
		gl.glVertex2f(x + width, y + height);
		gl.glVertex2f(x + width, y);
		
		gl.glEnd();
	}
}
