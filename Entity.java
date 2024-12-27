import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.*;

public class Entity {
    public int x, y;
    public int speed;
    public BufferedImage up1, up2, up3, left1, left2, left3, right1, right2, right3, down1, down2, down3;
    public BufferedImage attackUp, attackDown, attackLeft, attackRight, special1, special2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea;
    public boolean collisionOn = false;
    public int hp;
    public GamePanel gp;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public int actionLockCounter = 0;
    public boolean isMoving;
    public boolean invincible = false;
    public int invincibleCount = 0;
    public int type; 
    public boolean attacking = false;
    public boolean specialAttacking = false;
    public Rectangle attackArea = new Rectangle();
    public Rectangle specialAttackArea = new Rectangle();
    public boolean alive = true;
    public boolean dying = false;
    public int dyingCounter;
    public boolean isCollidable = true;
    public int maxLife;
    public boolean hpBarOn = false;
    public int hpBarCounter = 0;
    public Entity(GamePanel gp)
    {
        this.gp = gp;
    }
    public void update() {
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);

        if (this.type == 1 && contactPlayer == true)
        {
            if(gp.player.invincible == false)
            {
                gp.player.hp--;
                gp.player.playHurt();
                gp.player.invincible = true;
            }
        }

        if(collisionOn == false)
        {
            switch(direction)
            {
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;
                case "right": x += speed; break;
            }
        }
        spriteCounter++;
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
        if (invincible == true)
        {
            invincibleCount++;
            isCollidable = false;
            if (invincibleCount > 40)
            {
                invincible = false;
                invincibleCount = 0;
                isCollidable = true;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        switch(direction)
        {
            case "up":
            if (spriteNum == 1)
            {
                image = up2;
            }
            if (spriteNum == 2)
            {
                image = up3;
            }
            if (!isMoving)
            {
                image = up1;
            }
            break;
            case "down":
            if (spriteNum == 1)
            {
                image = down2;
            }
            if (spriteNum == 2)
            {
                image = down3;
            }
            if (!isMoving)
            {
                image = down1;
            }
            break;
            case "left":
            if (spriteNum == 1)
            {
                image = left2;
            }
            if (spriteNum == 2)
            {
                image = left3;
            }
            if (!isMoving)
            {
                image = left1;
            }
            break;
            case "right":
            if (spriteNum == 1)
            {
                image = right2;
            }
            if (spriteNum == 2)
            {
                image = right3;
            }
            if (!isMoving)
            {
                image = right1;
            }
            break;
            case "none":
                image = right1;
        }

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
    }
    public void dyingAnimation(Graphics2D g2) {
        dyingCounter++;
        if (dyingCounter%5 == 0 && dyingCounter%10 == 5)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f));
        }
        if (dyingCounter%5 == 0 && dyingCounter%10 == 0)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
        if (dyingCounter > 40)
        {
            alive = false;
            dying = false;
        }
    }
    public void playHitSFX()
    {
        gp.playSE(2);
    }
    public void playDeathSFX() {
        gp.playSE(3);
    }
}
