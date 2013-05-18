package com.gmail.elroman4.gungame;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;

/**
 * Created with IntelliJ IDEA.
 * User: ren
 * Date: 18.05.13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
class MyFrame extends JFrame {

    static final long serialVersionUID = 0L;

    public MyFrame() throws MidiUnavailableException {
        setTitle("Kill them all!");
        setSize(300, 300);
        getContentPane().add(new MyPanel());

    }
}
