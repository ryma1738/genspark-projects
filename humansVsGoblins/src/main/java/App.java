import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;


public class App {
    public static void main( String[] args ) throws Exception {
        //initial Vars
        Land map = new Land();
        Human player = new Human(21);
        map.player = player;
        Scanner input = new Scanner(System.in);
        ArrayList<int[]> gobsCords = map.addGoblins(10);
        ArrayList<int[]> treasureCords = map.addTreasure(7);
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
                        if (sorroundings.size() == 1) break;
                        String directs = "";
                        for (String key : directions) directs += "(" + key + ") - " + sorroundings.get(key).getClass().getSimpleName() + "\n";
                        System.out.println(
                            "You have encountered multiple entities! Choose which one you will interact with first.\n" + directs
                        );
                        String encounter = input.next().toUpperCase();
                        if (directions.contains(encounter)) {
                            if (sorroundings.get(encounter).getClass() == Goblin.class) {
                                player.interactWith(player, (Goblin) sorroundings.get(encounter), input);
                                map.removeEntity((Goblin) sorroundings.get(encounter));
                                sorroundings.remove(encounter);
                                if (player.health <= 0) {
                                    System.out.println("You have Died! Game over...");
                                    System.exit(0);
                                }
                                player.postBattle();
                            } else {
                                player.interactWith(player, (Treasure) sorroundings.get(encounter), input);
                                map.removeEntity((Treasure) sorroundings.get(encounter));
                                sorroundings.remove(encounter);
                            }
                        } else {
                            System.out.println("Invalid Input please try again!");
                        }
                    }

                } if (sorroundings.size() == 1) {
                    if (sorroundings.get(directions.get(0)).getClass() == Goblin.class) {
                        if (round == 1) System.out.println("You got unlucky and spawned next to a Goblin! Let the fight begin!");
                        player.interactWith(player, (Goblin) sorroundings.get(directions.get(0)), input);
                        map.removeEntity((Goblin) sorroundings.get(directions.get(0)));
                        System.out.println(map.toString());
                        if (player.health <= 0) {
                            System.out.println("You have Died! Game over...");
                            System.exit(0);
                        }
                        player.postBattle();
                    } else {
                        if (round == 1) System.out.println("You got lucky and spawned next to a Treasure chest!");
                        player.interactWith(player, (Treasure) sorroundings.get(directions.get(0)), input);
                        map.removeEntity((Treasure) sorroundings.get(directions.get(0)));
                        System.out.println(map.toString());
                    }
                }
            }
            if (map.allGoblins.size() == 0) {
                System.out.println("Congratulations! You killed all the goblins and won the game!");
                System.exit(0);
            }
            while (true) { // true while trying to get valid user Input
                System.out.println("Which direction would you like to move? (N, S, E, W) or (M) to manageInventory");
                String direction = input.next().toUpperCase();
                if (direction.equals("N") || direction.equals("S") || direction.equals("E") || direction.equals("W")) {
                    int[] newPosition = player.moveEntity(direction, player.cords);
                    if (!(newPosition[0] == player.cords[0] && newPosition[1] == player.cords[1])) {
                        boolean successfulMove = map.moveEntityIcon(player.cords, newPosition, player);
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
                } else if (direction.equals("M")) {
                    player.manageInventory(input);
                    break;
                } else {
                    System.out.println("You must enter a valid direction!");
                }
            }
            for (Goblin gob : map.allGoblins) {
                String direction = gob.trackPlayer(player);
                if (direction != null) {
                    int[] newPosition = gob.moveEntity(direction, gob.cords);
                    if (!newPosition.equals(gob.cords)) {
                        boolean worked = map.moveEntityIcon(gob.cords, newPosition, gob);
                        if (worked) {
                            gob.cords = newPosition;
                        }
                    }
                } 
            }
            round++;
        }
    }

    public static void timeOut(int ms) {
        //credit: https://stackoverflow.com/questions/24104313/how-do-i-make-a-delay-in-java/51920600
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
