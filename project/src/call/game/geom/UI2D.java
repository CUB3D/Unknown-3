package call.game.geom;

import java.awt.Point;

import javax.media.opengl.GL2;

import call.game.main.Unknown;

public class UI2D
{
	public static void rect(int x, int y, int width, int height, int col)
	{
		GL2 gl = Unknown.getGL();

		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glBegin(GL2.GL_QUADS);

		gl.glColor4f(red / 255, green / 255, blue / 255, alpha / 255);

		gl.glVertex2f(x, y);
		gl.glVertex2f(x, y + height);
		gl.glVertex2f(x + width, y + height);
		gl.glVertex2f(x + width, y);

		gl.glEnd();
	}

	public static void square(int x, int y, int size, int col)
	{
		rect(x, y, size, size, col);
	}

	public static void triangle(int x, int y, int width, int height, int col)
	{
		GL2 gl = Unknown.getGL();

		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glBegin(GL2.GL_TRIANGLES);

		gl.glColor4f(red / 255, green / 255, blue / 255, alpha / 255);

		gl.glVertex2f(x + width / 2, y + height);
		gl.glVertex2f(x, y);
		gl.glVertex2f(x + width, y);

		gl.glEnd();
	}

	public static void polygon(int x, int y, Point[] verts)
	{
		GL2 gl = Unknown.getGL();

		gl.glBegin(GL2.GL_POLYGON);

		gl.glColor3f(1, 1, 0);


		for(Point d : verts)
			gl.glVertex2d(x + d.getX(), y + d.getY());

		gl.glEnd();
	}
}
