package theForgottenWar.characters;

import theForgottenWar.graphics.Assets;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.system.combat.Collision;
import theForgottenWar.system.manager.LevelingManager;
import theForgottenWar.tiles.Tile;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Iselda extends Character {

    private final int levelingRange = 100;

    public Iselda(int x, int y) {
        super(Assets.iselda, x, y, "Iselda", 10, 0);
        x = x * Tile.TILE_WIDTH;
        y = y * Tile.TILE_HEIGHT;
        setX(x);
        setY(y);
        this.setColumn(0);
    }

    @Override
    public void Update(int frame) {
        super.Update(frame);
        LevelingManager.inRange = Collision.inRange(
                this.getX(),
                this.getY(),
                levelingRange,
                CharacterList.myPlayer.getX(),
                CharacterList.myPlayer.getY(),
                CharacterList.myPlayer.getCharacterHeight(),
                CharacterList.myPlayer.getCharacterWidth()
                ) &&
                LevelingManager.cooldown == 0;
    }

    @Override
    public int getCharacterWidth() {
        return Character.ISELDA_WIDTH;
    }

    @Override
    public int getCharacterHeight() {
        return Character.ISELDA_HEIGHT;
    }

}