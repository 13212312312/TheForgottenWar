package theForgottenWar.characters;

import theForgottenWar.system.combat.observers.Observer;
import theForgottenWar.system.game.Game;
import theForgottenWar.lists.CharacterList;
import theForgottenWar.system.combat.Collision;
import theForgottenWar.system.Listener.KeyEventManager;
import theForgottenWar.tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class Character implements Comparable<Character> {
    public static int DRAKE_WIDTH = 60;
    public static int DRAKE_HEIGHT = 80;
    public static int DRAKE_FAST_WIDTH = 40;
    public static int DRAKE_FAST_HEIGHT = 50;
    public static int DRAKE_SLOW_WIDTH = 100;
    public static int DRAKE_SLOW_HEIGHT = 140;
    public static int DRAKE_GHOST_WIDTH = 60;
    public static int DRAKE_GHOST_HEIGHT = 80;
    public static int PLAYER_WIDTH = 50;
    public static int PLAYER_HEIGHT = 70;
    public static int ISELDA_WIDTH = 100;
    public static int ISELDA_HEIGHT = 140;
    public static int wndHeight;
    public static int wndWidth;
    public static int diff = 5;
    protected BufferedImage[][][] img;
    private int Initialx;
    private int Initialy;
    private int health;
    private int maxhealth;
    private int standardspeed = 10;
    private int speed = 10;
    private int state = 0;
    private int x, y;
    private int row = 0;
    private int column = 0;
    private int up = 0;
    private int type = 0;
    private int currentType;
    private int attack = 1;
    private int currentframe = 0;
    private boolean alive = true;
    private Observer myObserver;
    private String name;
    private final int id;
    private static int money = 10000;


    public Character(BufferedImage[][][] image, int x, int y, String name, int id, int type) {
        this.x = x;
        this.y = y;
        Initialx = x;
        Initialy = y;
        img = image;
        this.id = id;
        this.name = name;
        this.type = type;
        this.currentType = type;
    }

    public static void SetScreen(int height, int width) {
        wndHeight = height;
        wndWidth = width;
    }

    public void Update(int frame) {
        currentframe++;
        if (myObserver != null) {
            myObserver.Update();
        }
        if (state == 1 && currentframe % 20 == 0) {
            currentframe = 0;
            row++;
            row = row % 4;
            up = 1 - row % 2;
        }
        if (state == 0) {
            row = 0;
        }
    }

    public int getStandardspeed() {
        return standardspeed;
    }

    public void setStandardspeed(int standardspeed) {
        this.standardspeed = standardspeed;
        speed = standardspeed;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxhealth() {
        return maxhealth;
    }

    public void setMaxhealth(int maxhealth) {
        this.maxhealth = maxhealth;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPositionX() {
        return x / Tile.TILE_WIDTH;
    }

    public int getPositionY() {
        return y / Tile.TILE_HEIGHT;
    }

    public int getPositionHeight(int width, int height) {
        return (height - diff) / Tile.TILE_HEIGHT + 1;
    }

    public int getPositionWidth(int width, int height) {
        return (width - diff) / Tile.TILE_WIDTH + 1;
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

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getCharacterWidth() {
        return 0;
    }

    public int getCharacterHeight() {
        return 0;
    }

    public void Draw(Graphics g) {
        /// Desenare dala
        g.setColor(Color.WHITE);
        g.setFont(new Font("SansSerif", Font.BOLD, 20));
        String myString = this.getName();
        if(!Objects.equals(myString, "Iselda") && !Objects.equals(myString, "Player"))
        {
            myString += ":" + this.getHealth();
        }
        g.drawString(myString,x,y-20);
        g.drawImage(img[type][row][column], x, y - up * getCharacterHeight() / 30, getCharacterWidth(), getCharacterHeight(), null);
    }

    public int getInitialx() {
        return Initialx;
    }

    public void setInitialx(int initialx) {
        Initialx = initialx;
    }

    public int getInitialy() {
        return Initialy;
    }

    public void setInitialy(int initialy) {
        Initialy = initialy;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        Character.money = money;
    }

    public void SetLocation(int x, int y) {
        setX(x);
        setY(y);
        setInitialx(x);
        setInitialy(y);
    }

    public void setFrame(int row, int column) {
        setRow(row);
        setColumn(column);
    }

    public boolean Check(int aux1, int aux2) {
        int pozX, pozY;
        pozX = aux1 / Tile.TILE_WIDTH;
        pozY = aux2 / Tile.TILE_HEIGHT;
        return Tile.CheckId(Game.getMap().getTile(pozX, pozY));
    }

    public boolean CheckCollision(int x1, int y1, int width, int height) {
        int h, w;
        h = getCharacterHeight();
        w = getCharacterWidth();
        return !Collision.CheckCollision(x1, y1, width, height, x, y, w, h);
    }

    public void moveUp() {
        if ((Check(x + diff, y - speed) && Check(x + getCharacterWidth() - diff, y - speed)) || getName().equals("Ghost Enemy")) {
            y = y - speed;
        }
        column = 3;
    }

    public void moveDown() {
        if (Check(x + diff, y + getCharacterHeight() + speed) && Check(x - diff + getCharacterWidth(), y + getCharacterHeight() + speed) || getName().equals("Ghost Enemy")) {
            y = y + speed;
        }
        column = 2;
    }

    public void moveRight() {
        if (Check(x + getCharacterWidth() + speed, y + diff) && Check(x + getCharacterWidth() + speed, y + getCharacterHeight() - diff) || getName().equals("Ghost Enemy")) {
            x = x + speed;
        }
        column = 1;
    }

    public void moveLeft() {
        if (Check(x - speed, y + diff) && Check(x - speed, y + getCharacterHeight() - diff) || getName().equals("Ghost Enemy")) {
            x = x - speed;
        }
        column = 0;
    }

    @Override
    public int compareTo(Character o) {
        return Integer.compare(this.y, o.getY());
    }

    public String GetName() {
        return name;
    }

    public void AI(int enemyFreezeTime) {
    }

    public void Move(KeyEventManager keyManager) {
    }

    public void resetPoz() {
        this.x = Initialx;
        this.y = Initialy;
    }

    public int getID() {
        return this.id;
    }

    public double calculateDistance() {
        int x1 = CharacterList.myPlayer.getX() + CharacterList.myPlayer.getCharacterWidth() / 2;
        int y1 = CharacterList.myPlayer.getY() + CharacterList.myPlayer.getCharacterHeight() / 2;
        int x2 = this.getX() + this.getCharacterWidth() / 2;
        int y2 = this.getY() + this.getCharacterHeight() / 2;
        return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
    }

    public void attach(Observer playerSystem) {
        setMyObserver(playerSystem);
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Observer getMyObserver() {
        return myObserver;
    }

    public void setMyObserver(Observer myObserver) {
        this.myObserver = myObserver;
    }

    public void receiveDamage(int attack) {
        myObserver.receiveDamage(attack);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getCurrentType() {
        return currentType;
    }

    public void setCurrentType(int currentType) {
        this.currentType = currentType;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void addMoney(int i) {
        this.money += i;
    }
}
