import java.awt.*;

import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 18;
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //54 x 54 tiles
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; //864 pixels
    final int screenHeight = tileSize * maxScreenRow; //648 pixels
    final int FPS = 60;

    Thread gameThread;
    UI ui = new UI(this);
    Credits creditHandler = new Credits(this);
    WaveHandler wHandler = new WaveHandler(this);
    KeyHandler keyH = new KeyHandler(this);
    Player player = new Player(this, keyH);
    public Entity monster[] = new Entity[10];
    TileManager tileM = new TileManager(this);
    CollisionChecker cChecker = new CollisionChecker(this);
    AssetSetter aSetter = new AssetSetter(this);
    Sound music = new Sound();
    Sound sfx = new Sound();

    public final int playState = 1;
    public final int pauseState = 2;
    public final int titleState = 0;
    public final int gameOverState = 3;
    public final int transitionState = 4;
    public final int creditState = 5;
    public int gameState = titleState;

    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void setUpGame()
    {
        wHandler.setNewWave();
        playMusic(1);
        gameState = titleState;
        //playMusic(1);
    }
    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime;
            if (delta >= 1)
            {
                update();
                repaint();
                delta--;
            }
        }
    }
    public void update()
    {
        if (gameState == playState)
        {
            player.update();
            tileM.update();            
            ui.timerUpdate();
            wHandler.update();
            for (int i = 0; i < monster.length; i++)
            {
                if (monster[i] != null)
                {
                    if(monster[i].alive == true && monster[i].dying == false)
                    {
                        monster[i].update();
                    }
                    if(monster[i].alive == false)
                    {
                        monster[i] = null;
                    }
                }
            }
        }
        if (gameState == pauseState)
        {

        }
        if (gameState == titleState)
        {

        }
        if (gameState == transitionState)
        {
            player.update();
            tileM.update();            
            ui.timerUpdate();
        }
        if (gameState == creditState)
        {
            if (creditHandler.finishingScene == true)
            {
                player.update();
                tileM.update();
                ui.timerUpdate();
                creditHandler.update();
            }
        }
    }
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        if (gameState == titleState)
        {
            ui.draw(g2);
        }else if (gameState == playState){
            long drawStart = 0;
            drawStart = System.nanoTime();
            tileM.draw(g2);
            player.draw(g2);
            for (int i = 0; i < monster.length; i++)
            {
                 if (monster[i] != null)
                 {
                     monster[i].draw(g2);
                 }
            }
            ui.draw(g2);
            if (keyH.checkDrawTime)
            {
                long drawEnd = System.nanoTime();
                long passed = drawEnd - drawStart;
                g2.setColor(Color.white);
                g2.drawString("Draw Time: " + passed, 10, 400);
                System.out.println("Draw Time: " + passed);
            }
        }else if (gameState == pauseState)
        {
            tileM.draw(g2);
            player.draw(g2);
            ui.draw(g2);
            for (int i = 0; i < monster.length; i++)
            {
                 if (monster[i] != null)
                 {
                     monster[i].draw(g2);
                 }
            }
        }else if (gameState == transitionState)
        {
            tileM.draw(g2);
            player.draw(g2);
            ui.draw(g2);
        }else if (gameState == gameOverState)
        {
            tileM.draw(g2);
            player.draw(g2);
            for (int i = 0; i < monster.length; i++)
            {
                 if (monster[i] != null)
                 {
                     monster[i].draw(g2);
                 }
            }
            ui.draw(g2);
        } else if (gameState == creditState)
        {
            if (creditHandler.finishingScene == true)
            {
                // System.out.println("drawing finsihingscene");
                // System.out.println(creditHandler.finishingScene);
                tileM.draw(g2);
                player.draw(g2);
                for (int i = 0; i < monster.length; i++)
                {
                    if (monster[i] != null)
                    {
                        monster[i].draw(g2);
                    }
                }
                ui.draw(g2);
            }else{
                creditHandler.draw(g2);
            }
        }
        g2.dispose();
    }
    public void playMusic(int i)
    {
        music.setFile(i);
        System.out.println(music.clip);
        music.play();
        music.loop();
    }
    public void stopMusic()
    {
        music.stop();
    }
    public void playSE(int i)
    {
        sfx.setFile(i);
        sfx.play();
    }
    public void retry()
    {
        player.restoreDefault();
        clearMonsters();
        ui.lastWaveMusic = false;
        wHandler.currentWave = 1;
        wHandler.setNewWave();
        ui.playTime = 0;
        sfx.stop();
    }
    public void clearMonsters()
    {
        for (int i = 0; i < monster.length; i++)
        {
            monster[i] = null;
        }
    }
}
