package theForgottenWar.lists;

import theForgottenWar.pickups.*;
import theForgottenWar.tiles.Tile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static theForgottenWar.system.game.Game.rand;

public class ItemList {
    public List<Item> myItems = new ArrayList<Item>();

    public void Update(int frame) {
        for (Item item : myItems) {
            item.Update(frame);
        }
    }

    public void AddItem(int x, int y, int type) {
        switch (type) {
            case 1 -> myItems.add(new Health(x, y, "health"));
            case 2 -> myItems.add(new HealthUP(x, y, "healthUP"));
            case 3 -> myItems.add(new Damage(x, y, "damage"));
            case 4 -> myItems.add(new Cooldown(x, y, "cooldown"));
            case 5 -> myItems.add(new Speed(x, y, "speed"));
            case 6 -> myItems.add(new Duration(x, y, "duration"));
            case 7 -> myItems.add(new Invulnerability(x, y, "invulnerability"));
            case 8 -> myItems.add(new Sprint(x, y, "sprint"));
        }
    }

    public boolean CheckNR() {
        int nr = 0;
        for (Item item : myItems) {
            if (!item.isPickedUp()) nr++;
        }
        return nr == 0;
    }
    public void replaceItem(int type)
    {
        int x,y;
        x = CharacterList.myPlayer.getInitialx()/Tile.TILE_WIDTH;
        y = CharacterList.myPlayer.getInitialy()/Tile.TILE_HEIGHT;
        myItems.clear();
        AddItem(x, y, type);
    }

    public void AddItems() {
        int x, y;
        x = CharacterList.myPlayer.getInitialx()/Tile.TILE_WIDTH;
        y = CharacterList.myPlayer.getInitialy()/Tile.TILE_HEIGHT;
        AddItem(x, y, rand.nextInt(1,8));
    }

    public void setVisible()
    {
        for (Item item : myItems) {
            item.setVisible(true);
        }
    }

    public void Draw(Graphics g) {
        for (Item item : myItems) {
            if(item.isVisible())
                item.Draw(g);
        }
    }
}
