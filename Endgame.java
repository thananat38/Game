import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.BorderLayout;

public class Endgame extends JFrame implements ActionListener {

	private JFrame frameend;
	private JFrame frame;
	public Endgame(JFrame frame) {
		this.frame = frame;
		
	}
	public void pop_upend(GameReporter reporter){
		frameend = new JFrame("END GAME");
		frameend.setVisible(true);

		frameend.getContentPane().setLayout(new BorderLayout());
		frameend.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();


		JButton btnClose = new JButton("Exit");
		JButton btnStart = new JButton("Start");
		btnClose.addActionListener(this);
		btnStart.addActionListener(this);
		
		panel.add(btnStart);
		panel.add(btnClose);

		frameend.add(new JLabel("Score = " + reporter.getScore()), BorderLayout.CENTER);
		frameend.add(panel, BorderLayout.PAGE_END);
		frameend.setSize(250, 200);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if ("Start".equals(e.getActionCommand())) {
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				getContentPane().removeAll();
				SpaceShip v = new SpaceShip(180, 550, 20, 20);
		        GamePanel gp = new GamePanel();
		        GameEngine engine = new GameEngine(gp, v, frame);
		        frame.addKeyListener(engine);
		        frame.getContentPane().add(gp, BorderLayout.CENTER);
		        frame.setVisible(true);
		        engine.start();
		        frameend.dispose();
			} else {
				frame.setVisible(false);
				frame.dispose();
				frameend.dispose();
			}
	}
}