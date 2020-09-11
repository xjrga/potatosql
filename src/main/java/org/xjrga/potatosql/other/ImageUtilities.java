package org.xjrga.potatosql.other;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageUtilities {
    static BufferedImage makeBufferedImage(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage subimage = image.getSubimage(0, 0, w, h);
        Graphics2D g2 = subimage.createGraphics();
        Rectangle2D tr = new Rectangle2D.Double(0, 0, w, h);
        TexturePaint tp = new TexturePaint(subimage, tr);
        g2.setStroke(new BasicStroke(5));
        g2.setPaint(Color.BLACK);
        //   g.fill(tr);
        g2.draw(tr);
        //String format = "PNG";
        //String pathname = "resources/myeditedimage.png";
        //writeBufferedImage(subimage,format,pathname);
        g2.dispose();
        return subimage;
    }

    static void writeBufferedImage(BufferedImage subimage, String format, String pathname) {
        /*String format = "PNG";
        String pathname = "resources/myeditedimage.png";
        writeBufferedImage(subimage,format,pathname);*/
        try {
            ImageIO.write(subimage, format, new File(pathname));
        } catch (IOException e) {
            Log.getLog().start("files/exception.log");
            Log.getLog().logMessage(e.toString());
            Log.getLog().write();
            Log.getLog().close();
            e.printStackTrace();
        }
    }

    public static BufferedImage readImage(String pathname) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(pathname));
        } catch (IOException e) {
            Log.getLog().start("files/exception.log");
            Log.getLog().logMessage(e.toString());
            Log.getLog().write();
            Log.getLog().close();
            e.printStackTrace();
        }
        return img;
    }

    static TexturePaint makeTexturePaint(BufferedImage image, Color color) {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage subimage = image.getSubimage(0, 0, w, h);
        Graphics2D g2 = subimage.createGraphics();
        Rectangle2D tr = new Rectangle2D.Double(0, 0, w, h);
        TexturePaint tp = new TexturePaint(subimage, tr);
        g2.setStroke(new BasicStroke(5));
        g2.setPaint(color);
        g2.draw(tr);
        g2.dispose();
        return tp;
    }

    static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }
}
