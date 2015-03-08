package call.game.tiles;

public class Tile
{
	private int id;
	
	public Tile(int id)
	{
		this.id = id;
		
		TileManager.addTile(this);
	}
	
	public void render(TileMap map, int x, int y) {}
	public void update(TileMap map, int x, int y) {}
	
	public int getID()
	{
		return this.id;
	}
}
