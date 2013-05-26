package com.gmail.elroman4.gungame;

import com.gmail.elroman4.gungame.objects.Alien;
import com.gmail.elroman4.gungame.objects.Bomb;
import com.gmail.elroman4.gungame.objects.GameObject;
import com.gmail.elroman4.gungame.objects.Tank;

import javax.imageio.ImageIO;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;


public class MyPanel extends JPanel {

    private static final long serialVersionUID = 1L;
    Tank tank;
    Bomb bomb;
    Alien alien;
    MidiChannel channel;
    int velocity = 64;
    int i = 30;
    Synthesizer synthesizer;
    int x2 = 0, y2 = 0, x3 = -30, y3 = -30;
    int stage;
    ArrayList<GameObject> array_obj = new ArrayList<GameObject>();
    Timer[] array_alien = new Timer[10];
    Timer t_object = new Timer(100, new ActionListener() {
        public void actionPerformed(ActionEvent event) {
            repaint();
   }
    });

    public MyPanel() throws MidiUnavailableException {
        addKeyListener(new MyKey());
        setFocusable(true);
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        channel = synthesizer.getChannels()[9];

         t_object.start();

    }

    private void clearObj(GameObject obj) {

           if (obj.getClear()) {
                array_obj.remove(obj);
               obj = null;
            }
  }

    public void paintComponent(Graphics g) {

        createGameObjects();
        calculateCoords();

        super.paintComponent(g);
        ClassLoader cldr = this.getClass().getClassLoader();

        try {
            URL spaceURL = cldr.getResource("space.png");
            Image spaceimg = ImageIO.read(spaceURL);
            g.drawImage(spaceimg, 0, 15, 300, 300, null);
        } catch (IOException ex) {
        }

        for(GameObject obj:array_obj){
            paintGameObj(obj, g);
            clearObj(obj);
        }
      g.drawLine(0, 15, getWidth(), 15);
        g.drawString("Killed alien invaders: " + stage, getWidth() - 200, 10);

    }

    private void createGameObjects() {
        if (tank == null) {
            tank = new Tank(this);
            array_obj.add(tank);
        }
        if (alien == null) {
            alien = new Alien(this);
            array_obj.add(alien);
        }
    }

    private void paintGameObj(GameObject obj, Graphics g) {
        if (obj != null) {
        g.drawImage(obj.getPicture(), obj.getX(), obj.getY(), null);
        }

    }

    private void calculateCoords() {

        if ((x3 > 0) && (x2 >= x3 - 5) && (x2 < (x3 + 30)) && (y2 >= y3)
                && (y2 < (y3 + 10))) {
            y2 = -30;
            y3 = -30;
            stage++;
            channel.noteOn(79, velocity);

        }
    }

    private class MyKey implements KeyListener {

        @Override
        public void keyPressed(KeyEvent event) {


            if ((array_alien[0] != null) && (y3 < 0)) {
                 array_alien[0] = null;
            }
       switch (event.getKeyCode()) {
                case KeyEvent.VK_SPACE:

                    MyPanel pan = (MyPanel) event.getSource();

                    sendBomb(pan);
                    channel.noteOn(35, velocity);

                    break;
                case KeyEvent.VK_RIGHT:
                   tank.moveRight();

                    channel.noteOn(62, velocity);
                    break;
                case KeyEvent.VK_LEFT:
                    tank.moveLeft();

                    channel.noteOn(62, velocity);
                    break;
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                default:
                    break;
            }

            repaint();
        }

        public void sendBomb(MyPanel myPanel) {

                 bomb = new Bomb(myPanel ,tank.getCentreX(),tank.getGunHeight());
                  System.out.println("Создана бомба "+ bomb);
                    array_obj.add(bomb);
    }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }



}
