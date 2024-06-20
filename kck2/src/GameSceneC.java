//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.font.TextAttribute;
import java.awt.image.ImageObserver;
import java.util.LinkedList;

public class GameSceneC extends Scene {
    Rect background = new Rect(0.0, 0.0, 480, 960);
    Rect foreground = new Rect(24.0, 96, 424.0, 840.0);
    Car car;

    KL keyListener;

    public void gamestop(){
        Danke.getWindow().changeState(5,car.getScore(),true,2);

    }

    public GameSceneC(KL keyListener) {
        this.car = new Car( 212, 780, 24, 24, this.foreground);
        this.keyListener = keyListener;

    }
    public int gScore(){
        return car.getScore();
    }

    public void update(double dt) {
        if (this.keyListener.isKeyPressed(38)) {
            this.car.changeDirecton(Direction.UP);
        } else if (this.keyListener.isKeyPressed(40)) {
            this.car.changeDirecton(Direction.DOWN);
        } else if (this.keyListener.isKeyPressed(39)) {
            this.car.changeDirecton(Direction.RIGHT);
        } else if (this.keyListener.isKeyPressed(37)) {
            this.car.changeDirecton(Direction.LEFT);
        }



        if(car.isAlive()==true){

            this.car.update(dt);
        }else {
            Danke.getWindow().changeState(6,car.getScore(),true,2);
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
        g2.drawString("Your score:"+car.getScore(),20,55);
        this.draw(g2,car.getBody());
        for(LinkedList<Rect> list: car.getOpponents()){
            draw(g2,list);

        }
    }

    public void draw(Graphics2D g2, LinkedList<Rect> body ) {



        for(Rect p : body)
        {
            double subWidth = (p.width - 6.0) / 2.0;
            double subHeight = (p.height - 6.0) / 2.0;
            g2.setColor(Color.BLACK);
            if(p.y>70)
                g2.fill(new Rectangle2D.Double(p.x + 2.0, p.y + 2.0, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(p.x + 4.0 + subWidth, p.y + 2.0, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(p.x + 2.0, p.y + 4.0 + subHeight, subWidth, subHeight));
            g2.fill(new Rectangle2D.Double(p.x + 4.0 + subWidth, p.y + 4.0 + subHeight, subWidth, subHeight));

        }
    }
}

