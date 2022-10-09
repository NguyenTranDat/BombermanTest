package Character.Bomber;

import Character.Character;
import Constant.Const;
import Constant.Sprite;
import StillEntity.Map;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bomber extends Character {

    private final BufferedImage[] left = Sprite.playerAnimLeft;
    private final BufferedImage[] right = Sprite.playerAnimRight;
    private final BufferedImage[] up = Sprite.playerAnimUp;
    private final BufferedImage[] down = Sprite.playerAnimDown;
    private final BufferedImage[] imgDie = Sprite.playerAnimDie;

    private static int countBoom = 1;
    private static int line = 1;

    public Bomber() {
        this.x = 48;
        this.y = 48;
        this.speed = 3;
        this.interval = 5;
        this.size = 38;
    }

    @Override
    public void update() {
        super.update();

        if(die) {
            frame++;
            if (frame > interval) {
                frame = 0;
                ++index;
            }
            if(index >= imgDie.length) img = null;
            else img = imgDie[index];

            return;
        }

        if (directRight && isFree(this.x + speed, this.y)) {
            this.x += speed;
        }
        if (directLeft && isFree(this.x - speed, this.y)) {
            this.x -= speed;
        }
        if (directUp && isFree(this.x, this.y - speed)) {
            this.y -= speed;
        }
        if (directDown && isFree(this.x, this.y + speed)) {
            this.y += speed;
        }

        frame++;
        if (frame > interval) {
            frame = 0;
            index = (++index) % 3;
        }

        if (directRight) {
            img = right[index];
        } else if (directLeft) {
            img = left[index];
        } else if (directUp) {
            img = up[index];
        } else if (directDown) {
            img = down[index];
        } else {
            img = down[1];
        }
    }

    public void setDirectRight() {
        this.directRight = true;
    }

    public void setDirectLeft() {
        this.directLeft = true;
    }

    public void setDirectUp() {
        this.directUp = true;
    }

    public void setDirectDown() {
        this.setDirect();
        this.directDown = true;
    }

    @Override
    public boolean isFree(int nextX, int nextY) {
        int nextX_1 = nextX / Const.size;
        int nextY_1 = nextY / Const.size;

        int nextX_2 = (nextX + size - 1) / Const.size;
        int nextY_2 = nextY / Const.size;

        int nextX_3 = nextX / Const.size;
        int nextY_3 = (nextY + size - 1) / Const.size;

        int nextX_4 = (nextX + size - 1) / Const.size;
        int nextY_4 = (nextY + size - 1) / Const.size;

        return !((Map.getMap(nextX_1, nextY_1) == 1 || Map.getMap(nextX_1, nextY_1) == -1) ||
                (Map.getMap(nextX_2, nextY_2) == 1 || Map.getMap(nextX_2, nextY_2) == -1) ||
                (Map.getMap(nextX_3, nextY_3) == 1 || Map.getMap(nextX_3, nextY_3) == -1) ||
                (Map.getMap(nextX_4, nextY_4) == 1 || Map.getMap(nextX_4, nextY_4) == -1) );
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(img, this.x, this.y, size, size, null);
    }

    public void setDie() {
        this.die = true;

        this.frame = 0;
        this.index = 0;
        this.interval += 10;
    }
    
    @Override
    public boolean getDie() {
        return this.die;
    }

    @Override
    public int getX() {
        return x + size / 2;
    }

    @Override
    public int getY() {
        return y + size / 2;
    }

    public static int getCountBoom() {
        return countBoom;
    }

    public void setCountBoom() {
        countBoom++;
    }

    public static int getLine() {
        return line;
    }

    public void setLine() {
        line++;
    }

    public void setSpeed() {
        this.speed += 2;
    }

    public int getSpeed() {
        return speed;
    }
}
