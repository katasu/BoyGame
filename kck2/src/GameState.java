import java.util.LinkedList;
import java.util.Random;

public class GameState
{

	private final static int SCORE_PENALTY = 25;

	private SnakeT snake;


	private LinkedList<Position> fruits;


	private LinkedList<Position> dynamites;

	private int score;


	private final Random rand;


	public GameState()
	{
		rand = new Random();

		snake  = new SnakeT(Direction.RIGHT);

		fruits    = new LinkedList<Position>();
		dynamites = new LinkedList<Position>();

		score  = 0;
	}


	public LinkedList<Position> getFruits()
	{
		return fruits;
	}


	public LinkedList<Position> getDynamites()
	{
		return dynamites;
	}
	public int getScore()
	{
		return score;
	}
	public boolean isSnakeAlive()
	{
		return snake.isAlive();
	}
	public void killSnake()
	{
		snake.kill();
	}

	public void moveSnake()
	{
		snake.move();
	}
	public LinkedList<Position> getSnakeBody()
	{
		return snake.getBody();
	}
	public Position getSnakeHead()
	{
		return snake.getHead();
	}
	public Position getSnakeTail()
	{
		return snake.getTail();
	}
	public boolean snakeAteFruit()
	{
		if(snake.ateFruit(fruits))
		{
			updateScore(true);
			snake.increaseSize();

			return true;
		}

		return false;
	}
	public boolean snakeSteppedDynamite()
	{
		if(snake.steppedOverDynamite(dynamites))
		{
			updateScore(false);

			return true;
		}

		return false;
	}
	public void setDirection(Direction dir)
	{
		switch(dir)
		{
			case UP:

				if(snake.getDirection() != Direction.DOWN)
					snake.setDirection(Direction.UP);

				break;

			case DOWN:

				if(snake.getDirection() != Direction.UP)
					snake.setDirection(Direction.DOWN);

				break;

			case LEFT:

				if(snake.getDirection() != Direction.RIGHT)
					snake.setDirection(Direction.LEFT);

				break;

			case RIGHT:

				if(snake.getDirection() != Direction.LEFT)
					snake.setDirection(Direction.RIGHT);

				break;

			default:
				throw new IllegalArgumentException("No such direction");
		}
	}


	private void updateScore(boolean increase)
	{
		if(increase)
			score += (snake.getBody().size() * 2 + dynamites.size());
		else
			score -= SCORE_PENALTY;
	}


	private int randomNumber(int min, int max)
	{
	    // nextInt is normally exclusive of the top value
	    return rand.nextInt(max - min) + min;
	}
	private Position generateRandomPosition(int max_x, int max_y)
	{
		int x = randomNumber(1, max_x);
		int y = randomNumber(1, max_y);

		return new Position(x, y);
	}
	private boolean isEmptyPosition(Position p)
	{
		return !(snake.isBody(p) || fruits.contains(p) || dynamites.contains(p));
	}

	public Position generateRandomObject(int x_max, int y_max)
	{
		Position p = null;

		do
		{
			p = generateRandomPosition(x_max, y_max);

		} while(!isEmptyPosition(p));

		return p;
	}
}
