import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;

public class GameEngine implements KeyListener, GameReporter{

    GamePanel gp;

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private SpaceShip v;

    private Timer timer;

    private long score = 0;
    private double difficulty = 0.1;

    private int hp = 5;

    public GameEngine(GamePanel gp, SpaceShip v) {
        this.gp = gp;
        this.v = v;
        
        gp.sprites.add(v);
        timer = new Timer(50, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                process();
            }
        });
        timer.setRepeats(true);

    }

    public void start() {
        timer.start();
    }

    private void generateEnemy() {
        Enemy e = new Enemy((int) (Math.random() * 390), 30);
        gp.sprites.add(e);
        enemies.add(e);
    }

    private void process() {
        if (Math.random() < difficulty) {
            generateEnemy();
        }

        Iterator<Enemy> e_iter = enemies.iterator();
        while (e_iter.hasNext()) {
            Enemy e = e_iter.next();
            e.proceed();

            if (!e.isAlive()) {
                e_iter.remove();
                gp.sprites.remove(e);
                score += 100;
            }
        }

       gp.updateGameUI(this);

        Rectangle2D.Double vr = v.getRectangle();
        Rectangle2D.Double er;
        for (Enemy e : enemies) {
            er = e.getRectangle();
            if (er.intersects(vr)) {
                e.dieEnemy();
                hp--;
                gp.updateGameUI(this);
                if (hp == 0) {
                    die();
                }
                return;
            }
        }
    }

    public void die() {
        timer.stop();
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
    public long getScore() {
        return score;
    }

    public int getHp() {
        return hp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        controlVehicle(e);
     
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