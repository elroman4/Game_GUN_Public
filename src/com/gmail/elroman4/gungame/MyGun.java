package com.gmail.elroman4.gungame;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

public class MyGun {

    public static void main(String[] args) throws MidiUnavailableException {

        MyFrame frame = new MyFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

