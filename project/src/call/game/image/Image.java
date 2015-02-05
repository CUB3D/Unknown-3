package call.game.image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.media.opengl.GL2;

import call.game.main.Unknown;
import call.game.physicx.BoundingBox;
import call.game.physicx.IBounded;
import call.utils.ImageUtils;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class Image implements IBounded
{
	public static final int FLIP_Y = 0x1;
	public static final int FLIP_X = 0x2;

	private Texture text = null;
	private int flipData = -1;

	private BoundingBox bounds = null;

	private BufferedImage backend = null;

	private boolean hasInit = false;

	private float scale = 1;


	public Image(String s)
	{
		backend = ImageCache.getImage(s);
	}

	public Image(BufferedImage img)
	{
		this.backend = img;
	}

	public void init()
	{
		GL2 gl = Unknown.getGL();

		AffineTransform at = AffineTransform.getScaleInstance(1, -1);
		at.translate(0, -backend.getHeight());
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

		this.backend = op.filter(backend, null);

		text = AWTTextureIO.newTexture(Unknown.getGLProfile(), backend, false);
		text.bind(gl);

		bounds = new BoundingBox(0, 0, text.getImageWidth() * scale, text.getImageHeight() * scale);

		hasInit = true;
	}

	public void render(int x, int y)
	{
		this.render(x, y, 0);
	}

	public void render(int x, int y, double angle)
	{
		if(!hasInit)
			init();

		bounds.x = x;
		bounds.y = y;

		GL2 gl = Unknown.getGL();

		text.enable(gl);

		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

		text.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);


		gl.glMatrixMode(GL2.GL_TEXTURE);
		gl.glLoadIdentity();
		gl.glTranslated(0.5, 0.5, 0);
		gl.glRotated(angle, 0, 0, 1.0);
		gl.glTranslated(-0.5, -0.5, 0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);

		gl.glColor3f(1, 1, 1);

		gl.glBegin(GL2.GL_QUADS);

		if((flipData | FLIP_X) == FLIP_X && (flipData | FLIP_Y) == FLIP_Y)
			renderFlipBoth(gl, x, y);
		else
			if((flipData | FLIP_Y) == FLIP_Y)
				renderFlipY(gl, x, y);
			else
				if((flipData | FLIP_X) == FLIP_X)
					renderFlipX(gl, x, y);
				else
					renderNoManipulate(gl, x, y);
		gl.glEnd();

		text.disable(gl);
	}

	public void renderFlipBoth(GL2 gl, float x, float y)
	{
		gl.glTexCoord2f(1, 1);
		gl.glVertex2f(x, y);

		gl.glTexCoord2f(1, 0);
		gl.glVertex2f(x, (float) (y + bounds.getHeight()));

		gl.glTexCoord2f(0, 0);
		gl.glVertex2f((float) (x + bounds.getWidth()), (float) (y + bounds.getHeight()));

		gl.glTexCoord2f(0, 1);
		gl.glVertex2f((float) (x + bounds.getWidth()), y);
	}

	public void renderFlipY(GL2 gl, float x, float y)
	{
		gl.glTexCoord2f(0, 1);
		gl.glVertex2f(x, y);

		gl.glTexCoord2f(0, 0);
		gl.glVertex2f(x, (float) (y + bounds.getHeight()));

		gl.glTexCoord2f(1, 0);
		gl.glVertex2f((float) (x + bounds.getWidth()), (float) (y + bounds.getHeight()));

		gl.glTexCoord2f(1, 1);
		gl.glVertex2f((float) (x + bounds.getWidth()), y);
	}

	public void renderFlipX(GL2 gl, float x, float y)
	{
		gl.glTexCoord2f(1, 0);
		gl.glVertex2f(x, y);

		gl.glTexCoord2f(1, 1);
		gl.glVertex2f(x, (float) (y + bounds.getHeight()));

		gl.glTexCoord2f(0, 1);
		gl.glVertex2f((float) (x + bounds.getWidth()), (float) (y + bounds.getHeight()));

		gl.glTexCoord2f(0, 0);
		gl.glVertex2f((float) (x + bounds.getWidth()), y);
	}

	public void renderNoManipulate(GL2 gl, float x, float y)
	{
		gl.glTexCoord2d(0, 0);
		gl.glVertex2d(x, y);

		gl.glTexCoord2d(0, 1);
		gl.glVertex2f(x, (float) (y + bounds.getHeight()));

		gl.glTexCoord2d(1, 1);
		gl.glVertex2d((float) (x + bounds.getWidth()), (float) (y + bounds.getHeight()));

		gl.glTexCoord2d(1, 0);
		gl.glVertex2d((float) (x + bounds.getWidth()), y);
	}

	public void setFlipType(int flip)
	{
		this.flipData = flip;
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

	public void setScale(float scale)
	{
		this.scale = scale;
		hasInit = false;
	}

	public float getScale()
	{
		return scale;
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
