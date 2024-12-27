import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean attackButtonPressed;
    public boolean specialButtonPressed;
    public final int regCooldown = 60;
    public final int specialCooldown = 300;
    boolean checkDrawTime;
    GamePanel gp;
    public KeyHandler(GamePanel gp)
    {
        this.gp = gp;
    }

    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (gp.gameState == gp.titleState)
        {
            if(code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0)
                {
                    gp.ui.commandNum = 1;
                }
            }   
            if(code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1)
                {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum == 0)
                {
                    gp.ui.timeRemaining = 300;
                    gp.gameState = gp.transitionState;
                    gp.stopMusic();
                    gp.playMusic(0);
                }
                if (gp.ui.commandNum == 1)
                {
                    System.exit(0);
                }
            }
        }
        if (gp.gameState == gp.playState || gp.gameState == gp.transitionState)
        {
            if(code == KeyEvent.VK_UP)
            {
                upPressed = true;
            }
            if(code == KeyEvent.VK_DOWN)
            {
                downPressed = true;
            }
            if(code == KeyEvent.VK_LEFT)
            {
                leftPressed = true;
            }
            if(code == KeyEvent.VK_RIGHT)
            {
                rightPressed = true;
            }
            if (code == KeyEvent.VK_T)
            {
                if (checkDrawTime == false)
                {
                    checkDrawTime = true;
                }else{
                    checkDrawTime = false;
                }
            }
            if (code == KeyEvent.VK_Z && !attackButtonPressed && gp.player.attackCooldown == 0)
            {
                attackButtonPressed = true;
                gp.player.attackCooldown = regCooldown;
                gp.player.playSwing();
            }
            if (code == KeyEvent.VK_X && !specialButtonPressed && gp.player.specialCooldown == 0)
            {
                specialButtonPressed = true;
                gp.player.specialCooldown = this.specialCooldown;
                gp.player.playSpin();
            }
        }
        if (gp.gameState == gp.gameOverState)
        {
            if (code == KeyEvent.VK_UP)
            {
                gp.ui.commandNum--;
                if (gp.ui.commandNum < 0)
                {
                    gp.ui.commandNum = 1;
                }
            }
            if(code == KeyEvent.VK_DOWN)
            {
                gp.ui.commandNum++;
                if (gp.ui.commandNum > 1)
                {
                    gp.ui.commandNum = 0;
                }
            }
            if (code == KeyEvent.VK_ENTER)
            {
                if (gp.ui.commandNum == 0)
                {
                    gp.retry();
                    gp.stopMusic();
                    gp.playMusic(0);
                    gp.gameState = gp.playState;
                }
                if (gp.ui.commandNum == 1)
                {
                    System.exit(0);
                }
            }
        }
        if (code == KeyEvent.VK_ESCAPE)
        {
            if (gp.gameState == gp.playState)
            {
                gp.gameState = gp.pauseState;
                gp.music.stop();
            }else if (gp.gameState == gp.pauseState){
                gp.gameState = gp.playState;
                gp.music.play();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_UP)
        {
            upPressed = false;
        }
        if(code == KeyEvent.VK_DOWN)
        {
            downPressed = false;
        }
        if(code == KeyEvent.VK_LEFT)
        {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT)
        {
            rightPressed = false;
        }
    }
    
}
