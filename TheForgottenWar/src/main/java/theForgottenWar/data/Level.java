package theForgottenWar.data;

import theForgottenWar.characters.Character;
import theForgottenWar.characters.Iselda;
import theForgottenWar.data.maps.objects.Exit;
import theForgottenWar.data.maps.types.BossMap;
import theForgottenWar.data.maps.types.Map;
import theForgottenWar.data.maps.types.SafeMap;
import theForgottenWar.data.maps.types.StandardMap;
import theForgottenWar.graphics.Assets;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.characters.enemies.Drake;
import theForgottenWar.lists.ItemList;
import theForgottenWar.lists.LevelList;
import theForgottenWar.system.combat.observers.DrakeSystem;
import theForgottenWar.system.game.Game;
import theForgottenWar.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

import static theForgottenWar.system.game.Game.rand;

public class Level {
    private final Map myMap;
    private final CharacterList List = new CharacterList();
    private final ItemList myItemList = new ItemList();
    private int enemyFreezeTime = 120;
    private boolean items = false;

    public Level(int type) {
        if (type == 0) {
            myMap = new StandardMap();
        }
        else if(type == 2) {
            List.setMyIselda(new Iselda(6,5));
            myMap = new SafeMap();
        }
        else
            myMap = new BossMap();
    }

    public CharacterList getList() {
        return List;
    }

    public void AddPlayer() {
        CharacterList.myPlayer.SetLocation(Map.screenWidth / 2 - Character.PLAYER_WIDTH, Map.screenHeight / 2 - Character.PLAYER_HEIGHT / 2);
        CharacterList.myPlayer.setFrame(3, 3);
        CharacterList.myPlayer.setStandardspeed(4);
    }

    public void AddEnemy(int x, int y, int enemyType, int levelID) {
        x = x * Tile.TILE_WIDTH;
        y = y * Tile.TILE_HEIGHT;
        Drake myEnemy1 = new Drake(x, y, "Enemy", List.size(), enemyType);
        switch (enemyType) {
            case 0 -> {
                myEnemy1.setStandardspeed(rand.nextInt(2) + 3);
                myEnemy1.setDistance(Drake.DRAKE_WIDTH);
                myEnemy1.setHealth(2);
                myEnemy1.setAttack(1);
                myEnemy1.setName("Normal Enemy");
            }
            case 1 -> {
                myEnemy1.setStandardspeed(rand.nextInt(2) + 5);
                myEnemy1.setDistance(Drake.DRAKE_FAST_WIDTH);
                myEnemy1.setHealth(1);
                myEnemy1.setAttack(2);
                myEnemy1.setName("Fast Enemy");
            }
            case 2 -> {
                myEnemy1.setStandardspeed(rand.nextInt(2) + 1);
                myEnemy1.setDistance(Drake.DRAKE_SLOW_WIDTH);
                myEnemy1.setHealth(5);
                myEnemy1.setAttack(3);
                myEnemy1.setName("Slow Enemy");
            }
            case 3 -> {
                myEnemy1.setStandardspeed(rand.nextInt(2) + 3);
                myEnemy1.setDistance(Drake.DRAKE_GHOST_WIDTH);
                myEnemy1.setHealth(3);
                myEnemy1.setAttack(1);
                myEnemy1.setName("Ghost Enemy");
            }
        }
        myEnemy1.attach(new DrakeSystem(myEnemy1,levelID));
        List.AddEntity(myEnemy1);
    }

    public void AddEnemies(int type, int levelType, int currentLevel) {
        int minim = 0;
        if (type == 1) {
            minim = 5;
        }
        int numberOfEnemies = rand.nextInt(currentLevel % LevelList.levelPerStage + 1) + 1 + minim;
        if(type == 2)
        {
            numberOfEnemies = 0;
        }
        int x, y;
        int index;
        for (index = 0; index < numberOfEnemies; index++) {
            do {
                x = rand.nextInt(29) + 3;
                y = rand.nextInt(20) + 3;
            } while (!Tile.CheckId(myMap.getTile(x, y)) && !Tile.CheckId(myMap.getTile(x, y + 1)));
            int enemyType;
            enemyType = rand.nextInt(levelType + 1);
            AddEnemy(x, y, enemyType,levelType);
        }
    }

    public void AddItems() {
        myItemList.AddItems();
    }

    public void ReplaceItems(int type){
        myItemList.replaceItem(type);
    }
    private void simpledraw(Graphics g) {
        int index1, index2;

        for (index1 = 0; index1 <= Map.screenWidth / Tile.TILE_WIDTH; index1++) {
            for (index2 = 0; index2 <= Map.screenHeight / Tile.TILE_HEIGHT; index2++) {
                if (myMap.needToDrawWater(index1, index2)) {
                    Tile.Water[LevelList.levelID][myMap.getTileRotation(index1, index2)].Draw(g, index1 * Tile.TILE_WIDTH, index2 * Tile.TILE_HEIGHT);
                } else if (index2 == myMap.getGameBorder(1) / Tile.TILE_WIDTH || index2 == myMap.getGameBorder(1) / Tile.TILE_WIDTH - 1) {
                    Tile.Wall[LevelList.levelID][myMap.getTileRotation(index1, index2)].Draw(g, index1 * Tile.TILE_WIDTH, index2 * Tile.TILE_HEIGHT);
                } else {
                    Tile.Floor[LevelList.levelID][myMap.getTileRotation(index1, index2)].Draw(g, index1 * Tile.TILE_WIDTH, index2 * Tile.TILE_HEIGHT);
                }
            }
        }
    }

    public void DrawTile(Graphics g, int level, int id, int rotation, int pozitionx, int pozitiony) {
        switch (id) {
            case 1 -> Tile.Water[LevelList.levelID][rotation].Draw(g, pozitionx * Tile.TILE_WIDTH, pozitiony * Tile.TILE_HEIGHT);
            case 2 -> Tile.Wall[LevelList.levelID][rotation].Draw(g, pozitionx * Tile.TILE_WIDTH, pozitiony * Tile.TILE_HEIGHT);
            case 3 -> Tile.Border[LevelList.levelID][rotation].Draw(g, pozitionx * Tile.TILE_WIDTH, pozitiony * Tile.TILE_HEIGHT);
            case 4 -> Tile.Background[LevelList.levelID][rotation].Draw(g, pozitionx * Tile.TILE_WIDTH, pozitiony * Tile.TILE_HEIGHT);
            case 5 -> Tile.BorderExternCorner[LevelList.levelID][rotation].Draw(g, pozitionx * Tile.TILE_WIDTH, pozitiony * Tile.TILE_HEIGHT);
            case 6 -> Tile.BorderInternCorner[LevelList.levelID][rotation].Draw(g, pozitionx * Tile.TILE_WIDTH, pozitiony * Tile.TILE_HEIGHT);
            case 7 -> Tile.LakeBorderInternCorner[LevelList.levelID][rotation].Draw(g, pozitionx * Tile.TILE_WIDTH, pozitiony * Tile.TILE_HEIGHT);
            case 8 -> Tile.LakeBorder[LevelList.levelID][rotation].Draw(g, pozitionx * Tile.TILE_WIDTH, pozitiony * Tile.TILE_HEIGHT);
            default -> Tile.Floor[LevelList.levelID][rotation].Draw(g, pozitionx * Tile.TILE_WIDTH, pozitiony * Tile.TILE_HEIGHT);
        }
    }

    public void Draw(Graphics g) {
        simpledraw(g);
        int index1, index2;
        for (index1 = 0; index1 <= Map.screenWidth / Tile.TILE_WIDTH; index1++) {
            for (index2 = 0; index2 <= Map.screenHeight / Tile.TILE_HEIGHT; index2++) {
                DrawTile(g, 1, myMap.getTile(index1, index2), myMap.getTileRotation(index1, index2), index1, index2);
            }
        }
        myItemList.Draw(g);
        List.Draw(g);
        if(myMap.isDisplayExit()) {
            for (int index = 0; index < myMap.getNumberOfExits(); index++) {
                Exit myExit = myMap.getExit(index);
                BufferedImage img;
                switch (myExit.getNextMapType()) {
                    case 1 -> img = Assets.healthPickup[0];
                    case 2 -> img = Assets.healthUPPickup[0];
                    case 3 -> img = Assets.damagePickup[0];
                    case 4 -> img = Assets.cooldownPickup[0];
                    case 5 -> img = Assets.speedPickup[0];
                    case 6 -> img = Assets.durationPickup[0];
                    case 7 -> img = Assets.invulnerabilityPickup[0];
                    case 8 -> img = Assets.sprintPickup[0];
                    default ->
                            {
                                img = Assets.iselda[0][0][0];
                            }
                }
                int x, y;
                x = Exit.getX() * Tile.TILE_WIDTH;
                y = myExit.getY() * Tile.TILE_HEIGHT;
                if ((LevelList.currentLevel + 1) % LevelList.levelPerStage == 0) {
                    img = Assets.iselda[0][0][0];
                }
                if((LevelList.currentLevel + 1 ) == LevelList.lastLevel)
                {
                    img = Assets.winImage[0];
                }
                g.drawImage(img, x, y, Tile.TILE_WIDTH * 2, Tile.TILE_HEIGHT * 2, null);
                if(LevelList.currentLevel == LevelList.lastLevel)
                {
                    img=Assets.winImage[1];
                    g.drawImage(img, 0, 0, Map.screenWidth, Map.screenHeight, null);
                    Game.setPaused(true);
                }
            }
        }
    }

    public void Update(int frame) {
        enemyFreezeTime = Math.max(enemyFreezeTime - 1, 0);
        List.Update(frame);
        myItemList.Update(frame);
        if (List.getEnemiesLeft() == 0) {
            if (!items) {
                items = true;
                myItemList.setVisible();
            }
        }
        myMap.Update(myItemList.CheckNR() && items);
    }

    public void AI() {
        List.AI(enemyFreezeTime);
    }

    public void GenerateMap() {
        myMap.GenerateMap();
    }

    public void resetPoz() {
        List.resetPoz();
    }

    public Map getMap() {
        return myMap;
    }

    public int getEnemyFreezeTime() {
        return enemyFreezeTime;
    }

    public void setEnemyFreezeTime(int enemyFreezeTime) {
        this.enemyFreezeTime = enemyFreezeTime;
    }
}
