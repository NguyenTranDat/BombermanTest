package Constant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Sprite implements Const {

    public static BufferedImage[] playerAnimUp = new BufferedImage[3];
    public static BufferedImage[] playerAnimDown = new BufferedImage[3];
    public static BufferedImage[] playerAnimRight = new BufferedImage[3];
    public static BufferedImage[] playerAnimLeft = new BufferedImage[3];

    public static BufferedImage[] bombAnim = new BufferedImage[4];
    public static BufferedImage[] fontExplosion = new BufferedImage[4];
    public static BufferedImage[] rightExplosion = new BufferedImage[4];
    public static BufferedImage[] leftExplosion = new BufferedImage[4];
    public static BufferedImage[] upExplosion = new BufferedImage[4];
    public static BufferedImage[] downExplosion = new BufferedImage[4];
    public static BufferedImage[] brickExplosion = new BufferedImage[4];

    public static BufferedImage[] balloon = new BufferedImage[11];
    public static BufferedImage[] oneal = new BufferedImage[11];
    public static BufferedImage[] doria = new BufferedImage[11];
    public static BufferedImage[] ovape = new BufferedImage[11];
    public static BufferedImage[] minvo = new BufferedImage[11];

    public static BufferedImage imageBrick, imageWall, player, portal;
    public static BufferedImage[] playerAnimDie = new BufferedImage[7];

    public static BufferedImage speedItem, flameItem, bombItem;

    public static BufferedImage[] score = new BufferedImage[8];

    public Sprite() throws IOException {
        BufferedImage spriteSheet = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));

        imageBrick = spriteSheet.getSubimage(4 * tileSize, 3 * tileSize, tileSize, tileSize);
        imageWall = spriteSheet.getSubimage(3 * tileSize, 3 * tileSize, tileSize, tileSize);
        player = spriteSheet.getSubimage(4 * tileSize, 0, tileSize, tileSize);
        portal = spriteSheet.getSubimage(11 * tileSize, 3 * tileSize, tileSize, tileSize);

        speedItem = spriteSheet.getSubimage(2 * tileSize, 14 * tileSize, tileSize, tileSize);
        bombItem = spriteSheet.getSubimage(0, 14 * tileSize, tileSize, tileSize);
        flameItem = spriteSheet.getSubimage(1 * tileSize, 14 * tileSize, tileSize, tileSize);

        fontExplosion[0] = spriteSheet.getSubimage(2 * tileSize, 6 * tileSize, tileSize, tileSize);
        fontExplosion[1] = spriteSheet.getSubimage(7 * tileSize, 6 * tileSize, tileSize, tileSize);
        fontExplosion[2] = spriteSheet.getSubimage(2 * tileSize, 11 * tileSize, tileSize, tileSize);
        fontExplosion[3] = spriteSheet.getSubimage(7 * tileSize, 11 * tileSize, tileSize, tileSize);

        rightExplosion[0] = spriteSheet.getSubimage(4 * tileSize, 6 * tileSize, tileSize, tileSize);
        rightExplosion[1] = spriteSheet.getSubimage(9 * tileSize, 6 * tileSize, tileSize, tileSize);
        rightExplosion[2] = spriteSheet.getSubimage(4 * tileSize, 11 * tileSize, tileSize, tileSize);
        rightExplosion[3] = spriteSheet.getSubimage(9 * tileSize, 11 * tileSize, tileSize, tileSize);

        leftExplosion[0] = spriteSheet.getSubimage(0, 6 * tileSize, tileSize, tileSize);
        leftExplosion[1] = spriteSheet.getSubimage(5 * tileSize, 6 * tileSize, tileSize, tileSize);
        leftExplosion[2] = spriteSheet.getSubimage(0, 11 * tileSize, tileSize, tileSize);
        leftExplosion[3] = spriteSheet.getSubimage(5 * tileSize, 11 * tileSize, tileSize, tileSize);

        upExplosion[0] = spriteSheet.getSubimage(2 * tileSize, 4 * tileSize, tileSize, tileSize);
        upExplosion[1] = spriteSheet.getSubimage(7 * tileSize, 4 * tileSize, tileSize, tileSize);
        upExplosion[2] = spriteSheet.getSubimage(2 * tileSize, 9 * tileSize, tileSize, tileSize);
        upExplosion[3] = spriteSheet.getSubimage(7 * tileSize, 9 * tileSize, tileSize, tileSize);

        downExplosion[0] = spriteSheet.getSubimage(2 * tileSize, 8 * tileSize, tileSize, tileSize);
        downExplosion[1] = spriteSheet.getSubimage(7 * tileSize, 8 * tileSize, tileSize, tileSize);
        downExplosion[2] = spriteSheet.getSubimage(2 * tileSize, 13 * tileSize, tileSize, tileSize);
        downExplosion[3] = spriteSheet.getSubimage(7 * tileSize, 13 * tileSize, tileSize, tileSize);

        for (int i = 0; i < 3; i++) {
            playerAnimLeft[i] = spriteSheet.getSubimage(i * tileSize, 0, tileSize, tileSize);
            playerAnimRight[i] = spriteSheet.getSubimage(i * tileSize, tileSize, tileSize, tileSize);
            playerAnimDown[i] = spriteSheet.getSubimage((i + 3) * tileSize, 0, tileSize, tileSize);
            playerAnimUp[i] = spriteSheet.getSubimage((i + 3) * tileSize, tileSize, tileSize, tileSize);
            bombAnim[i] = spriteSheet.getSubimage(i * tileSize, 3 * tileSize, tileSize, tileSize);
            brickExplosion[i] = spriteSheet.getSubimage((i + 5) * tileSize, 3 * tileSize, tileSize, tileSize);
        }

        for (int i = 0; i < 11; ++i) {
            balloon[i] = spriteSheet.getSubimage(i * tileSize, 15 * tileSize, tileSize, tileSize);
        }

        for (int i = 0; i < 7; ++i) {
            oneal[i] = spriteSheet.getSubimage(i * tileSize, 16 * tileSize, tileSize, tileSize);
            playerAnimDie[i] = spriteSheet.getSubimage(i * tileSize, 2 * tileSize, tileSize, tileSize);
            doria[i] = spriteSheet.getSubimage(i * tileSize, 19 * tileSize, tileSize, tileSize);
            ovape[i] = spriteSheet.getSubimage(i * tileSize, 20 * tileSize, tileSize, tileSize);
            minvo[i] = spriteSheet.getSubimage(i * tileSize, 18 * tileSize, tileSize, tileSize);
        }

        for (int i = 7; i < 11; ++i) {
            oneal[i] = spriteSheet.getSubimage(i * tileSize, 18 * tileSize, tileSize, tileSize);
            doria[i] = spriteSheet.getSubimage(i * tileSize, 18 * tileSize, tileSize, tileSize);
            ovape[i] = spriteSheet.getSubimage(i * tileSize, 17 * tileSize, tileSize, tileSize);
            minvo[i] = spriteSheet.getSubimage(i * tileSize, 15 * tileSize, tileSize, tileSize);
        }


        for (int i = 0; i < 4; ++i) {
            score[i] = spriteSheet.getSubimage(7 * tileSize, 21 * tileSize + i * tileSize / 2, tileSize, tileSize / 2);
            score[i + 4] = spriteSheet.getSubimage(8 * tileSize, 21 * tileSize + i * tileSize / 2, tileSize, tileSize / 2);
        }
    }
}
