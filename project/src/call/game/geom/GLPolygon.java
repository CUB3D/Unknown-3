package call.game.geom;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

import call.game.main.Unknown;

public class GLPolygon
{
	private List<Point> vertexes = new ArrayList<Point>();
	
	public GLPolygon() {}
	
	public GLPolygon(Point[] points)
	{
		for(Point d : points)
			vertexes.add(d);
	}
	
	public void render(int x, int y)
	{
		GL2 gl = Unknown.getGL();
		
		gl.glBegin(GL2.GL_POLYGON);
		
		gl.glColor3f(1, 1, 0);
		

		for(Point d : vertexes)
			gl.glVertex2d(x + d.getX(), y + d.getY());
		
		gl.glEnd();
	}
	
	public void addVertex(Point vertex)
	{
		vertexes.add(vertex);
	}
}
