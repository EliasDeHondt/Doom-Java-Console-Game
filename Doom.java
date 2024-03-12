import Entities.Monster;
import Entities.Player;
import Entities.Room;
import MonsterSpecialization.Follower;
import MonsterSpecialization.SimpleMonster;
import MonsterSpecialization.TimeBomb;
import MonsterSpecialization.Zombie;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Random;
import java.util.Scanner;

/**
 * Author Elias De Hondt
 * 24/11/2022
 */
public class Doom {
    private Room room;
    private Random random = new Random();
    private String currentDate;
    private LocalTime currentTime;
    private LocalTime startTime;
    private Scanner input;
    private int keuze;
    private int settingsKeuze;
    private int minMobs = 1;
    private int maxMobs = 50;
    private int speed = 500;


    public Doom() {
        input = new Scanner(System.in);
    }

    public boolean isFinished() {
        return room.isFinished();
    }

    public void start() {
        startTime = LocalTime.now();
        Player player = new Player(random.nextInt(2, Room.getWidth() - 1),random.nextInt(2, Room.getHeight() -1));
        Monster[] monsters = new Monster[random.nextInt(minMobs,maxMobs+1)];
        for (int i = 0; i < monsters.length; i++){
            switch (random.nextInt(0,4)) {
                case 0 : Array.set(monsters,i,new Follower(player, random.nextInt(2, Room.getWidth() - 1),random.nextInt(2,Room.getHeight()-1))); break;
                case 1 : Array.set(monsters,i, new SimpleMonster(player,random.nextInt(2, Room.getWidth() - 1),random.nextInt(2,Room.getHeight()-1))); break;
                case 2 : Array.set(monsters,i, new TimeBomb(player,random.nextInt(2, Room.getWidth() - 1),random.nextInt(2,Room.getHeight()-1))); break;
                case 3 : Array.set(monsters,i, new Zombie(player,random.nextInt(2, Room.getWidth() - 1),random.nextInt(2,Room.getHeight()-1))); break;
            }
        }
        this.room = new Room(player, monsters);


        while (!isFinished()) {
            try {
                currentTime = LocalTime.now();
                room.update();
                showInfo();
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void showInfo(){
        currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
        String currentTimeString = DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(currentTime);
        int maxLength = Room.getWidth();
        String healthFormat = "Health: %.0f\n";
        int length = (maxLength/2) - (healthFormat.length()/2);
        String dateFormat = "%-" + length + "s";
        String timeFormat = "%-" + length + "s";
        String format = dateFormat + " " + timeFormat + " " + healthFormat;
        System.out.format(format,currentDate,currentTimeString, room.getPlayer().getHealth() < 0 ? 0 : room.getPlayer().getHealth());
        if(isFinished()){
            Duration timeAlive = Duration.between(startTime,currentTime);
            System.out.printf("The player survived for %d seconds...", timeAlive.getSeconds());
        }
    }

    public void menu(){
       do {
           System.out.println("""
                   Welkom op deze DOOM simulatie
                   =============================
                   Maak je keuze:
                   1) settings
                   2) start
                   3) exit""");
           System.out.print("uw keuze: ");
           keuze = input.nextInt();
       } while(keuze < 0 || keuze > 3);
       if(keuze == 2){
           start();
       } else if(keuze == 3){
           System.exit(0);
       }else if(keuze == 1){
           settings();
       }
    }

    public void settings(){
        do{
            System.out.println("""
                    Settings menu
                    =============================
                    Maak je keuze: 
                    1) Size
                    2) Difficulty
                    3) Update speed
                    4) Back to menu
                    """);
            System.out.print("uw keuze: ");
            settingsKeuze = input.nextInt();
        } while(settingsKeuze < 0 || settingsKeuze > 4);
        if(settingsKeuze == 4){
            menu();
        } else if(settingsKeuze == 1){
            sizeSettings();
        } else if(settingsKeuze == 2){
            difficultySettings();
        } else if(settingsKeuze == 3){
            updateSpeedSettings();
        }
    }

    public void sizeSettings(){
            System.out.printf("""
                    Settings menu
                    =============================
                    Current height: %d
                    Current width: %d
                    """, Room.getHeight(), Room.getWidth());
        System.out.print("New height: ");
        Room.setHeight(input.nextInt());
        System.out.print("New Width: ");
        Room.setWidth(input.nextInt());
        System.out.println("returning to settings...");
        try{
            Thread.sleep(500);
            settings();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void difficultySettings(){
        System.out.printf("""
                    Settings menu
                    =============================
                    Current min of mobs: %d
                    Current max of mobs: %d
                    """, minMobs, maxMobs);
        System.out.print("New min of mobs: ");
        minMobs = input.nextInt();
        System.out.print("New max of mobs: ");
        maxMobs = input.nextInt();
        System.out.println("returning to settings...");
        try{
            Thread.sleep(500);
            settings();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void updateSpeedSettings(){
        System.out.printf("""
                    Settings menu
                    =============================
                    Current update speed: %d
                    """, speed);
        System.out.print("New update speed: ");
        speed = input.nextInt();
        System.out.println("returning to settings...");
        try{
            Thread.sleep(500);
            settings();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
