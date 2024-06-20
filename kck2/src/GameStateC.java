import java.util.LinkedList;
import java.util.Random;

public class GameStateC
{


    private CarT carT;

    private LinkedList<Position> opponent;
    private LinkedList<Position> temp;
    private LinkedList<LinkedList<Position>> opponents;

    private LinkedList<LinkedList<Position>> tempopp;
    private int score;
    private final Random rand;

    public GameStateC()
    {
        rand = new Random();
        carT = new CarT(Direction.RIGHT);

        opponent    = new LinkedList<Position>();
        opponents = new LinkedList<LinkedList<Position>>();
        score  = 0;
    }
    public LinkedList<Position> getOpponent(){
        return opponent;
    }
    public LinkedList<LinkedList<Position>> getOpponents(){
        return opponents;
    }
    public int getScore()
    {
        return score;
    }

    public boolean isCarAlive()
    {
        return carT.isAlive();
    }

    public void breakCar()
    {
        carT.kill();
    }

    public void moveCar()
    {
        carT.move();
    }

    public LinkedList<Position> getCarBody()
    {
        return carT.getBody();
    }

    public Position getCarHead()
    {
        return carT.getHead();
    }

    public boolean car_collision()
    {
        if(collided(opponents))
        {
            return true;
        }

        return false;
    }
    public void generateNewCar(){
        temp = new LinkedList<Position>();
        Position p = generateRandomObject(78, 20);
        Position gl = new Position(75, p.getY());
        temp.add(gl);
        temp.add(new Position((gl.getX()+1), (gl.getY()+1)));
        temp.add(new Position((gl.getX()+1), (gl.getY())));
        temp.add(new Position((gl.getX()+1), (gl.getY()-1)));
        temp.add(new Position((gl.getX()+2), (gl.getY())));
        temp.add(new Position((gl.getX()+3), (gl.getY()+1)));
        temp.add(new Position((gl.getX()+3), (gl.getY())));
        temp.add(new Position((gl.getX()+3), (gl.getY()-1)));
        opponents.add(temp);


    }
    public void moveAll() {
        tempopp = new LinkedList<LinkedList<Position>>();
        for(LinkedList<Position> lista: opponents){
            temp = new LinkedList<Position>();
            for (Position p: lista){
                p=new Position(p.getX()-1, p.getY());
                temp.add(p);
            }
            lista=temp;
            tempopp.add(lista);
        }
        opponents=tempopp;
        if(opponents.getFirst().getLast().getX()==1){
            opponents.removeFirst();
            updateScore(true);
        }

    }
    public boolean collided(LinkedList<LinkedList<Position>> opponents) {
        for (LinkedList<Position> lista: opponents){
            for (Position p: lista){
                for(Position main: carT.getBody()){
                    if(main.equals(p)){
                        return true;
                    }

                }
            }
        }

        return false;
    }
    public void setDirection(Direction dir)
    {
        switch(dir)
        {
            case UP:

                carT.setDirection(Direction.UP);

                break;

            case DOWN:

                carT.setDirection(Direction.DOWN);

                break;

            case LEFT:

                carT.setDirection(Direction.LEFT);

                break;

            case RIGHT:

                carT.setDirection(Direction.RIGHT);

                break;

            default:
                throw new IllegalArgumentException("No such direction");
        }
    }
    private void updateScore(boolean increase)
    {
        if(increase)
            score += (10+ opponents.size()*2);

    }

    private int randomNumber(int min, int max)
    {
        // nextInt is normally exclusive of the top value
        return rand.nextInt(max - min) + min;
    }

    private Position generateRandomPosition(int max_x, int max_y)
    {
        int x = randomNumber(1, max_x);
        int y = randomNumber(2, max_y);

        return new Position(x, y);
    }

    private boolean isEmptyPosition(Position p)
    {
        return !(carT.isBody(p) || opponents.contains(p));
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
