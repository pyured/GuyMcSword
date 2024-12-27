import java.awt.Rectangle;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;

public class Skeleton extends Entity{
    public Skeleton(GamePanel gp)
    {
        super(gp);
        speed = 2;
        maxLife = 3;
        hp = maxLife;
        Random random = new Random();
        int i = random.nextInt(100) + 1;
        isMoving = true;
        if (i <= 20)
        {
            direction = "up";
        }
        if (i > 20 && i <= 40)
        {
            direction = "down";
        }
        if (i > 40 && i <= 60)
        {
            direction = "left";
        }
        if (i > 60 && i <= 80)
        {
            direction = "right";
        }
        if (i > 80 && i <= 100)
        {
            direction = "none";
            isMoving = false;
        }
        type = 1;

        solidArea = new Rectangle(10, 15, 35, 39);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    public void getImage()
    {
        try {
            File left = new File("./src/enemySprites/skeletonStandLeft.png");
            File right = new File("./src/enemySprites/skeletonStandRight.png");
            File leftWalk1 = new File("./src/enemySprites/skeletonWalkLeft1.png");
            File leftWalk2 = new File("./src/enemySprites/skeletonWalkLeft2.png");
            File rightWalk1 = new File("./src/enemySprites/skeletonWalkRight1.png");
            File rightWalk2 = new File("./src/enemySprites/skeletonWalkRight2.png");

            up1 = ImageIO.read(left);
            up2 = ImageIO.read(leftWalk1);
            up3 = ImageIO.read(leftWalk2);
            left1 = ImageIO.read(left);
            left2 = ImageIO.read(leftWalk1);
            left3 = ImageIO.read(leftWalk2);
            right1 = ImageIO.read(right);
            right2 = ImageIO.read(rightWalk1);
            right3 = ImageIO.read(rightWalk2);
            down1 = ImageIO.read(right);
            down2 = ImageIO.read(rightWalk1);
            down3 = ImageIO.read(rightWalk2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void setAction()
    {
        actionLockCounter++;
        if (actionLockCounter == 120)
        {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            isMoving = true;
            if (i <= 20)
            {
                direction = "up";
            }
            if (i > 20 && i <= 40)
            {
                direction = "down";
            }
            if (i > 40 && i <= 60)
            {
                direction = "left";
            }
            if (i > 60 && i <= 80)
            {
                direction = "right";
            }
            if (i > 80 && i <= 100)
            {
                direction = "none";
                isMoving = false;
            }
            actionLockCounter = 0;
        }
    }
    public void update()
    {
        setAction();
        super.update();
    }
    public void playHitSFX()
    {
        gp.playSE(2);
    }
}
