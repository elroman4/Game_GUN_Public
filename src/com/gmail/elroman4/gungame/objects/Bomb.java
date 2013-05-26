package com.gmail.elroman4.gungame.objects;


import com.gmail.elroman4.gungame.MyPanel;
import com.gmail.elroman4.gungame.Utils;

import java.awt.*;

public class Bomb implements GameObject {
    private final int WIDTH = 3;
    private final int HEIGHT = 10;
    private final String PICTURE_FILE_NAME = "Bomb.png";
    private final int SPEED = 15;

    private Image picture;
    private int x;
    private int y;
    private int minY;
    private int maxY;

    public Bomb(MyPanel panel, int startX, int startY) {
        picture = Utils.getImage(PICTURE_FILE_NAME, WIDTH, HEIGHT);
        x = startX;
        y = startY;
        minY = 0;
        maxY = y;

    }

    @Override
    public Image getPicture() {
        return picture;
    }

    @Override
    public int getX() {
        return x;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getY() {
        int nowY = y;
        y = y-SPEED;
        return Math.max(minY, nowY) ;
    }

    @Override
    public boolean getClear() {
        return y<=minY;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
