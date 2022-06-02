package theForgottenWar.lists;

import theForgottenWar.characters.Character;
import theForgottenWar.characters.Iselda;
import theForgottenWar.characters.Player;
import theForgottenWar.system.combat.Collision;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterList {
    public static Character myPlayer = Player.getInstance();
    public int enemiesLeft = -1;
    public List<Character> Entities = new ArrayList<>();
    private Iselda myIselda = null;

    public CharacterList(ArrayList<Character> entities) {
        Entities = entities;
    }

    public CharacterList() {
        new CharacterList(null);
    }

    public boolean CheckIfMove(Character myCharacter) {
        for (Character character : Entities) {
            if(character.isAlive())
            if (character.calculateDistance() < myCharacter.calculateDistance() && character.getCurrentType() == myCharacter.getCurrentType()) {
                if (character.getSpeed() == myCharacter.getSpeed() && Collision.CheckCollision(
                        character.getX(),
                        character.getY(),
                        character.getCharacterWidth(),
                        character.getCharacterHeight(),
                        myCharacter.getX(),
                        myCharacter.getY(),
                        myCharacter.getCharacterWidth(),
                        myCharacter.getCharacterHeight()
                )) return false;
            }
        }
        return true;
    }

    public void resetPoz() {
        myPlayer.resetPoz();
        for (Character myCharacter : Entities) {
            myCharacter.resetPoz();
        }
    }

    public void AddEntity(Character o) {
        Entities.add(o);
    }

    public Character GetCharacter(int poz) {
        return Entities.get(poz);
    }

    public void Draw(Graphics g) {
        int index;
        Collections.sort(Entities);
        boolean drawPlayer = false;
        for (index = 0; index < Entities.size(); index++) {
            {
                drawPlayer = true;
                if (myPlayer.getY() < GetCharacter(index).getY() && GetCharacter(index).isAlive()) {
                    myPlayer.Draw(g);
                    GetCharacter(index).Draw(g);
                } else {
                    if (GetCharacter(index).isAlive()) {
                        GetCharacter(index).Draw(g);
                    }
                    myPlayer.Draw(g);
                }
            }
        }
        if(!drawPlayer)
        {
            myPlayer.Draw(g);
        }
        if(myIselda != null)
        {
            myIselda.Draw(g);
        }
    }

    public void Update(int frame) {
        int index;
        myPlayer.Update(frame);
        enemiesLeft = 0;
        for (index = 0; index < Entities.size(); index++) {
            if (GetCharacter(index).isAlive()) {
                enemiesLeft++;
                GetCharacter(index).Update(frame);
            }
        }
        if(myIselda != null)
        {
            myIselda.Update(frame);
        }
    }

    public void AI(int enemyFreezeTime) {
        int index;
        enemiesLeft = 0;
        for (index = 0; index < Entities.size(); index++) {
            if (GetCharacter(index).isAlive()) {
                enemiesLeft++;
                GetCharacter(index).AI(enemyFreezeTime);
            }
        }
    }

    public int size() {
        return Entities.size();
    }

    public int getEnemiesLeft() {
        return enemiesLeft;
    }

    public void setEnemiesLeft(int enemiesLeft) {
        this.enemiesLeft = enemiesLeft;
    }

    public Iselda getMyIselda() {
        return myIselda;
    }

    public void setMyIselda(Iselda myIselda) {
        this.myIselda = myIselda;
    }
}
