package call.game.image;

import java.awt.image.BufferedImage;
import java.io.File;
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
		if(!ENABLED)
		{
			try
			{
				return ImageIO.read(FileHelper.getURL(path));
			}catch(Exception e) {e.printStackTrace();}
		}

		BufferedImage img = cache.get(path);

		if(img == null)
		{
			try
			{
				img = ImageIO.read(FileHelper.getURL(path));
			}catch(Exception e) {e.printStackTrace();}

			cache.put(path, img);
		}

		return img;
	}

	public static void preload(File f)
	{
		if(!f.isDirectory())
			return;

		for(File ff : f.listFiles())
		{
			if(ff.isDirectory())
				preload(ff);
			else
			{
				BufferedImage img = null;

				try
				{
					img = ImageIO.read(FileHelper.getURL(ff.getPath()));
				}catch(Exception e) {e.printStackTrace();}

				cache.put(ff.getName(), img);

				System.out.println("Preloaded: " + ff.getPath() + " as " + ff.getName());
			}
		}
	}
}
