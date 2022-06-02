package theForgottenWar.tiles;

import theForgottenWar.graphics.Assets;

public class BorderInternCornerTile extends Tile {
    public BorderInternCornerTile(int id, int lvl, int type) {
        super(Assets.BorderInternCornerTileLevel1[lvl][type], id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
