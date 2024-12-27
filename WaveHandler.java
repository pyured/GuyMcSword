public class WaveHandler {
    GamePanel gp;
    public int currentWave;
    public boolean waveDone;
    public int currentEnemies = 0;
    public WaveHandler(GamePanel gp)
    {
        this.gp = gp;
        currentWave = 0;
        currentEnemies = 0;
    }
    public void update()
    {
        if (gp.gameState == gp.playState)
        {
            checkEnemiesLeft();
        }

    }
    public void checkEnemiesLeft()
    {
        int enemyCount = 0;
        for (int i = 0; i < gp.monster.length; i++)
        {
            if (gp.monster[i] != null)
            {
                enemyCount++;
            }
        }
        if (enemyCount == 0 && currentWave != 5)
        {
            gp.gameState = gp.transitionState;
            gp.player.hp += 2;
            gp.ui.timeRemaining = 300;
        }
    }
    public void setNewWave() {
        switch (currentWave)
        {
            case 1: gp.aSetter.setWave1(); System.out.println("set wave1"); break;
            case 2: gp.aSetter.setWave2(); System.out.println("set wave 2"); break;
            case 3: gp.aSetter.setWave3(); System.out.println("set wave 3"); break;
            case 4: gp.aSetter.setWave4(); System.out.println("set wave 4"); break;
        }
        //System.out.println(currentWave);
    }
}
