package StillEntity;

import Constant.Const;
import StillEntity.Item.Item;

import java.awt.*;
import java.util.ArrayList;

public class Map implements Const {
    private static int height;
    private static int width;
    private static final Still[][] map = new Still[100][100];
    private static final ArrayList<Still> stills = new ArrayList<>();

    private static final boolean[][] dangerous = new boolean[100][100];

    public Map() {
        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 100; ++j) {
                map[i][j] = null;
                dangerous[i][j] = false;
            }
        }
    }

    public static void setDie(int x, int y, boolean value) {
        if (x < width && y < height && x > 0 && y > 0) {
            dangerous[x][y] = value;
        }
    }

    public static boolean getDie(int x, int y) {
        if (x < width && y < height && x > 0 && y > 0)
            return dangerous[x][y];
        return false;
    }

    public void render(Graphics2D g2) {
        for(int i = stills.size() - 1; i >= 0; --i) {
            if(stills.get(i) instanceof Item) {
                Item item = (Item) stills.get(i);
                if(item.isCollide()) stills.remove(i);
                else stills.get(i).render(g2);
            } else stills.get(i).render(g2);
        }

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (map[i][j] == null) continue;
                if(map[i][j] instanceof Brick) map[i][j].render(g2);
            }
        }

        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                if (map[i][j] == null) continue;
                if(map[i][j] instanceof Brick) continue;
                map[i][j].render(g2);
            }
        }
    }

    public static void setMap(int x, int y, int number) {
        if (x > width || y > height || x < 0 || y < 0) return;
        if (number == hashCodeGrass) {
            map[x][y] = null;
        }
        if (number == hashCodeBrick) {
            map[x][y] = new Brick(x, y);
        }
        if (number == hashCodeWall) {
            map[x][y] = new Wall(x, y);
        }
        if (number == hashCodeBoom) {
            map[x][y] = new Boom(x, y);
        }
    }

    public static int getMap(int x, int y) {
        if (x > width || y > height || x < 0 || y < 0) return 1;
        if (map[x][y] == null) return 0;
        return map[x][y].hashCode();
    }

    public static int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        Map.height = height;
    }

    public static int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        Map.width = width;
    }

    public static void addStill(Still still) {
        Map.stills.add(still);
    }

    public static Still getStill(int i) {
        if(i >= Map.stills.size()) return null;
        return Map.stills.get(i);
    }
}
