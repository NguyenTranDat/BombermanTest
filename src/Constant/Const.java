package Constant;

public interface Const {
    int tileSize = 16;
    int scale = 3;

    int size = tileSize * scale;
    int FPS = 60;

    String path = "/Image/bomberman.png";
    String path_music = "";

    int hashCodeBrick = -1;
    int hashCodeGrass = 0;
    int hashCodeWall = 1;
    int hashCodeItem = 2;
    int hashCodeBoom = 3;
}
