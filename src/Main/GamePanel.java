package Main;

import Character.Bomber.Bomber;
import Character.Monster.*;
import Constant.Const;
import StillEntity.Item.*;
import StillEntity.Map;
import StillEntity.Still;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GamePanel extends JPanel implements Const, Runnable {
    boolean isRunning;
    Thread thread;

    protected int screenWidth;
    protected int screenHeight;
    protected int level = 1;

    protected BufferedImage view;

    private final KeyHandler keyHandler = new KeyHandler();
    private final Bomber bomber = new Bomber();
    private final ArrayList<Monster> monsters = new ArrayList<>();
    Map map = new Map();

    private static int countBoom = Bomber.getCountBoom();

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            isRunning = true;
            thread.start();
        }
    }

    public GamePanel() {
        this.monsters.clear();

        try {
            Scanner in = new Scanner(new File("src/Stage/stage" + level + ".txt"));

            level = in.nextInt();
            int height = in.nextInt();
            int width = in.nextInt();
            screenHeight = height * size;
            screenWidth = width * size;
            map.setWidth(width);
            map.setHeight(height);
            in.nextLine();

            for (int j = 0; j < height; ++j) {
                String line = in.nextLine();
                for (int i = 0; i < width; ++i) {
                    if (line.charAt(i) == ' ') {
                        Map.setMap(i, j, hashCodeGrass);
                    } else if (line.charAt(i) == '#') {
                        Map.setMap(i, j, hashCodeWall);
                    } else if (line.charAt(i) == 'p') {
                        this.bomber.setX(i * size);
                        this.bomber.setY(j * size);
                    } else if (line.charAt(i) == '1') {
                        this.monsters.add(new Balloon(i * size, j * size));
                    } else if (line.charAt(i) == '2') {
                        this.monsters.add(new Oneal(i * size, j * size));
                    } else if (line.charAt(i) == '3') {
                        this.monsters.add(new Doria(i * size, j * size));
                    } else if (line.charAt(i) == '4') {
                        this.monsters.add((new Ovape(i * size, j * size)));
                    } else if (line.charAt(i) == '5') {
                        this.monsters.add(new Minvo(i * size, j * size));
                    } else {
                        Map.setMap(i, j, hashCodeBrick);
                        if (line.charAt(i) == 'x') {
                            Map.addStill(new Portal(i, j));
                        } else if (line.charAt(i) == 'b') {
                            Map.addStill(new BombItem(i * size, j * size));
                        } else if (line.charAt(i) == 's') {
                            Map.addStill(new SpeedItem(i * size, j * size));
                        } else if (line.charAt(i) == 'f') {
                            Map.addStill(new FlameItem(i * size, j * size));
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.addKeyListener(keyHandler);
    }

    public void start() {
        view = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_RGB);
    }

    @Override
    public void run() {
        try {
            requestFocus();
            start();
            while (isRunning) {
                update();
                draw();
                Thread.sleep(1000 / 60);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (this.bomber.getDie()) {
            this.bomber.update();
            return;
        }

        this.bomber.setDirect();
        if (this.keyHandler.isLeftPressed()) {
            this.bomber.setDirectLeft();
        } else if (this.keyHandler.isRightPressed()) {
            this.bomber.setDirectRight();
        } else if (this.keyHandler.isUpPressed()) {
            this.bomber.setDirectUp();
        } else if (this.keyHandler.isDownPressed()) {
            this.bomber.setDirectDown();
        }

        if (this.keyHandler.isBoom()) {
            System.out.println(GamePanel.countBoom);
            if (Map.getMap(this.bomber.getX() / size, this.bomber.getY() / size) != hashCodeBoom) {
                if (GamePanel.countBoom > 0) {
                    Map.setMap(this.bomber.getX() / size, this.bomber.getY() / size, 3);
                    GamePanel.countBoom--;
                }
            }

        }

        this.bomber.update();

        for (int i = this.monsters.size() - 1; i >= 0; --i) {
            if (monsters.get(i).getDie()) {
                monsters.remove(i);
            } else {
                monsters.get(i).move(bomber.getX(), bomber.getY());
                monsters.get(i).update();
                if (monsters.get(i).getX() / size == bomber.getX() / size && monsters.get(i).getY() / size == bomber.getY() / size) {
                    if (!monsters.get(i).isDie()) bomber.setDie();
                }
            }
        }

        for (int i = 0; ; ++i) {
            Still still = Map.getStill(i);

            if (still == null) return;
            if (still instanceof Portal) continue;

            Item item = (Item) still;
            item.update(this.bomber);
        }
    }

    public void draw() {
        Graphics2D g2 = (Graphics2D) view.getGraphics();
        g2.setColor(new Color(56, 135, 0));
        g2.fillRect(0, 0, screenWidth, screenHeight);

        map.render(g2);
        this.bomber.render(g2);
        for (Monster monster : monsters) {
            monster.render(g2);
        }

        Graphics g = getGraphics();
        g.drawImage(view, 0, 0, screenWidth, screenHeight, null);
        g.dispose();
    }

    public static void setCountBoom() {
        if (GamePanel.countBoom + 1 > Bomber.getCountBoom()) {
            return;
        }
        GamePanel.countBoom++;
    }
}
