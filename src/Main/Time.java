package Main;

import Constant.Const;

public class Time implements Const {
    private double delta = 0;
    private long lastTime = System.nanoTime();

    public boolean check(double timeSleep) {
        long currentTime = System.nanoTime();
        double drawInterval = 1000000000 / FPS;
        this.delta += (currentTime - this.lastTime) / drawInterval;
        this.lastTime = currentTime;
        if (this.delta >= timeSleep) {
            this.delta -= timeSleep;
            return true;
        }
        return false;
    }
}
