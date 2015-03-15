package call.game.image;
import java.awt.image.BufferedImage;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import call.game.main.Unknown;
import call.game.physicx.BoundingBox;
import call.game.physicx.IBounded;
import call.utils.ImageUtils;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class Image implements IBounded
{
	public static final float[] FLIP_BOTH = {
			
			1, 1,
			1, 0,
			0, 0,
			
			1, 1,
			0, 1,
			0, 0
	};
	
	public static final float[] FLIP_X = {
			
			1, 0,
			1, 1,
			0, 1,
			
			1, 0,
			0, 0,
			0, 1
	};
	
	public static final float[] FLIP_Y = {
		0, 1,
		0, 0,
		1, 0,
		
		0, 1,
		1, 1,
		1, 0
	};
	
	public static final float[] FLIP_NONE = {
		
		0, 0,
		0, 1,
		1, 1,
		
		0, 0,
		1, 0,
		1, 1
};
	
	
	private Texture texture;
	
	private int id;
	
	private float width;
	private float height;
	
	private float scale = 1.0f;
	
	private BufferedImage backend;
	
	private BoundingBox bounds = null;

	
	public Image(String name)
	{
		this.backend = ImageCache.getImage(name);
		
		this.id = TexturedVBO.getNextVBOID();
		
		init();
	}
	
	public Image(BufferedImage image)
	{
		this.backend = image;
		
		init();
	}

	public void init()
	{
		TexturedVBO.init();
		
		try {
			this.texture = AWTTextureIO.newTexture(Unknown.getGLProfile(), backend, true);
		} catch (Exception e) {e.printStackTrace();}	
		
		width = texture.getWidth() * scale;

		height = texture.getHeight() * scale;
		
		bounds = new BoundingBox(0, 0, width, height);

		float[] vertexes = {

				0, width,
				0, 0,
				height, 0,

				0, width,
				height, width,
				height, 0,
		};


		TexturedVBO.setVertexBuffer(id, vertexes);
		
		TexturedVBO.setTextureBuffer(id, FLIP_NONE);
	}


	public void render(double x, double y)
	{
		this.render(x, y, 0);
	}
	
	public void render(double x, double y, double angle)
	{
		Unknown.getImageBulkRenderer().addImage(new RenderData(this, x, y, angle));
	}
	
	public void render_raw(double x, double y, double angle)
	{
		GL2 gl = Unknown.getGL();
		float dx = width / 2;
		float dy = height / 2;
		
		
		gl.glTranslated(x + dx, y + dy, 0);
		
		gl.glRotated(angle, 0, 0, 1);
		gl.glTranslated(-dx, -dy, 0);
		
		// bind texture
		gl.glBindTexture(GL.GL_TEXTURE_2D, texture.getTextureObject()); // bind texture
		gl.glTexParameterf(GL.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);
		gl.glTexParameterf(GL.GL_TEXTURE_2D, GL2.GL_TEXTURE_MIN_FILTER, GL2.GL_NEAREST);
		// bind texture buffer
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, TexturedVBO.getBuffer(id, TexturedVBO.BUFFER_TEXTURE)); // bind texture buffer
		gl.glTexCoordPointer(2, GL.GL_FLOAT, 0, 0);
		//bind vertex buffer
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, TexturedVBO.getBuffer(id, TexturedVBO.BUFFER_VERTEX));
		gl.glVertexPointer(3, GL.GL_FLOAT, 0, 0);
		

		gl.glDrawArrays(GL.GL_TRIANGLES, 0, TexturedVBO.getSize(TexturedVBO.BUFFER_VERTEX, id));
		
		gl.glTranslated(dx, dy, 0);
		gl.glRotated(-angle, 0, 0, 1);
		
		gl.glTranslated(- x - dx, -y - dy, 0);
	}
	
	public void setTextureCoords(float[] data)
	{
		TexturedVBO.setTextureBuffer(this.id, data);
	}
	
	public Image setScale(float scale)
	{
		this.scale = scale;
		init();

		return this;
	}

	public float getScale()
	{
		return scale;
	}
	
	public BufferedImage getBackend()
	{
		return backend;
	}
	
	public int getWidth()
	{
		return backend.getWidth();
	}

	public int getHeight()
	{
		return backend.getHeight();
	}
	
	@Override
	public BoundingBox getBounds()
	{
		return bounds;
	}

	@Override
	public Object clone()
	{
		return new Image(ImageUtils.cloneImage(backend));
	}
}
