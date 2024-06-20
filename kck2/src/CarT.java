import java.util.LinkedList;

public class CarT
{

    private static LinkedList<Position> body;
    private static LinkedList<Position> temp;

    private Direction direction;

    private boolean alive;

    public CarT(Direction starting_direction)
    {
        body = new LinkedList<Position>();


        body.add(new Position(1, 9));
        body.add(new Position(1, 10));
        body.add(new Position(1, 11));
        body.add(new Position(2, 10));
        body.add(new Position(3, 9));
        body.add(new Position(3, 10));
        body.add(new Position(3, 11));
        body.add(new Position(4, 10));

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
        // Get current head position
        temp = new LinkedList<Position>();
        boolean check=false;
        // Determine head's new position based on snake's direction
        switch(direction)
        {
            case UP:

                for(Position p : getBody())
                {
                    p=new Position(p.getX(), p.getY()-1);
                    temp.add(p);
                }
                break;

            case DOWN:

                for(Position p : getBody())
                {
                    p=new Position(p.getX(), p.getY()+1);

                    temp.add(p);
                }
                break;

            case LEFT:

                for(Position p : getBody())
                {
                    p=new Position(p.getX()-1, p.getY());

                    temp.add(p);
                }
                break;

            case RIGHT:

                for(Position p : getBody())
                {
                    p=new Position(p.getX()+1, p.getY());

                    temp.add(p);
                }
                break;

            default:
                throw new IllegalArgumentException("There is no such direction");
        }
        for(Position p : temp)
        {
            if(p.getX()==79 || p.getX()==0 || p.getY()==0 || p.getY()==21){
                check=true;

            }
        }
        if (check==false){
            body=temp;
        }


    }

    public boolean isBody(Position p)
    {
        return body.contains(p);
    }

}
