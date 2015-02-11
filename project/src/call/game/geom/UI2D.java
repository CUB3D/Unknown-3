package call.game.geom;

import java.awt.Font;
import java.awt.Point;

import javax.media.opengl.GL2;

import call.game.main.Unknown;

import com.jogamp.opengl.util.awt.TextRenderer;

public class UI2D
{
	public static void rect(double x, double y, double width, double height)
	{
		rect(x, y, width, height, 0xFFFFFFFF);
	}
	
	public static void rect(double x, double y, double width, double height, int col)
	{
		GL2 gl = Unknown.getGL();

		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glBegin(GL2.GL_QUADS);

		gl.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);

		gl.glVertex2d(x, y);
		gl.glVertex2d(x, y + height);
		gl.glVertex2d(x + width, y + height);
		gl.glVertex2d(x + width, y);

		gl.glEnd();
	}
	
	public static void outlineRect(double x, double y, double width, double height, int col)
	{
		int linewidth = 8;
		
		UI2D.line(x, y, x, y + height, linewidth, col);
		
		UI2D.line(x, y, x + width, y, linewidth, col);
		
		UI2D.line(x, y + height, x + width, y + height, linewidth, col);
		
		UI2D.line(x + width, y, x + width, y + height, linewidth, col);
	}

	public static void square(double x, double y, double size)
	{
		square(x, y, size, 0xFFFFFFFF);
	}
	
	public static void square(double x, double y, double size, int col)
	{
		rect(x, y, size, size, col);
	}
	
	public static void triangle(double x, double y, double width, double height)
	{
		triangle(x, y, width, height, 0xFFFFFFFF);
	}

	public static void triangle(double x, double y, double width, double height, int col)
	{
		GL2 gl = Unknown.getGL();

		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glBegin(GL2.GL_TRIANGLES);

		gl.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);

		gl.glVertex2d(x + width / 2, y + height);
		gl.glVertex2d(x, y);
		gl.glVertex2d(x + width, y);

		gl.glEnd();
	}
	
	public static void polygon(double x, double y, Point[] verts)
	{
		polygon(x, y, verts, 0xFFFFFFFF);
	}

	public static void polygon(double x, double y, Point[] verts, int col)
	{
		GL2 gl = Unknown.getGL();
		
		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glBegin(GL2.GL_POLYGON);

		gl.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);

		for(Point d : verts)
			gl.glVertex2d(x + d.getX(), y + d.getY());

		gl.glEnd();
	}
	
	public static void line(double x1, double y1, double x2, double y2, float width)
	{
		line(x1, y1, x2, y2, width, 0xFFFFFFFF);
	}
	
	public static void line(double x1, double y1, double x2, double y2, float width, int col)
	{
		GL2 gl = Unknown.getGL();

		int blue = (col >> 0) & 0xFF;
		int green = (col >> 8) & 0xFF;
		int red = (col >> 16) & 0xFF;
		int alpha = (col >> 24) & 0xFF;

		gl.glDisable(GL2.GL_LIGHTING);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		gl.glBegin(GL2.GL_LINES);
		
		gl.glLineWidth(width);
		
		gl.glColor4f(red / 255.0f, green / 255.0f, blue / 255.0f, alpha / 255.0f);

		gl.glVertex2d(x1, y1);
		gl.glVertex2d(x2, y2);
		
		gl.glEnd();
	}
	
	public static void renderText(String text, int x, int y)
	{
		renderText(text, x, y, new Font("Serif", Font.PLAIN, 11));
	}
	
	public static void renderText(String text, int x, int y, Font font)
	{
		TextRenderer tr = new TextRenderer(font);

		GL2 gl = Unknown.getGL();
		
		tr.begin3DRendering();
		
		gl.glPushMatrix();
		
		tr.draw(text, x, y);
		
		tr.flush();
		
		gl.glPopMatrix();
		
		tr.end3DRendering(); 
	}
}
