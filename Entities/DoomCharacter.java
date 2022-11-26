package Entities;

import java.util.Random;

/**
 * Van Elias De Hondt
 * 24/11/2022
 */
public class DoomCharacter {
    protected int x;
    protected int y;
    protected Random random = new Random();
    protected Room room;

    public DoomCharacter(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setRoom(Room room){
        this.room = room;
    }

    protected void move(){
        int posX = random.nextInt(-1, 2);
        int posY = random.nextInt(-1, 2);
        if(this.room.isFree(this.x + posX, this.y + posY -1)){
            this.x += posX;
            this.y += posY;
        }
    }

}
