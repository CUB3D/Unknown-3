package call.game.image;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import call.game.main.FileHelper;


public class ImageCache
{
	public static boolean ENABLED = true;

	private static Map<String, BufferedImage> cache = new HashMap<String, BufferedImage>();

	public static BufferedImage getImage(String path)
	{
		BufferedImage img = ENABLED ? cache.get(path) : null;

		if(img == null)
		{
			try
			{
				img = ImageIO.read(FileHelper.getURL(path));
			}catch(Exception e) {e.printStackTrace();}

			if(ENABLED)
				cache.put(path, img);
		}

		return img;
	}
}
