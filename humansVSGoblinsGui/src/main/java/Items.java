import java.util.HashMap;

public class Items {
    private final HashMap<String, Integer> allWeapons = setHash("weapon");
    private final HashMap<String, Integer> allArmor = setHash("armor");
    private final HashMap<String, Integer> allConsumables = setHash("consumable");
    public String type;
    public String name;
    public int modifier;
    

    public Items(String type) { // constructor to create a random item
        randomItem(type);
    }

    public Items(String type, String name) { // constructor for specific weapon, armor piece, or consumable
        if (type.equals("weapon")) {
            if (allWeapons.containsKey(name)) {
                this.type = "weapon";
                this.name = name;
                this.modifier = allWeapons.get(name);
            } else {
                randomItem(type);
            }
        } else if (type.equals("armor")) {
            if (allArmor.containsKey(name)) {
                this.type = "armor";
                this.name = name;
                this.modifier = allArmor.get(name);
            } else {
                randomItem(type);
            }
        } else if (type.equals("consumable")) {
            if (allConsumables.containsKey(name)) {
                this.type = "consumable";
                this.name = name;
                this.modifier = allConsumables.get(name);
            } else {
                randomItem(type);
            }
        } else { // if it cant find the piece create random item instead (error handling)
            randomItem(type);
        }
    }
  
    private void randomItem(String type) { 
        // provides random Items based on type and percentage values (nice Items are harder to get).
        int selector = (int) Math.floor(Math.random() * 100);
        if (type.equals("weapon")) {
            this.type = "weapon";
            String[] name = { "Stick", "Work Hammer", "Wooden Club", "Iron Sword", "Bronze Dagger", "Axe", 
                            "Steal Sword", "Gold Plated Sword"};
            if (selector >= 0 && selector <19) {         // 20%
                this.name = name[0];      
                this.modifier = allWeapons.get(name[0]);
            } else if (selector >= 19 && selector <34) { //15%
                this.name = name[1];
                this.modifier = allWeapons.get(name[1]);
            } else if (selector >= 34 && selector < 49) { //15%
                this.name = name[2];
                this.modifier = allWeapons.get(name[2]);
            } else if (selector >=49 && selector < 65) { // 15%
                this.name = name[3];
                this.modifier = allWeapons.get(name[3]);
            } else if (selector >=65  && selector < 76) { // 11%
                this.name = name[4];
                this.modifier = allWeapons.get(name[4]);
            } else if (selector >= 76 && selector < 87) { //11%
                this.name = name[5];
                this.modifier = allWeapons.get(name[5]);
            } else if (selector >= 87 && selector < 95 ) { // 8%
                this.name = name[6];
                this.modifier = allWeapons.get(name[6]);
            } else {                                       // 5%
                this.name = name[7];
                this.modifier = allWeapons.get(name[7]);
            }
        } else if (type.equals("armor")) {
            this.type = "armor";
            String[] name = {"Peasants Cloths", "Goblin Leather", "Padded Leather Suit", "Chain Mail Armor Piece",
                            "Full Chain Mail Armor"};
            if (selector >= 0 && selector < 39) {         //40%
                this.name = name[0];
                this.modifier = allArmor.get(name[0]);
            } else if (selector >= 39 && selector < 69) { //30%
                this.name = name[1];
                this.modifier = allArmor.get(name[1]);
            } else if (selector >= 69 && selector < 84) { // 15%
                this.name = name[2];
                this.modifier = allArmor.get(name[2]);
            } else if (selector >= 84 && selector < 95) { //11%
                this.name = name[3];
                this.modifier = allArmor.get(name[3]);
            } else {                                      // 5%
                this.name = name[4];
                this.modifier = allArmor.get(name[4]);
            }
        } else if (type.equals("consumable")) {
            this.type = "consumable";
            String[] name = {"Goblin Bomb", "Health Potion", "Great Health Potion", "Goblin Missile"};
            if (selector >= 0 && selector < 39) {          // 40%
                this.name = name[0];
                this.modifier = allConsumables.get(name[0]);
            } else if (selector >= 39 && selector < 79) {  // 40%
                this.name = name[1];
                this.modifier = allConsumables.get(name[1]);
            } else if (selector >= 79 && selector < 89) {  // 10%
                this.name = name[2];
                this.modifier = allConsumables.get(name[2]);
            } else {                                       // 10%
                this.name = name[3];
                this.modifier = allConsumables.get(name[3]);
            }
        } else {
            String[] types = {"weapon", "armor", "consumable"};
            int typeIndex = (int) Math.floor(Math.random() * 3);
            randomItem(types[typeIndex]);
        }
    }

    @Override
    public String toString() {
        if (this.type.equals("weapon")){
            return this.name + ": type = Weapon, Damage + " + this.modifier;
        } else if (this.type.equals("armor")) {
            return this.name + ": type = Armor, Damage Resistance + " + this.modifier;
        } else {
            if (this.modifier > 0) {
                return this.name + ": type = Consumable, Heals the player by " + this.modifier + " points.";
            } else {
                return this.name + ": type = Consumable, Damages enemy by " + this.modifier + ".";
            }
        }
    }

    private HashMap<String, Integer> setHash(String type) {
        HashMap<String, Integer> tempHash = new HashMap<String, Integer>();
        if (type.equals("weapon")) {
            tempHash.put("Steal Sword", 5);
            tempHash.put("Bronze Dagger", 3);
            tempHash.put("Wooden Club", 2);
            tempHash.put("Gold Plated Sword", 7);
            tempHash.put("Iron Sword", 3);
            tempHash.put("Stick", 1);
            tempHash.put("Axe", 4);
            tempHash.put("Work Hammer", 2);

        } else if (type.equals("consumable")) {
            tempHash.put("Goblin Bomb", -5);
            tempHash.put("Health Potion", 5);
            tempHash.put("Great Health Potion", 10);
            tempHash.put("Goblin Missile", -10);

        } else if (type.equals("armor")) {
            tempHash.put("Cloth Robe", 0);
            tempHash.put("Peasants Cloths", 1);
            tempHash.put("Goblin Leather", 2);
            tempHash.put("Padded Leather Suit", 3);
            tempHash.put("Chain Mail Armor Piece", 4);
            tempHash.put("Full Chain Mail Armor", 6);
        }
        return tempHash;
    }
}