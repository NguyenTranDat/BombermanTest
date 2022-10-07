package Character.Monster;

import Character.Character;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Monster extends Character {
    protected BufferedImage score;

    public Monster() {}

    public Monster(int x, int y) {
        super(x, y);
    }

    public Monster(int x, int y, int speed, int index, int frame, int interval, BufferedImage score) {
        super(x, y, speed, index, frame, interval);
        this.score = score;
    }


    abstract public void move();
    public void move(int nextX, int nextY) {}

    @Override
    public void render(Graphics2D graphics2D) {
        super.render(graphics2D);

        if(img == null) {
            graphics2D.drawImage(score, this.x, this.y, 16 * 3, 8 * 3,null);
        }
    }
}
