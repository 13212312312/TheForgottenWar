package theForgottenWar.pickups;

import theForgottenWar.characters.Character;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.system.combat.Collision;
import theForgottenWar.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {

    private final BufferedImage[] img;
    private int x, y;
    private int ITEM_WIDTH = 50;
    private int ITEM_HEIGHT = 50;
    private final String name;
    private int poz = 0;
    private boolean pickedUp;
    private boolean visible;

    public Item(BufferedImage[] image, int x, int y, String name) {
        img = image;
        this.x = x * Tile.TILE_WIDTH;
        this.y = y * Tile.TILE_HEIGHT;
        this.name = name;
        pickedUp = false;
        visible = false;
    }

    public void Update(int frame) {
        if (frame % 10 == 0) {
            poz++;
            poz = poz % 4;
        }
        if (Collision.CheckCollision(x / Tile.TILE_WIDTH,
                y / Tile.TILE_HEIGHT,
                (ITEM_WIDTH - Character.diff) / Tile.TILE_WIDTH,
                (ITEM_HEIGHT - Character.diff) / Tile.TILE_HEIGHT,
                CharacterList.myPlayer.getPositionX(),
                CharacterList.myPlayer.getPositionY(),
                CharacterList.myPlayer.getPositionWidth(Character.PLAYER_WIDTH, Character.PLAYER_HEIGHT),
                CharacterList.myPlayer.getPositionHeight(Character.PLAYER_WIDTH, Character.PLAYER_HEIGHT)) && isVisible()) {
            pickUp();
        }
    }

    public void pickUp() {
        pickedUp = true;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getItemWidth() {
        return ITEM_WIDTH;
    }

    public void setItemWidth(int itemWidth) {
        ITEM_WIDTH = itemWidth;
    }

    public int getItemHeight() {
        return ITEM_HEIGHT;
    }

    public void setItemHeight(int itemHeight) {
        ITEM_HEIGHT = itemHeight;
    }

    public void Draw(Graphics g) {
        if (!pickedUp)
            g.drawImage(img[poz], x, y, getItemWidth(), getItemHeight(), null);
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
