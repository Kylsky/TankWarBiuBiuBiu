package tank.Day8;


public class Main {
    public static void main(String[] args){
        TankFrame tkFrame = new TankFrame();
        while(true){
            try {
                Thread.sleep(50);
                tkFrame.repaint();
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}