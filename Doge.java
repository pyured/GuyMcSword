import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Doge extends Entity{
    boolean contactPlayer;
    int timeStayed;
    int timeAtPoint;
    public Doge(GamePanel gp)
    {
        super(gp);
        speed = 3;
        maxLife = 20;
        hp = maxLife;
        isCollidable = true;
        type = 1;
        direction = "left";
        solidArea = new Rectangle(10, 2, 34, 50);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    private void getImage() {
        try {
            File left = new File("./src/enemySprites/dogeStandLeft.png");
            File right = new File("./src/enemySprites/dogeStandRight.png");
            File leftWalk1 = new File("./src/enemySprites/dogeWalkLeft1.png");
            File leftWalk2 = new File("./src/enemySprites/dogeWalkLeft2.png");
            File rightWalk1 = new File("./src/enemySprites/dogeWalkRight1.png");
            File rightWalk2 = new File("./src/enemySprites/dogeWalkRight2.png");
            File specialAttack = new File("./src/enemySprites/dogeAttack.png");
            left1 = ImageIO.read(left);
            left2 = ImageIO.read(leftWalk1);
            left3 = ImageIO.read(leftWalk2);
            right1 = ImageIO.read(right);
            right2 = ImageIO.read(rightWalk1);
            right3 = ImageIO.read(rightWalk2);
            down1 = ImageIO.read(right);
            down2 = ImageIO.read(rightWalk1);
            down3 = ImageIO.read(rightWalk2);
            up1 = ImageIO.read(left);
            up2 = ImageIO.read(leftWalk1);
            up3 = ImageIO.read(leftWalk2);
            special1 = ImageIO.read(specialAttack);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update()
    {
        if (attacking)
        {
            attacking();
        }else{
            approachPlayer(speed);
            if (this.type == 1 && contactPlayer == true)
            {
                if(gp.player.invincible == false)
                {
                    gp.player.hp--;
                    gp.player.playHurt();
                    gp.player.invincible = true;
                    contactPlayer = false;
                }
            }
            if (invincible == true)
            {
                invincibleCount++;
                if (invincibleCount > 40)
                {
                    invincible = false;
                    invincibleCount = 0;
                }
            }
            spriteCounter++;
            if (timeAtPoint > 180)
            {
                attacking = true;
                timeAtPoint = 0;
            }
            if (spriteCounter > 12)
            {
                if (spriteNum == 1)
                {
                    spriteNum = 2;
                }else{
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    private void attacking() {
        spriteCounter++;
        if(gp.player.invincible == false)
        {
            gp.player.hp--;
            gp.player.playHurt();
            gp.player.invincible = true;
        }
        if (spriteCounter > 40)
        {
            spriteCounter = 0;
            attacking = false;
        }
    }

    private void approachPlayer(int speed) {
        int pastX = x;
        int pastY = y;
        boolean xStuck = false;
        boolean yStuck = false;

        int targetX = gp.player.x;
        int targetY = gp.player.y;

        // Calculate the difference between the target position and current enemy position
        int dx = targetX - x;
        int dy = targetY - y;

        // Calculate the distance to the target position
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Calculate the interpolation amount based on the desired speed (3 pixels per frame)
        double interpolationAmount = 3.0 / distance;

        if (dx >= 0)
        {
            direction = "right";
        }else{
            direction = "left";
        }
        gp.cChecker.checkTile(this);
        gp.cChecker.checkEntity(this, gp.monster);
        contactPlayer = gp.cChecker.checkPlayer(this);
        if (!collisionOn)
        {
            x = this.x + (int) (dx * interpolationAmount);
        }

        if (dy >= 0)
        {
            direction = "down";
        }else{
            direction = "up";
        }
        gp.cChecker.checkTile(this);
        gp.cChecker.checkEntity(this, gp.monster);
        if (!contactPlayer)
        {
            contactPlayer = gp.cChecker.checkPlayer(this);
        }
        if (!collisionOn)
        {
            y = this.y + (int) (dy * interpolationAmount);
        }
        if (pastY == y)
        {
            yStuck = true;
        }
        if (pastX == x)
        {
            xStuck = true;
        }
        collisionOn = false;
        if (xStuck && yStuck)
        {
            timeAtPoint++;
        }else{
            timeAtPoint = 0;
        }
    }
    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        switch(direction)
        {
            case "left": if (spriteNum == 1){image = left2;} if (spriteNum == 2){image = left3;} 
            case "right": if (spriteNum == 1){image = right2;} if (spriteNum == 2){image = right3;} 
            case "up": if (spriteNum == 1){image = up2;} if (spriteNum == 2){image = up3;} 
            case "down": if (spriteNum == 1){image = down2;} if (spriteNum == 2){image = down3;}
        }
        if (attacking) {image = special1;}
        if (type == 1 && hpBarOn)
        {
            double oneScale = (double)gp.tileSize/maxLife;
            double hpBarValue = oneScale*hp;
            g2.setColor(Color.BLACK);
            g2.fillRect(x - 3, y - 18, gp.tileSize + 6, 16);
            g2.setColor(new Color(0xdb3535));
            g2.fillRect(x, y - 15, (int)hpBarValue, 10);

            hpBarCounter++;
            if (hpBarCounter > 360)
            {
                hpBarCounter = 0;
                hpBarOn = false;
            }
        }
        if (invincible == true)
        {
            hpBarOn = true;
            hpBarCounter = 0;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        if(dying)
        {
            dyingAnimation(g2);
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        if (attacking)
        {
            g2.setColor(new Color(255, 0, 0, 125));
            g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
