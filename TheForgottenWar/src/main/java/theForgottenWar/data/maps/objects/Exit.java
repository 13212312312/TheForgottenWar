package theForgottenWar.data.maps.objects;

import theForgottenWar.data.maps.types.Map;

public class Exit {
    private static int x;
    private int y;
    private int nextMapType;

    public Exit(int x, int y, int nextMapType) {
        Exit.x = x;
        this.y = y;
        this.nextMapType = nextMapType;
    }

    public static int getX() {
        return x;
    }

    public static void setX(int x) {
        Exit.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getNextMapType() {
        return nextMapType;
    }

    public void setNextMapType(int nextMapType) {
        this.nextMapType = nextMapType;
    }

    public void Generate(Map map, int min, int max) {
        int poz = this.y;
        map.idlist[x][poz] = 100;
        if (poz == min) {
            map.idlist[x][poz] = 2;
            map.idlist[x][poz + 1] = 0;


            map.idlist[x][poz + 2] = 5;
            map.simpleidlist[x][poz + 2] = 0;

            map.idlist[x][poz - 1] = 3;
            map.simpleidlist[x][poz - 1] = 2;
        } else if (poz == max - 1) {
            map.idlist[x][poz] = 0;
            map.idlist[x][poz + 1] = 0;

            map.idlist[x][poz - 1] = 5;
            map.simpleidlist[x][poz - 1] = 3;


            map.idlist[x][poz + 2] = 3;
            map.simpleidlist[x][poz + 2] = 0;
        } else {
            map.idlist[x][poz] = 0;
            map.idlist[x][poz + 1] = 0;

            map.idlist[x][poz + 2] = 5;
            map.simpleidlist[x][poz + 2] = 0;

            map.idlist[x][poz - 1] = 5;
            map.simpleidlist[x][poz - 1] = 3;
        }

        map.idlist[x + 1][poz] = 3;
        map.simpleidlist[x + 1][poz] = 3;
        map.idlist[x + 1][poz + 1] = 3;
        map.simpleidlist[x + 1][poz + 1] = 3;

        map.idlist[x + 1][poz + 2] = 6;
        map.simpleidlist[x + 1][poz + 2] = 0;

        map.idlist[x + 1][poz - 1] = 6;
        map.simpleidlist[x + 1][poz - 1] = 3;
    }

}
