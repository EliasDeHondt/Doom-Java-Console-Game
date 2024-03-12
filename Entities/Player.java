package Entities;

/**
 * Author Elias De Hondt
 * 24/11/2022
 */
public class Player extends DoomCharacter{

    public static final double START_HEALTH = 9;
    private double health;

    public Player(int x, int y){
        super(x,y);
        this.health = START_HEALTH;
    }

    public double getHealth() {
        return health;
    }

    public void attack(double damage){
        this.health -= damage;
    }

    public boolean isDeath(){
        return this.health <= 0;
    }

    @Override
    public String toString() {
        return "P";
    }
}
