package theForgottenWar.data.maps.types;

import theForgottenWar.data.maps.objects.EmptyLake;
import theForgottenWar.data.maps.objects.Exit;
import theForgottenWar.data.maps.objects.Lake;
import theForgottenWar.tiles.Tile;

public class BossMap extends Map {
    @Override
    public void GenerateMap() {
        SimpleMapGenerate();

        GenerateBorder(getGameBorder(0) / Tile.TILE_HEIGHT, getGameBorder(1) / Tile.TILE_WIDTH, getGameBorder(2) / Tile.TILE_HEIGHT, getGameBorder(3) / Tile.TILE_WIDTH);

        int x = getGameBorder(3) / Tile.TILE_WIDTH;
        int max = getGameBorder(2) / Tile.TILE_HEIGHT - 1;
        int min = getGameBorder(0) / Tile.TILE_HEIGHT;

        setNumberOfExits(1);
        setExit(new Exit[1]);
        setExit(0, new Exit(x, GenerateExitPozition(min, max), rand.nextInt(5)));

        setNumberOfLakes(4);
        setLakeList(new Lake[4]);

        setLake(0, new EmptyLake(6, 5, 2, 2));
        setLake(1, new EmptyLake(29, 13, 2, 2));
        setLake(2, new EmptyLake(6, 13, 2, 2));
        setLake(3, new EmptyLake(29, 5, 2, 2));
        GenerateLake();
    }

    @Override
    public Exit findTheChosenExit(int pozY) {
        return null;
    }
}