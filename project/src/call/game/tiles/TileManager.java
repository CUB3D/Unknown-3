package call.game.tiles;

import java.util.ArrayList;
import java.util.List;

public class TileManager
{
	private static List<Tile> tiles = new ArrayList<Tile>();
	
	public static void addTile(Tile t)
	{
		tiles.add(t);
	}
	
	public static Tile getTile(int id)
	{
		for(Tile t : tiles)
			if(t.getID() == id)
				return t;
		
		return null;
	}
	
	public static List<Tile> getTiles()
	{
		return tiles;
	}
}
