package call.game.physicx;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.HashSet;

import call.game.image.Image;


public class Physicx
{
	public static boolean isIntersecting(Rectangle a, Rectangle b)
	{
		if((a.getBounds().intersects(b.getBounds()) || b.getBounds().intersects(a.getBounds())) || (a.getBounds().contains(b.getBounds()) || b.getBounds().contains(a.getBounds())))
			return true;

		return false;
	}

	public static boolean isIntersecting(Rectangle a, BoundingBox b)
	{
		return isIntersecting(a, b.getBounds());
	}

	public static boolean isIntersecting(BoundingBox a, BoundingBox b)
	{
		if(a == null || b == null)
			return false;
		
		return isIntersecting(a.getBounds(), b.getBounds());
	}

	public static HashSet<String> createMesh(Image image)
	{
		HashSet<String> mesh = new HashSet<String>();
		
		BufferedImage img = image.getBackend();
		
		BoundingBox box = image.getBounds();
		
		if(box == null)
			return mesh;
		
		for(int x = 0; x < img.getWidth(); x++)
		{
			for(int y = 0; y < img.getHeight(); y++)
			{
				int col = img.getRGB(x, y);
				
				int alpha = (col >> 24) & 0xff;
				
				if(alpha > 0)
				{
					int abX = (int) (box.getX() + x);
					int abY = (int) (box.getY() + y);
					mesh.add("" + abX + "," + abY);
				}
			}
		}
		
		return mesh;
	}
	
	public static boolean isPPIntersecting(Image a, Image b)
	{
		if(!isIntersecting(a.getBounds(), b.getBounds()))
			return false;
		
		HashSet<String> meshA = createMesh(a);
		HashSet<String> meshB = createMesh(b);
		
		meshA.retainAll(meshB);
			
		return meshA.size() > 0;
	}
}
