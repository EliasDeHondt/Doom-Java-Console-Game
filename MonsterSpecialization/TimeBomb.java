package MonsterSpecialization;

import Entities.Monster;
import Entities.Player;

/**
 * Van Elias De Hondt
 * 24/11/2022
 */
public class TimeBomb extends Monster {
    private static final double BOMB_STRENGTH = 3;
    private int startTime;
    private int timeLeft;

    public TimeBomb(Player player, int x, int y){
        super(player,x,y);
        this.startTime = random.nextInt(5,11);
        this.timeLeft = startTime;
    }

    @Override
    public void move() {
        this.timeLeft--;
    }

    @Override
    public void attackPlayer() {
        if(timeLeft == 0){
            timeLeft = startTime;
            if(super.getPlayer().getX() - getX() <= 5 && super.getPlayer().getX() - getX() >= -5 && super.getPlayer().getY() - getY() <= 5 && getPlayer().getY() - getY() >= -5){
                super.getPlayer().attack(BOMB_STRENGTH);
            }

        }
    }

    @Override
    public String toString() {
        return String.valueOf(timeLeft);
    }
}
