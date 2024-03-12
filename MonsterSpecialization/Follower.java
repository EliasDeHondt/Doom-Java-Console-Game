package MonsterSpecialization;

import Entities.Player;

/**
 * Author Elias De Hondt
 * 24/11/2022
 */
public class Follower extends SimpleMonster {

    public Follower(Player player, int x, int y){
        super(player,x,y);
    }

    @Override
    public void move() {
        if(getX() < getPlayer().getX()){
            if(room.isFree(x + 1, y)) {
                this.x += 1;
            }
        } else if(getX() > getPlayer().getX()){
            if(room.isFree(x - 1, y)) {
                this.x -= 1;
            }
        }
        if(getY() < getPlayer().getY()){
            if(room.isFree(x, y)) {
                this.y += 1;
            }
        } else if(getY() > getPlayer().getY()){
            if(room.isFree(x, y - 2)) {
                this.y -= 1;
            }
        }
    }

    @Override
    public String toString() {
        return "F";
    }
}
