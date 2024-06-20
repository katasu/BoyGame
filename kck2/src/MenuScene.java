//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;

public class MenuScene extends Scene {
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

    public MenuScene(KL keyListener, ML mouseListener) {
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;

        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/men.png"));
            BufferedImage spritesheet2 = ImageIO.read(new File("assets/menu2.png"));
            this.title = spritesheet.getSubimage(28, 775, 350, 100);
            this.play = spritesheet.getSubimage(48, 151, 299, 95);
            this.playC = spritesheet.getSubimage(373, 45, 223, 95);
            this.leaderboard = spritesheet.getSubimage(44, 434, 613, 95);
            this.playPressed = spritesheet2.getSubimage(9, 196, 299, 95);
            this.playCPressed = spritesheet2.getSubimage(350, 110, 223, 95);
            this.leadPressed = spritesheet2.getSubimage(44, 487, 613, 95);
            this.exit = spritesheet.getSubimage(1, 0, 233, 93);
            this.exitPressed = spritesheet2.getSubimage(38, 18, 233, 93);
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
        this.exitRect = new Rect(10, 505, 130.0, 55.0);
    }

    public void update(double dt) {
        if (this.mouseListener.getX() >= this.playSRect.x && this.mouseListener.getX() <= this.playSRect.x + this.playSRect.width && this.mouseListener.getY() >= this.playSRect.y && this.mouseListener.getY() <= this.playSRect.y + this.playSRect.height) {
            this.playCurrentImage = this.playPressed;
            if (this.mouseListener.isPressed()) {
                Danke.getWindow().changeState(2,0,false,2);
            }
        } else {
            this.playCurrentImage = this.play;
        }
        if (this.mouseListener.getX() >= this.playCRect.x && this.mouseListener.getX() <= this.playCRect.x + this.playCRect.width && this.mouseListener.getY() >= this.playCRect.y && this.mouseListener.getY() <= this.playCRect.y + this.playCRect.height) {
           this.playCCurrentImage = this.playCPressed;
            if (this.mouseListener.isPressed()) {
                Danke.getWindow().changeState(3,0,false,2);
            }
        } else {
           this.playCCurrentImage = this.playC;
        }
        if (this.mouseListener.getX() >= this.leadRect.x && this.mouseListener.getX() <= this.leadRect.x + this.leadRect.width && this.mouseListener.getY() >= this.leadRect.y && this.mouseListener.getY() <= this.leadRect.y + this.leadRect.height) {
           this.leadeCurrentImage = this.leadPressed;
            if (this.mouseListener.isPressed()) {
                Danke.getWindow().changeState(4,0,false,2);
            }
        } else {
           this.leadeCurrentImage = this.leaderboard;
        }

        if (this.mouseListener.getX() >= this.exitRect.x && this.mouseListener.getX() <= this.exitRect.x + this.exitRect.width && this.mouseListener.getY() >= this.exitRect.y && this.mouseListener.getY() <= this.exitRect.y + this.exitRect.height) {
            this.exitCurrentImage = this.exitPressed;
            if (this.mouseListener.isPressed()) {
                Danke.getWindow().close();
            }
        } else {
            this.exitCurrentImage = this.exit;
        }

    }

    public void draw(Graphics g) {
        g.setColor(new Color(176, 248, 255));
        g.fillRect(0, 0, 480, 960);
        g.drawImage(this.title, (int)this.titleRect.x, (int)this.titleRect.y, (int)this.titleRect.width, (int)this.titleRect.height, (ImageObserver)null);
        g.drawImage(this.playCurrentImage, (int)this.playSRect.x, (int)this.playSRect.y, (int)this.playSRect.width, (int)this.playSRect.height, (ImageObserver)null);
        g.drawImage(this.leadeCurrentImage, (int)this.leadRect.x, (int)this.leadRect.y, (int)this.leadRect.width, (int)this.leadRect.height, (ImageObserver)null);
        g.drawImage(this.playCCurrentImage, (int)this.playCRect.x, (int)this.playCRect.y, (int)this.playCRect.width, (int)this.playCRect.height, (ImageObserver)null);
        g.drawImage(this.exitCurrentImage, (int)this.exitRect.x, (int)this.exitRect.y, (int)this.exitRect.width, (int)this.exitRect.height, (ImageObserver)null);

    }
}
