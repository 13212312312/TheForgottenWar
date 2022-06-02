package theForgottenWar.tiles;

import theForgottenWar.graphics.Assets;

public class WaterTile extends Tile {
    public WaterTile(int id, int lvl, int type) {
        super(Assets.WaterTileLevel1[lvl][type], id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
