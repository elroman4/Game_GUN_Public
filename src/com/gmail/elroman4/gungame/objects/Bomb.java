package com.gmail.elroman4.gungame.objects;

import com.gmail.elroman4.gungame.Utils;
import com.gmail.elroman4.gungame.objects.abstract_object.Weapon;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

public class Bomb extends Weapon implements IUnit {

    private final String stSoundCrush;

    public Bomb(int startX, int startY) {
        super("bomb.png", 4, 13);
        stSoundCrush = "E:/=Java=/workspace/Game_GUN/resources/LASRH.WAV";

        of_enemy= false;

        X = startX;
        Y = startY;
        minY = 10;
        maxY = Y;

        speed = 3;
        musicOnDestroy = 52;
        super.playSound(stSoundCrush);
    }

    @Override
    public int moveY() {
        int nowY = Y;
        Y -= speed;
        return Math.max(minY, nowY);
    }

    @Override
    public boolean getClear() {
        if (Y <= minY)
            clear = true;
        return clear;
    }

//    public void playSound(){
//
//        try{
//            ClassLoader classLoader = Utils.class.getClassLoader();
//            URL url = classLoader.getResource("LASRH.WAV");
//
//
//            AudioPlayer p = AudioPlayer.player;
//
//            AudioStream as;
//            as = new AudioStream(new FileInputStream("E:/=Java=/workspace/Game_GUN/resources/LASRH.WAV"));
//           // as = new AudioStream(new FileInputStream(String.valueOf(url)));
//
//            p.start(as);
//
//        }
//
//        catch(IOException IOE){}
//
//    }
}

