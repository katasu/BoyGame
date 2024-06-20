

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

public class SnakeMScene extends Scene {
    public KL keyListener;
    public ML mouseListener;
    public BufferedImage title;
    public BufferedImage play;
    public BufferedImage playPressed;
    public BufferedImage exit;
    public BufferedImage exitPressed;
    public Rect playRect;
    public Rect exitRect;
    public Rect titleRect;
    public BufferedImage playCurrentImage;
    public BufferedImage exitCurrentImage;

    public SnakeMScene(KL keyListener, ML mouseListener) {
        this.keyListener = keyListener;
        this.mouseListener = mouseListener;


        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/men.png"));
            BufferedImage spritesheet2 = ImageIO.read(new File("assets/menu2.png"));
            this.title = spritesheet.getSubimage(48, 151, 300, 95);
            this.play = spritesheet.getSubimage(417, 712, 220, 120);
            this.playPressed = spritesheet2.getSubimage(423, 752, 220, 120);
            this.exit = spritesheet.getSubimage(1, 0, 233, 93);
            this.exitPressed = spritesheet2.getSubimage(38, 18, 233, 93);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        this.playCurrentImage = this.play;
        this.exitCurrentImage = this.exit;
        this.titleRect = new Rect(50.0, 100.0, 320.0, 100.0);
        this.playRect = new Rect(150, 280.0, 150.0, 70.0);
        this.exitRect = new Rect(150, 355.0, 130.0, 55.0);
    }

    public void update(double dt) {
        if (this.mouseListener.getX() >= this.playRect.x && this.mouseListener.getX() <= this.playRect.x + this.playRect.width && this.mouseListener.getY() >= this.playRect.y && this.mouseListener.getY() <= this.playRect.y + this.playRect.height) {
            this.playCurrentImage = this.playPressed;
            if (this.mouseListener.isPressed()) {
                Danke.getWindow().changeState(1,0,false,2);
            }
        } else {
            this.playCurrentImage = this.play;
        }

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
        g.drawImage(this.playCurrentImage, (int)this.playRect.x, (int)this.playRect.y, (int)this.playRect.width, (int)this.playRect.height, (ImageObserver)null);
        g.drawImage(this.exitCurrentImage, (int)this.exitRect.x, (int)this.exitRect.y, (int)this.exitRect.width, (int)this.exitRect.height, (ImageObserver)null);

    }
}
