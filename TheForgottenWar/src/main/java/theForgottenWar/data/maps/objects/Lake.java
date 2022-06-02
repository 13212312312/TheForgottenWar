package theForgottenWar.data.maps.objects;

import theForgottenWar.data.maps.types.Map;

public class Lake {
    private int x;
    private int y;
    private int height;
    private int width;

    public Lake(int x, int y, int height, int width) {
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isFullLake() {
        return false;
    }

    public boolean needToDraw(int pozx, int pozy) {
        if (isFullLake()) {
            if (x - 1 <= pozx && pozx <= x + width) {
                return y - 1 <= pozy && pozy <= y + height;
            }
        }
        return false;
    }

    public void Generate(Map map) {

    }
}
