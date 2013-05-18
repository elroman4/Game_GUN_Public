package com.gmail.elroman4.gungame;

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

/**
 * Created with IntelliJ IDEA.
 * User: ren
 * Date: 18.05.13
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
class MyPanel extends JPanel {

    MidiChannel channel; // The channel we play on: 10 is for percussion

    int velocity = 64; // Default volume is 50%
    int i = 30;
    Synthesizer synthesizer;


    private static final long serialVersionUID = 1L;
    String gun = "o-I-o", bomb = "o", alien = "I-()-I";
    int x = 0, y = 0, x2 = 0, y2 = 0, x3 = -30, y3 = -30;
    boolean start = true;
    int stage;
    Timer[] array_bomb = new Timer[10];
    Timer[] array_alien = new Timer[10];

    Toolkit tk = Toolkit.getDefaultToolkit();

    public MyPanel() throws MidiUnavailableException {
        addKeyListener(new MyKey());
        setFocusable(true);
        Synthesizer synthesizer = MidiSystem.getSynthesizer();
        synthesizer.open();
        channel = synthesizer.getChannels()[9];
    }

    public void paintComponent(Graphics g) {

        calculateCoords();

        super.paintComponent(g);
        // setBackground()
        ClassLoader  cldr = this.getClass() .getClassLoader();


        try {
            URL spaceURL = cldr.getResource("space.png");
            Image spaceimg = ImageIO.read(spaceURL);
            g.drawImage(spaceimg, 0, 15, 300, 300, null);
        } catch (IOException ex) {
        }

        try {
            URL tankURL = cldr.getResource("tank.png");
            Image tankimg = ImageIO.read(tankURL);
            g.drawImage(tankimg, x, y - 20, 25, 25, null);
        } catch (IOException ex) {
            g.drawString(gun, x, y);
        }

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
        //g.drawString(bomb, x2, y2);

        g.drawLine(0, 15, getWidth(), 15);
        // g.fiLine(0, 15, getWidth(), 15);
        g.drawString("Killed alien invaders: " + stage, getWidth() - 200, 10);

    }

    private void calculateCoords() {
        if (start) {
            if (x < 10)
                x = getWidth() / 2 - 3;
            if (y < 10)
                y = getHeight();
            start = false;
        } else {
            if (x < 10)
                x = 10;
        }
        if (x > getWidth() - 40)
            x = getWidth() - 40;

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
                    x += 5;
                    channel.noteOn(62, velocity);
                    break;
                case KeyEvent.VK_LEFT:
                    x -= 5;
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
                x2 = x + 10;
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
