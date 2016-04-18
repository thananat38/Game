import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;


import javax.swing.JPanel;

public class GamePanel extends JPanel {

    private BufferedImage bi;
    Graphics2D big;


    public GamePanel() {
        bi = new BufferedImage(400, 600, BufferedImage.TYPE_INT_ARGB);
        big = (Graphics2D) bi.getGraphics();
        big.setBackground(Color.BLACK);
        big.clearRect(0, 0, 400, 600);
    }


    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(bi, null, 0, 0);
    }

}