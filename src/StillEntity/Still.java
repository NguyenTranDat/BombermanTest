package StillEntity;

import Constant.Const;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Still implements Const {
    protected BufferedImage image = null;
    protected int x, y;

    public Still() {
    }

    public Still(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Still(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void update() {}

    public void render(Graphics2D graphics2D) {
        if (image == null) return;
        graphics2D.drawImage(image, x * size, y * size, size, size, null);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
