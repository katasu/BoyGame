//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

public class Food {
    public Rect background;
    public Snake snake;

    public BufferedImage play;
    public int width;
    public int height;
    public Color color;
    public Rect rect;
    public int xPadding;
    public BufferedImage getPlay(){
        return play;
    }
    public int getxPadding(){
        return xPadding;
    }
    public boolean isSpawned = false;
    public Rect foodR(){
        return rect;
    }
    public Food(Rect background, Snake snake, int width, int height, Color color) {
        this.background = background;
        this.snake = snake;
        this.width = width;
        this.height = height;
        this.color = color;
        this.rect = new Rect(0.0, 0.0, (double)width, (double)height);
        this.xPadding = (int)((double)(24.0F - (float)this.width) / 2.0);
        try {
            BufferedImage spritesheet = ImageIO.read(new File("assets/app.png"));
            this.play = spritesheet.getSubimage(1226, 151, 2721, 1905);

        } catch (Exception var4) {
            var4.printStackTrace();
        }
    }

    public void spawn() {
        do {
            double randX = (double)((float)((int)(Math.random() * (double)((int)(this.background.width / 24.0)))) * 24.0F) + this.background.x;
            double randY = (double)((float)((int)(Math.random() * (double)((int)(this.background.height / 24.0)))) * 24.0F) + this.background.y;
            this.rect.x = randX;
            this.rect.y = randY;
        } while(this.snake.intersectingWithRect(rect));

        this.isSpawned = true;
    }

    public void update(double dt) {
        if (this.snake.atefruit(this.rect)) {
            this.snake.grow();
            this.rect.x = -100.0;
            this.rect.y = -100.0;
            this.isSpawned = false;
        }

    }


}
