package Menu.Infor;

import java.awt.*;

public class CountMonster extends Infor{
    public CountMonster(String text, int x, int y) {
        super(text, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.setColor(Color.cyan);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.BOLD, size);
        graphics2D.setFont(font);
        graphics2D.drawString("Monsters:" + text, x, y + size);
    }
}
