package call.game.tiles;

import call.game.image.Image;

public class ImageTile extends Tile
{
	private Image image;
	
	public ImageTile(int id, Image image)
	{
		super(id);
		
		this.image = image;
	}
	
	@Override
	public void render(TileMap map, int x, int y)
	{
		super.render(map, x, y);
		
		image.render(x * map.getTileSize(), y * map.getTileSize());
	}
}
