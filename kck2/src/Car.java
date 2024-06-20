//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class Car {


    private static LinkedList<Rect> body;
    private static LinkedList<Rect> temp;

    private Direction direction;

    private boolean alive;

    private LinkedList<Rect> opponent;
    private LinkedList<LinkedList<Rect>> tempopp;
    private LinkedList<Rect> temp2;
    private LinkedList<LinkedList<Rect>> opponents;


    public double bodyWidth;
    public double bodyHeight;
    public int size;
    public LinkedList<Rect> getBody()
    {
        return body;
    }
    private int score;
    public int getScore(){
        return score;
    }
    public void updateScore(){
        score= score+10*body.size();
    }
    public Rect getHead()
    {
        return this.body.getLast();
    }

    public Rect getTail()
    {
        return body.getFirst();
    }

    public double ogWaitBetweenUpdates;
    public double waitTimeLeft;
    public Rect background;
    private final Random rand;
    private int cant;
    private void setCant(){
        cant=0;
    }
    private void addCant(){
        cant+=1;
    }
    private int getCant(){
        return cant;
    }
    private int siz=24;
    public boolean isAlive(){
        return alive;
    }
    public void kill(){
        alive=false;
    }
    public LinkedList<Rect> getOpponent(){
        return opponent;
    }
    public LinkedList<LinkedList<Rect>> getOpponents(){
        return opponents;
    }
    public Car(double startX, double startY, double bodyWidth, double bodyHeight, Rect background) {

        this.direction = Direction.UP;
        this.ogWaitBetweenUpdates = 0.09;
        this.waitTimeLeft = this.ogWaitBetweenUpdates;
        rand = new Random();
        setCant();
        opponent    = new LinkedList<Rect>();
        opponents = new LinkedList<LinkedList<Rect>>();
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;
        this.background = background;
        score=0;
        alive=true;
        this.body = new LinkedList<Rect>();
        body.add(new Rect(startX-24, startY+72,siz,siz));
        body.add(new Rect(startX, startY+72,siz,siz));
        body.add(new Rect(startX+24, startY+72,siz,siz));
        body.add(new Rect(startX, startY+48,siz,siz));
        body.add(new Rect(startX+24, startY+24,siz,siz));
        body.add(new Rect(startX, startY+24,siz,siz));
        body.add(new Rect(startX-24, startY+24,siz,siz));
        body.add(new Rect(startX, startY,siz,siz));
    }

    public void changeDirecton(Direction newDirection) {
        if (newDirection == Direction.RIGHT ) {
            this.direction = newDirection;
        } else if (newDirection == Direction.LEFT ) {
            this.direction = newDirection;
        } else if (newDirection == Direction.UP ) {
            this.direction = newDirection;
        } else if (newDirection == Direction.DOWN) {
            this.direction = newDirection;
        }

    }

    public void update(double dt) {

        while (isAlive()){
            //Rect head = getHead();
            Rect test;
            temp = new LinkedList<Rect>();
            boolean check=false;


            if (this.waitTimeLeft > 0.0) {
                this.waitTimeLeft -= dt;
                return;
            } else {
               // if (this.intersectingWithScreenBoundaries(head)) {
                   // kill();

                //}


                this.waitTimeLeft = this.ogWaitBetweenUpdates;
                if (this.direction == Direction.RIGHT) {
                    for(Rect p : getBody())
                    {
                        p=new Rect(p.x+5, p.y,siz,siz);
                        temp.add(p);
                    }
                } else if (this.direction == Direction.LEFT) {
                    for(Rect p : getBody())
                    {
                        p=new Rect(p.x-5, p.y,siz,siz);
                        temp.add(p);
                    }
                } else if (this.direction == Direction.UP) {
                    for(Rect p : getBody())
                    {
                        p=new Rect(p.x, p.y-5,siz,siz);
                        temp.add(p);
                    }
                } else{
                    for(Rect p : getBody())
                    {
                        p=new Rect(p.x, p.y+5,siz,siz);
                        temp.add(p);
                    }
                }

                for(Rect p : temp)
                {
                    if(p.x>=440 || p.x<=20 || p.y<=85 || p.y>=945){
                        check=true;

                    }
                }
                if (check==false){
                    body=temp;
                }
                if (getCant()%80==0){
                    generateNewCar();
                }
                moveAll();
                addCant();
                if(collided(opponents)){
                    kill();
                }
            }}
    }



    public boolean intersectingWithSelf(Rect r) {
        for(Rect s:getBody()){
            if (intersecting(r, s)) return true;
        }
        return false;
    }

    public boolean intersectingWithRect(Rect rect) {
        for(Rect s:getBody()){
            if (intersecting(rect, s)) return true;
        }
        return false;
    }

    public boolean intersecting(Rect r1, Rect r2) {
        return (r1.x >= r2.x && r1.x + r1.width <= r2.x + r2.width &&
                r1.y >= r2.y && r1.y + r1.height <= r2.y + r2.height);
    }

    public void generateNewCar(){
        temp = new LinkedList<Rect>();
        Rect p = generateRandomObject(400, 800);
        Rect gl = new Rect(p.x, 90,24,24);
        temp.add(gl);

        temp.add(new Rect(gl.x, gl.y-24,siz,siz));
        temp.add(new Rect(gl.x+24, gl.y-24,siz,siz));
        temp.add(new Rect(gl.x-24, gl.y-24,siz,siz));
        temp.add(new Rect(gl.x, gl.y-48,siz,siz));
        temp.add(new Rect(gl.x, gl.y-72,siz,siz));
        temp.add(new Rect(gl.x+24, gl.y-72,siz,siz));
        temp.add(new Rect(gl.x-24, gl.y-72,siz,siz));

        opponents.add(temp);


    }
    public void moveAll(){
        tempopp = new LinkedList<LinkedList<Rect>>();
        for(LinkedList<Rect> lista: opponents){
            temp = new LinkedList<Rect>();
            for (Rect p: lista){
                p=new Rect(p.x, p.y+5,24,24);
                temp.add(p);
            }
            lista=temp;
            tempopp.add(lista);
        }
        opponents=tempopp;
        if(opponents.getFirst().getLast().y>=980){
            opponents.removeFirst();
            updateScore(true);
        }
    }
    private void updateScore(boolean increase)
    {
        if(increase)
            score += (10+ opponents.size()*2);

    }
    public Rect generateRandomObject(int x_max, int y_max)
    {
        Rect p = null;

        do
        {
            p = generateRandomPosition(x_max, y_max);

        } while(!isEmptyPosition(p));

        return p;
    }
    private Rect generateRandomPosition(int max_x, int max_y)
    {
        int x = randomNumber(40, max_x);
        int y = randomNumber(2, max_y);

        return new Rect(x, y,24,24);
    }
    private int randomNumber(int min, int max) {
        return this.rand.nextInt(max - min) + min;
    }
    private boolean isEmptyPosition(Rect p)
    {
        return !(isBody(p) || opponents.contains(p));
    }
    public boolean intersectingWithScreenBoundaries(Rect head) {
        return body.getLast().x < this.background.x-36 || body.getLast().x + body.getLast().width > this.background.x + this.background.width+36 || body.getLast().y < this.background.y+36 || body.getLast().y + body.getLast().height > this.background.y + this.background.height-36;
    }
    public boolean isBody(Rect p)
    {
        return body.contains(p);
    }

    public boolean collided(LinkedList<LinkedList<Rect>> opponents) {
        for (LinkedList<Rect> lista: opponents){
            for (Rect p: lista){
                for(Rect main: getBody()){
                    for(var z=p.x; z<=(p.x+12);z++){
                        int g=(int)z;
                        if(g>=main.x && g<=(main.x+24) && p.y>=main.y&&p.y<+(main.y+24)){
                            updateScore();
                            return true;
                        }
                    }
                    for(var z=p.y; z<=(p.y+12);z++){
                        int g=(int)z;
                        if(p.x>=main.x && p.x<=(main.x+24) && g>=main.y && g<+(main.y+24)){
                            return true;
                        }
                    }


                }
            }
        }

        return false;
    }


}

