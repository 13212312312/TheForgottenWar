package theForgottenWar.system.combat.observers;

public interface Observer {

    void Update();
    void receiveDamage(int receivedDamage);
    int getInvulnerabiltityFrames();
}
