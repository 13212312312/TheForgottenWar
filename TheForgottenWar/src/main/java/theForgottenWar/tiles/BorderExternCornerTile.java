package theForgottenWar.tiles;

import theForgottenWar.graphics.Assets;

public class BorderExternCornerTile extends Tile {
    public BorderExternCornerTile(int id, int lvl, int type) {
        super(Assets.BorderExternCornerTileLevel1[lvl][type], id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
