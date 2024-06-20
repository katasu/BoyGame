//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import java.util.concurrent.RecursiveAction;

public class Snake {
    public static LinkedList<Rect> body;
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
    public Direction direction;
    public double ogWaitBetweenUpdates;
    public double waitTimeLeft;
    public Rect background;
    private boolean alive;
    public boolean isAlive(){
        return alive;
    }
    public void kill(){
        alive=false;
    }
    public Snake(int size, double startX, double startY, double bodyWidth, double bodyHeight, Rect background) {

        this.direction = Direction.DOWN;
        this.ogWaitBetweenUpdates = 0.2;
        this.waitTimeLeft = this.ogWaitBetweenUpdates;
        this.size = size;
        this.bodyWidth = bodyWidth;
        this.bodyHeight = bodyHeight;
        this.background = background;
        score=0;
        alive=true;
        this.body = new LinkedList<Rect>();
        for(int i = 0; i <= size; i++) {
            this.body.add(new Rect(startX + (double)i * bodyWidth, startY, bodyWidth, bodyHeight));

        }
    }

    public void changeDirecton(Direction newDirection) {
        if (newDirection == Direction.RIGHT && this.direction != Direction.LEFT) {
            this.direction = newDirection;
        } else if (newDirection == Direction.LEFT && this.direction != Direction.RIGHT) {
            this.direction = newDirection;
        } else if (newDirection == Direction.UP && this.direction != Direction.DOWN) {
            this.direction = newDirection;
        } else if (newDirection == Direction.DOWN && this.direction != Direction.UP) {
            this.direction = newDirection;
        }

    }

    public void update(double dt) {
        while (isAlive()){
            Rect head = getHead();
            Rect test;
            if (this.waitTimeLeft > 0.0) {
                this.waitTimeLeft -= dt;
                return;
            } else {
                if (this.intersectingWithScreenBoundaries(head)) {
                    kill();

                }
                body.removeFirst();
                this.waitTimeLeft = this.ogWaitBetweenUpdates;
                if (this.direction == Direction.RIGHT) {
                    test = new Rect(head.x+this.bodyWidth, head.y, this.bodyWidth,this.bodyHeight);
                } else if (this.direction == Direction.LEFT) {
                    test = new Rect(head.x-this.bodyWidth, head.y, this.bodyWidth,this.bodyHeight);
                } else if (this.direction == Direction.UP) {
                    test = new Rect(head.x, head.y- this.bodyHeight, this.bodyWidth,this.bodyHeight);
                } else{
                    test = new Rect(head.x, head.y+ this.bodyHeight, this.bodyWidth,this.bodyHeight);
                }

                if (this.intersectingWithSelf(test)) {
                    kill();
                }
                body.addLast(test);
            }}
        }


    public boolean atefruit(Rect r){
        Rect head=getHead();
        for(var z=r.x; z<=(r.x+12);z++){
            int g=(int)z;
            if(g>=head.x && g<=(head.x+24) && r.y>=head.y&&r.y<+(head.y+24)){
                updateScore();
                return true;
            }
        }
        for(var z=r.y; z<=(r.y+12);z++){
            int g=(int)z;
            if(r.x>=head.x && r.x<=(head.x+24) && g>=head.y && g<+(head.y+24)){
                return true;
            }
        }
        return false;
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


    public boolean intersectingWithScreenBoundaries(Rect head) {
        return body.getLast().x < this.background.x-24 || body.getLast().x + body.getLast().width > this.background.x + this.background.width+24 || body.getLast().y < this.background.y+11 || body.getLast().y + body.getLast().height > this.background.y + this.background.height;
    }

    public void grow() {
        Rect head = getTail();
        body.addFirst(new Rect(head.x, head.y, head.width, head.height));
    }



    }

