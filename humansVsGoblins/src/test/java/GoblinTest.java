
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.Before;

public class GoblinTest {
    private Land map = new Land();
    private Human player = new Human(21);

    @Before
    public void setUp() {
        ArrayList<int[]> allGobsCords = map.addGoblins(20);
        for (int i = 0; i < allGobsCords.size(); i++) {
            map.allGoblins.add(new Goblin(2, allGobsCords.get(i)));
        }
    }

    @Test
    public void trackPlayer() {
        ArrayList<String> options = new ArrayList<String>();
        options.add("N");
        options.add("S");
        options.add("E");
        options.add("W");
        Goblin gobTest = new Goblin(2, new int[] { 13, 17 });
        for (Goblin gob : map.allGoblins) {
            assertTrue(options.contains(gob.trackPlayer(player)) || gob.trackPlayer(player) == null);
        }  
        assertTrue("Was actually: " + gobTest.trackPlayer(player), gobTest.trackPlayer(player).equals("W") || gobTest.trackPlayer(player).equals("N")); 
    }
}
