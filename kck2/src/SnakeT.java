import java.util.LinkedList;


public class SnakeT
{
	private final static int SNAKE_INITIAL_SIZE = 4;

	private static LinkedList<Position> body;

	private Direction direction;

	private boolean alive;

	public SnakeT(Direction starting_direction)
	{
		body = new LinkedList<Position>();
		for(int i = 0; i < SNAKE_INITIAL_SIZE; i++)
		{
			body.add(new Position(i + 3, 15));
		}

		direction = starting_direction;
		alive     = true;
	}

	public LinkedList<Position> getBody()
	{
		return body;
	}

	public Position getHead()
	{
		return body.getLast();
	}

	public Position getTail()
	{
		return body.getFirst();
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void kill()
	{
		alive = false;
	}

	public void move()
	{
		Position head = getHead();

		body.removeFirst();
		switch(direction)
		{
			case UP:

				head = new Position(head.getX(), head.getY() - 1);
				break;

			case DOWN:

				head = new Position(head.getX(), head.getY() + 1);
				break;

			case LEFT:

				head = new Position(head.getX() - 1, head.getY());
				break;

			case RIGHT:

				head = new Position(head.getX() + 1, head.getY());
				break;

			default:
				throw new IllegalArgumentException("There is no such direction");
		}

		body.addLast(head);
	}
	public boolean isBody(Position p)
	{
		return body.contains(p);
	}

	public boolean ateFruit(LinkedList<Position> fruits)
	{
		Position head = getHead();

		for(Position p : fruits)
		{
			if(head.equals(p))
			{
				fruits.remove(p);

				return true;
			}
		}

		return false;
	}

	public boolean steppedOverDynamite(LinkedList<Position> dynamites)
	{
		Position head = getHead();

		for(Position p : dynamites)
		{
			if(head.equals(p))
			{
				dynamites.remove(p);

				return true;
			}
		}

		return false;
	}
	public void increaseSize()
	{
		Position tail = getTail();

		body.addFirst(new Position(tail.getX(), tail.getY()));
	}
}
