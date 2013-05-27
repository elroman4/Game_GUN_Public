package com.gmail.elroman4.gungame.objects;


import com.gmail.elroman4.gungame.MyPanel;
import com.gmail.elroman4.gungame.Utils;

import java.awt.*;

public class Alien implements GameObject {
    private final int WIDTH = 25;
    private final int HEIGHT = 20;
    private final String PICTURE_FILE_NAME = "alien1.png";
    private final int SPEED = 10;

    private static Image picture;
    private int x;
    private int y;
    private int minY;
    private int maxY;

    public Alien(MyPanel panel) {
        if (picture == null)
            picture = Utils.getImage(PICTURE_FILE_NAME, WIDTH, HEIGHT);
        x = (int) (Math.random() * (panel.getWidth() - WIDTH));
        y = HEIGHT;
        minY = 0;
        maxY = panel.getHeight();
    }

    public Image getPicture() {
        return picture;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getX() {
        return x;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getY() {
        int nowY = y;
        y = y + SPEED;
        return Math.min(maxY, nowY);  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean getClear() {
        return y > maxY;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
