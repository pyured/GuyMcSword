import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];
    int spriteCounter;
    int torchSprite = 0;
    public TileManager(GamePanel gp){
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];
        getTileImage();
        loadMap();
        for (int[] ints : mapTileNum)
        {
            for (int i : ints)
            {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
    public void getTileImage(){
        try {
            tile[0] = new Tile();
            File floorTile = new File("./src/tiles/floorTile.png");
            tile[0].image = ImageIO.read(floorTile);

            tile[1] = new Tile();
            File wallTile = new File("./src/tiles/brickTile.png");
            tile[1].image = ImageIO.read(wallTile);
            tile[1].collision = true;

            tile[2] = new Tile();
            File gateTile = new File("./src/tiles/gate.png");
            tile[2].image = ImageIO.read(gateTile);
            tile[2].collision = true;

            tile[3] = new Tile();
            File torch = new File("./src/tiles/torch.png");
            tile[3].image = ImageIO.read(torch);
            tile[3].collision = true;
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void loadMap(){
        try {
            File mapText = new File("./src/map.txt");
            InputStream is = new FileInputStream(mapText);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            for (int row = 0; row < gp.maxScreenRow; row++) {
                String line = br.readLine();
                String[] numbers = line.split(" ");
                for (int col = 0; col < gp.maxScreenCol; col++) {
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[row][col] = num;
                }
            }
            br.close();
        } catch (Exception e) {
            
        }
    }
    public void update()
    {
        spriteCounter++;
        if (spriteCounter > 10)
        {
            if (torchSprite == 0)
            {
                try {
                    torchSprite = 1;
                    tile[3].image = ImageIO.read(new File("./src/tiles/torch.png"));
                } catch (Exception e) {
                }
            }else{
                try {
                    torchSprite = 0;
                    tile[3].image = ImageIO.read(new File("./src/tiles/torch2.png"));
                } catch (Exception e) {
                    
                }
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D g2){
        int x = 0;
        int y = 0;
        for (int r = 0; r < gp.maxScreenRow; r++)
        {
            for (int c = 0; c < gp.maxScreenCol; c++)
            {
                int tileNum = mapTileNum[r][c];
                g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
                x += gp.tileSize;
            }
            y += gp.tileSize;
            x = 0;
        }   
    }
}
