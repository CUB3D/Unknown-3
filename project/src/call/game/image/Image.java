package call.game.image;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import javax.media.opengl.GL2;

import call.game.main.Unknown;
import call.game.physicx.bounding.BoundingBox;
import call.utils.ImageUtils;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.awt.AWTTextureIO;

public class Image
{
	public static final int FLIP_Y = 0x1;
	public static final int FLIP_X = 0x2;

	private Texture text;
	private int flipData = -1;

	private BoundingBox bounds;

	private BufferedImage backend;

	private boolean hasInit;


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
		AffineTransform at = AffineTransform.getScaleInstance(1, -1);
		at.translate(0, -backend.getHeight());
		AffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

		this.backend = op.filter(backend, null);

		text = AWTTextureIO.newTexture(Unknown.getGLProfile(), backend, false);

		bounds = new BoundingBox(0, 0, text.getImageWidth(), text.getImageHeight());

		hasInit = true;
	}

	public void render(int x, int y)
	{
		if(!hasInit)
			init();

		bounds.x = x;
		bounds.y = y;

		GL2 gl = Unknown.getGL();

		gl.glPushMatrix();

		text.enable(gl);
		text.bind(gl);

		text.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);

		gl.glColor3f(1, 1, 1);

		gl.glBegin(GL2.GL_QUADS);

		float scale = text.getAspectRatio();

		float width = text.getImageWidth() / scale;
		float height = text.getImageHeight() / scale;

		if((flipData | FLIP_X) == FLIP_X && (flipData | FLIP_Y) == FLIP_Y)
		{
			gl.glTexCoord2f(1, 1);
			gl.glVertex2f(x, y);

			gl.glTexCoord2f(1, 0);
			gl.glVertex2f(x, y + height);

			gl.glTexCoord2f(0, 0);
			gl.glVertex2f(x + width, y + height);

			gl.glTexCoord2f(0, 1);
			gl.glVertex2f(x + width, y);
		}
		else
			if((flipData | FLIP_Y) == FLIP_Y)
			{
				gl.glTexCoord2f(0, 1);
				gl.glVertex2f(x, y);

				gl.glTexCoord2f(0, 0);
				gl.glVertex2f(x, y + height);

				gl.glTexCoord2f(1, 0);
				gl.glVertex2f(x + width, y + height);

				gl.glTexCoord2f(1, 1);
				gl.glVertex2f(x + width, y);
			}
			else
				if((flipData | FLIP_X) == FLIP_X)
				{
					gl.glTexCoord2f(1, 0);
					gl.glVertex2f(x, y);

					gl.glTexCoord2f(1, 1);
					gl.glVertex2f(x, y + height);

					gl.glTexCoord2f(0, 1);
					gl.glVertex2f(x + width, y + height);

					gl.glTexCoord2f(0, 0);
					gl.glVertex2f(x + width, y);
				}
				else
				{	
					gl.glTexCoord2f(0, 0);
					gl.glVertex2f(x, y);

					gl.glTexCoord2f(0, 1);
					gl.glVertex2f(x, y + height);

					gl.glTexCoord2f(1, 1);
					gl.glVertex2f(x + width, y + height);

					gl.glTexCoord2f(1, 0);
					gl.glVertex2f(x + width, y);
				}

		gl.glEnd();

		gl.glPopMatrix();
	}

	public void setFlipType(int flip)
	{
		this.flipData = flip;
	}

	public BoundingBox getBounds()
	{
		return bounds;
	}
	
	public BufferedImage getBackend()
	{
		return backend;
	}

	@Override
	public Object clone()
	{
		return new Image(ImageUtils.cloneImage(backend));
	}
}
