package StillEntity;

import Character.Bomber.Bomber;
import Main.GamePanel;
import Constant.Sprite;
import Main.Time;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Boom extends Still {
    protected int frame, interval = 10;
    protected int index = 0;
    private Time time = new Time();
    int count = 0;
    private final BufferedImage[] img = Sprite.bombAnim;
    private final BufferedImage[] front = Sprite.fontExplosion;
    private final BufferedImage[] left = Sprite.leftExplosion;
    private final BufferedImage[] right = Sprite.rightExplosion;
    private final BufferedImage[] up = Sprite.upExplosion;
    private final BufferedImage[] down = Sprite.downExplosion;
    private final BufferedImage[] brickbat = Sprite.brickExplosion;

    private int line = 1;
    private final int maxLine = 5;
    private int[][] checkLine = new int[maxLine * 2 + 1][maxLine * 2 + 1];

    public Boom(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        this.line = Bomber.getLine();

        for (int i = 0; i < maxLine * 2 + 1; ++i) {
            for (int j = 0; j < maxLine * 2 + 1; ++j) {
                checkLine[i][j] = 0;
            }
        }

        checkLine[maxLine][maxLine] = 1;
        for (int i = 1; i <= line; ++i) {
            if (Map.getMap(this.x, this.y - i) == 0 || Map.getMap(this.x, this.y - i) == 3)
                checkLine[maxLine][maxLine - i] = checkLine[maxLine][maxLine - i + 1];
            if (Map.getMap(this.x, this.y + i) == 0 || Map.getMap(this.x, this.y + i) == 3)
                checkLine[maxLine][maxLine + i] = checkLine[maxLine][maxLine + i - 1];
            if (Map.getMap(this.x - i, this.y) == 0 || Map.getMap(this.x - i, this.y) == 3)
                checkLine[maxLine - i][maxLine] = checkLine[maxLine - i + 1][maxLine];
            if (Map.getMap(this.x + i, this.y) == 0 || Map.getMap(this.x + i, this.y) == 3)
                checkLine[maxLine + i][maxLine] = checkLine[maxLine + i - 1][maxLine];
        }
        for (int i = 1; i <= line; ++i) {
            if (Map.getMap(this.x, this.y - i) == -1)
                checkLine[maxLine][maxLine - i] = (checkLine[maxLine][maxLine - i + 1] == 1 ? 2 : 0);
            if (Map.getMap(this.x, this.y + i) == -1)
                checkLine[maxLine][maxLine + i] = (checkLine[maxLine][maxLine + i - 1] == 1 ? 2 : 0);
            if (Map.getMap(this.x - i, this.y) == -1)
                checkLine[maxLine - i][maxLine] = (checkLine[maxLine - i + 1][maxLine] == 1 ? 2 : 0);
            if (Map.getMap(this.x + i, this.y) == -1)
                checkLine[maxLine + i][maxLine] = (checkLine[maxLine + i - 1][maxLine] == 1 ? 2 : 0);
        }

        if (count == 4) {
            frame++;
            if (frame > interval) {
                frame = 0;
                ++this.index;
            }
            for(int i = 1; i<=line; ++i){
                if (checkLine[maxLine + i][maxLine] == 1) Map.setDie(x + i, y, true);
                if (checkLine[maxLine - i][maxLine] == 1) Map.setDie(x - i, y, true);
                if (checkLine[maxLine][maxLine - i] == 1) Map.setDie(x, y - i, true);
                if (checkLine[maxLine][maxLine + i] == 1) Map.setDie(x, y + i, true);
            }
            if (index == 4) {
                Map.setMap(x, y, 0);
                GamePanel.setCountBoom();
                for (int i = 0; i <= line; ++i) {
                    if (checkLine[maxLine + i][maxLine] == 1) Map.setDie(x + i, y, false);
                    if (checkLine[maxLine - i][maxLine] == 1) Map.setDie(x - i, y, false);
                    if (checkLine[maxLine][maxLine - i] == 1) Map.setDie(x, y - i, false);
                    if (checkLine[maxLine][maxLine + i] == 1) Map.setDie(x, y + i, false);

                    if (checkLine[maxLine + i][maxLine] == 2) Map.setMap(x + i, y, hashCodeGrass);
                    if (checkLine[maxLine - i][maxLine] == 2) Map.setMap(x - i, y, hashCodeGrass);
                    if (checkLine[maxLine][maxLine - i] == 2) Map.setMap(x, y - i, hashCodeGrass);
                    if (checkLine[maxLine][maxLine + i] == 2) Map.setMap(x, y + i, hashCodeGrass);
                }
            }
            return;
        }

        frame++;
        if (frame > interval) {
            frame = 0;
            this.index = (++this.index) % 3;
            if (this.index == 2) count++;
            if (count == 4) {
                frame = 0;
                this.index = 0;
                this.interval = 5;
            }
        }

        image = img[index];
    }

    @Override
    public void render(Graphics2D graphics2D) {
        this.update();

        if (count < 4) super.render(graphics2D);
        else {
            if (index == front.length) return;
            graphics2D.drawImage(front[index], x * size, y * size, size, size, null);

            for (int i = 1; i <= line; ++i) {
                if (checkLine[maxLine + i][maxLine] != 0)
                    graphics2D.drawImage(right[index], (x + i) * size - size / 2, y * size, size + size / 2, size, null);
                if (checkLine[maxLine - i][maxLine] != 0)
                    graphics2D.drawImage(left[index], (x - i) * size, y * size, size + size / 2, size, null);
                if (checkLine[maxLine][maxLine - i] != 0)
                    graphics2D.drawImage(up[index], x * size, (y - i) * size, size, size + size / 2, null);
                if (checkLine[maxLine][maxLine + i] != 0)
                    graphics2D.drawImage(down[index], x * size, (y + i) * size - size / 2, size, size + size / 2, null);

                if (checkLine[maxLine + i][maxLine] == 2)
                    graphics2D.drawImage(brickbat[index], (x + i) * size, y * size, size, size, null);
                if (checkLine[maxLine - i][maxLine] == 2)
                    graphics2D.drawImage(brickbat[index], (x - i) * size, y * size, size, size, null);
                if (checkLine[maxLine][maxLine - i] == 2)
                    graphics2D.drawImage(brickbat[index], x * size, (y - i) * size, size, size, null);
                if (checkLine[maxLine][maxLine + i] == 2)
                    graphics2D.drawImage(brickbat[index], x * size, (y + i) * size, size, size, null);
            }
            graphics2D.drawImage(front[index], x * size, y * size, size, size, null);
        }
    }

    @Override
    public int hashCode() {
        return hashCodeBoom;
    }

}
