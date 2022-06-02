package theForgottenWar.data.maps.objects;

import theForgottenWar.data.maps.types.Map;

public class FullLake extends Lake {
    public FullLake(int x, int y, int height, int width) {
        super(x, y, height, width);
    }

    @Override
    public boolean isFullLake() {
        return true;
    }

    @Override
    public void Generate(Map map) {
        int index1, index2;
        int x;
        int y;
        int heigth;
        int width;
        x = this.getX();
        y = this.getY();
        heigth = this.getHeight();
        width = this.getWidth();
        for (index1 = x; index1 < x + width; index1++) {
            //stanga
            map.idlist[index1][y - 1] = 8;
            map.simpleidlist[index1][y - 1] = 2;

            //dreapta
            map.idlist[index1][y + heigth] = 8;
            map.simpleidlist[index1][y + heigth] = 0;

        }
        for (index1 = y; index1 < y + heigth; index1++) {
            //sus

            map.idlist[x - 1][index1] = 8;
            map.simpleidlist[x - 1][index1] = 1;

            //jos
            map.idlist[x + width][index1] = 8;
            map.simpleidlist[x + width][index1] = 3;
        }
        //corner

        map.idlist[x - 1][y - 1] = 7;
        map.simpleidlist[x - 1][y - 1] = 2;
        map.idlist[x + width][y - 1] = 7;
        map.simpleidlist[x + width][y - 1] = 3;
        map.idlist[x - 1][y + heigth] = 7;
        map.simpleidlist[x - 1][y + heigth] = 1;
        map.idlist[x + width][y + heigth] = 7;
        map.simpleidlist[x + width][y + heigth] = 0;


        for (index1 = x; index1 < x + width; index1++)
            for (index2 = y; index2 < y + heigth; index2++) {
                map.idlist[index1][index2] = 1;
            }

    }
}
