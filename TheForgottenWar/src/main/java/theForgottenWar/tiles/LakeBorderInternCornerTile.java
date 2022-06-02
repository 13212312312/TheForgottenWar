package theForgottenWar.tiles;

import theForgottenWar.graphics.Assets;

public class LakeBorderInternCornerTile extends Tile {
    public LakeBorderInternCornerTile(int id, int lvl, int type) {
        super(Assets.LakeBorderInternCornerTileLevel1[lvl][type], id);
    }

    @Override
    public boolean IsSolid() {
        return false;
    }
}
