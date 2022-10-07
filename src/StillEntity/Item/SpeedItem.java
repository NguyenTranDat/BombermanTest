package StillEntity.Item;

import Character.Bomber.Bomber;
import Constant.Sprite;
import Main.Time;

public class SpeedItem extends Item{

    public SpeedItem(int x, int y) {
        super(Sprite.speedItem, x, y);
    }

    @Override
    public void update(Bomber bomber) {
        if(this.collide) return;
        if(this.x /size == bomber.getX() /size && this.y/size == bomber.getY()/size) {
            this.collide = true;
            bomber.setSpeed();
            this.time = new Time();
        }
    }

    @Override
    public void update(){
        if(this.collide && this.time.check(20)) {
            this.hidden = true;
        }
    }
}
