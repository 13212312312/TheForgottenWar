package theForgottenWar.data.maps.types;

import theForgottenWar.data.maps.objects.Exit;
import theForgottenWar.data.maps.objects.Lake;
import theForgottenWar.tiles.Tile;

public class StandardMap extends Map {
    @Override
    public void GenerateMap() {
        SimpleMapGenerate();
        int index1, tl;

        GenerateBorder(getGameBorder(0) / Tile.TILE_HEIGHT, getGameBorder(1) / Tile.TILE_WIDTH, getGameBorder(2) / Tile.TILE_HEIGHT, getGameBorder(3) / Tile.TILE_WIDTH);

        int x = getGameBorder(3) / Tile.TILE_WIDTH;
        int max = getGameBorder(2) / Tile.TILE_HEIGHT - 1;
        int min = getGameBorder(0) / Tile.TILE_HEIGHT;
        int minExit = 2;
        int maxExit = 8;
        setNumberOfExits(rand.nextInt(maxExit - minExit) + minExit);
        setExit(new Exit[getNumberOfExits()]);
        for (index1 = 0; index1 < getNumberOfExits(); index1++) {
            tl = GenerateExitPozition(min, max);
            if (tl == -1) {
                setNumberOfExits(getLastExit());
                break;
            } else {
                setLastExit(index1);
                setExit(index1, new Exit(x, tl, rand.nextInt(8) + 1));
            }
        }

        setNumberOfLakes(rand.nextInt(4) + 2);
        setLakeList(new Lake[getNumberOfLakes()]);
        int ruleaza = 1;
        for (index1 = 0; index1 < getNumberOfLakes() && ruleaza == 1; index1++) {
            ruleaza = GenerateLakePos(index1);
        }
        GenerateLake();
    }
}