package theForgottenWar.system.manager;

import theForgottenWar.data.SaveFile;
import theForgottenWar.lists.LevelList;
import theForgottenWar.system.Database;
import theForgottenWar.system.game.Game;

import java.sql.*;

public class SaveManager {
    public static void save() {
        SaveFile x = new SaveFile(Game.seed, LevelList.currentLevel);
        x.CopyFromPowerUpSystem();
        create(x);
    }
    public static void create(SaveFile x) {
        Connection con = Database.getConnection();
        try (PreparedStatement pstmt = con.prepareStatement(
                "insert into Save (id," +
                        "seed," +
                        "current_level," +
                        "HealthLevel," +
                        "DamageLevel," +
                        "CooldownLevel," +
                        "SpeedLevel," +
                        "DurationLevel," +
                        "InvulnerabilityLevel," +
                        "SprintLevel," +
                        "BonusHealth," +
                        "BonusDamage," +
                        "BonusCooldown," +
                        "BonusSpeed," +
                        "BonusDuration," +
                        "BonusInvulnerability," +
                        "BonusSprint," +
                        "currentHealth," +
                        "money) values (1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
            pstmt.setLong(1, x.getSeed());
            pstmt.setInt(2, x.getCurrentLevel());

            pstmt.setInt(3, x.getHealthLevel());
            pstmt.setInt(4, x.getDamageLevel());
            pstmt.setInt(5, x.getCooldownLevel());
            pstmt.setInt(6, x.getSpeedLevel());
            pstmt.setInt(7, x.getDurationLevel());
            pstmt.setInt(8, x.getInvulnerabilityLevel());
            pstmt.setInt(9, x.getSprintLevel());

            pstmt.setInt(10, x.getBonusHealth());
            pstmt.setInt(11, x.getBonusDamage());
            pstmt.setInt(12, x.getBonusCooldown());
            pstmt.setInt(13, x.getBonusSpeed());
            pstmt.setInt(14, x.getBonusDuration());
            pstmt.setInt(15, x.getBonusInvulnerability());
            pstmt.setInt(16, x.getBonusSprint());

            pstmt.setInt(17,x.getCurrentHealth());
            pstmt.setInt(18,x.getMoney());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            Database.closeConnection();
        }
    }
    public static SaveFile findById(int id) {
        Connection con = Database.getConnection();
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select seed," +
                             "current_level," +
                             "HealthLevel," +
                             "DamageLevel," +
                             "CooldownLevel," +
                             "SpeedLevel," +
                             "DurationLevel," +
                             "InvulnerabilityLevel," +
                             "SprintLevel," +
                             "BonusHealth," +
                             "BonusDamage," +
                             "BonusCooldown," +
                             "BonusSpeed," +
                             "BonusDuration," +
                             "BonusInvulnerability," +
                             "BonusSprint," +
                             "currentHealth," +
                             "money from Save where ID='" + id + "'")) {
            SaveFile x;
            if (rs.next()) {
                x = new SaveFile(rs.getLong(1),rs.getInt(2));
                x.setId(id);
                x.setHealthLevel(rs.getInt(3));
                x.setDamageLevel(rs.getInt(4));
                x.setCooldownLevel(rs.getInt(5));
                x.setSpeedLevel(rs.getInt(6));
                x.setDurationLevel(rs.getInt(7));
                x.setInvulnerabilityLevel(rs.getInt(8));
                x.setSprintLevel(rs.getInt(9));

                x.setBonusHealth(rs.getInt(10));
                x.setBonusDamage(rs.getInt(11));
                x.setBonusCooldown(rs.getInt(12));
                x.setBonusSpeed(rs.getInt(13));
                x.setBonusDuration(rs.getInt(14));
                x.setBonusInvulnerability(rs.getInt(15));
                x.setBonusSprint(rs.getInt(16));

                x.setCurrentHealth(rs.getInt(17));
                x.setMoney(rs.getInt(18));
            } else {
                x = null;
            }
            return x;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally
        {
            Database.closeConnection();
        }
        return null;
    }
}
