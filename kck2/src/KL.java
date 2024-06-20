//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KL extends KeyAdapter implements KeyListener {
    private boolean[] keyPressed = new boolean[128];

    public KL() {
    }

    public void keyPressed(KeyEvent keyEvent) {
        this.keyPressed[keyEvent.getKeyCode()] = true;
    }

    public void keyReleased(KeyEvent keyEvent) {
        this.keyPressed[keyEvent.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int keyCode) {
        return this.keyPressed[keyCode];
    }
}
