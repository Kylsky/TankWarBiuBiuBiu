package tank.Day8;

import tank.Day8.tank.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    public static final int WIDTH = 1024,HEIGHT = 960;
    GameModel gameModel = GameModel.getInstance();
//    Tank superTank = SuperGameFactory.getInstance().createTank(200,400, Dir.DOWN, Group.GOOD,this);
//    public List<Bullet> bullets = new ArrayList();
//    public List<Tank> tanks = new ArrayList();
//    public List<BaseExplode> baseExplodes = new ArrayList();

    public TankFrame(){
        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setTitle("BaseTank War");
        addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);
    }

    private Image offScreenImage = null;
    @Override
    public void update(Graphics g){
        if (offScreenImage == null){
            offScreenImage = this.createImage(WIDTH,HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,WIDTH,HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g){
        gameModel.paint(g);
//        explode.paint(g);
    }

    class MyKeyListener extends KeyAdapter{
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            if (!gameModel.getMainTank().isAlive)  return;
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                case KeyEvent.VK_CONTROL:
                    gameModel.getMainTank().fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
            new Thread(()->new Audio("tank/Day4/audio/tank_move.wav").play()).start();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (!gameModel.getMainTank().isAlive)  return;
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir(){
            Tank mainTank = gameModel.getMainTank();
            if (!bL && !bR && !bU && !bD)
                gameModel.getMainTank().isMoving=false;
            else
                gameModel.getMainTank().isMoving=true;
            if (bL) mainTank.setDir(tank.Day8.Dir.LEFT);
            if (bR) mainTank.setDir(tank.Day8.Dir.RIGHT);
            if (bU) mainTank.setDir(tank.Day8.Dir.UP);
            if (bD) mainTank.setDir(Dir.DOWN);
            }
    }
}