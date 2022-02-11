
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

public class LandTest {
    private Land map = new Land();
    
    @Test
    public void addAndRemoveGoblins() {
        ArrayList<int[]> allGobsCords = map.addGoblins(10);
        for (int i = 0; i < allGobsCords.size(); i++) {
            map.allGoblins.add(new Goblin(2, allGobsCords.get(i)));
        }
        for (int i = 0; i < map.allGoblins.size(); i++) {
            assertEquals("G", map.map.get(map.allGoblins.get(i).cords[0])[map.allGoblins.get(i).cords[1]]);
        }
        //test remove method
        int pastSize = map.allGoblins.size() -1;
        map.removeEntity(map.allGoblins.get(0));
        assertEquals(pastSize, map.allGoblins.size());
    }
    
    @Test
    public void addAndRemoveTressure() {
        ArrayList<int[]> allTreasureCords = map.addTreasure(7);
        for (int i = 0; i < allTreasureCords.size(); i++) {
            map.allTreasures.add(new Treasure(allTreasureCords.get(i)));
        }
        for (int i = 0; i < map.allTreasures.size(); i++) {
            assertEquals("T", map.map.get(map.allTreasures.get(i).cords[0])[map.allTreasures.get(i).cords[1]]);
        }
        // test remove method
        int pastSize = map.allTreasures.size() -1;
        map.removeEntity(map.allTreasures.get(0));
        assertEquals(pastSize, map.allTreasures.size());
    }

    @Test
    public void moveEntityAndCheckSorroundings() throws Exception {
        //this test test the moveEntityIcon and checkSorroundings
        for (int i = 0; i < 10000; i++) {
            //set up method
            Land map2 = new Land();
            ArrayList<int[]> allGobsCords = map2.addGoblins(10);
            ArrayList<int[]> allTreasureCords = map2.addTreasure(7);
            //test moveEntityIcon method
            for (int j = 0; j < allTreasureCords.size(); j++) 
                map2.allTreasures.add(new Treasure(allTreasureCords.get(j)));
            for (int j = 0; j < allGobsCords.size(); j++) {
                boolean empty;
                map2.allGoblins.add(new Goblin(2, allGobsCords.get(j)));
                int[] newPos = new int[]{allGobsCords.get(j)[0], allGobsCords.get(j)[1] +1};
                if (map2.map.get(newPos[0])[newPos[1]].equals("*")) empty = true;
                else empty = false;
                assertEquals(empty, map2.moveEntityIcon(allGobsCords.get(j), newPos, map2.allGoblins.get(j)));
            }
            //test checkSorroundings method
            int[] currentPos = new int[] { 12, 15 };
            HashMap<String, Object> sorroundings = map2.checkSorroundings(currentPos);
            ArrayList<String> keys = new ArrayList<String>(sorroundings.keySet());
            ArrayList<String> directs = new ArrayList<String>();
            directs.add("N");
            directs.add("S");
            directs.add("W");
            directs.add("E");
            for (int j = 0; j < sorroundings.size(); j++) {
                assertTrue(directs.contains(keys.get(j)));
                assertTrue(sorroundings.get(keys.get(0)) == Goblin.class || sorroundings.get(keys.get(0)) == Treasure.class);
            }
        }
    }

    private void assertTrue(boolean contains) {
    }
}
