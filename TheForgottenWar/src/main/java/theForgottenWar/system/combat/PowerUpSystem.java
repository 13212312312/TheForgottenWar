package theForgottenWar.system.combat;

import theForgottenWar.lists.CharacterList;

public class PowerUpSystem {
    private static int powerupcost = 100;

    private static int health = 3;
    private static int damage = 1;
    private static int speed = 3;

    private static int HealthLevel = 1;
    private static int DamageLevel = 1;
    private static int CooldownLevel = 1;
    private static int SpeedLevel = 1;
    private static int DurationLevel = 1;
    private static int InvulnerabilityLevel = 1;
    private static int SprintLevel = 1;

    private static int BonusHealth = 0;
    private static int BonusDamage = 0;
    private static int BonusCooldown = 0;
    private static int BonusSpeed = 0;
    private static int BonusDuration = 0;
    private static int BonusInvulnerability = 0;
    private static int BonusSprint = 0;

    private static int MaxHealthLevel = 8;
    private static int MaxDamageLevel = 8;
    private static int MaxCooldownLevel = 8;
    private static int MaxSpeedLevel = 8;
    private static int MaxDurationLevel = 8;
    private static int MaxInvulnerabilityLevel = 8;
    private static int MaxSprintLevel = 8;

    public static int getHealth() {
        return health + HealthLevel + BonusHealth - 1;
    }

    public static int getDamage() {
        return damage + DamageLevel + BonusDamage - 1;
    }

    public static int getCooldown() {
        return CooldownLevel + BonusCooldown - 1;
    }

    public static int getSpeed() {
        return speed + SpeedLevel + BonusSpeed - 1;
    }

    public static int getAttackDuration() {
        return DurationLevel + BonusDuration - 1;
    }

    public static int getInvulnerability() {
        return InvulnerabilityLevel + BonusInvulnerability - 1;
    }

    public static int getSprint() {
        return SprintLevel + BonusSprint - 1;
    }

    public static void increaseHealthLevel() {
        HealthLevel++;
        CharacterList.myPlayer.setHealth(CharacterList.myPlayer.getHealth()+1);
    }

    public static void increaseDamageLevel() {
        DamageLevel++;
    }

    public static void increaseCooldownLevel() {
        CooldownLevel++;
    }

    public static void increaseSpeedLevel() {
        SpeedLevel++;
    }

    public static void increaseDurationLevel() {
        DurationLevel++;
    }

    public static void increaseInvulnerabilityLevel() {
        InvulnerabilityLevel++;
    }

    public static void increaseSprintLevel() {
        SprintLevel++;
    }

    public static void increaseBonusHealth() {
        BonusHealth++;
    }

    public static void increaseBonusDamage() {
        BonusDamage++;
    }

    public static void increaseBonusCooldown() {
        BonusCooldown++;
    }

    public static void increaseBonusSpeed() {
        BonusSpeed++;
    }

    public static void increaseBonusDuration() {
        BonusDuration++;
    }

    public static void increaseBonusInvulnerability() {
        BonusInvulnerability++;
    }

    public static void increaseBonusSprint() {
        BonusSprint++;
    }

    public static void setHealth(int health) {
        PowerUpSystem.health = health;
    }

    public static void setDamage(int damage) {
        PowerUpSystem.damage = damage;
    }

    public static void setSpeed(int speed) {
        PowerUpSystem.speed = speed;
    }

    public static int getHealthLevel() {
        return HealthLevel;
    }

    public static void setHealthLevel(int healthLevel) {
        HealthLevel = healthLevel;
    }

    public static int getDamageLevel() {
        return DamageLevel;
    }

    public static void setDamageLevel(int damageLevel) {
        DamageLevel = damageLevel;
    }

    public static int getCooldownLevel() {
        return CooldownLevel;
    }

    public static void setCooldownLevel(int cooldownLevel) {
        CooldownLevel = cooldownLevel;
    }

    public static int getSpeedLevel() {
        return SpeedLevel;
    }

    public static void setSpeedLevel(int speedLevel) {
        SpeedLevel = speedLevel;
    }

    public static int getDurationLevel() {
        return DurationLevel;
    }

    public static void setDurationLevel(int durationLevel) {
        DurationLevel = durationLevel;
    }

    public static int getInvulnerabilityLevel() {
        return InvulnerabilityLevel;
    }

    public static void setInvulnerabilityLevel(int invulnerabilityLevel) {
        InvulnerabilityLevel = invulnerabilityLevel;
    }

    public static int getSprintLevel() {
        return SprintLevel;
    }

    public static void setSprintLevel(int sprintLevel) {
        SprintLevel = sprintLevel;
    }

    public static int getBonusHealth() {
        return BonusHealth;
    }

    public static void setBonusHealth(int bonusHealth) {
        BonusHealth = bonusHealth;
    }

    public static int getBonusDamage() {
        return BonusDamage;
    }

    public static void setBonusDamage(int bonusDamage) {
        BonusDamage = bonusDamage;
    }

    public static int getBonusCooldown() {
        return BonusCooldown;
    }

    public static void setBonusCooldown(int bonusCooldown) {
        BonusCooldown = bonusCooldown;
    }

    public static int getBonusSpeed() {
        return BonusSpeed;
    }

    public static void setBonusSpeed(int bonusSpeed) {
        BonusSpeed = bonusSpeed;
    }

    public static int getBonusDuration() {
        return BonusDuration;
    }

    public static void setBonusDuration(int bonusDuration) {
        BonusDuration = bonusDuration;
    }

    public static int getBonusInvulnerability() {
        return BonusInvulnerability;
    }

    public static void setBonusInvulnerability(int bonusInvulnerability) {
        BonusInvulnerability = bonusInvulnerability;
    }

    public static int getBonusSprint() {
        return BonusSprint;
    }

    public static void setBonusSprint(int bonusSprint) {
        BonusSprint = bonusSprint;
    }

    public static int getMaxHealthLevel() {
        return MaxHealthLevel;
    }

    public static void setMaxHealthLevel(int maxHealthLevel) {
        MaxHealthLevel = maxHealthLevel;
    }

    public static int getMaxDamageLevel() {
        return MaxDamageLevel;
    }

    public static void setMaxDamageLevel(int maxDamageLevel) {
        MaxDamageLevel = maxDamageLevel;
    }

    public static int getMaxCooldownLevel() {
        return MaxCooldownLevel;
    }

    public static void setMaxCooldownLevel(int maxCooldownLevel) {
        MaxCooldownLevel = maxCooldownLevel;
    }

    public static int getMaxSpeedLevel() {
        return MaxSpeedLevel;
    }

    public static void setMaxSpeedLevel(int maxSpeedLevel) {
        MaxSpeedLevel = maxSpeedLevel;
    }

    public static int getMaxDurationLevel() {
        return MaxDurationLevel;
    }

    public static void setMaxDurationLevel(int maxDurationLevel) {
        MaxDurationLevel = maxDurationLevel;
    }

    public static int getMaxInvulnerabilityLevel() {
        return MaxInvulnerabilityLevel;
    }

    public static void setMaxInvulnerabilityLevel(int maxInvulnerabilityLevel) {
        MaxInvulnerabilityLevel = maxInvulnerabilityLevel;
    }

    public static int getMaxSprintLevel() {
        return MaxSprintLevel;
    }

    public static void setMaxSprintLevel(int maxSprintLevel) {
        MaxSprintLevel = maxSprintLevel;
    }

    public static int getPowerupcost() {
        return powerupcost;
    }

    public static void setPowerupcost(int powerupcost) {
        PowerUpSystem.powerupcost = powerupcost;
    }
}
