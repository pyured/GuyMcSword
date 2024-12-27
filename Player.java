import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends Entity{
    KeyHandler keyH;
    boolean isMoving;
    Sound playerSound = new Sound();
    public int attackCooldown;
    public int specialCooldown;
    public Player(GamePanel gp, KeyHandler keyH)
    {
        super(gp);
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        solidArea = new Rectangle();
        solidArea.x = 12;
        solidArea.y = 10;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 44;
        attackArea.width = 48;
        attackArea.height = 48;
        specialAttackArea.width = 160;
        specialAttackArea.height = 160;
    }
    public void setDefaultValues()
    {
        x = 414;
        y = 306;
        speed = 4;
        direction = "down";
        hp = 5;
    }
    public void getPlayerImage()
    {
        try{
            File faceUp = new File("./src/playerSprites/guyUpIdle.png");
            File faceLeft = new File("./src/playerSprites/guyLeftIdle.png");
            File faceRight = new File("./src/playerSprites/guyRightIdle.png");
            File walkUp1 = new File("./src/playerSprites/guyUpWalk1.png");
            File walkUp2 = new File("./src/playerSprites/guyUpWalk2.png");
            File walkLeft1 = new File("./src/playerSprites/guyLeftWalk1.png");
            File walkLeft2 = new File("./src/playerSprites/guyLeftWalk2.png");
            File walkRight1 = new File("./src/playerSprites/guyRightWalk1.png");
            File walkRight2 = new File("./src/playerSprites/guyRightWalk2.png");
            up1 = ImageIO.read(faceUp);
            up2 = ImageIO.read(walkUp1);
            up3 = ImageIO.read(walkUp2);
            left1 = ImageIO.read(faceLeft);
            left2 = ImageIO.read(walkLeft1);
            left3 = ImageIO.read(walkLeft2);
            right1 = ImageIO.read(faceRight);
            right2 = ImageIO.read(walkRight1);
            right3 = ImageIO.read(walkRight2);
            down1 = ImageIO.read(faceLeft);
            down2 = ImageIO.read(walkLeft1);
            down3 = ImageIO.read(walkLeft2);
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void getPlayerAttackImage()
    {
        try {
            File guyAttackUp = new File("./src/playerSprites/guyAttackUp.png");
            File guyAttackDown = new File("./src/playerSprites/guyAttackDown.png");
            File guyAttackLeft = new File("./src/playerSprites/guyAttackLeft.png");
            File guyAttackRight = new File("./src/playerSprites/guyAttackRight.png");
            File guySpecial1 = new File("./src/playerSprites/guySpecialAttack1.png");
            File guySpecial2 = new File("./src/playerSprites/guySpecialAttack2.png");
            attackUp = ImageIO.read(guyAttackUp);
            attackDown = ImageIO.read(guyAttackDown);
            attackLeft = ImageIO.read(guyAttackLeft);
            attackRight = ImageIO.read(guyAttackRight);
            special1 = ImageIO.read(guySpecial1);
            special2 = ImageIO.read(guySpecial2);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void update()
    {
        if(keyH.attackButtonPressed == true)
        {
            attacking = true;
        }else if(keyH.specialButtonPressed){
            specialAttacking = true;
        }
        if (attacking == true)
        {
            attacking();
        }else if (specialAttacking == true){
            specialAttacking();
        }else if (keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true)
        {
            if (keyH.upPressed == true)
            {
                direction = "up";
                isMoving = true;
            }else if(keyH.downPressed == true)
            {
                direction = "down";
                isMoving = true;
            }else if(keyH.leftPressed == true)
            {
                direction = "left";
                isMoving = true;
            }else if(keyH.rightPressed == true)
            {
                direction = "right";
                isMoving = true;
            }
            collisionOn = false;
            gp.cChecker.checkTile(this);
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            if (collisionOn == false)
            {
                switch(direction)
                {
                    case "up":
                        y -= speed;
                        break;
                    case "down":
                        y += speed;
                        break;
                    case "left":
                        x -= speed;
                        break;
                    case "right":
                        x += speed;
                        break;
                }
            }

            spriteCounter++;
            if (spriteCounter > 5)
            {
                if (spriteNum == 1)
                {
                    spriteNum = 2;
                }else if (spriteNum == 2)
                {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }else{
            isMoving = false;
        }
        if (invincible == true)
        {
            invincibleCount++;
            if (invincibleCount > 90)
            {
                invincible = false;
                invincibleCount = 0;
            }
        }
        if (hp <= 0)
        {
            gp.gameState = gp.gameOverState;
            gp.playSE(6);
            gp.stopMusic();
        }
        if (attackCooldown > 0)
        {
            attackCooldown--;
        }
        if (specialCooldown > 0)
        {
            specialCooldown--;
        }
    }
    private void specialAttacking() {
        spriteCounter++;
        if (spriteCounter%4 == 0)
        {
            if (spriteNum == 2)
            {
                spriteNum = 1;
            }else{
                spriteNum = 2;
            }
        }
        int currentX = x;
        int currentY = y;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        x -= 54;
        y -= 54;

        solidArea.width = specialAttackArea.width;
        solidArea.height = specialAttackArea.height;
        ArrayList<Integer> monsterIndex = gp.cChecker.checkEntityMultiple(this, gp.monster);
        damageMonster(monsterIndex);

        x = currentX;
        y = currentY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;

        if (spriteCounter > 40)
        {
            spriteNum = 1;
            spriteCounter = 0;
            specialAttacking = false;
            keyH.specialButtonPressed = false;
        }
        // attackCooldown = 90;
    }
    private void attacking() {
        spriteCounter++;
        int currentX = x;
        int currentY = y;
        int solidAreaWidth = solidArea.width;
        int solidAreaHeight = solidArea.height;

        switch(direction)
        {
            case "up": y -= attackArea.height; break;
            case "down": y += attackArea.height; break;
            case "left": x -= attackArea.width; break;
            case "right": x += attackArea.width; break;
        }

        solidArea.width = attackArea.width;
        solidArea.height = attackArea.height;

        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        damageMonster(monsterIndex);

        x = currentX;
        y = currentY;
        solidArea.width = solidAreaWidth;
        solidArea.height = solidAreaHeight;

        if (spriteCounter > 20)
        {
            spriteCounter = 0;
            attacking = false;
            keyH.attackButtonPressed = false;
            //System.out.println("done attack");
        }
        // attackCooldown = 90;
    }
    private void damageMonster(int monsterIndex) {
        if (monsterIndex != 999)
        {
            if (gp.monster[monsterIndex].invincible == false)
            {
                if(gp.monster[monsterIndex].hp == 1 || gp.monster[monsterIndex].hp == 2 && specialAttacking)
                {
                    gp.playSE(3);
                }else{
                    gp.monster[monsterIndex].playHitSFX();
                }
                if (specialAttacking)
                {
                    gp.monster[monsterIndex].hp-=2;
                }else{
                    gp.monster[monsterIndex].hp--;
                }
                gp.monster[monsterIndex].invincible = true;
                if (gp.monster[monsterIndex].hp <= 0)
                {
                    gp.monster[monsterIndex].dying = true;;
                }
            }
        }
    }
    private void damageMonster(ArrayList<Integer> monsterIndexes) {
        if (monsterIndexes.size() > 0)
        {
            for (int i = 0; i < monsterIndexes.size(); i++)
            {
                if (gp.monster[monsterIndexes.get(i)] != null && gp.monster[monsterIndexes.get(i)].invincible == false)
                {
                    if(gp.monster[monsterIndexes.get(i)].hp == 1 || gp.monster[monsterIndexes.get(i)].hp == 2 && specialAttacking)
                    {
                        gp.playSE(3);
                    }else{
                        gp.monster[monsterIndexes.get(i)].playHitSFX();
                    }
                    if (specialAttacking)
                    {
                        gp.monster[monsterIndexes.get(i)].hp-=2;
                        if (gp.monster[monsterIndexes.get(i)].hp < 0)
                        {
                            gp.monster[monsterIndexes.get(i)].hp = 0;
                        }
                    }else{
                        gp.monster[monsterIndexes.get(i)].hp--;
                    }
                    gp.monster[monsterIndexes.get(i)].invincible = true;
                    if (gp.monster[monsterIndexes.get(i)].hp <= 0)
                    {
                        gp.monster[monsterIndexes.get(i)].dying = true;
                    }
                }
            }
        }
    }
    public void restoreDefault()
    {
        x = 414;
        y = 306;
        hp = 5;
        invincible = false;
        attacking = false;
        specialCooldown = 0;
        attackCooldown = 0;
    }
    private void contactMonster(int monsterIndex) {
        if (monsterIndex != 999)
        {
            if (invincible == false && !gp.monster[monsterIndex].dying && gp.monster[monsterIndex].alive)
            {
                hp--;
                playHurt();
                invincible = true;
            }
        }
    }
    public void draw(Graphics2D g2)
    {
        BufferedImage image = null;
        int widthScale = 1;
        int heightScale = 1;
        int moveX = 0;
        int moveY = 0;
        switch(direction)
        {
            case "up":
                if (attacking)
                {
                    heightScale = 2;
                    moveY = -gp.tileSize;
                    image = attackUp;
                }
                if (!attacking && !specialAttacking)
                {
                    heightScale = 1;
                    moveY = 0;
                    if (spriteNum == 1){image = up2;}
                    if (spriteNum == 2){image = up3;}
                    if (!isMoving){image = up1;}
                }
                break;
            case "down":
                if (attacking)
                {
                    heightScale = 2;
                    image = attackDown;
                }
                if (!attacking && !specialAttacking)
                {
                    heightScale = 1;
                    if (spriteNum == 1){image = down2;}
                    if (spriteNum == 2){image = down3;}
                    if (!isMoving){image = down1;}
                }
                break;
            case "left":
                if (attacking)
                {
                    widthScale = 2;
                    moveX = -gp.tileSize;
                    image = attackLeft;
                }
                if (!attacking && !specialAttacking)
                {
                    widthScale = 1;
                    moveX = 0;
                    if (spriteNum == 1){image = left2;}
                    if (spriteNum == 2){image = left3;}
                    if (!isMoving){image = left1;}
                }
                break;
            case "right":
                if (attacking)
                {
                    widthScale = 2;
                    image = attackRight;
                }
                if (!attacking && !specialAttacking)
                {
                    widthScale = 1;
                    if (spriteNum == 1){image = right2;}
                    if (spriteNum == 2){image = right3;}
                    if (!isMoving){image = right1;}
                }
                break;
        }
        if (specialAttacking)
        {
            widthScale = 3;
            heightScale = 3;
            moveX = -gp.tileSize;
            moveY = -gp.tileSize;
            if(spriteNum == 1){image = special1;}
            if(spriteNum == 2){image = special2;}
        }
        if (invincible == true)
        {
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, x + moveX, y + moveY, gp.tileSize*widthScale, gp.tileSize*heightScale, null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
    public void playHurt()
    {
        playerSound.setFile(4);
        playerSound.play();
    }
    public void playSwing()
    {
        playerSound.setFile(5);
        playerSound.play();
    }
    public void playSpin() {
        playerSound.setFile(7);
        playerSound.play();
    }
}
