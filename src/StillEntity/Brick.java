package StillEntity;

import Constant.Sprite;

public class Brick extends Still{
    public Brick(int x, int y) {
        super(Sprite.imageBrick, x, y);
    }

    @Override
    public int hashCode() {
        return hashCodeBrick;
    }
}
