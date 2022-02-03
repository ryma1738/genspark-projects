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
        int selector = (int) Math.round(Math.random() * 99);
        if (type.equals("weapon")) {
            if (selector >= 0 && selector <10) {

            } else if (selector >= 10 && selector <20) {

            }
        } else if (type.equals("armor")) {

        } else if (type.equals("consumable")) {

        } else {
            String[] types = {"weapon", "armor", "consumable"};
            int typeIndex = (int) Math.round(Math.random() * 2);
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
                return this.name + ": type = Consumable, Damages enemy by " + this.modifier + " points.";
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

        } else if (type.equals("consumable")) {
            tempHash.put("Goblin Bomb", -5);
            tempHash.put("Health Potion", 5);
            tempHash.put("Great Health Potion", 10);
            tempHash.put("Goblin Missile", -10);

        } else if (type.equals("armor")) {
            tempHash.put("Peasants Cloths", 1);
            tempHash.put("Goblin Leather", 2);

        }
        return tempHash;
    }
}