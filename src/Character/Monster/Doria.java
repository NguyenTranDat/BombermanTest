package Character.Monster;

import Constant.Const;
import Constant.Sprite;
import StillEntity.Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Doria extends Monster {

    private final BufferedImage[] image;
    private final ArrayList<Integer> direct = new ArrayList<>();

    public Doria(int x, int y) {
        super(x, y, 1, 0, 0, 20, Sprite.score[4]);
        image = Sprite.doria;
    }

    @Override
    public void update() {
        super.update();
        if (die) {
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

            if (index == image.length) img = score;
            else if (index > image.length) return;
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
    public boolean getDie() {
        return index > image.length;
    }

    @Override
    public void move() {
        if (direct.size() == 0) {
            if (isFree(this.x + speed, this.y)) {
                directRight = true;
            } else if (isFree(this.x - speed, this.y)) {
                directLeft = true;
            } else if (isFree(this.x, this.y - speed)) {
                directUp = true;
            } else if (isFree(this.x, this.y + speed)) {
                directDown = true;
            }
        } else {
            int tmp = direct.get(direct.size() - 1);
            direct.remove(direct.size() - 1);

            if (tmp == 1) {
                directLeft = true;
            } else if (tmp == 2) {
                directRight = true;
            } else if (tmp == 8) {
                directUp = true;
            } else if (tmp == 16) {
                directDown = true;
            }
        }
    }

    @Override
    public void move(int nextX, int nextY) {
        Queue<Point> q = new LinkedList<>();

        int[][] f = new int[Map.getWidth()][Map.getHeight()];

        for (int[] ints : f) {
            Arrays.fill(ints, -1);
        }

        f[this.x / size][this.y / size] = 0;
        q.add(new Point(this.x / size, this.y / size));

        while (!q.isEmpty()) {
            int tmpX = (int) q.peek().getX();
            int tmpY = (int) q.peek().getY();
            q.poll();

            if (tmpX == nextX / size && tmpY == nextY / size) break;

            if (Map.getMap(tmpX + 1, tmpY) != 3 && Map.getMap(tmpX + 1, tmpY) != 1 && f[tmpX + 1][tmpY] == -1) {
                f[tmpX + 1][tmpY] = f[tmpX][tmpY] + 1;
                q.add(new Point(tmpX + 1, tmpY));
            }
            if (Map.getMap(tmpX - 1, tmpY) != 3 && Map.getMap(tmpX - 1, tmpY) != 1 && f[tmpX - 1][tmpY] == -1) {
                f[tmpX - 1][tmpY] = f[tmpX][tmpY] + 1;
                q.add(new Point(tmpX - 1, tmpY));
            }
            if (Map.getMap(tmpX, tmpY + 1) != 3 && Map.getMap(tmpX, tmpY + 1) != 1 && f[tmpX][tmpY + 1] == -1) {
                f[tmpX][tmpY + 1] = f[tmpX][tmpY] + 1;
                q.add(new Point(tmpX, tmpY + 1));
            }
            if (Map.getMap(tmpX, tmpY - 1) !=3 && Map.getMap(tmpX, tmpY - 1) !=1 && f[tmpX][tmpY - 1] == -1) {
                f[tmpX][tmpY - 1] = f[tmpX][tmpY] + 1;
                q.add(new Point(tmpX, tmpY - 1));
            }
        }

        direct.clear();
        if(f[nextX/size][nextY/size] == -1) return;

        int tmpX = nextX/size;
        int tmpY = nextY/size;
        while(f[tmpX][tmpY] != 0) {
            if(f[tmpX + 1][tmpY] == (f[tmpX][tmpY] - 1)) {
                direct.add(1);
                ++tmpX;
            } else if(f[tmpX - 1][tmpY] == f[tmpX][tmpY] - 1) {
                direct.add(2);
                --tmpX;
            } else if(f[tmpX][tmpY+1] == f[tmpX][tmpY] - 1) {
                direct.add(8);
                ++tmpY;
            }else if(f[tmpX][tmpY-1] == f[tmpX][tmpY] - 1) {
                direct.add(16);
                --tmpY;
            }
        }
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

        return !((Map.getMap(nextX_1, nextY_1) == Const.hashCodeWall || Map.getMap(nextX_1, nextY_1) == Const.hashCodeBoom) ||
                (Map.getMap(nextX_2, nextY_2) == Const.hashCodeWall || Map.getMap(nextX_1, nextY_1) == Const.hashCodeBoom) ||
                (Map.getMap(nextX_3, nextY_3) == Const.hashCodeWall || Map.getMap(nextX_1, nextY_1) == Const.hashCodeBoom) ||
                (Map.getMap(nextX_4, nextY_4) == Const.hashCodeWall || Map.getMap(nextX_1, nextY_1) == Const.hashCodeBoom));
    }
}
