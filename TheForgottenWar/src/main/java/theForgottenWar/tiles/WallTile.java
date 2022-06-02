package theForgottenWar.tiles;

import theForgottenWar.graphics.Assets;

public class WallTile extends Tile {

    public WallTile(int id, int lvl, int type) {
        super(Assets.WallTileLevel1[lvl][type], id);
    }

    @Override
    public boolean IsSolid() {
        return false;
    }
}
