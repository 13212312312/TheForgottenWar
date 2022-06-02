package theForgottenWar.data;

import theForgottenWar.lists.CharacterList;
import theForgottenWar.system.combat.PowerUpSystem;

public class SaveFile {
    int id;
    long seed;
    int currentLevel;

    private int HealthLevel = 1;
    private int DamageLevel = 1;
    private int CooldownLevel = 1;
    private int SpeedLevel = 1;
    private int DurationLevel = 1;
    private int InvulnerabilityLevel = 1;
    private int SprintLevel = 1;

    private int BonusHealth = 0;
    private int BonusDamage = 0;
    private int BonusCooldown = 0;
    private int BonusSpeed = 0;
    private int BonusDuration = 0;
    private int BonusInvulnerability = 0;
    private int BonusSprint = 0;

    private int currentHealth = 0;

    private int money = 0;

    public SaveFile(long seed, int currentLevel) {
        this.seed = seed;
        this.currentLevel = currentLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSeed() {
        return seed;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getHealthLevel() {
        return HealthLevel;
    }

    public void setHealthLevel(int healthLevel) {
        HealthLevel = healthLevel;
    }

    public int getDamageLevel() {
        return DamageLevel;
    }

    public void setDamageLevel(int damageLevel) {
        DamageLevel = damageLevel;
    }

    public int getCooldownLevel() {
        return CooldownLevel;
    }

    public void setCooldownLevel(int cooldownLevel) {
        CooldownLevel = cooldownLevel;
    }

    public int getSpeedLevel() {
        return SpeedLevel;
    }

    public void setSpeedLevel(int speedLevel) {
        SpeedLevel = speedLevel;
    }

    public int getDurationLevel() {
        return DurationLevel;
    }

    public void setDurationLevel(int durationLevel) {
        DurationLevel = durationLevel;
    }

    public int getInvulnerabilityLevel() {
        return InvulnerabilityLevel;
    }

    public void setInvulnerabilityLevel(int invulnerabilityLevel) {
        InvulnerabilityLevel = invulnerabilityLevel;
    }

    public int getSprintLevel() {
        return SprintLevel;
    }

    public void setSprintLevel(int sprintLevel) {
        SprintLevel = sprintLevel;
    }

    public int getBonusHealth() {
        return BonusHealth;
    }

    public void setBonusHealth(int bonusHealth) {
        BonusHealth = bonusHealth;
    }

    public int getBonusDamage() {
        return BonusDamage;
    }

    public void setBonusDamage(int bonusDamage) {
        BonusDamage = bonusDamage;
    }

    public int getBonusCooldown() {
        return BonusCooldown;
    }

    public void setBonusCooldown(int bonusCooldown) {
        BonusCooldown = bonusCooldown;
    }

    public int getBonusSpeed() {
        return BonusSpeed;
    }

    public void setBonusSpeed(int bonusSpeed) {
        BonusSpeed = bonusSpeed;
    }

    public int getBonusDuration() {
        return BonusDuration;
    }

    public void setBonusDuration(int bonusDuration) {
        BonusDuration = bonusDuration;
    }

    public int getBonusInvulnerability() {
        return BonusInvulnerability;
    }

    public void setBonusInvulnerability(int bonusInvulnerability) {
        BonusInvulnerability = bonusInvulnerability;
    }

    public int getBonusSprint() {
        return BonusSprint;
    }

    public void setBonusSprint(int bonusSprint) {
        BonusSprint = bonusSprint;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void CopyLevelToPowerUpSystem()
    {
        PowerUpSystem.setHealthLevel(HealthLevel);
        PowerUpSystem.setDamageLevel(DamageLevel);
        PowerUpSystem.setCooldownLevel(CooldownLevel);
        PowerUpSystem.setSpeedLevel(SpeedLevel);
        PowerUpSystem.setDurationLevel(DurationLevel);
        PowerUpSystem.setInvulnerabilityLevel(InvulnerabilityLevel);
        PowerUpSystem.setSprintLevel(SprintLevel);
    }
    public void CopyFromPowerUpSystem()
    {
        HealthLevel = PowerUpSystem.getHealthLevel();
        DamageLevel = PowerUpSystem.getDamageLevel();
        CooldownLevel = PowerUpSystem.getCooldownLevel();
        SpeedLevel = PowerUpSystem.getSpeedLevel();
        DurationLevel = PowerUpSystem.getDurationLevel();
        InvulnerabilityLevel = PowerUpSystem.getInvulnerabilityLevel();
        SprintLevel = PowerUpSystem.getSprintLevel();

        BonusHealth = PowerUpSystem.getBonusHealth();
        BonusDamage = PowerUpSystem.getBonusDamage();
        BonusCooldown = PowerUpSystem.getBonusCooldown();
        BonusSpeed = PowerUpSystem.getBonusSpeed();
        BonusDuration = PowerUpSystem.getBonusDuration();
        BonusInvulnerability = PowerUpSystem.getBonusInvulnerability();
        BonusSprint = PowerUpSystem.getBonusSprint();

        currentHealth = CharacterList.myPlayer.getHealth();
        money = CharacterList.myPlayer.getMoney();
    }
    public void CopyToPowerUpSystem()
    {
        CopyLevelToPowerUpSystem();

        PowerUpSystem.setBonusHealth(BonusHealth);
        PowerUpSystem.setBonusDamage(BonusDamage);
        PowerUpSystem.setBonusCooldown(BonusCooldown);
        PowerUpSystem.setBonusSpeed(BonusSpeed);
        PowerUpSystem.setBonusDuration(BonusDuration);
        PowerUpSystem.setBonusInvulnerability(BonusInvulnerability);
        PowerUpSystem.setBonusSprint(BonusSprint);

        CharacterList.myPlayer.setHealth(currentHealth);
        CharacterList.myPlayer.setMoney(money);
    }
}
