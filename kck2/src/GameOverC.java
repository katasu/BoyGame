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

public class GameOverC extends Scene {
    public KL keyListener;
    public ML mouseListener;
    public BufferedImage title;
    public BufferedImage title2;
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
    public Rect title2Rect;
    public BufferedImage playCurrentImage;
    public BufferedImage playCCurrentImage;
    public BufferedImage leadeCurrentImage;
    public BufferedImage exitCurrentImage;


    public GameOverC(KL keyListener, ML mouseListener) {
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/men.png"));
            BufferedImage spritesheet2 = ImageIO.read(new File("assets/menu2.png"));
            BufferedImage spritesheet3 = ImageIO.read(new File("assets/back.png"));
            BufferedImage spritesheet4 = ImageIO.read(new File("assets/back2.png"));
            BufferedImage spritesheet5 = ImageIO.read(new File("assets/again.png"));
            this.title=spritesheet.getSubimage(10,294,515,94);
            this.play = spritesheet.getSubimage(417, 712, 220, 120);
            this.playPressed = spritesheet2.getSubimage(423, 752, 220, 120);
            this.playC = spritesheet5.getSubimage(205, 0, 300, 130);
            this.playCPressed = spritesheet5.getSubimage(105, 197, 300, 130);
            this.exit = spritesheet3.getSubimage(1, 0, 124, 100);
            this.exitPressed = spritesheet4.getSubimage(1, 0, 124, 100);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.playCurrentImage = this.play;
        this.playCCurrentImage = this.playC;
        this.exitCurrentImage = this.exit;
        this.titleRect = new Rect(80, 250, 320.0, 100.0);
        this.playSRect = new Rect(150, 420, 150.0, 70.0);
        this.playCRect = new Rect(150, 490, 150.0, 70.0);
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
        if (this.mouseListener.getX() >= this.playCRect.x && this.mouseListener.getX() <= this.playCRect.x + this.playCRect.width && this.mouseListener.getY() >= this.playCRect.y && this.mouseListener.getY() <= this.playCRect.y + this.playCRect.height || this.mouseListener.getX() >= this.playSRect.x && this.mouseListener.getX() <= this.playSRect.x + this.playSRect.width && this.mouseListener.getY() >= this.playSRect.y && this.mouseListener.getY() <= this.playSRect.y + this.playSRect.height) {
            this.playCurrentImage = this.playPressed;
            this.playCCurrentImage = this.playCPressed;
            if (this.mouseListener.isPressed()) {
                Danke.getWindow().changeState(3,0,false,2);
            }
        } else {
            this.playCurrentImage = this.play;
            this.playCCurrentImage = this.playC;
        }

    }

    public void draw(Graphics g) {
        g.setColor(new Color(176, 248, 255));
        g.fillRect(0, 0, 480, 960);
        g.drawImage(this.title, (int)this.titleRect.x, (int)this.titleRect.y, (int)this.titleRect.width, (int)this.titleRect.height, (ImageObserver)null);
        g.drawImage(this.playCurrentImage, (int)this.playSRect.x, (int)this.playSRect.y, (int)this.playSRect.width, (int)this.playSRect.height, (ImageObserver)null);
        g.drawImage(this.playCCurrentImage, (int)this.playCRect.x, (int)this.playCRect.y, (int)this.playCRect.width, (int)this.playCRect.height, (ImageObserver)null);
        g.drawImage(this.exitCurrentImage, (int)this.exitRect.x, (int)this.exitRect.y, (int)this.exitRect.width, (int)this.exitRect.height, (ImageObserver)null);

    }
}
