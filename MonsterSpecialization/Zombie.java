package MonsterSpecialization;

import Entities.Monster;
import Entities.Player;

/**
 * Author Elias De Hondt
 * 24/11/2022
 */
public class Zombie extends Monster {

    private ZombieStatus status;

    public Zombie(Player player, int x, int y){
        super(player,x,y);
        this.status = ZombieStatus.DEATH;
    }

    @Override
    public void move() {
        if(status == ZombieStatus.DEATH){
            if(random.nextInt(1,11) == 1){
                status = ZombieStatus.ALIVE;
                super.move();
            }
        } else if(status == ZombieStatus.ALIVE){
            if(random.nextInt(1,11) < 3){
                status = ZombieStatus.DEATH;
            } else {
                super.move();
            }
        }
    }

    @Override
    public void attackPlayer() {
        if(status == ZombieStatus.ALIVE) {
            super.attackPlayer();
        }
    }

    @Override
    public String toString() {
        if(status == ZombieStatus.DEATH){
            return "+";
        } else {
            return "Z";
        }
    }
}
