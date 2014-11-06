package call.game.geom;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

import call.game.main.Unknown;
import call.game.utils.Vec2;

public class GLPolygon
{
	private List<Vec2.Double> vertexes = new ArrayList<Vec2.Double>();
	
	public GLPolygon() {}
	
	public GLPolygon(Vec2.Double[] points)
	{
		for(Vec2.Double d : points)
			vertexes.add(d);
	}
	
	public void render(int x, int y)
	{
		GL2 gl = Unknown.getGL();
		
		gl.glBegin(GL2.GL_POLYGON);
		
		gl.glColor3f(1, 1, 0);
		

		for(Vec2.Double d : vertexes)
			gl.glVertex2d(x + d.getX(), y + d.getY());
		
		gl.glEnd();
	}
	
	public void addVertex(Vec2.Double vertex)
	{
		vertexes.add(vertex);
	}
}
