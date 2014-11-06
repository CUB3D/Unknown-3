package call.game.image;

import java.net.URL;

import javax.media.opengl.GL2;

import call.game.main.FileHelper;
import call.game.main.Unknown;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class SpriteSheet
{
	public static final int FLIP_NONE = -1;
	public static final int FLIP_Y = 0x1;
	public static final int FLIP_X = 0x2;

	private Texture text;
	private double textureScale;
	private int size;

	public SpriteSheet(String s, String type, int size)
	{
		URL path = FileHelper.getURL(s);

		try
		{
			text = TextureIO.newTexture(path, true, type);
		}catch(Exception e) {e.printStackTrace();}

		this.textureScale = 1.0 / size;
		this.size = size;
	}

	/**
	 * Note tiles start from bottom left corner
	 * 
	 * @param x
	 * @param y
	 * @param tileX
	 * @param tileY
	 */
	public void render(int x, int y, int tileX, int tileY, int flipData)
	{	
		GL2 gl = Unknown.getGL();

		gl.glPushMatrix();

		text.enable(gl);
		text.bind(gl);

		text.setTexParameteri(gl, GL2.GL_TEXTURE_MAG_FILTER, GL2.GL_NEAREST);

		gl.glColor3f(1, 1, 1);

		gl.glBegin(GL2.GL_QUADS);

		float scale = text.getAspectRatio();

		double texCordX = (tileX * textureScale);
		double texCordEndX = texCordX + textureScale;

		double texCordY = (tileY * textureScale);
		double texCordEndY = texCordY + textureScale;

		double width = (text.getImageWidth() / scale) / size;
		double height = (text.getImageHeight() / scale) / size;

		if((flipData | FLIP_X) == FLIP_X && (flipData | FLIP_Y) == FLIP_Y)
		{
			System.out.println("Test");
			
			gl.glTexCoord2d(texCordX, texCordY);
			gl.glVertex2d(x + width, y + height);

			gl.glTexCoord2d(texCordX, texCordEndY);
			gl.glVertex2d(x + width, y);

			gl.glTexCoord2d(texCordEndX, texCordEndY);
			gl.glVertex2d(x, y);

			gl.glTexCoord2d(texCordEndX, texCordY);
			gl.glVertex2d(x, y + height);
		}
		else
			if((flipData | FLIP_X) == FLIP_X)
			{
				gl.glTexCoord2d(texCordX, texCordY);
				gl.glVertex2d(x + width, y);

				gl.glTexCoord2d(texCordX, texCordEndY);
				gl.glVertex2d(x + width, y + height);

				gl.glTexCoord2d(texCordEndX, texCordEndY);
				gl.glVertex2d(x, y + height);

				gl.glTexCoord2d(texCordEndX, texCordY);
				gl.glVertex2d(x, y);
			}
			else
				if((flipData | FLIP_Y) == FLIP_Y)
				{
					gl.glTexCoord2d(texCordX, texCordY);
					gl.glVertex2d(x, y + height);

					gl.glTexCoord2d(texCordX, texCordEndY);
					gl.glVertex2d(x, y);

					gl.glTexCoord2d(texCordEndX, texCordEndY);
					gl.glVertex2d(x + width, y);

					gl.glTexCoord2d(texCordEndX, texCordY);
					gl.glVertex2d(x + width, y + height);
				}
				else
				{
					gl.glTexCoord2d(texCordX, texCordY);
					gl.glVertex2d(x, y);

					gl.glTexCoord2d(texCordX, texCordEndY);
					gl.glVertex2d(x, y + height);

					gl.glTexCoord2d(texCordEndX, texCordEndY);
					gl.glVertex2d(x + width, y + height);

					gl.glTexCoord2d(texCordEndX, texCordY);
					gl.glVertex2d(x + width, y);
				}

		gl.glEnd();

		gl.glPopMatrix();
	}

	public void render(int x, int y, int id, int flip)
	{
		int xpos = id % size;
		int ypos = (id - xpos) / size + 1;
		render(x, y, xpos, size - ypos, flip);
	}

	public void render(int x, int y, int id)
	{
		render(x, y, id, FLIP_NONE);
	}

	public void renderReverse(int x, int y, int id, int flip)
	{
		int xpos = id % size;
		int ypos = (id - xpos) / size;
		render(x, y, xpos, ypos, flip);
	}

	public void renderReverse(int x, int y, int id)
	{
		renderReverse(x, y, id, FLIP_NONE);
	}
}
