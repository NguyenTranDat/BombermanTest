package StillEntity;

import Constant.Sprite;

public class Wall extends Still{
    public Wall() {
    }

    public Wall(int x, int y) {
        super(Sprite.imageWall, x, y);
    }

    @Override
    public int hashCode(){
        return hashCodeWall;
    }
}
