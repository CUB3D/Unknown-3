package call.game.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

public class FileHelper
{
	public static URL getURL(String path)
	{
		try
		{
			if(path.startsWith("/"))
			{
				return FileHelper.class.getResource(path);
			}
			else
			{
				return new File(path).toURI().toURL();
			}
		}catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}

	public static InputStream getStream(String path)
	{
		try
		{
			if(path.startsWith("/"))
			{
				return FileHelper.class.getResourceAsStream(path);
			}
			else
			{
				return new FileInputStream(new File(path));
			}
		}catch(Exception e) 
		{
			e.printStackTrace();
			return null;
		}
	}
}
