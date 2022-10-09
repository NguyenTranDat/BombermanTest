package Menu.Infor;

import Constant.Const;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Infor {
    protected BufferedImage image;
    protected int x;
    protected int y;
    protected int size = Const.size / 2;
    protected String text = "";

    public Infor(String text, int x, int y) {
        this.x = x;
        this.y = y;
        this.text = text;
    }

    public void render(Graphics2D graphics2D) {
        if (image == null) return;
        graphics2D.drawImage(image, x, y, size, size, null);

        graphics2D.setColor(Color.cyan);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.BOLD, size);
        graphics2D.setFont(font);
        graphics2D.drawString(":" + text, x + size, y + size);
    }
}
