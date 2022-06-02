package theForgottenWar.tiles;

import theForgottenWar.graphics.Assets;

public class FloorTile extends Tile {
    public FloorTile(int id, int lvl, int type) {
        super(Assets.FloorTileLevel1[lvl][type], id);
    }

    @Override
    public boolean IsSolid() {
        return false;
    }
}
