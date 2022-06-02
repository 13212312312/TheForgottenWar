package theForgottenWar.system.combat;

import static java.lang.Math.abs;

public class Collision {
    public static boolean CheckCollision(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        if (CheckColl(x1, y1, width1, height1, x2, y2, width2, height2)) return true;
        return CheckColl(x2, y2, width2, height2, x1, y1, width1, height1);

    }

    private static boolean CheckColl(int x1, int y1, int width1, int height1, int x2, int y2, int width2, int height2) {
        if (x1 <= x2 && x2 <= x1 + width1) {
            if (y1 <= y2 && y2 <= y1 + height1)
                return true;
        }
        if (x1 <= x2 && x2 <= x1 + width1) {
            if (y1 <= y2 + height2 && y2 + height2 <= y1 + height1)
                return true;
        }
        if (x1 <= x2 + width2 && x2 + width2 <= x1 + width1) {
            if (y1 <= y2 && y2 <= y1 + height1)
                return true;
        }
        if (x1 <= x2 + width2 && x2 + width2 <= x1 + width1) {
            if (y1 <= y2 + height2 && y2 + height2 <= y1 + height1)
                return true;
        }
        if ((x2 <= x1 && x1 <= x2 + width2) && (x2 <= x1 + width1 && x1 + width1 <= x2 + width2)) {
            return (y1 <= y2 && y2 <= y1 + height1) && (y1 <= y2 + height2 && y2 + height2 <= y1 + height1);
        }
        return false;
    }

    public static boolean inRange(int Circlex, int Circley, int Circler, int Rectx, int Recty, int Rectheight, int Rectwidth) {
        int circleDistancex = abs(Circlex - Rectx);
        int circleDistancey = abs(Circley - Recty);

        if (circleDistancex > (Rectwidth / 2 + Circler)) {
            return false;
        }
        if (circleDistancey > (Rectheight / 2 + Circler)) {
            return false;
        }

        if (circleDistancex <= (Rectwidth / 2)) {
            return true;
        }
        if (circleDistancey <= (Rectheight / 2)) {
            return true;
        }

        int cornerDistance_sq = (circleDistancex - Rectwidth / 2) ^ 2 + (circleDistancey - Rectheight / 2) ^ 2;

        return (cornerDistance_sq <= (Circler * Circler));
    }
}
