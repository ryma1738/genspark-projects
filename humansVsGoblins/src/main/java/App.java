import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import javax.management.monitor.GaugeMonitorMBean;


public class App {
    public static void main( String[] args ) throws Exception {
        //initial Vars
        Land map = new Land();
        Human player = new Human(21);
        map.player = player;
        Scanner input = new Scanner(System.in);
        ArrayList<int[]> gobsCords = map.addGoblins(10);
        ArrayList<int[]> treasureCords = map.addTressure(6);
        for (int i = 0; i < gobsCords.size(); i++) {
            map.allGoblins.add(new Goblin(2, gobsCords.get(i)));
        }
        for (int i = 0; i < treasureCords.size(); i++) {
            map.allTreasures.add(new Treasure(treasureCords.get(i)));
        }
        
        System.out.println("Welcome to Humans vs Goblins!");
        int round = 1;
        while (true) { // game loop
            HashMap<String, Object> sorroundings;
            System.out.println(map.toString());
            sorroundings = map.checkSorroundings(player.cords);
            if (sorroundings.size() > 0) { //their is something near the player
                Set<String> keys = sorroundings.keySet();
                ArrayList<String> directions = new ArrayList<String>();
                for (String key : keys) directions.add(key);
                System.out.println(directions.toString());
                if (sorroundings.size() > 1) {
                    while (true) {
                        String directs = "";
                        for (String key : directions) directs += "(" + key + ") - " + sorroundings.get(key).getClass().getSimpleName() + "\n";
                        System.out.println(
                            "You have encountered multiple entities! Choose which one you will interact with first.\n" + directs
                        );
                        String encounter = input.next().toUpperCase();
                        //TODO
                    }

                } else {
                    if (sorroundings.get(directions.get(0)).equals(Goblin.class)) {
                        if (round == 1) System.out.println("You got unlucky and spawned next to a Goblin! Let the fight begin!");
                        player.interactWith(player, (Goblin) sorroundings.get(directions.get(0)), input);
                        map.removeEntity((Goblin) sorroundings.get(directions.get(0)));
                    } else {
                        if (round == 1) System.out.println("You got lucky and spawned next to a Treasure chest!");
                        player.interactWith(player, (Treasure) sorroundings.get(directions.get(0)), input);
                        map.removeEntity((Treasure) sorroundings.get(directions.get(0)));
                    }
                }
            }
            while (true) { // true while trying to get valid user Input
                System.out.println("Which direction would you like to move? (N, S, E, W)");
                String direction = input.next().toUpperCase();
                if (direction.equals("N") || direction.equals("S") || direction.equals("E") || direction.equals("W")) {
                    int[] newPosition = player.moveEntity(direction, player.cords);
                    System.out.println(!(newPosition[0] == player.cords[0] && newPosition[1] == player.cords[1]));
                    if (!(newPosition[0] == player.cords[0] && newPosition[1] == player.cords[1])) {
                        boolean successfulMove = map.moveEntityIcon(player.cords, newPosition, player);
                        System.out.println(successfulMove);
                        if (successfulMove) {
                           player.cords = newPosition;
                           break; 
                        } else {
                            System.out.println(
                                "You are unable to move that direction due to an obstacle in your path, Please chose a new direction!"
                            ); 
                        }
                    } else {
                        System.out.println(
                            "You are unable to move that direction due to an obstacle in your path, Please chose a new direction!"
                        );
                    }
                } else {
                    System.out.println("You must enter a valid direction!");
                }
            }
            for (Goblin gob : map.allGoblins) {
                String direction = gob.trackPlayer(player);
                if (!direction.equals(null)) {
                    int[] newPosition = gob.moveEntity(direction, gob.cords);
                    if (!newPosition.equals(gob.cords)) {
                        boolean worked = map.moveEntityIcon(gob.cords, newPosition, gob);
                        if (worked) {
                            gob.cords = newPosition;
                        } 
                    }
                } 
            }
            
        }
    }
}
