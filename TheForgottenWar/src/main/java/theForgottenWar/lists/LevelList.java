package theForgottenWar.lists;

import theForgottenWar.data.Level;
import theForgottenWar.data.maps.types.Map;

import java.awt.*;

public class LevelList {
    public static int lastLevel;
    public static int levelPerStage = 6;
    public static Level[] myLevels;
    public static int currentLevel = 0;
    public static int levelID = 0;
    public static boolean finished = false;

    public static void Initialize(int lastLevel) {
        LevelList.lastLevel = lastLevel;
        myLevels = new Level[lastLevel + 1];
        levelID = (currentLevel / levelPerStage) % 4;
        int type;
        for (int index = 0; index <= lastLevel; index++) {
            if(index%levelPerStage == 0) type = 2;
            else
            if ((index + 1) % levelPerStage == 0) type = 1;
            else type = 0;
            myLevels[index] = new Level(type);
            myLevels[index].AddPlayer();
            myLevels[index].GenerateMap();
                myLevels[index].AddEnemies(type, (index / levelPerStage % 4),index);
            if(type != 2)
                myLevels[index].AddItems();
        }
    }

    public static Map getMap() {
        return myLevels[currentLevel].getMap();
    }

    public static void Update(int frame) {
        myLevels[currentLevel].Update(frame);
    }

    public static void AI() {
        myLevels[currentLevel].AI();
    }

    public static void Draw(Graphics g) {
        myLevels[currentLevel].Draw(g);
    }

    public static void resetCharacterPoz() {
        myLevels[currentLevel].resetPoz();
    }

    public static void setCurrentLevel(int currentLevel) {
        LevelList.currentLevel = currentLevel;
        LevelList.levelID = (currentLevel / levelPerStage) % 4;
    }
}
