package Entities;

/**
 * Van Elias De Hondt
 * 24/11/2022
 */
public class Room {

    private static int width = 128;
    private static int height = 20;
    private Player player;
    private char[][] floorPlan;
    private Monster[] monsters;

    public Room(Player player, Monster[] monsters) {
        this.floorPlan = createFloorPlan();
        this.player = player;
        this.player.setRoom(Room.this);
        this.monsters = monsters;
        for (Monster monster : this.monsters) {
            monster.setRoom(Room.this);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static void setWidth(int width) {
        Room.width = width;
    }

    public static void setHeight(int height) {
        Room.height = height;
    }

    private char[][] createFloorPlan() {
        floorPlan = new char[height][width];
        for (int i = 0; i < floorPlan.length; i++) {
            for (int f = 0; f < floorPlan[i].length; f++) {
                if (f == 0 || f == floorPlan[i].length - 1) {
                    floorPlan[i][f] = '|';
                } else if (i == 0 || i == floorPlan.length - 1) {
                    floorPlan[i][f] = '-';
                } else {
                    floorPlan[i][f] = ' ';
                }
            }
        }
        return floorPlan;
    }

    //    public void draw() {
    //        for (int i = 0; i < floorPlan.length; i++) {
    //            for(int f = 0; f < floorPlan[i].length; f++) {
    //                if( i == player.getY() - 1 && f == player.getX()){
    //                    System.out.print(player);
    //                } else {
    //                    for (Monster monster : monsters) {
    //                        if (i == monster.getY() - 1 && f == monster.getX()) {
    //                            System.out.print(monster);
    //                            f++;
    //                        }
    //                    }
    //                    System.out.print(floorPlan[i][f]);
    //                    if( f == floorPlan[i].length - 1){
    //                        System.out.println();
    //                    }
    //
    //                }
    //            }
    //        }
    //    }

    public void draw() {
        for (int i = 0; i < floorPlan.length; i++) {
            for (int f = 0; f < floorPlan[i].length; f++) {
                if (floorPlan[i][f] == player.toString().charAt(0)) {
                    floorPlan[i][f] = ' ';
                }
                for (Monster monster : monsters) {
                    if (floorPlan[i][f] == monster.toString().charAt(0)) {
                        floorPlan[i][f] = ' ';
                    }
                }
                floorPlan[player.getY() - 1][player.getX()] = player.toString().charAt(0);
                for (Monster monster : monsters) {
                    floorPlan[monster.getY() - 1][monster.getX()] = monster.toString().charAt(0);
                }
                System.out.print(floorPlan[i][f]);
                if (f == floorPlan[i].length - 1) {
                    System.out.println();
                }
            }
        }
    }


    public boolean isFinished() {
        return player.isDeath();
    }

    public boolean isFree(int x, int y) {
        boolean isFree = false;
        if(floorPlan[y][x] == ' ' && (x != player.getX() && y != player.getY() - 1)){
            for(Monster monster : monsters){
                if(monster.getX() == x && monster.getY() -1 == y){
                    isFree = false;
                    break;
                } else {
                    isFree = true;
                }
            }
        } return isFree;
        //return floorPlan[y][x] == ' ' && (x != player.getX() && y != player.getY() - 1);
    }

    public void update() {
        this.player.move();
        for (Monster monster : monsters) {
            monster.move();
            monster.attackPlayer();
        }
        if (isFinished()) {
            System.out.println("I'm death!\n" +
                    "Aaarrgh....");
        }
        draw();
    }

}
