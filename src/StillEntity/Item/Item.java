package StillEntity.Item;

import Character.Bomber.Bomber;
import Main.Time;
import StillEntity.Still;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Item extends Still {
    protected boolean collide = false;
    protected boolean hidden = false;

    protected Time time;

    public Item() {
    }

    public Item(int x, int y) {
        super(x, y);
    }

    public Item(BufferedImage image, int x, int y) {
        super(image, x, y);
    }

    abstract public void update(Bomber bomber);

    public boolean isCollide() {
        return collide;
    }

    @Override
    public int hashCode() {
        return hashCodeItem;
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.drawImage(this.image, this.x, this.y, size, size, null);
    }
}
