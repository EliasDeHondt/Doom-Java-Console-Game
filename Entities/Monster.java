package Entities;

/**
 * Van Elias De Hondt
 * 24/11/2022
 */
public class Monster extends DoomCharacter{
    public static final int STRENGTH = 1;
    private Player player;

    public Monster(Player player,int x,int y){
        super(x,y);
        this.player = player;
    }

    protected Player getPlayer() {
        return player;
    }

    public void attackPlayer(){
        if(player.getX() - getX() <= 1 && player.getX() - getX() >= -1 && player.getY() - getY() <= 1 && player.getY() - getY() >= -1){
            player.attack(STRENGTH);
        }
    }

    @Override
    public String toString() {
        return "M";
    }
}
