package call.game.tiles;

import java.awt.Point;

import call.game.main.EnumCallTime;
import call.game.main.IRenderable;
import call.game.main.IUpdateable;
import call.game.main.Unknown;
import call.mappy.Map;

public class TileMap implements IRenderable, IUpdateable
{
	private Map gameMap;

	private int tileSize = 0;

	public TileMap(int size, int tileSize, EnumCallTime time)
	{
		this(size, size, tileSize, time);
	}

	public TileMap(int width, int height, int tileSize, EnumCallTime time)
	{
		gameMap = new Map(width, height);
		this.tileSize = tileSize;

		Unknown.registerRenderable(this, time);
		Unknown.registerUpdateable(this, time);
	}
	
	public void fillMap(Tile t)
	{
		for(int x = 0; x < gameMap.getWidth(); x++)
			for(int y = 0; y < gameMap.getHeight(); y++)
			{
				this.setTile(x, y, t);
			}
	}
	
	public boolean tileContains(int x, int y, Point pos)
	{
		int tx = x * tileSize;
		int ty = y * tileSize;
		
		if(pos.x >= tx && pos.y <= tx + tileSize)
			if(pos.y >= ty && pos.y <= pos.y + tileSize)
				return true;
		
		return false;
	}

	@Override
	public void update()
	{
		for(int x = 0; x < gameMap.getWidth(); x++)
			for(int y = 0; y < gameMap.getHeight(); y++)
			{
				Tile t = this.getTile(x, y);
				if(t != null)
					t.update(this, x, y);
			}
	}

	@Override
	public void render()
	{
		for(int x = 0; x < gameMap.getWidth(); x++)
			for(int y = 0; y < gameMap.getHeight(); y++)
			{
				Tile t = this.getTile(x, y);
				if(t != null)
					t.render(this, x, y);
			}
	}

	public void setTile(int x, int y, Tile t)
	{
		this.gameMap.setTileID(x, y, t.getID());
	}

	public Tile getTile(int x, int y)
	{
		return TileManager.getTile(gameMap.getTileID(x, y));
	}

	public int getTileSize()
	{
		return tileSize;
	}

	public Map getMap()
	{
		return this.gameMap;
	}
}
