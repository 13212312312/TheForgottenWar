package theForgottenWar.system;

import theForgottenWar.data.Level;
import theForgottenWar.data.maps.objects.Exit;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.data.maps.types.Map;
import theForgottenWar.lists.LevelList;
import theForgottenWar.system.game.Game;
import theForgottenWar.system.game.GameWindow;
import theForgottenWar.system.manager.SaveManager;
import theForgottenWar.tiles.Tile;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class NextLevel {
    public static void Next(int type) {
        if (LevelList.currentLevel == LevelList.lastLevel) {
            System.out.println("Ai terminat! Felicitari");
            Game.wnd.getWndFrame().removeWindowListener(GameWindow.myAdapter);
            LevelList.levelID = 0;
            LevelList.finished = true;
            LevelList.currentLevel = 0;
            SaveManager.save();
        } else {
            LevelList.currentLevel++;
            LevelList.levelID = (LevelList.currentLevel / LevelList.levelPerStage) % 4;
        }
        if(type != -1)
        {
            LevelList.myLevels[LevelList.currentLevel].ReplaceItems(type);
        }
        LevelList.resetCharacterPoz();
        SaveManager.save();
    }

    public static void Verify() {
        int x = CharacterList.myPlayer.getX();
        int width = CharacterList.myPlayer.getCharacterWidth();
        int pozX = (x + width / 2) / Tile.TILE_WIDTH;
        Map currentMap = LevelList.getMap();
        int exitX = currentMap.getExitPozX();
        if (pozX == exitX) {
            Exit myExit = currentMap.findTheChosenExit(CharacterList.myPlayer.getY());
            int type;
            if(myExit == null)
            {
                type = -1;
            }
            else
            {
                type = myExit.getNextMapType();
            }
            Next(type);
        }
    }
}
