package Character;

import Constant.Const;
import StillEntity.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Character {
    protected int size = Const.size;

    protected int x;
    protected int y;
    protected int speed;
    protected boolean die = false;

    protected int index = 0;
    protected int frame = 0;
    protected int interval = 0;
    protected boolean directRight, directLeft, directUp, directDown;

    protected BufferedImage img = null;

    public Character() {}

    public Character(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Character(int x, int y, int speed, int index, int frame, int interval) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.index = index;
        this.frame = frame;
        this.interval = interval;
    }

    public int getX() {
        return x + size / 2;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y + size / 2;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void update() {
        if(!die) {
            int nextX_1 = getX() / Const.size;
            int nextY_1 = getY() / Const.size;

            if(Map.getDie(nextX_1, nextY_1)) this.die = true;

            if(this.die) {
                this.frame = 0;
                this.index = 0;
                this.interval += 10;
            }
        }
    }

    public void render(Graphics2D graphics2D) {
        if (img == null) return;
        graphics2D.drawImage(img, x, y, size, size, null);
    }

    public boolean isFree(int nextX, int nextY) {
        int nextX_1 = nextX / Const.size;
        int nextY_1 = nextY / Const.size;

        int nextX_2 = (nextX + this.size - 1) / Const.size;
        int nextY_2 = nextY / Const.size;

        int nextX_3 = nextX / Const.size;
        int nextY_3 = (nextY + this.size - 1) / Const.size;

        int nextX_4 = (nextX + this.size - 1) / Const.size;
        int nextY_4 = (nextY + this.size - 1) / Const.size;

        return !((Map.getMap(nextX_1, nextY_1) == Const.hashCodeWall || Map.getMap(nextX_1, nextY_1) == Const.hashCodeBrick || Map.getMap(nextX_1, nextY_1) == Const.hashCodeBoom) ||
                (Map.getMap(nextX_2, nextY_2) == Const.hashCodeWall || Map.getMap(nextX_2, nextY_2) == Const.hashCodeBrick || Map.getMap(nextX_1, nextY_1) == Const.hashCodeBoom) ||
                (Map.getMap(nextX_3, nextY_3) == Const.hashCodeWall || Map.getMap(nextX_3, nextY_3) == Const.hashCodeBrick || Map.getMap(nextX_1, nextY_1) == Const.hashCodeBoom) ||
                (Map.getMap(nextX_4, nextY_4) == Const.hashCodeWall || Map.getMap(nextX_4, nextY_4) == Const.hashCodeBrick || Map.getMap(nextX_1, nextY_1) == Const.hashCodeBoom));
    }

    public void setDirect() {
        directDown = false;
        directUp = false;
        directLeft = false;
        directRight = false;
    }

    abstract public boolean getDie();

    public boolean isDie() {
        return die;
    }
}
