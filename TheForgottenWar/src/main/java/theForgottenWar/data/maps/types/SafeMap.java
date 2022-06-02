package theForgottenWar.data.maps.types;

import theForgottenWar.characters.Iselda;
import theForgottenWar.data.maps.objects.EmptyLake;
import theForgottenWar.data.maps.objects.Exit;
import theForgottenWar.data.maps.objects.Lake;
import theForgottenWar.tiles.Tile;

public class SafeMap extends Map {
    @Override
    public void GenerateMap() {
        SimpleMapGenerate();

        GenerateBorder(getGameBorder(0) / Tile.TILE_HEIGHT, getGameBorder(1) / Tile.TILE_WIDTH, getGameBorder(2) / Tile.TILE_HEIGHT, getGameBorder(3) / Tile.TILE_WIDTH);

        int x = getGameBorder(3) / Tile.TILE_WIDTH;

        setNumberOfExits(1);
        setExit(new Exit[1]);
        setExit(0, new Exit(x, Map.screenHeight/2/Tile.TILE_HEIGHT - 1, rand.nextInt(8) + 1));

        setLakeList(new Lake[2]);

        setLake(0, new EmptyLake(10, 5, 2, 20));
        setLake(1, new EmptyLake(10, 13, 2, 20));
        GenerateLake();
    }

}