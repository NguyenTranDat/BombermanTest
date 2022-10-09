package Character.Monster;

import Constant.Sprite;
import StillEntity.Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Balloon extends Monster {
    private final BufferedImage[] image;

    public Balloon(int x, int y) {
        super(x, y, 1, 0, 0, 10, Sprite.score[0]);
        image = Sprite.balloon;
    }


    @Override
    public void update() {
        super.update();
        if(die) {
            if (index < 6) {
                index = 6;
                frame = 0;
            } else {
                frame++;
                if (frame > interval) {
                    frame = 0;
                    ++index;
                }
            }

            if(index == image.length) img = score;
            else if(index > image.length) return;
            else img = image[index];

            return;
        }

        frame++;
        if (frame > interval) {
            frame = 0;
            index = (++index) % 6;
        }

        if (this.x % size == 0 && this.y % size == 0) {
            this.setDirect();
            move();
        }

        if (directRight && isFree(this.x + speed, this.y)) {
            this.x += speed;
        } else if (directLeft && isFree(this.x - speed, this.y)) {
            this.x -= speed;
        } else if (directUp && isFree(this.x, this.y - speed)) {
            this.y -= speed;
        } else if (directDown && isFree(this.x, this.y + speed)) {
            this.y += speed;
        }

        img = image[index];
    }

    @Override
    public void move() {
        if (this.y % size != 0 || this.x % size != 0) {
            return;
        }
        ArrayList<Point> d = new ArrayList<>();
        if (Map.getMap(this.x / size - 1, this.y / size) == 0) {
            d.add(new Point(-1, 0));
        }
        if (Map.getMap(this.x / size + 1, this.y / size) == 0) {
            d.add(new Point(1, 0));
        }
        if (Map.getMap(this.x / size, this.y / size - 1) == 0) {
            d.add(new Point(0, -1));
        }
        if (Map.getMap(this.x / size, this.y / size + 1) == 0) {
            d.add(new Point(0, 1));
        }

        if(d.size() == 0) return;

        Random random = new Random();
        int tmp = random.nextInt(d.size());

        if (d.get(tmp).getX() == 0) {
            if (d.get(tmp).getY() == 1) directDown = true;
            else directUp = true;
        } else {
            if (d.get(tmp).getX() == 1) directRight = true;
            else directLeft = true;
        }
    }

    @Override
    public int getScore() {
        return 100;
    }

    @Override
    public boolean getDie() {
        return index > image.length;
    }
}
