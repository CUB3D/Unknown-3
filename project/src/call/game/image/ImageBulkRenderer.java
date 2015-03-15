package call.game.image;
import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL2;

import call.game.main.EnumCallTime;
import call.game.main.IRenderable;
import call.game.main.Unknown;

public class ImageBulkRenderer implements IRenderable
{
	
	private List<RenderData> images = new ArrayList<RenderData>();
	
	public ImageBulkRenderer()
	{
		Unknown.registerRenderable(this, EnumCallTime.END);
	}
	
	public void addImage(RenderData i)
	{
		images.add(i);
	}
	
	@Override
	public void render()
	{
		GL2 gl = Unknown.getGL();
		
		gl.glEnableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		gl.glEnableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glEnable(GL2.GL_TEXTURE_2D);            
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_SRC_ALPHA); // blends out background without causing blurring

		
		for(RenderData rd : images)
			rd.render();
		
		gl.glDisableClientState(GL2.GL_VERTEX_ARRAY);
		gl.glDisableClientState(GL2.GL_TEXTURE_COORD_ARRAY);
		gl.glDisable(GL2.GL_TEXTURE_2D);
		gl.glDisable(GL2.GL_BLEND);
		
		images.clear();
	}
}
