package call.game.image;


public class RenderData
{
	public Image image;
	public double x;
	public double y;
	public double angle;
	
	public RenderData(Image image, double x, double y, double angle)
	{
		this.image = image;
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public void render()
	{
		this.image.render_raw(this.x, this.y, this.angle);
	}
}