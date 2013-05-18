package com.gmail.elroman4.gungame;

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

public class MyPanel extends JPanel {

    Tank tank;
    MidiChannel channel; // The channel we play on: 10 is for percussion

    int velocity = 64; // Default volume is 50%
    int i = 30;
    Synthesizer synthesizer;


    private static final long serialVersionUID = 1L;
    String bomb = "o", alien = "I-()-I";
    int x2 = 0, y2 = 0, x3 = -30, y3 = -30;
    int stage;
    Timer[] array_bomb = new Timer[10];
    Timer[] array_alien = new Timer[10];

    public MyPanel() throws MidiUnavailableException {
        addKeyListener(new MyKey());
        setFocusable(true);
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        channel = synthesizer.getChannels()[9];
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


        paintGameObj(tank, g);

        try {
            URL alienURL = cldr.getResource("alien1.png");
            Image alienimg = ImageIO.read(alienURL);
            g.drawImage(alienimg, x3, y3, 25, 25, null);
        } catch (IOException ex) {
            g.drawString(alien, x3, y3);
        }


        try {
            URL bombURL = cldr.getResource("Bomb.png");
            Image bombimg = ImageIO.read(bombURL);
            g.drawImage(bombimg, x2, y2, 5, 10, null);
        } catch (IOException ex) {
            g.drawString(bomb, x2, y2);
        }

        g.drawLine(0, 15, getWidth(), 15);
        g.drawString("Killed alien invaders: " + stage, getWidth() - 200, 10);

    }

    private void createGameObjects() {
        if (tank == null) {
            tank = new Tank(this);
        }
    }

    private void paintGameObj(GameObject obj, Graphics g) {
        g.drawImage(obj.getPicture(), obj.getX(), obj.getY(), null);
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

            if ((array_bomb[0] != null) && (y2 < 0)) {
                array_bomb[0].stop();
                array_bomb[0] = null;
                // this.channel.noteOn(72, velocity);
            }

            if ((array_alien[0] != null) && (y3 < 0)) {
                array_alien[0].stop();
                array_alien[0] = null;
            }

            // System.out.println("array_alien[0]    " + array_alien[0]);
            sendAlien();

            switch (event.getKeyCode()) {
                case KeyEvent.VK_SPACE:
                    sendBomb();
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

        private void sendAlien() {
            if (array_alien[0] == null) {
                x3 = (int) (Math.random() * (getWidth() - 40));
                y3 = 25;
                Timer t_alien = new Timer(100, new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if ((y3 >= 0) && (y3 < getHeight() - 5)) {
                            y3 = y3 + 5;
                        } else {
                            x3 = -30;
                            y3 = -30;
                        }
                        repaint();
                    }
                });
                t_alien.start();
                array_alien[0] = t_alien;
            }
        }

        private void sendBomb() {
            if (array_bomb[0] == null) {
                x2 = tank.getX() + 10;
                y2 = getHeight() - 30;
                Timer t_bomb = new Timer(100, new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        if (y2 > 25) {
                            y2 = y2 - 5;
                        } else {
                            x2 = -1;
                            y2 = -1;
                        }
                        repaint();
                    }
                });
                t_bomb.start();

                array_bomb[0] = t_bomb;

            }
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

}
