package call.game.image;
import java.nio.FloatBuffer;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import call.game.main.GameSettings;
import call.game.main.Unknown;


public class TexturedVBO
{
	public static final int BUFFER_VERTEX = 0;
	public static final int BUFFER_TEXTURE = 1;
	
	protected static boolean hasInit;
	
	protected static int[] buffers = null;
	protected static int[] sizes = null;
	
	protected static int currentVBO = 0;
	
	public static void init()
	{
		GameSettings gs = Unknown.getSettings();
		
		buffers = new int[gs.getRequiredVBOSpace()];
		sizes = new int[gs.getRequiredVBOSpace()];
		
		GL2 gl = Unknown.getGL();
		
		gl.glGenBuffers(buffers.length, buffers, 0);

		hasInit = true;
	}
	
	
	public static void setVertexBuffer(int id, float[] verts)
	{
		if(!hasInit)
			init();
		
		GL2 gl = Unknown.getGL();
		
		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, getBuffer(id, BUFFER_VERTEX)); // writing to vertex buffer

		int numTriangles = verts.length / 2; // 2 values per vertex in array (no z position provided)
		int size = numTriangles * 3;
		
		setSize(id, BUFFER_VERTEX, size); // 3 values per vertex added to buffer (x, y, z)

		FloatBuffer vert = FloatBuffer.allocate(size);

		for(int i = 0; i < verts.length; i += 2)
		{
			vert.put(verts[i]);     // x
			vert.put(verts[i + 1]); // y
			vert.put(1);            // z (always 1)
		}

		vert.rewind(); // rewind buffer to beginning

		
		
		gl.glBufferData(GL.GL_ARRAY_BUFFER, size * 4, vert, GL.GL_STATIC_DRAW); // store buffer in VBO
	}

	public static void setTextureBuffer(int id, float[] textureCoords)
	{
		if(!hasInit)
			init();
		
		GL2 gl = Unknown.getGL();

		gl.glBindBuffer(GL.GL_ARRAY_BUFFER, getBuffer(id, BUFFER_TEXTURE));  

		// Textures have 2 coordinates (u,v) per vertex and 3 vertices per triangle/face
		int size = textureCoords.length;
		setSize(id, BUFFER_TEXTURE, size);
		FloatBuffer textures = FloatBuffer.allocate(size);

		for (int i = 0; i < textureCoords.length; i += 2)
		{

			// put the u,v coordinates of the texture vertices into the textures FloatBuffer
			textures.put(textureCoords[i]);
			textures.put(textureCoords[i + 1]);
		}  

		// Rewind buffer
		textures.rewind();  

		// Write out the textures buffer to the currently bound VBO.
		// Use 'totalBufferSize*4' because we have 4 bytes for a float.
		gl.glBufferData(GL.GL_ARRAY_BUFFER, size * 4, textures, GL.GL_STATIC_DRAW); 
	}
	
	public static int getBuffer(int id, int number)
	{
		int x = number + 2 * id;
		
		return buffers[x];
	}
	
	public static int getSize(int number, int id)
	{
		int x = number + 2 * id;
		
		//System.out.println("Getting: " + x + ", " + sizes[x] + ", id: " + id + ", NUM: " + number);
		
		return sizes[x];
	}
	
	private static void setSize(int id, int number, int value)
	{
		int x = number + 2 * id;
		
		sizes[x] = value;
		
		//System.out.println("Setting: " + x + ", " + value + ", id: " + id);
	}
	
	public static int getNextVBOID()
	{
		if(!hasInit)
			init();
		
		return currentVBO++;
	}
	
	public static void debug()
	{
		for(int i : buffers)
			System.out.println(i);
		
		System.out.println("----");
		
		for(int i : sizes)
			System.out.println(i);
		
		System.out.println("----");
	}
}
