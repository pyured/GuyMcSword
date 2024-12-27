import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;

import java.awt.*;

public class Credits {
    GamePanel gp;
    public boolean finishingScene = false;
    public int finalSceneCounter = 0;
    public int phase = 0;
    public int currentText;
    public float opacityScale = 0f;
    public  Font pixelMplus;
    public Graphics2D g2;
    public int counter;
    public boolean fadeText;
    public ArrayList<String[]> specialThanks = new ArrayList<String[]>();
    public int currentPerson = 0;
    public ImageIcon americanFlag; //220 by 220
    public DecimalFormat dFormat = new DecimalFormat("#0.00");
    public Credits(GamePanel gp)
    {
        this.gp = gp;
        try{
            //registering font
            pixelMplus = Font.createFont(Font.TRUETYPE_FONT, new File("./src/PixelMplus10-Regular.ttf")).deriveFont(30f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./src/PixelMplus10-Regular.ttf")));

            americanFlag = new ImageIcon("src\\UIAssets\\july-american-flag.gif");
        }catch(IOException | FontFormatException e){

        }
        phase = 0;
        specialThanks.add(new String[]{"Marcus", "For taking his eyes off Dokkan Battle for more than 5 minutes"});
        specialThanks.add(new String[]{"Christian", "For being an inspiration and W friend"});
        specialThanks.add(new String[]{"Adam", "For sharing the struggles of Italian and switching to VSC"});
        specialThanks.add(new String[]{"Muhammad", "For not being a nazi and being my three time FRQ partner"});
        specialThanks.add(new String[]{"Andy", "For emotional support"});
        specialThanks.add(new String[]{"John", "For emotional and hunger support"});
        specialThanks.add(new String[]{"Mani", "For carrying me in Fortnite that one time"});
        specialThanks.add(new String[]{"All of APCSA 5 & 6", "For making my junior year amazing"});
        specialThanks.add(new String[]{"Mr. Holmer", "For teaching us how to be now."});
    }
    public void update() {
        if (finishingScene = true)
        {
            finalSceneCounter--;
            if (finalSceneCounter == 0)
            {
                finishingScene = false;
            }
        }
    }
    public void draw(Graphics2D g2) {
        this.g2 = g2;
        switch (phase)
        {
            case 0: drawPart1(); break;
            case 1: drawPart2(); break;
            case 2: drawPart3(); break;
            case 3: drawPart4(); break;
            case 4: drawPart5(); break;
        }
    }
    private void drawPart1() {
        int x;
        int y;
        String text;
        g2.setFont(pixelMplus.deriveFont(25f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "You slayed the weird evil dog and got your jordans back.";
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y);
        counter++;
        if (counter > 360 && !fadeText)
        {
            fadeText = true;
            counter = 0;
        }
        if (counter % 30 == 0 && fadeText)
        {
            opacityScale++;
            if (counter == 300)
            {
                fadeText = false;
                counter = 0;
                phase = 1;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                opacityScale = 0;
                gp.playSE(9);
            }
        }
    }
    private void drawPart2() {
        int x;
        int y;
        String text;
        g2.setFont(pixelMplus.deriveFont(65f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "Art and Design";
        x = getXCenteredText(text);
        y = gp.screenHeight/2 - 20;
        g2.setColor(Color.white);
        System.out.println("doing part 2");
        g2.drawString(text, x, y);

        g2.setFont(pixelMplus.deriveFont(35f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "Felix Sierra";
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y + 10);

        g2.setFont(pixelMplus.deriveFont(35f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "My girlfriend";
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y + 45);

        counter++;
        if (counter > 360 && !fadeText)
        {
            fadeText = true;
            counter = 0;
        }
        if (counter % 10 == 0 && fadeText)
        {
            opacityScale++;
            if (counter == 100)
            {
                fadeText = false;
                counter = 0;
                phase = 2;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                opacityScale = 0;
            }
        }
    }
    private void drawPart3() {
        int x;
        int y;
        String text;
        counter++;
        g2.setFont(pixelMplus.deriveFont(65f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "Sound and Music";
        x = getXCenteredText(text);
        y = gp.screenHeight/2 - 20;
        g2.setColor(Color.white);
        System.out.println("doing part 3");
        g2.drawString(text, x, y);

        g2.setFont(pixelMplus.deriveFont(25f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "Playboi Carti?";
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y + 10);

        g2.setFont(pixelMplus.deriveFont(25f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "Guidus Game Developers";
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y + 35);


        g2.setFont(pixelMplus.deriveFont(25f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "Ray Charles";
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y + 60);

        g2.setFont(pixelMplus.deriveFont(25f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "Some Roblox Game I Found";
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y + 85);

        g2.setFont(pixelMplus.deriveFont(25f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = "Sonic Team";
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y + 110);

        if (counter > 360 && !fadeText)
        {
            fadeText = true;
            counter = 0;
        }
        if (counter % 10 == 0 && fadeText)
        {
            opacityScale++;
            if (counter == 100)
            {
                fadeText = false;
                counter = 0;
                phase = 3;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                opacityScale = 0;
            }
        }
    }
    private void drawPart4() {
        int x;
        int y;
        counter++;
        String text;
        g2.setFont(pixelMplus.deriveFont(65f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        text = "Special Thanks";
        x = getXCenteredText(text);
        y = gp.screenHeight/2 - 20;
        g2.setColor(Color.white);
        System.out.println("doing part 4");
        g2.drawString(text, x, y);

        g2.setFont(pixelMplus.deriveFont(35f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = specialThanks.get(currentPerson)[0];
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y + 12);

        g2.setFont(pixelMplus.deriveFont(25f));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f - (0.1f * opacityScale)));
        text = specialThanks.get(currentPerson)[1];
        x = getXCenteredText(text);
        y = gp.screenHeight/2;
        g2.setColor(Color.white);
        g2.drawString(text, x, y + 35);

        if (counter > 360 && !fadeText)
        {
            fadeText = true;
            counter = 0;
        }
        if (counter % 10 == 0 && fadeText)
        {
            opacityScale++;
            if (counter == 100)
            {
                currentPerson++;
                if (currentPerson >= specialThanks.size())
                {
                    phase = 4;
                }else{
                    phase = 3;
                }
                fadeText = false;
                counter = 0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
                opacityScale = 0;
            }
        }
    }
    private void drawPart5()
    {
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0f + (0.1f * opacityScale)));
        g2.drawImage(americanFlag.getImage(), gp.screenWidth/2 - 220/2, gp.screenHeight/2 - 220/2, 220, 220, null);
        int x;
        int y;
        String text;
        text = "Time:" + dFormat.format(gp.ui.playTime);
        g2.setFont(pixelMplus.deriveFont(65f));
        x = getXCenteredText(text);
        y = gp.screenHeight/2 + 175;
        g2.setColor(Color.white);
        g2.drawString(text,  x, y);
        if (opacityScale < 10)
        {
            opacityScale++;
        }
    }
    public int getXCenteredText(String text)
    {
        int length = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}