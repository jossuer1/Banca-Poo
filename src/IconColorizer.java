import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class IconColorizer {

    public static ImageIcon toWhite(ImageIcon icon) {
        Image img = icon.getImage();
        BufferedImage buff = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = buff.createGraphics();
        g.drawImage(img, 0, 0, null);

        for (int y = 0; y < buff.getHeight(); y++) {
            for (int x = 0; x < buff.getWidth(); x++) {
                int rgba = buff.getRGB(x, y);
                Color col = new Color(rgba, true);

                if (col.getAlpha() > 0 && col.getRed() < 200 && col.getGreen() < 200 && col.getBlue() < 200) {
                    buff.setRGB(x, y, new Color(255, 255, 255, col.getAlpha()).getRGB());
                }
            }
        }

        g.dispose();
        return new ImageIcon(buff);
    }
}
