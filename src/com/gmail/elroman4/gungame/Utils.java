package com.gmail.elroman4.gungame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class Utils {

    public static Image getImage(String name, int width, int height) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        URL url = classLoader.getResource(name);
        Image image;
        try {
            image = ImageIO.read(url);
        } catch (IOException e) {
            image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics graphics = image.getGraphics();
            graphics.setColor(Color.RED);
            graphics.fillRect(0, 0, width, height);
        }
        return image;
    }

}
