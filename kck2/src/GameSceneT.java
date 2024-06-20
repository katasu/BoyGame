//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Point;
import java.awt.font.TextAttribute;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class GameSceneT extends Scene {
    Rect background = new Rect(0.0, 0.0, 480, 960);
    Rect foreground = new Rect(24.0, 96, 424.0, 840.0);
    Snake snake;

    KL keyListener;
    public Food food;


    public GameSceneT(KL keyListener) {
        this.snake = new Snake(5, 20, 120, 24, 24, this.foreground);
        this.keyListener = keyListener;
        this.food = new Food(this.foreground, this.snake, 12, 12, Color.GREEN);
        this.food.spawn();
    }
    public int gScore(){
        return snake.getScore();
    }
    public void update(double dt) {
        if (this.keyListener.isKeyPressed(38)) {
            this.snake.changeDirecton(Direction.UP);
        } else if (this.keyListener.isKeyPressed(40)) {
            this.snake.changeDirecton(Direction.DOWN);
        } else if (this.keyListener.isKeyPressed(39)) {
            this.snake.changeDirecton(Direction.RIGHT);
        } else if (this.keyListener.isKeyPressed(37)) {
            this.snake.changeDirecton(Direction.LEFT);
        }

        if (!this.food.isSpawned) {
            this.food.spawn();
        }

        if(snake.isAlive()==true){
            this.food.update(dt);
            this.snake.update(dt);
        }else {
            Danke.getWindow().changeState(5,snake.getScore(),true,1);
        }
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.BLACK);
        g2.fill(new Rectangle2D.Double(this.background.x, this.background.y, this.background.width, this.background.height));
        g2.setColor(Color.WHITE);
        g2.fill(new Rectangle2D.Double(this.foreground.x, this.foreground.y, this.foreground.width, this.foreground.height));
        g2.setColor(Color.YELLOW);
        g2.setFont(new Font("Microsoft YaHei", Font.PLAIN, 28));
        g2.drawString("Your score:"+snake.getScore(),20,55);
        this.draw(g2,snake.getBody());
        this.draw(g2,food.foodR());
    }
    public void draw(Graphics2D g2, LinkedList<Rect> body ) {



        for(Rect p : body)
        {
            double subWidth = (p.width - 6.0) / 2.0;
            double subHeight = (p.height - 6.0) / 2.0;
            g2.setColor(Color.BLACK);
            g2.fill(new Rectangle2D.Double(p.x + 2.0, p.y + 2.0, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(p.x + 4.0 + subWidth, p.y + 2.0, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(p.x + 2.0, p.y + 4.0 + subHeight, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(p.x + 4.0 + subWidth, p.y + 4.0 + subHeight, subWidth, subHeight));


        }
    }
    public void draw(Graphics g2, Rect rect) {
        g2.setColor(Color.ORANGE);




        g2.drawImage(food.getPlay(), (int)rect.x + food.getxPadding(), (int)rect.y + food.getxPadding(), 22, 22, (ImageObserver)null);
    }
}

