import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements KeyListener{

    GamePanel gp;

    private SpaceShip v;
    private double difficulty = 0.1;

    public GameEngine(GamePanel gp, SpaceShip v) {
        this.gp = gp;
        this.v = v;
        
        gp.sprites.add(v);
        gp.updateGameUI();
    }

    void controlVehicle(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                v.move(-1);
                break;
            case KeyEvent.VK_RIGHT:
                v.move(1);
                break;
            case KeyEvent.VK_D:
                difficulty += 0.1;
                break;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        controlVehicle(e);
        gp.updateGameUI();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //do nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //do nothing		
    }
}
