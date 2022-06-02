package theForgottenWar.tiles;

import theForgottenWar.graphics.Assets;

public class BackgroundTile extends Tile {
    public BackgroundTile(int id, int lvl, int type) {
        super(Assets.BackgroundTileLevel1[lvl][type], id);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}
