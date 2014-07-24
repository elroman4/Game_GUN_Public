
package com.gmail.elroman4.gungame.objects;

import com.gmail.elroman4.gungame.GamePanel;
import com.gmail.elroman4.gungame.MyPanel;
import com.gmail.elroman4.gungame.objects.abstract_object.Army;

public class Tank extends Army implements IUnit  {

    public Tank() {

        super("tank.png", 32, 32);

        X = (GamePanel.WIDTH - WIDTH) / 2;
        Y = (GamePanel.HEIGHT - HEIGHT);
        minX = 0;
        maxX = GamePanel.WIDTH - WIDTH;

        speed = 5;
        musicOnDestroy = 71;
    }



    public void moveRight() {
        X = Math.min(maxX, (X + speed));
    }

    public void moveLeft() {
        X = Math.max(minX, (X - speed));
    }

    public int getCentreX() {
        return X + (WIDTH / 2);
    }

    public int getGunHeight() {
        return Y - HEIGHT;
    }

    public boolean getClear() {
        return false;
    }
}
