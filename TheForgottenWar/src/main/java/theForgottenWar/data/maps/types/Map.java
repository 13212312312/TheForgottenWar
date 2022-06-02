package theForgottenWar.data.maps.types;

import theForgottenWar.characters.Character;
import theForgottenWar.characters.Iselda;
import theForgottenWar.data.maps.objects.EmptyLake;
import theForgottenWar.data.maps.objects.Exit;
import theForgottenWar.data.maps.objects.FullLake;
import theForgottenWar.data.maps.objects.Lake;
import theForgottenWar.system.game.Game;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.system.combat.Collision;
import theForgottenWar.tiles.Tile;

import java.util.Arrays;
import java.util.Random;


public class Map {
    private Iselda Iselda = null;
    private static final int[] gameBorder = new int[4];
    public static int screenWidth;
    public static int screenHeight;
    public final int[][] idlist = new int[100][100];
    public final int[][] simpleidlist = new int[100][100];
    private final int borderLimit = 2;
    private final int MaxLakeHeight = 5;
    private final int MaxLakeWidth = 10;
    Random rand = Game.rand;
    private int numberOfExits;
    private Exit[] exit;
    private int lastExit = -1;
    private int numberOfLakes = 1;
    private boolean displayExit = false;
    private Lake[] lakeList;
    private int chosenExit;

    public Map() {
        gameBorder[0] = borderLimit * Tile.TILE_HEIGHT;
        gameBorder[1] = borderLimit * Tile.TILE_WIDTH;
        gameBorder[2] = screenHeight - gameBorder[0];
        gameBorder[3] = screenWidth - gameBorder[1];
        Exit.setX(gameBorder[3] / Tile.TILE_WIDTH);
    }

    public int getTile(int x, int y) {
        return idlist[x][y];
    }

    public int getTileRotation(int x, int y) {
        return simpleidlist[x][y];
    }

    public void SimpleMapGenerate() {
        int index1, index2;
        int tl;
        for (index1 = 0; index1 < screenWidth / Tile.TILE_WIDTH; index1++) {
            for (index2 = 0; index2 < screenHeight / Tile.TILE_HEIGHT; index2++) {
                tl = rand.nextInt(4);
                idlist[index1][index2] = 0;
                simpleidlist[index1][index2] = tl;
            }
        }
    }

    public void setExit(int index1, Exit exit) {
        this.exit[index1] = exit;
    }

    public Exit[] getExit() {
        return exit;
    }

    public void setExit(Exit[] exit) {
        this.exit = exit;
    }

    public Lake getLake(int index) {
        return lakeList[index];
    }

    public void setLake(int index, Lake lake) {
        lakeList[index] = lake;
    }

    public Exit findTheChosenExit(int pozY)
    {
        pozY = pozY / Tile.TILE_HEIGHT;
        for(int index = 0;index < numberOfExits;index++)
        {
            if(pozY == exit[index].getY() || pozY-1 == exit[index].getY() || pozY+1 == exit[index].getY())
            {
                return exit[index];
            }
        }
        return null;
    }
    public Exit getExit(int i) {
        return exit[i];
    }

    public int getBorderLimit() {
        return borderLimit;
    }

    public int getLastExit() {
        return lastExit;
    }

    public void setLastExit(int lastExit) {
        this.lastExit = lastExit;
    }

    public int getNumberOfLakes() {
        return numberOfLakes;
    }

    public void setNumberOfLakes(int numberOfLakes) {
        this.numberOfLakes = numberOfLakes;
    }

    public int getMaxLakeHeight() {
        return MaxLakeHeight;
    }

    public int getMaxLakeWidth() {
        return MaxLakeWidth;
    }

    public int getExitX() {
        return Exit.getX();
    }

    public boolean isDisplayExit() {
        return displayExit;
    }

    public void setDisplayExit(boolean displayExit) {
        this.displayExit = displayExit;
    }

    public Lake[] getLakeList() {
        return lakeList;
    }

    public void setLakeList(Lake[] lakeList) {
        this.lakeList = lakeList;
    }

    public int getChosenExit() {
        return chosenExit;
    }

    public void setChosenExit(int chosenExit) {
        this.chosenExit = chosenExit;
    }

    public int GenerateExitPozition(int min, int max) {
        int index;
        int finalpoz = -1;
        int tl;
        int ok;
        int tries = 0;
        do {
            tries++;
            ok = 1;
            tl = rand.nextInt(max - min - 1) + min;
            for (index = 0; index <= lastExit; index++) {
                if (Math.abs(exit[index].getY() - tl) < 4) {
                    ok = 0;
                }
            }
        } while (ok == 0 && tries < 5000);
        if (tries < 5000) {
            finalpoz = tl;
        }
        return finalpoz;
    }


    public void GenerateLake() {
        for (Lake myLake : lakeList) {
            myLake.Generate(this);
        }
    }

    public void GenerateBorder(int y1, int x1, int y2, int x2) {
        int index1, index2;
        for (index1 = 0; index1 <= x2 + x1; index1++) {
            for (index2 = 0; index2 <= x2 + x1; index2++) {
                if (index1 >= x1 + 1 && index1 <= x2 - 1 && index2 >= y1 + 1 && index2 <= y2 - 1) {
                    idlist[index1][index2] = 0;
                } else {
                    idlist[index1][index2] = 4;
                }
            }
        }
        for (index1 = x1; index1 <= x2; index1++) {
            //perete sus
            idlist[index1][y1] = 2;
            //margine sus
            idlist[index1][y1 - 1] = 3;
            simpleidlist[index1][y1 - 1] = 2;
            //margine jos
            idlist[index1][y2] = 3;
            simpleidlist[index1][y2] = 0;
        }
        for (index2 = y1; index2 <= y2; index2++) {
            //stanga
            idlist[x1][index2] = 3;
            simpleidlist[x1][index2] = 1;
            //dreapta
            idlist[x2][index2] = 3;
            simpleidlist[x2][index2] = 3;
        }
        //colturi
        idlist[x1][y1 - 1] = 6;
        simpleidlist[x1][y1 - 1] = 2;

        idlist[x1][y2] = 6;
        simpleidlist[x1][y2] = 1;

        idlist[x2][y1 - 1] = 6;
        simpleidlist[x2][y1 - 1] = 3;

        idlist[x2][y2] = 6;
        simpleidlist[x2][y2] = 0;
    }

    public int GenerateLakePos(int index1) {
        int lastX = (gameBorder[3] - gameBorder[1]) / Tile.TILE_WIDTH - MaxLakeWidth - 2 * borderLimit - 1;
        int lastY = (gameBorder[2] - gameBorder[0]) / Tile.TILE_HEIGHT - MaxLakeHeight - 2 * borderLimit - 1;
        int pozx, pozy, height, width, fulllake;
        int tries = 0;
        do {
            tries++;
            pozx = rand.nextInt(lastX) + borderLimit + 3;
            pozy = rand.nextInt(lastY) + borderLimit + 3;
            height = rand.nextInt(MaxLakeHeight) + 1;
            width = rand.nextInt(MaxLakeWidth) + 1;
            fulllake = rand.nextInt(2);
        } while (!IsGood(pozx, pozy, width, height, fulllake, index1) && tries < 30000);
        if (tries >= 30000) {
            numberOfLakes = index1;
            lakeList = Arrays.copyOf(lakeList, numberOfLakes);
            return 0;
        } else {
            if (fulllake == 0)
                lakeList[index1] = new EmptyLake(pozx, pozy, height, width);
            else
                lakeList[index1] = new FullLake(pozx, pozy, height, width);
        }
        return 1;
    }

    public void GenerateMap() {

    }

    private boolean IsGood(int pozx, int pozy, int width, int height, int fulllake, int index1) {
        if (CheckOtherLakes(pozx, pozy, width, height, fulllake, index1)) return false;
        return !Collision.CheckCollision(pozx, pozy, width, height,
                CharacterList.myPlayer.getPositionX(),
                CharacterList.myPlayer.getPositionY(),
                CharacterList.myPlayer.getPositionWidth(Character.PLAYER_WIDTH, Character.PLAYER_HEIGHT),
                CharacterList.myPlayer.getPositionHeight(Character.PLAYER_WIDTH, Character.PLAYER_HEIGHT));

    }

    private boolean CheckOtherLakes(int x1, int y1, int width, int height, int fulllake, int poz) {
        int index;
        int x, y, h, w;
        x1 -= 2;
        y1 -= 3;
        width += 2;
        height += 3;
        if (fulllake == 0) {
            width++;
            height++;
            y1--;
            x1--;
        }
        for (index = 0; index < poz; index++) {
            x = lakeList[index].getX() - 2;
            y = lakeList[index].getY() - 3;
            h = lakeList[index].getHeight() + 2;
            w = lakeList[index].getWidth() + 3;
            if (lakeList[index].isFullLake()) {
                h++;
                w++;
                x--;
                y--;
            }
            if (Collision.CheckCollision(x1, y1, width, height, x, y, w, h)) return true;
        }
        return false;
    }

    public boolean needToDrawWater(int x, int y) {
        int index;
        for (index = 0; index < numberOfLakes; index++) {
            if (lakeList[index].needToDraw(x, y))
                return true;
        }
        return false;
    }

    public int getGameBorder(int i) {
        return gameBorder[i % 4];
    }

    public int getNumberOfExits() {
        return numberOfExits;
    }

    public void setNumberOfExits(int numberOfExits) {
        this.numberOfExits = numberOfExits;
    }

    public int getExitPozY(int index) {
        return exit[index].getY();
    }

    public int getExitPozX() {
        return Exit.getX();
    }

    public void Update(boolean drawExit) {
        if (drawExit) {
            if (!displayExit) {
                displayExit = true;
                DisplayExit();
            }
        }
    }

    private void DisplayExit() {
        int max = gameBorder[2] / Tile.TILE_HEIGHT - 1;
        int min = gameBorder[0] / Tile.TILE_HEIGHT;
        int index;
        for (index = 0; index < numberOfExits; index++)
            exit[index].Generate(this, min, max);
    }
}
