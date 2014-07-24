package com.gmail.elroman4.gungame;

import javax.sound.midi.MidiUnavailableException;
import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public MyFrame() throws MidiUnavailableException {
        setTitle("Kill them all!");
        setSize(300, 300);
       // setResizable(false);
        Container pane = getContentPane();
        pane.add(new ServicePanel(), BorderLayout.NORTH);
        pane.add(new GamePanel(), BorderLayout.CENTER);

    }



}
