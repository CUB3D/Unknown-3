package call.game.shader;

import cub3d.file.main.FileAPI;
import cub3d.file.reader.BasicReader;

public class FileShader extends Shader
{
	public FileShader(String name)
	{
		this(name + ".vert", name + ".frag");
	}
	
	public FileShader(String vert, String frag)
	{
		super(vert, frag);
		
		this.fragmentShader = loadShader(frag);
		this.vertexShader = loadShader(vert);
	}
	
	private String loadShader(String name)
	{
		FileAPI api = new FileAPI(name);

		String s = "";

		try
		{
			BasicReader br = new BasicReader(api.getReader());

			String line;

			while((line = br.readLine()) != null)
				s += line;
		}catch(Exception e) {
			e.printStackTrace();
		}

		return s;
	}
}
