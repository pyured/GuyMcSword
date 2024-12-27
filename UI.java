import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
public class UI {
    public GamePanel gp;
    public  Font pixelMplus;
    public BufferedImage[] images;
    public boolean messageOn = false;
    public double playTime;
    public Graphics2D g2;
    public DecimalFormat dFormat = new DecimalFormat("#0.00");
    public int commandNum = 0;
    int decrementHeightReg = 0;
    int decrementHeightSpecial = 0;
    int timeRemaining = 0;
    boolean lastWaveMusic = false;
    public UI(GamePanel gp)
    {
        this.gp = gp;
        try{
            //registering font
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("./src/PixelMplus10-Regular.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./src/PixelMplus10-Regular.ttf")));
        }catch(IOException | FontFormatException e){

        }
        try {
            //registering images for ui
            images = new BufferedImage[10];
            images[0] = ImageIO.read(new File("./src/UIAssets/heart.png"));
            images[1] = ImageIO.read(new File("./src/UIAssets/logop1.png"));
            images[2] = ImageIO.read(new File("./src/UIAssets/logop2.png"));
        } catch (Exception e) {
        }
    }
    public void draw(Graphics2D g2)
    {
        this.g2 = g2;
        if (gp.gameState == gp.playState)
        {
            drawPlayScreen(g2);
        }
        if (gp.gameState == gp.pauseState)
        {
            drawPauseScreen();
        }
        if (gp.gameState == gp.titleState)
        {
            drawTitleScreen();
        }
        if (gp.gameState == gp.gameOverState)
        {
            drawGameOverScreen();
        }
        if (gp.gameState == gp.transitionState)
        {
            drawTransitionScreen();
        }
    }
    private void drawTransitionScreen() {
        timeRemaining--;
        if (gp.wHandler.currentWave == 4)
        {
            gp.music.stop();
            gp.playSE(8);
            gp.creditHandler.finishingScene = true;
            gp.creditHandler.finalSceneCounter = 260;
            gp.gameState = gp.creditState;
        }else{
            drawTimer(g2);
            drawPlayerLife(g2);
            drawCooldownButtons();
            int x;
            int y;
            String text;
            g2.setFont(pixelMplus.deriveFont(75f));
            text = "Wave " + (gp.wHandler.currentWave + 1) + " starting in " + (timeRemaining + 60)/60;
            x = getXCenteredText(text);
            y = gp.screenHeight/3;
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+5);
            g2.setColor(Color.white);
            g2.drawString(text, x, y);
            if (gp.wHandler.currentWave + 1 == 4)
            {
                if (!lastWaveMusic)
                {
                    gp.music.stop();
                    gp.playMusic(10);
                    lastWaveMusic = true;
                }
                text = "The dog has arrived.";
                x = getXCenteredText(text);
                y = 2*gp.screenHeight/3;
                g2.setColor(Color.RED);
                g2.drawString(text, x+5, y+5);
                g2.setColor(Color.white);
                g2.drawString(text, x, y);
            }
            if (timeRemaining == 0)
            {
                gp.wHandler.currentWave++;
                gp.wHandler.setNewWave();
                gp.gameState = gp.playState;
            }
        }
    }
    private void drawGameOverScreen() {
        g2.setColor(new Color(0, 0, 0, 125));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        int x;
        int y;
        String text;
        g2.setFont(pixelMplus.deriveFont(75f));
        text = "you died LMFAO no shot";
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        text = "retry";
        x = getXCenteredText(text);
        y = 5*gp.screenHeight/8;
        g2.drawString(text, x, y);
        if (commandNum == 0)
        {
            g2.drawString(">", x - 50, y);
        }

        text = "quit";
        x = getXCenteredText(text);
        y = 6*gp.screenHeight/8;
        g2.drawString(text, x, y);
        if (commandNum == 1)
        {
            g2.drawString(">", x - 50, y);
        }


    }
    private void drawTimer(Graphics2D g2) {
        g2.setFont(pixelMplus.deriveFont(30f));
        g2.setColor(Color.white);
        g2.drawString("Time:" + dFormat.format(playTime), gp.tileSize*13 - 12, gp.tileSize);
    }
    private void drawTitleScreen() {
        g2.setColor(new Color(0xa62d3d));
        g2.fillRect(0,0,gp.getWidth(),gp.getHeight());

        g2.drawImage(images[1], gp.screenWidth/2 - 250, gp.screenHeight/100, 500, 288, null);
        g2.drawImage(images[2], gp.screenWidth/2 - 120, gp.screenHeight/6, 240, 240, null);

        g2.setFont(pixelMplus.deriveFont(75f));
        String text = "GUY MCSWORD'S REVENGE";
        int x = getXCenteredText(text);
        int y = 2*gp.screenHeight/3;
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        g2.setFont(pixelMplus.deriveFont(40f));

        text = "START";
        x = getXCenteredText(text);
        y = 4*gp.screenHeight/5;
        g2.drawString(text, x, y);
        if (commandNum == 0)
        {
            g2.drawString(">", x - 20, y);
        }

        text = "QUIT";
        x = getXCenteredText(text);
        y = 6*gp.screenHeight/7;
        g2.drawString(text, x, y);
        if (commandNum == 1)
        {
            g2.drawString(">", x - 20, y);
        }
    }
    public void timerUpdate()
    {
        playTime += (double)1/60;
    }
    public void drawPauseScreen()
    {
        g2.setFont(pixelMplus.deriveFont(50f));
        g2.setColor(Color.white);
        String text = "PAUSED";
        int x = getXCenteredText(text);
        int y = gp.screenHeight/2;
        g2.drawString(text, x, y);
    }
    public void drawPlayScreen(Graphics2D g2)
    {
        drawPlayerLife(g2);
        drawTimer(g2);
        drawCooldownButtons();
    }
    private void drawCooldownButtons() {
        int x;
        int y;
        x = 8*gp.screenWidth/10;
        y = 9*gp.screenHeight/10 - 10;
        
        int width = 60;
        int height = 60;

        g2.setColor(new Color(0xd6d6d6));
        g2.fillRect(x+3, y+3, width, height);
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, width, height);
        g2.setColor(new Color(0xdb3535));
        getCooldownHeight();
        //System.out.println(decrementHeightReg);
        g2.fillRect(x, y + (width - decrementHeightReg), height, decrementHeightReg);
        g2.setColor(Color.BLACK);
        g2.setFont(pixelMplus.deriveFont(40f));
        g2.drawString("Z", x + (width-10)/2 - (int)g2.getFontMetrics().getStringBounds("Z", g2).getWidth()/2, y + 35);

        x = x + 70;
        g2.setColor(new Color(0xd6d6d6));
        g2.fillRect(x+3, y+3, width, height);
        g2.setColor(Color.WHITE);
        g2.fillRect(x, y, width, height);
        g2.setColor(new Color(0xdb3535));
        specialCooldownHeight();
        g2.fillRect(x, y + (width - decrementHeightSpecial), height, decrementHeightSpecial);
        g2.setColor(Color.BLACK);
        g2.setFont(pixelMplus.deriveFont(40f));
        g2.drawString("X", x + (width-10)/2 - (int)g2.getFontMetrics().getStringBounds("Z", g2).getWidth()/2, y + 35);

    }
    public int getXCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public void drawPlayerLife(Graphics2D g2)
    {
        for (int i = 0; i < gp.player.hp; i++)
        {
            int gap = gp.tileSize;
            g2.drawImage(images[0], gp.tileSize/2 + i*gap, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
        }
    }
    public void getCooldownHeight()
    {
        if (gp.player.attackCooldown > 0 && gp.player.attackCooldown < 60)
        {
            
            decrementHeightReg = (60 - gp.player.attackCooldown) + 3; 
        
        }else{
        decrementHeightReg = 0;
        }
    }
    public void specialCooldownHeight()
    {
        if (gp.player.specialCooldown > 0 && gp.player.specialCooldown < 300)
        {
            
            if(gp.player.specialCooldown%5 == 0)
            {
                decrementHeightSpecial++;
            } 
        
        }else{
        decrementHeightSpecial = 0;
        }
    }
}
