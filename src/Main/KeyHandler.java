package Main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public final class KeyHandler implements KeyListener {
    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean boom;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_DOWN || keyCode == KeyEvent.VK_S) {
            this.setupPressed();
            this.downPressed = true;
        } else if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_W) {
            this.setupPressed();
            this.upPressed = true;
        } else if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_A) {
            this.setupPressed();
            this.leftPressed = true;
        } else if (keyCode == KeyEvent.VK_RIGHT || keyCode == KeyEvent.VK_D) {
            this.setupPressed();
            this.rightPressed = true;
        }
        if (keyCode == KeyEvent.VK_SPACE) {
            this.boom = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.setupPressed();
        this.boom = false;
    }

    private void setupPressed() {
        this.downPressed = false;
        this.rightPressed = false;
        this.upPressed = false;
        this.leftPressed = false;
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public boolean isBoom() {
        return boom;
    }
}