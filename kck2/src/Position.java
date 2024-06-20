
public class Position
{
	/*
	 * X-Axis coordinate.
	 */
	private final int x;

	/*
	 * Y-Axis coordinate.
	 */
	private final int y;

	public Position(int x, int y)
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
	@Override public boolean equals(Object other)
	{
	    boolean result = false;

	    if(other instanceof Position)
	    {
	        Position that = (Position) other;
	        result = (getX() == that.getX() && getY() == that.getY());
	    }

	    return result;
	}
}
