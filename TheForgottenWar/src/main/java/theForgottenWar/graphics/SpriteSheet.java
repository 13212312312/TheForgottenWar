package theForgottenWar.graphics;

import java.awt.image.BufferedImage;

public class SpriteSheet {
    private static final int CharacterWidth = 1080;
    private static final int CharacterHeight = 1600;
    private static final int TileWidth = 100;
    private static final int TileHeight = 100;
    private static final int WinHeight = 280;
    private static final int WinWidth = 500;
    private final BufferedImage spriteSheet;

    public SpriteSheet(BufferedImage buffImg) {
        spriteSheet = buffImg;
    }

    public BufferedImage TileCrop(int x, int y) {
        return spriteSheet.getSubimage(x * TileWidth, y * TileHeight, TileWidth, TileHeight);
    }

    public BufferedImage CharacterCrop(int x, int y) {
        return spriteSheet.getSubimage(x * CharacterWidth, y * CharacterHeight, CharacterWidth, CharacterHeight);
    }

    public BufferedImage WinCrop(int x,int y)
    {
        return spriteSheet.getSubimage(x * WinWidth, y * WinHeight, WinWidth, WinHeight);
    }
}
