package tank.Day8;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManager {
    public static BufferedImage goodTankL1,goodTankR1,goodTankU1,goodTankD1,badTankL1,badTankR1,badTankU1,badTankD1;
    public static BufferedImage goodTankL2,goodTankR2,goodTankU2,goodTankD2,badTankL2,badTankR2,badTankU2,badTankD2;
    public static BufferedImage bulletL,bulletR,bulletU,bulletD;
    public static BufferedImage[] explode = new BufferedImage[16];

    static {
        try {
            goodTankU1 = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("tank/Day4/images/GoodTank1.png"));
            goodTankR1 = tank.Day8.ImageUtil.rotateImage(goodTankU1,90);
            goodTankL1 = tank.Day8.ImageUtil.rotateImage(goodTankU1,-90);
            goodTankD1 = tank.Day8.ImageUtil.rotateImage(goodTankU1,180);

            badTankU1 = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("tank/Day4/images/BadTank1.png"));
            badTankR1 = tank.Day8.ImageUtil.rotateImage(badTankU1,90);
            badTankL1 = tank.Day8.ImageUtil.rotateImage(badTankU1,-90);
            badTankD1 = tank.Day8.ImageUtil.rotateImage(badTankU1,180);

            goodTankU2 = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("tank/Day4/images/GoodTank2.png"));
            goodTankR2 = tank.Day8.ImageUtil.rotateImage(goodTankU1,90);
            goodTankL2 = tank.Day8.ImageUtil.rotateImage(goodTankU1,-90);
            goodTankD2 = tank.Day8.ImageUtil.rotateImage(goodTankU1,180);

            badTankU2 = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("tank/Day4/images/BadTank2.png"));
            badTankR2 = tank.Day8.ImageUtil.rotateImage(badTankU1,90);
            badTankL2 = tank.Day8.ImageUtil.rotateImage(badTankU1,-90);
            badTankD2 = tank.Day8.ImageUtil.rotateImage(badTankU1,180);

            bulletU = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("tank/Day4/images/bulletU.png"));
            bulletR = tank.Day8.ImageUtil.rotateImage(bulletU,90);
            bulletL = tank.Day8.ImageUtil.rotateImage(bulletU,-90);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            for (int i =0;i<16;i++){
                explode[i] = ImageIO.read(ResourceManager.class.getClassLoader().getResourceAsStream("tank/Day4/images/e"+(i+1)+".gif"));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}