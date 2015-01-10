package call.game.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.imageio.ImageIO;

import call.game.image.Animation;
import call.game.image.Image;
import cub3d.file.main.Element;
import cub3d.file.reader.BasicReader;
import cub3d.file.reader.CallReader;
import cub3d.file.reader.Reader;

public class AnimationIO
{
	public static Animation loadAnimation(File file)
	{
		try
		{
			return loadAnimation(new FileInputStream(file));
		}catch(Exception e) {e.printStackTrace();}

		return null;
	}

	public static Animation loadAnimation(InputStream stream)
	{
		Animation animation = null;

		try
		{
			ZipInputStream z = new ZipInputStream(stream);

			ZipEntry dataEntry = null;

			while(true)
			{
				ZipEntry e = z.getNextEntry();
				
				if(e == null)
					break;

				if(e.getName().equals("Data.call"))
				{
					dataEntry = e;

					ByteArrayOutputStream baos = new ByteArrayOutputStream();

					while(true)
					{
						byte b = (byte) z.read();
						
						if(b == -1) break;
						
						baos.write(b);
					}
					
					CallReader data = new CallReader(new BasicReader(new Reader(new ByteArrayInputStream(baos.toByteArray()))));

					Element ani = data.getElementByName("Animation");

					double duration = ani.getValue("Duration").getDouble();
					boolean loop = ani.getValue("Loop").getBoolean(false);

					animation = new Animation(duration, loop);
				}

				if(dataEntry != null)
				{
					if(e.getName().endsWith("png"))
					{
						ByteArrayOutputStream baos = new ByteArrayOutputStream();

						while(z.available() > 0)
							baos.write((byte) z.read());

						BufferedImage bi = ImageIO.read(new ByteArrayInputStream(baos.toByteArray()));

						Image i = new Image(bi);

						animation.addFrame(i);
					}
				}
			}

			z.close();

			return animation;

		}catch(Exception e) {e.printStackTrace();}

		return null;
	}

/*	public static void writeAnimation(Animation a, File f)
	{
		try
		{
			writeAnimation(a, new FileOutputStream(f));
		}catch(Exception e) {e.printStackTrace();}
	}

	public static void writeAnimation(Animation a, OutputStream o)
	{
		ZipOutputStream zos = new ZipOutputStream(o);
		try
		{
			zos.putNextEntry(new ZipEntry("Data.call"));

			File temp = File.createTempFile("Data", "call");

			CFile cf = new CFile(temp);

			Element e = new Element("Animation");

			e.addValue(new Value("Duration", "" + a.getTimer().getDuration()));
			e.addValue(new Value("Loop", "" + a.willLoop()));

			cf.addElement(e);

			cf.save();

			FileAPI api = new FileAPI(temp);

			zos.write(api.getBytes());

			zos.closeEntry();

			for(int i = 0; i < a.getFrames().size(); i++)
			{
				BufferedImage bi = a.getFrames().get(i).getImage();

				zos.putNextEntry(new ZipEntry("Frame-" + i + ".png"));

				ByteArrayOutputStream baos = new ByteArrayOutputStream();	

				ImageIO.write(bi, "PNG", baos);

				zos.write(baos.toByteArray());

				zos.closeEntry();
			}

			zos.close();

		}catch(Exception e) {e.printStackTrace();}
	}*/
}
