//package com.gmail.elroman4.gungame;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

public class MyFrame extends JFrame {

    static final long serialVersionUID = 0L;

    public MyFrame() throws MidiUnavailableException {
        setTitle("Kill them all!");
        setSize(300, 300);
        setResizable(false);
       // getContentPane().add(new MyPanel());

    }
}
