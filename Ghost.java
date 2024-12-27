import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Ghost extends Entity{

    public Ghost(GamePanel gp)
    {
        super(gp);
        speed = 2;
        maxLife = 5;
        hp = maxLife;
        isCollidable = false;
        type = 1;
        direction = "left";
        solidArea = new Rectangle(10, 10, 34, 34);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    private void getImage() {
        try {
            File left = new File("./src/enemySprites/ghostLeft.png");
            File right = new File("./src/enemySprites/ghostRight.png");
            left1 = ImageIO.read(left);
            right1 = ImageIO.read(right);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void update()
    {
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        //System.out.println(contactPlayer);
        if (this.type == 1 && contactPlayer == true)
        {
            if(gp.player.invincible == false)
            {
                gp.player.hp--;
                gp.player.playHurt();
                gp.player.invincible = true;
            }
        }
        approachPlayer(speed);
        if (invincible == true)
        {
            invincibleCount++;
            if (invincibleCount > 40)
            {
                invincible = false;
                invincibleCount = 0;
            }
        }
    }
    private void approachPlayer(int speed) {
        int targetX = gp.player.x;
        int targetY = gp.player.y;

        // Calculate the difference between the target position and current enemy position
        int dx = targetX - x;
        int dy = targetY - y;

        // Calculate the distance to the target position
        double distance = Math.sqrt(dx * dx + dy * dy);

        // Calculate the interpolation amount based on the desired speed (3 pixels per frame)
        double interpolationAmount = 3.0 / distance;

     // Interpolate the enemy's position towards the target position
        x = this.x + (int) (dx * interpolationAmount);
        y = this.y + (int) (dy * interpolationAmount);

        // this.x = interpolatedX;
        // this.y = interpolatedY;
    }
    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        switch(direction)
        {
            case "left": image = left1; break;
            case "right": image = right1; break;
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
}
