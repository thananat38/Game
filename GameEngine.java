import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFrame;
import javax.swing.Timer;

public class GameEngine implements KeyListener, GameReporter{

    GamePanel gp;

    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    private ArrayList<BoxHp> bhp = new ArrayList<BoxHp>();
    private int checkHp = 0;
    private int numHp = 0;
    private SpaceShip v;

    private Timer timer;

    private long score = 0;
    private double difficulty = 0.1;

    private int hp = 5;
    private JFrame frame;

    public GameEngine(GamePanel gp, SpaceShip v,JFrame frame) {
        this.frame=frame;
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

    private void generateBoxHp() {
        BoxHp e = new BoxHp((int) (Math.random() * 390), 30);
        gp.sprites.add(e);
        bhp.add(e);
    }

    private void process() {
        if (Math.random() < difficulty) {
            generateEnemy();
        }

        if(score%2200 == 0 && score != 0 && numHp == checkHp){
            generateBoxHp();
            numHp++;
        }
        if(score%2200 == 100 && score != 100 && numHp > checkHp){
            checkHp++;
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

        Iterator<BoxHp> e_iterbox = bhp.iterator();
        while (e_iterbox.hasNext()) {
            BoxHp e = e_iterbox.next();
            e.proceed();

            if (!e.isAlive()) {
                e_iterbox.remove();
                gp.sprites.remove(e);
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
                    Endgame end = new Endgame(frame);
                    end.pop_upend(this);
                }
                return;
            }
        }

        Rectangle2D.Double vrb = v.getRectangle();
        Rectangle2D.Double erb;
        for (BoxHp e : bhp) {
            erb = e.getRectangle();
            if (erb.intersects(vrb)) {
                if(hp<5){
                    hp++;
                }
                gp.updateGameUI(this);
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