package theForgottenWar.tiles;

import java.awt.*;
import java.awt.image.BufferedImage;
public class Tile {
    public static final int TILE_WIDTH = 50;
    public static final int TILE_HEIGHT = 50;
    private static final int NO_TILES = 32;
    public static Tile[] tiles = new Tile[NO_TILES];
    public static Tile[][] Floor = new Tile[4][4];
    public static Tile[][] Water = new Tile[4][4];
    public static Tile[][] Wall = new Tile[4][4];
    public static Tile[][] Border = new Tile[4][4];
    public static Tile[][] Background = new Tile[4][4];
    public static Tile[][] LakeBorder = new Tile[4][4];
    public static Tile[][] BorderExternCorner = new Tile[4][4];
    public static Tile[][] BorderInternCorner = new Tile[4][4];
    public static Tile[][] LakeBorderInternCorner = new Tile[4][4];
    protected final int id;
    protected BufferedImage img;
    public Tile(BufferedImage image, int idd) {
        img = image;
        id = idd;
        tiles[id] = this;
    }

    public static void init() {
        int index;
        for (int index1 = 0; index1 < 4; index1++) {
            for (index = 0; index < 4; index++) {
                Floor[index1][index] = new FloorTile(0, index1, index);
                Water[index1][index] = new WaterTile(1, index1, index);
                Wall[index1][index] = new WallTile(2, index1, index);
                Border[index1][index] = new BorderTile(3, index1, index);
                Background[index1][index] = new BackgroundTile(4, index1, index);
                BorderExternCorner[index1][index] = new BorderExternCornerTile(5, index1, index);
                BorderInternCorner[index1][index] = new BorderInternCornerTile(6, index1, index);
                LakeBorderInternCorner[index1][index] = new LakeBorderInternCornerTile(7, index1, index);
                LakeBorder[index1][index] = new LakeBorderTile(8, index1, index);
            }
        }
    }

    public static boolean CheckId(int id) {
        boolean x;
        x = tiles[id].IsSolid();
        return !x;
    }

    public void Update() {

    }

    public void Draw(Graphics g, int x, int y) {
        g.drawImage(img, x, y, TILE_WIDTH, TILE_HEIGHT, null);
    }

    public boolean IsSolid() {
        return false;
    }

    public int GetId() {
        return id;
    }
}
