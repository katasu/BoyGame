//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ML extends MouseAdapter implements MouseMotionListener {
    public boolean isPressed = false;
    public double x = 0.0;
    public double y = 0.0;

    public ML() {
    }

    public void mousePressed(MouseEvent e) {
        this.isPressed = true;
    }

    public void mouseReleased(MouseEvent e) {
        this.isPressed = false;
    }

    public void mouseMoved(MouseEvent e) {
        this.x = (double)e.getX();
        this.y = (double)e.getY();
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public boolean isPressed() {
        return this.isPressed;
    }
}
