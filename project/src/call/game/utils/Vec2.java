package call.game.utils;

public class Vec2
{
	public static class Float
	{
		private float x;
		private float y;

		public Float(float x, float y)
		{
			this.x = x;
			this.y = y;
		}

		public float getX()
		{
			return x;
		}

		public float getY()
		{
			return y;
		}
	}
	
	public static class Integer
	{
		private int x;
		private int y;

		public Integer(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		public int getX()
		{
			return x;
		}

		public int getY()
		{
			return y;
		}
	}
}
