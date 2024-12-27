public class AssetSetter {
    GamePanel gp;
    public AssetSetter(GamePanel gp)
    {
        this.gp = gp;
    }
    public void setWave1()
    {
        gp.monster[0] = new Skeleton(gp);
        gp.monster[0].x = gp.tileSize * 7;
        gp.monster[0].y = gp.tileSize * 1;

        gp.monster[1] = new Skeleton(gp);
        gp.monster[1].x = gp.tileSize * 3;
        gp.monster[1].y = gp.tileSize * 6;

        gp.monster[2] = new Skeleton(gp);
        gp.monster[2].x = gp.tileSize * 12;
        gp.monster[2].y = gp.tileSize * 6;

        gp.monster[3] = new Skeleton(gp);
        gp.monster[3].x = gp.tileSize * 7;
        gp.monster[3].y = gp.tileSize * 9;

        gp.monster[4] = new Skeleton(gp);
        gp.monster[4].x = gp.tileSize * 12;
        gp.monster[4].y = gp.tileSize * 4;

        gp.monster[5] = new Skeleton(gp);
        gp.monster[5].x = gp.tileSize * 2;
        gp.monster[5].y = gp.tileSize * 6;
    }
    public void setWave2()
    {
        gp.monster[0] = new Ghost(gp);
        gp.monster[0].x = gp.tileSize * 1;
        gp.monster[0].y = gp.tileSize * 1;

        gp.monster[1] = new Ghost(gp);
        gp.monster[1].x = gp.tileSize * 14;
        gp.monster[1].y = gp.tileSize * 10;

        gp.monster[2] = new Ghost(gp);
        gp.monster[2].x = gp.tileSize * 1;
        gp.monster[2].y = gp.tileSize * 10;

        gp.monster[3] = new Ghost(gp);
        gp.monster[3].x = gp.tileSize * 14;
        gp.monster[3].y = gp.tileSize * 1;
    }
    public void setWave3()
    {
        gp.monster[0] = new Skeleton(gp);
        gp.monster[0].x = gp.tileSize * 7;
        gp.monster[0].y = gp.tileSize * 3;

        gp.monster[1] = new Skeleton(gp);
        gp.monster[1].x = gp.tileSize * 8;
        gp.monster[1].y = gp.tileSize * 3;

        gp.monster[2] = new Skeleton(gp);
        gp.monster[2].x = gp.tileSize * 4;
        gp.monster[2].y = gp.tileSize * 5;

        gp.monster[3] = new Skeleton(gp);
        gp.monster[3].x = gp.tileSize * 4;
        gp.monster[3].y = gp.tileSize * 6;

        gp.monster[4] = new Skeleton(gp);
        gp.monster[4].x = gp.tileSize * 11;
        gp.monster[4].y = gp.tileSize * 5;

        gp.monster[5] = new Skeleton(gp);
        gp.monster[5].x = gp.tileSize * 11;
        gp.monster[5].y = gp.tileSize * 6;

        gp.monster[6] = new Skeleton(gp);
        gp.monster[6].x = gp.tileSize * 6;
        gp.monster[6].y = gp.tileSize * 8;

        gp.monster[7] = new Skeleton(gp);
        gp.monster[7].x = gp.tileSize * 9;
        gp.monster[7].y = gp.tileSize * 8;

        gp.monster[8] = new Ghost(gp);
        gp.monster[8].x = gp.tileSize * 1;
        gp.monster[8].y = gp.tileSize * 1;

        gp.monster[9] = new Ghost(gp);
        gp.monster[9].x = gp.tileSize * 14;
        gp.monster[9].y = gp.tileSize * 10;
    }
    public void setWave4()
    {
        gp.monster[0] = new Ghost(gp);
        gp.monster[0].x = gp.tileSize * 1;
        gp.monster[0].y = gp.tileSize * 1;

        gp.monster[1] = new Ghost(gp);
        gp.monster[1].x = gp.tileSize * 14;
        gp.monster[1].y = gp.tileSize * 1;

        gp.monster[2] = new Skeleton(gp);
        gp.monster[2].x = gp.tileSize * 4;
        gp.monster[2].y = gp.tileSize * 5;

        gp.monster[3] = new Skeleton(gp);
        gp.monster[3].x = gp.tileSize * 4;
        gp.monster[3].y = gp.tileSize * 6;

        gp.monster[4] = new Skeleton(gp);
        gp.monster[4].x = gp.tileSize * 11;
        gp.monster[4].y = gp.tileSize * 5;

        gp.monster[5] = new Skeleton(gp);
        gp.monster[5].x = gp.tileSize * 11;
        gp.monster[5].y = gp.tileSize * 6;

        gp.monster[6] = new Ghost(gp);
        gp.monster[6].x = gp.tileSize * 1;
        gp.monster[6].y = gp.tileSize * 10;

        gp.monster[7] = new Ghost(gp);
        gp.monster[7].x = gp.tileSize * 14;
        gp.monster[7].y = gp.tileSize * 10;

        gp.monster[8] = new Doge(gp);
        gp.monster[8].x = gp.tileSize * 7;
        gp.monster[8].y = gp.tileSize * 1;

    }
}
