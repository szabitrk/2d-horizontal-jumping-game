package gameplay;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

//template for main containers used with card layout in the frame
public class ContainerPanel extends JPanel {
    private BufferedImage background;

    public ContainerPanel(String backgroundPath) {
        try {
            background = ImageIO.read(new File(backgroundPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(background, 0, 0, null);
    }
}
