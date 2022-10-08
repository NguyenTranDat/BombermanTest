package Menu.Menu;

import Constant.Const;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Menu {
    protected BufferedImage image;
    protected int x;
    protected int y;
    protected int size = Const.size / 3;

    Menu() {
    }

    public Menu(BufferedImage image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }

    public void render(Graphics2D graphics2D) {
        if (image == null) return;
        graphics2D.drawImage(image, x, y, size, size, null);
    }
}
