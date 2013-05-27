package com.gmail.elroman4.gungame.objects;

import com.gmail.elroman4.gungame.MyPanel;
import com.gmail.elroman4.gungame.Utils;

import java.awt.*;

public class Tank implements GameObject {

    private final int WIDTH = 25;
    private final int HEIGHT = 25;
    private final String PICTURE_FILE_NAME = "tank.png";
    private final int SPEED = 5;

    private Image picture;
    private int x;
    private int y;
    private int minX;
    private int maxX;

    public Tank(MyPanel panel) {
        if (picture == null)
            picture = Utils.getImage(PICTURE_FILE_NAME, WIDTH, HEIGHT);
        x = (panel.getWidth() - WIDTH) / 2;
        y = panel.getHeight() - HEIGHT;
        minX = 0;
        maxX = panel.getWidth() - WIDTH;
    }

    @Override
    public Image getPicture() {
        return picture;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public boolean getClear() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void moveRight() {
        x = Math.min(maxX, (x + SPEED));
    }

    public void moveLeft() {
        x = Math.max(minX, (x - SPEED));
    }

    public int getCentreX() {
        return (WIDTH / 2) + this.getX();
    }

    public int getGunHeight() {
        return this.getY() - HEIGHT;
    }

}
