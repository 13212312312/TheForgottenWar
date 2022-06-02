package theForgottenWar.data.maps.objects;

import theForgottenWar.data.maps.types.Map;

public class EmptyLake extends Lake {
    public EmptyLake(int x, int y, int height, int width) {
        super(x, y, height, width);
    }

    @Override
    public boolean isFullLake() {
        return false;
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
            map.idlist[index1][y - 1] = 3;
            map.simpleidlist[index1][y - 1] = 0;

            //dreapta
            map.idlist[index1][y + heigth] = 3;
            map.simpleidlist[index1][y + heigth] = 2;

        }
        for (index1 = y; index1 < y + heigth; index1++) {
            //sus

            map.idlist[x - 1][index1] = 3;
            map.simpleidlist[x - 1][index1] = 3;

            //jos
            map.idlist[x + width][index1] = 3;
            map.simpleidlist[x + width][index1] = 1;
        }
        //corner

        map.idlist[x - 1][y - 1] = 5;
        map.simpleidlist[x - 1][y - 1] = 0;
        map.idlist[x + width][y - 1] = 5;
        map.simpleidlist[x + width][y - 1] = 1;
        map.idlist[x - 1][y + heigth] = 5;
        map.simpleidlist[x - 1][y + heigth] = 3;
        map.idlist[x + width][y + heigth] = 5;
        map.simpleidlist[x + width][y + heigth] = 2;


        for (index1 = x; index1 < x + width; index1++)
            for (index2 = y; index2 < y + heigth; index2++) {
                map.idlist[index1][index2] = 4;
            }
    }
}
