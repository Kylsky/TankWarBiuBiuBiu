package tank.Day8;

import tank.Day8.cor.ColliderChain;
import tank.Day8.cor.TankTankCollider;
import tank.Day8.fireStrategy.FourDirFireStrategy;
import tank.Day8.gameFactory.DefaultGameFactory;
import tank.Day8.gameFactory.SuperGameFactory;
import tank.Day8.tank.Tank;
import tank.Day8.wall.FirmWall;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
    Tank superTank = SuperGameFactory.getInstance().createTank(200, 400, Dir.DOWN, Group.GOOD);
    //    public List<Bullet> bullets = new ArrayList();
//    public List<Tank> tanks = new ArrayList();
//    public List<BaseExplode> baseExplodes = new ArrayList();
    private List<GameObject> objects = new ArrayList<>();
    private ColliderChain colliderChain = new ColliderChain();
    private Random random = new Random();
    private static final GameModel gameModel= new GameModel();

    public static GameModel getInstance(){
        return gameModel;
    }

    private GameModel() {
        //��ʼ���з�̹��
        for (int i=1;i<14;i++){
            Tank tank = DefaultGameFactory.getInstance().createTank(50+i*20*random.nextInt(10),
                    100+i*20*random.nextInt(10), Dir.DOWN, Group.BAD);
            add(tank);
        }
        //�趨��ս̹�˷��ڲ���
        superTank.setFireStrategy(new FourDirFireStrategy());
        superTank.isMoving = false;
        //��ӱ�������
        new Thread(() -> new Audio("tank/Day8/audio/war1.wav").loop()).start();
        //���ǽ
        add(new FirmWall(100, 200, 100, 50));
        add(new FirmWall(400, 200, 200, 50));
        //�����ս̹��
        add(superTank);

    }


//    private void initTanks(int num) {
//        for (int i = 0; i < num; i++) {
//            Tank tank = DefaultGameFactory.getInstance().createTank(50 + i * 30 * random.nextInt(10),
//                    50 + i * 10 * random.nextInt(10), Dir.DOWN, Group.BAD, this);
//            if (objects.size() == 0) {
//                add(tank);
//            }else {
//                while (!collideWith(tank)){
//                    tank = DefaultGameFactory.getInstance().createTank(120 + i * 30 * random.nextInt(10),
//                            70 + i * 20 * random.nextInt(10), Dir.DOWN, Group.BAD, this);
//                }
//                add(tank);
//            }
//        }
//    }

    public boolean collideWith(Tank tank) {
        for (int i =0;i< objects.size();i++){
            if (new TankTankCollider().collide(tank,objects.get(i))){
                return false;
            }
        }
        return true;
    }

    public void add(GameObject object) {
        objects.add(object);
    }

    public void remove(GameObject object) {
        objects.remove(object);
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("�ӵ�������:" + bullets.size(), 10, 60);
//        g.drawString("���˵�����:" + tanks.size(), 10, 80);
        g.setColor(c);

        for (int i = 0; i < objects.size(); i++)
            objects.get(i).paint(g);

//        for (int i = 0; i< bullets.size(); i++){
//            for(int j = 0; j< tanks.size(); j++){
//                bullets.get(i).collideWith(tanks.get(j));
//            }
//        }

        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                colliderChain.collide(o1, o2);
            }
        }
    }

    Tank getMainTank() {
        return superTank;
    }
}
