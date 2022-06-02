package theForgottenWar.system.combat.observers;

import theForgottenWar.characters.Character;
import theForgottenWar.system.combat.PowerUpSystem;
import theForgottenWar.system.Listener.KeyEventManager;

public class PlayerSystem implements Observer {
    private final Character character;
    public static int cooldown = 0;
    public static int cooldownFramesValues = 90;
    public static int initialCooldownFramesValues = 90;

    public static int invulnerabilityFrames = 0;
    public static int InvulnerabilityFramesValue = 60;
    public static int initialInvulnerabilityFramesValues = 60;

    public boolean isAttacking = false;

    public static int attackFrames = 0;
    public static int attackFramesValues = 120;
    public static int initialAttackFramesValues = 120;

    public static int sprintFrames = 120;
    public static int sprintFramesValues = 120;
    public static int initialSprintFramesValues = 120;

    public static int currentEnemies = 0;
    public static int totalEnemies = 4;

    public PlayerSystem(Character character) {
        this.character = character;
        this.character.setHealth(PowerUpSystem.getHealth());
    }

    @Override
    public void Update() {
        UpdatePowerUps();
        cooldown = Math.max(0, cooldown - 1);
        invulnerabilityFrames = Math.max(0, invulnerabilityFrames - 1);
        if(currentEnemies == 0)
        {
            attackFrames = 0;
        }
        attackFrames = Math.max(0, attackFrames - 1);
        CheckAttack();
        ChangeType();
        CheckSprint();
    }

    private void UpdatePowerUps() {
        attackFramesValues = initialAttackFramesValues + PowerUpSystem.getAttackDuration() * 10;
        sprintFramesValues = initialSprintFramesValues + PowerUpSystem.getSprint() * 30;
        InvulnerabilityFramesValue = initialInvulnerabilityFramesValues + PowerUpSystem.getInvulnerability() * 15;
        cooldownFramesValues = Math.max(initialCooldownFramesValues - PowerUpSystem.getCooldown() * 10, 0);
    }

    private void CheckSprint() {
        character.setSpeed(PowerUpSystem.getSpeed());
        if (KeyEventManager.sprint) {
            if (sprintFrames != 0) {
                sprintFrames = Math.max(sprintFrames - 1, 0);
                character.setSpeed(character.getSpeed() * 2);
            }
        } else {
            sprintFrames = Math.min(sprintFrames + 1, sprintFramesValues);
        }
    }

    private void ChangeType() {
        if (isAttacking) {
            character.setType(1);
        } else if (invulnerabilityFrames != 0) {
            character.setType(2);
        } else {
            character.setType(0);
        }
    }

    @Override
    public void receiveDamage(int receivedDamage) {
        if (invulnerabilityFrames == 0) {
            character.setHealth(character.getHealth() - receivedDamage);
            invulnerabilityFrames = InvulnerabilityFramesValue;
        }
    }

    @Override
    public int getInvulnerabiltityFrames() {
        return invulnerabilityFrames;
    }

    public void Attack() {
        character.setAttack(PowerUpSystem.getDamage());
        if (cooldown == 0 && !isAttacking && KeyEventManager.attack) {
            isAttacking = true;
            currentEnemies = totalEnemies;
            attackFrames = attackFramesValues;
        }
    }

    public void CheckAttack() {
        Attack();
        if (attackFrames == 0 && isAttacking) {
            cooldown = cooldownFramesValues;
            currentEnemies = 0;
            isAttacking = false;
        }
    }
}
