package theForgottenWar.system.combat.observers;

import theForgottenWar.characters.Character;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.lists.LevelList;
import theForgottenWar.system.combat.PowerUpSystem;

public class DrakeSystem implements Observer {
    private final Character character;

    public int invulnerabilityFrames;
    public int InvulnerabilityFramesValue = 60;
    public int attackFramesValues = 90;
    public int standardattack;
    public int standardHealth;

    public DrakeSystem(Character character, int levelID) {

        this.character = character;
        standardattack = this.character.getAttack();
        standardHealth = this.character.getHealth();
        character.setAttack((int) (standardattack * (Math.pow(2,levelID))));
        character.setHealth((int) (standardHealth * (Math.pow(2,levelID))));
        attackFramesValues += 30 * PowerUpSystem.getAttackDuration();
    }

    @Override
    public void Update() {
        invulnerabilityFrames = Math.max(0, invulnerabilityFrames - 1);
        ChangeType();
    }

    private void ChangeType() {
        if (invulnerabilityFrames != 0) {
            if (invulnerabilityFrames / 5 % 2 == 0) {
                character.setType(4);
            } else {
                character.setType(character.getCurrentType());
            }
        } else {
            character.setType(character.getCurrentType());
        }
    }

    @Override
    public void receiveDamage(int value) {
        if (invulnerabilityFrames == 0) {
            PlayerSystem.currentEnemies = Math.max(PlayerSystem.currentEnemies-1,0);
            character.setHealth(character.getHealth() - value);
            if (character.getHealth() <= 0) {
                character.setAlive(false);
                CharacterList.myPlayer.addMoney((int) (20 * Math.pow(2,LevelList.levelID)));
            }
            invulnerabilityFrames = InvulnerabilityFramesValue;
        }
    }

    @Override
    public int getInvulnerabiltityFrames() {
        return invulnerabilityFrames;
    }
}
