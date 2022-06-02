package theForgottenWar.tiles;

import theForgottenWar.graphics.Assets;

public class BorderTile extends Tile {
    public BorderTile(int id, int lvl, int type) {
        super(Assets.BorderTileLevel1[lvl][type], id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
