package call.game.physicx;

import java.awt.Rectangle;

import call.game.physicx.bounding.BoundingBox;


public class Physicx
{
	public static boolean isIntersecting(Rectangle a, Rectangle b)
	{
		if((a.getBounds().intersects(b.getBounds()) || b.getBounds().intersects(a.getBounds())) || (a.getBounds().contains(b.getBounds()) || b.getBounds().contains(a.getBounds())))
		{
			return true;
		}

		return false;
	}

	public static boolean isIntersecting(Rectangle a, BoundingBox b)
	{
		return isIntersecting(a, b.getBounds());
	}

	public static boolean isIntersecting(BoundingBox a, BoundingBox b)
	{
		return isIntersecting(a.getBounds(), b.getBounds());
	}

	/*public static HashSet<String> createMesh(Image image)
	{
		HashSet<String> mesh = new HashSet<String>();
		
		BufferedImage img = image.getImage();
		
		BoundingBox box = image.getBounds();
		
		for(int x = 0; x < img.getWidth(); x++)
		{
			for(int y = 0; y < img.getHeight(); y++)
			{
				int col = img.getRGB(x, y);
				
				int alpha = (col >> 24) & 0xff;
				
				if(alpha > 0)
				{
					int abX = (box.getX() + x);
					int abY = (box.getY() - y);
					mesh.add("" + abX + "," + abY);
				}
			}
		}
		
		return mesh;
	}
	
	public static boolean isPPIntersecting(Image a, Image b)
	{
		if(!isIntersecting(a.getBounds(), b.getBounds())) // collision impossable
			return false;
		
		HashSet<String> meshA = createMesh(a);
		HashSet<String> meshB = createMesh(b);
		
		meshA.retainAll(meshB);
			
		return meshA.size() > 0;
	}
	
	// TODO: Redo Pixel prefect collision
	
*/}
