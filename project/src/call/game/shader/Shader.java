package call.game.shader;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import javax.media.opengl.GL2;

import call.game.main.Unknown;


public class Shader
{
	protected String vertexShader;
	protected String fragmentShader;
	private int vertexShaderProgram;
	private int fragmentShaderProgram;
	private int shaderprogram;

	public void init()
	{
		GL2 gl = Unknown.getGL();

		// create shader's
		vertexShaderProgram = gl.glCreateShader(GL2.GL_VERTEX_SHADER);
		fragmentShaderProgram = gl.glCreateShader(GL2.GL_FRAGMENT_SHADER);

		// compile vertex shader
		gl.glShaderSource(vertexShaderProgram, 1, new String[] {vertexShader}, null, 0);
		gl.glCompileShader(vertexShaderProgram);

		// compile fragment shader
		gl.glShaderSource(fragmentShaderProgram, 1, new String[] {fragmentShader}, null, 0);
		gl.glCompileShader(fragmentShaderProgram);

		// create shader program
		shaderprogram = gl.glCreateProgram();

		// attach shader
		gl.glAttachShader(shaderprogram, vertexShaderProgram);
		gl.glAttachShader(shaderprogram, fragmentShaderProgram);

		// link shader
		gl.glLinkProgram(shaderprogram);

		// validate shader
		gl.glValidateProgram(shaderprogram);


		IntBuffer intBuffer = IntBuffer.allocate(1);

		gl.glGetProgramiv(shaderprogram, GL2.GL_LINK_STATUS,intBuffer);

		if (intBuffer.get(0) != 1)
		{
			gl.glGetProgramiv(shaderprogram, GL2.GL_INFO_LOG_LENGTH,intBuffer);

			int size = intBuffer.get(0);

			System.err.println("Program link error: ");

			if (size>0)
			{
				ByteBuffer byteBuffer = ByteBuffer.allocate(size);

				gl.glGetProgramInfoLog(shaderprogram, size, intBuffer, byteBuffer);

				for(byte b:byteBuffer.array())
					System.err.print((char)b);
			}
			else
			{
				System.out.println("Unknown");
			}

			System.exit(1);
		}
	}

	public void setUniformFloat(String name, float value)
	{
		GL2 gl = Unknown.getGL();

		int uniformID = gl.glGetUniformLocation(shaderprogram, name);
		gl.glUniform1f(uniformID, value);
	}

	public void setUniformVec4(String name, float a, float b, float c, float d)
	{
		GL2 gl = Unknown.getGL();
		int uniformID = gl.glGetUniformLocation(shaderprogram, name);
		gl.glUniform4f(uniformID, a, b, c, d);
	}

	public void bind()
	{
		GL2 gl = Unknown.getGL();

		gl.glUseProgram(shaderprogram);
	}

	public void unbind()
	{
		GL2 gl = Unknown.getGL();

		gl.glUseProgram(0);
	}

	public String getFragmentShader()
	{
		return fragmentShader;
	}

	public String getVertexShader()
	{
		return vertexShader;
	}
}
