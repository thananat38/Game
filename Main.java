import java.awt.BorderLayout;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Space War");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 650);
        frame.getContentPane().setLayout(new BorderLayout());

        GamePanel gp = new GamePanel();

        frame.getContentPane().add(gp, BorderLayout.CENTER);
        frame.setVisible(true);

    }
}
