package theForgottenWar.characters.enemies;

import theForgottenWar.characters.Character;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.graphics.Assets;
import theForgottenWar.lists.LevelList;
import theForgottenWar.system.combat.Collision;
import theForgottenWar.system.combat.observers.DrakeSystem;
import theForgottenWar.system.combat.observers.Observer;
import theForgottenWar.system.combat.observers.PlayerSystem;

public class Drake extends Character {
    public int CHARACTER_WIDTH = 50;
    public int CHARACTER_HEIGHT = 70;
    private int distance = 0;

    public Drake(int x, int y, String enemy, int id, int type) {
        super(Assets.enemy, x, y, enemy, id, type);
    }

    public void CheckColision() {
        if (Collision.CheckCollision(getX(),
                getY(),
                getCharacterWidth() - Character.diff,
                getCharacterHeight() - Character.diff,
                CharacterList.myPlayer.getX(),
                CharacterList.myPlayer.getY(),
                CharacterList.myPlayer.getCharacterWidth() - Character.diff,
                CharacterList.myPlayer.getCharacterHeight() - Character.diff)) {
            if (CharacterList.myPlayer.getType() == 1) {
                receiveDamage(CharacterList.myPlayer.getAttack());
            } else {
                Observer obs = getMyObserver();
                if(obs != null) {
                    if (obs.getInvulnerabiltityFrames() != 0) {
                        CharacterList.myPlayer.receiveDamage(getAttack());
                    }
                }
            }
        }
    }

    public void AI(int enemyFreezeTime) {
        if (enemyFreezeTime == 0) {
            CheckColision();
            int aux1, aux2;
            aux1 = CharacterList.myPlayer.getX() + CharacterList.myPlayer.getCharacterWidth() / 2;
            aux2 = CharacterList.myPlayer.getY() + CharacterList.myPlayer.getCharacterHeight() / 2;
            int playerx = getX() + CHARACTER_WIDTH / 2;
            int playery = getY() + CHARACTER_HEIGHT / 2;
            if (shouldMove()) {
                if (aux1 - playerx == distance && aux2 - playery == distance) {
                    setState(0);
                } else {
                    setState(1);
                }
                if (aux2 < playery) {
                    moveUp();
                } else {
                    moveDown();
                }

                if (aux1 < playerx) {
                    moveLeft();
                } else {
                    moveRight();
                }
                if (Math.abs(aux2 - playery) > Math.abs(aux1 - playerx)) {
                    if (aux2 - playery > distance) {
                        setColumn(2);
                    } else {
                        setColumn(3);
                    }
                } else {
                    if (aux1 - playerx > distance) {
                        setColumn(1);
                    } else {
                        setColumn(0);
                    }
                }
            } else {
                setState(0);
            }
        }
    }

    private boolean shouldMove() {
        if (isAlive())
        return LevelList.myLevels[LevelList.currentLevel].getList().CheckIfMove(this);
        return false;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    @Override
    public int getCharacterWidth() {
        return switch (getType()) {
            case 0 -> Character.DRAKE_WIDTH;
            case 1 -> Character.DRAKE_FAST_WIDTH;
            case 2 -> Character.DRAKE_SLOW_WIDTH;
            case 3 -> Character.DRAKE_GHOST_WIDTH;
            default -> 0;
        };
    }

    @Override
    public int getCharacterHeight() {
        return switch (getType()) {
            case 0 -> Character.DRAKE_HEIGHT;
            case 1 -> Character.DRAKE_FAST_HEIGHT;
            case 2 -> Character.DRAKE_SLOW_HEIGHT;
            case 3 -> Character.DRAKE_GHOST_HEIGHT;
            default -> 0;
        };
    }
}