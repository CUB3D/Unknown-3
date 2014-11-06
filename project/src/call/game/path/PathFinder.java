package call.game.path;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import call.game.utils.Translate;
import call.mappy.Map;

public class PathFinder
{
	private static Translate last;

	public static List<Point> blocked = new ArrayList<Point>();

	public static Translate findDir(int x1, int y1, int x2, int y2, Map map, IMapInfo info)
	{	
		if(x1 == x2 && y1 == y2)
			return Translate.NONE;


		Translate dir = null;
		int i = 100000000;

		if(y1 + 1 < map.getTileArray().length / map.getWidth())
			if(!isBlocked(x1, y1 + 1, map, info))
				if(distance(x1, y1 + 1, x2, y2) < i)
				{
					i = distance(x1, y1 + 1, x2, y2);
					dir = Translate.UP;
				}

		if(y1 - 1 > -1)
			if(!isBlocked(x1, y1 - 1, map, info))
				if(distance(x1, y1 - 1, x2, y2) < i)
				{
					i = distance(x1, y1 - 1, x2, y2);
					dir = Translate.DOWN;
				}

		if(!isBlocked(x1 - 1, y1, map, info))
			if(distance(x1 - 1, y1, x2, y2) < i)
			{
				i = distance(x1 - 1, y1, x2, y2);
				dir = Translate.LEFT;
			}

		if(!isBlocked(x1 + 1, y1, map, info))
			if(distance(x1 + 1, y1, x2, y2) < i)
			{
				i = distance(x1 + 1, y1, x2, y2);
				dir = Translate.RIGHT;
			}


		if(dir == Translate.UP && last == Translate.DOWN)
		{
			System.out.println("Blocking up");
			blocked.add(new Point(x1, y1 + 1));
		}

		if(dir == Translate.DOWN && last == Translate.UP)
			blocked.add(new Point(x1, y1 - 1));

		if(dir == Translate.RIGHT && last == Translate.LEFT)
			blocked.add(new Point(x1 + 1, y1));

		if(dir == Translate.LEFT && last == Translate.RIGHT)
			blocked.add(new Point(x1 - 1, y1));


		last = dir;
		
		if(dir == null && last == null)
		{
			blocked.clear();
			return Translate.NONE;
		}

		return dir;
	}


	public static boolean isBlocked(int x, int y, Map map, IMapInfo info)
	{
		for(Point p : blocked)
			if(p.x == x && p.y == y)
				return true;

		return info.isBlocked(x, y, map.getTileID(x, y));
	}

	public static int distance(int x1, int y1, int x2, int y2)
	{
		Point2D p1 = (Point2D) new Point(x1, y1);
		Point2D p2 = (Point2D) new Point(x2, y2);

		return (int) p1.distance(p2);
	}
}
