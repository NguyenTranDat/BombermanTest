package StillEntity.Item;

import Constant.Sprite;
import StillEntity.Still;

public class Portal extends Still {
    public Portal(int x, int y) {
        super(Sprite.portal, x, y);
    }

    @Override
    public int hashCode(){
        return hashCodeItem;
    }
}
