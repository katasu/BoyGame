//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class LeadMenu extends Scene {
    public KL keyListener;
    public ML mouseListener;
    public BufferedImage title;
    public BufferedImage play;
    public BufferedImage playC;
    public BufferedImage playPressed;
    public BufferedImage playCPressed;
    public BufferedImage exit;
    public BufferedImage exitPressed;
    public BufferedImage leaderboard;
    public BufferedImage leadPressed;
    public Rect playSRect;
    public Rect playCRect;
    public Rect exitRect;
    public Rect leadRect;
    public Rect titleRect;
    public BufferedImage playCurrentImage;
    public BufferedImage playCCurrentImage;
    public BufferedImage leadeCurrentImage;
    public BufferedImage exitCurrentImage;


    public LeadMenu(KL keyListener, ML mouseListener) {
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/men.png"));
            BufferedImage spritesheet2 = ImageIO.read(new File("assets/menu2.png"));
            BufferedImage spritesheet3 = ImageIO.read(new File("assets/back.png"));
            BufferedImage spritesheet4 = ImageIO.read(new File("assets/back2.png"));
            this.title = spritesheet.getSubimage(44, 434, 613, 95);
            this.exit = spritesheet3.getSubimage(1, 0, 124, 100);
            this.exitPressed = spritesheet4.getSubimage(1, 0, 124, 100);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.playCurrentImage = this.play;
        this.playCCurrentImage = this.playC;
        this.leadeCurrentImage = this.leaderboard;
        this.exitCurrentImage = this.exit;
        this.titleRect = new Rect(50.0, 100.0, 320.0, 100.0);
        this.playSRect = new Rect(10, 280.0, 150.0, 70.0);
        this.playCRect = new Rect(10, 355, 150.0, 70.0);
        this.leadRect = new Rect(10, 430, 350, 55.0);
        this.exitRect = new Rect(10, 800, 124, 100);
    }

    public void update(double dt) {

        if (this.mouseListener.getX() >= this.exitRect.x && this.mouseListener.getX() <= this.exitRect.x + this.exitRect.width && this.mouseListener.getY() >= this.exitRect.y && this.mouseListener.getY() <= this.exitRect.y + this.exitRect.height) {
            this.exitCurrentImage = this.exitPressed;
            if (this.mouseListener.isPressed()) {

                Danke.getWindow().changeState(0,0,false,2);
            }
        } else {
            this.exitCurrentImage = this.exit;
        }

    }

    public void draw(Graphics g) {
        g.setColor(new Color(176, 248, 255));
        g.fillRect(0, 0, 480, 960);
        g.drawImage(this.title, (int)this.titleRect.x, (int)this.titleRect.y, (int)this.titleRect.width, (int)this.titleRect.height, (ImageObserver)null);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Microsoft YaHei", Font.PLAIN, 28));
        var p1=20;
        var p2=300;
        g.drawString("Top 5 scores SNAKE:",p1,p2);
        Integer[] snakes= new Integer[Danke.getSnSc().size()];
        var pom=0;
        for(String s:Danke.getSnSc()){
            snakes[pom]=Integer.parseInt(s);
            pom++;
        }
        Arrays.sort(snakes);
        int rozs=snakes.length;
        for(var i = 1; i<=5; i++){
            p2+=30;
            g.drawString(i+". "+snakes[rozs-i],p1,p2);
        }
        p1=20;
        p2=500;
        g.drawString("Top 5 scores Cars:",p1,p2);
        Integer[] cars= new Integer[Danke.getCrSc().size()];
        pom=0;
        for(String s:Danke.getCrSc()){
            cars[pom]=Integer.parseInt(s);
            pom++;
        }
        Arrays.sort(cars);
        rozs=cars.length;
        for(var i = 1; i<=5; i++){
            p2+=30;
            g.drawString(i+". "+cars[rozs-i],p1,p2);
        }

        g.drawImage(this.exitCurrentImage, (int)this.exitRect.x, (int)this.exitRect.y, (int)this.exitRect.width, (int)this.exitRect.height, (ImageObserver)null);

    }
}
