package theForgottenWar.graphics;

import theForgottenWar.tiles.Tile;

import java.awt.image.BufferedImage;

public class Assets {
    public static BufferedImage[][][] player = new BufferedImage[3][4][4];
    public static BufferedImage[][][] enemy = new BufferedImage[5][4][4];
    public static BufferedImage[][][] iselda = new BufferedImage[1][1][4];
    public static BufferedImage[][] WallTileLevel1 = new BufferedImage[4][4];
    public static BufferedImage[][] FloorTileLevel1 = new BufferedImage[4][4];
    public static BufferedImage[][] WaterTileLevel1 = new BufferedImage[4][4];
    public static BufferedImage[][] BorderTileLevel1 = new BufferedImage[4][4];
    public static BufferedImage[][] LakeBorderTileLevel1 = new BufferedImage[4][4];
    public static BufferedImage[][] BackgroundTileLevel1 = new BufferedImage[4][4];
    public static BufferedImage[][] BorderExternCornerTileLevel1 = new BufferedImage[4][4];
    public static BufferedImage[][] BorderInternCornerTileLevel1 = new BufferedImage[4][4];
    public static BufferedImage[][] LakeBorderInternCornerTileLevel1 = new BufferedImage[4][4];
    public static BufferedImage[] healthPickup = new BufferedImage[4];
    public static BufferedImage[] healthUPPickup = new BufferedImage[4];
    public static BufferedImage[] damagePickup = new BufferedImage[4];
    public static BufferedImage[] cooldownPickup = new BufferedImage[4];
    public static BufferedImage[] speedPickup = new BufferedImage[4];
    public static BufferedImage[] durationPickup = new BufferedImage[4];
    public static BufferedImage[] invulnerabilityPickup = new BufferedImage[4];
    public static BufferedImage[] sprintPickup = new BufferedImage[4];
    public static BufferedImage[] winImage = new BufferedImage[2];

    public static void Init() {

        int index1, index2;
        SpriteSheet[] myTileSprite = new SpriteSheet[5];
        myTileSprite[0] = new SpriteSheet(ImageLoader.LoadImage("/textures/spriteLevel1.png"));
        myTileSprite[1] = new SpriteSheet(ImageLoader.LoadImage("/textures/spriteLevel2.png"));
        myTileSprite[2] = new SpriteSheet(ImageLoader.LoadImage("/textures/spriteLevel3.png"));
        myTileSprite[3] = new SpriteSheet(ImageLoader.LoadImage("/textures/spriteLevel4.png"));
        SpriteSheet sheet2 = new SpriteSheet(ImageLoader.LoadImage("/textures/WinWithoutBackground.png"));
        SpriteSheet sheet3 = new SpriteSheet(ImageLoader.LoadImage("/textures/WinWithBackground.png"));
        SpriteSheet sheet4 = new SpriteSheet(ImageLoader.LoadImage("/textures/spritePickup.png"));
        SpriteSheet sheet5 = new SpriteSheet(ImageLoader.LoadImage("/textures/Player.png"));

        winImage[0] = sheet2.WinCrop(0,0);
        winImage[1] = sheet3.WinCrop(0,0);

        for (index1 = 0; index1 < 4; index1++) {
            for (index2 = 0; index2 < 4; index2++) {
                WallTileLevel1[index1][index2] = myTileSprite[index1].TileCrop(index2 % 2, 0);
                BorderTileLevel1[index1][index2] = myTileSprite[index1].TileCrop(index2, 1);
                FloorTileLevel1[index1][index2] = myTileSprite[index1].TileCrop(index2, 2);
                BackgroundTileLevel1[index1][index2] = myTileSprite[index1].TileCrop(index2, 3);
                BorderExternCornerTileLevel1[index1][index2] = myTileSprite[index1].TileCrop(index2, 4);
                BorderInternCornerTileLevel1[index1][index2] = myTileSprite[index1].TileCrop(index2, 5);
                WaterTileLevel1[index1][index2] = myTileSprite[index1].TileCrop(index2, 6);
                LakeBorderInternCornerTileLevel1[index1][index2] = myTileSprite[index1].TileCrop(index2, 7);
                LakeBorderTileLevel1[index1][index2] = myTileSprite[index1].TileCrop(index2, 8);
            }
        }
        for (index1 = 0; index1 < 4; index1++) {
            healthPickup[index1] = sheet4.TileCrop(index1, 0);
            healthUPPickup[index1] = sheet4.TileCrop(index1, 1);
            damagePickup[index1] = sheet4.TileCrop(index1,2);
            cooldownPickup[index1] = sheet4.TileCrop(index1,3);
            speedPickup[index1] = sheet4.TileCrop(index1,4);
            durationPickup[index1] = sheet4.TileCrop(index1,5);
            invulnerabilityPickup[index1] = sheet4.TileCrop(index1,6);
            sprintPickup[index1] = sheet4.TileCrop(index1,7);
            iselda[0][0][index1] = sheet5.CharacterCrop(index1,2);
        }
        Tile.init();
        myTileSprite[0] = new SpriteSheet(ImageLoader.LoadImage("/textures/Player.png"));
        myTileSprite[1] = new SpriteSheet(ImageLoader.LoadImage("/textures/Player_attack.png"));
        myTileSprite[2] = new SpriteSheet(ImageLoader.LoadImage("/textures/Player_hit.png"));
        for (int type = 0; type < 3; type++) {
            for (index1 = 0; index1 < 4; index1++) {
                for (index2 = 0; index2 < 4; index2++) {
                    player[type][index1][index2] = myTileSprite[type].CharacterCrop(index1, index2);
                }
            }
        }
        myTileSprite[0] = new SpriteSheet(ImageLoader.LoadImage("/textures/Drake.png"));
        myTileSprite[1] = new SpriteSheet(ImageLoader.LoadImage("/textures/Drake_fast.png"));
        myTileSprite[2] = new SpriteSheet(ImageLoader.LoadImage("/textures/Drake_slow.png"));
        myTileSprite[3] = new SpriteSheet(ImageLoader.LoadImage("/textures/Drake_ghost.png"));
        myTileSprite[4] = new SpriteSheet(ImageLoader.LoadImage("/textures/Drake.png"));
        for (int type = 0; type < 5; type++) {
            for (index1 = 0; index1 < 4; index1++) {
                for (index2 = 0; index2 < 4; index2++) {
                    enemy[type][index1][index2] = myTileSprite[type].CharacterCrop(index1, index2);
                }
            }
        }

    }
}
