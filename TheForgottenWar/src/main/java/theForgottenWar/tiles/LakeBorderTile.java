package theForgottenWar.tiles;

import theForgottenWar.graphics.Assets;

public class LakeBorderTile extends Tile {
    public LakeBorderTile(int id, int lvl, int type) {
        super(Assets.LakeBorderTileLevel1[lvl][type], id);
    }

    @Override
    public boolean IsSolid() {
        return false;
    }
}
