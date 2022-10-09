package Menu.Infor;

import java.awt.*;

public class Speed extends Infor{
    public Speed(String text, int x, int y) {
        super(text, x, y);
    }

    @Override
    public void render(Graphics2D graphics2D) {
        graphics2D.setColor(Color.cyan);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Font font = new Font("Serif", Font.BOLD, size);
        graphics2D.setFont(font);
        graphics2D.drawString("Speed:" + text, x, y + size);
    }
}
