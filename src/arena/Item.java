/**
 * Item class to hold information about our items (armor, weapons, potions).
 *
 * @author Joshua Kronemeyer
 * @date 3/30/2020
 * <p>
 * Assignment 5 Design Patterns
 * AbstractFactory is used here to return the appropriate item based on type provided, while still
 * maintaining a class type of Item.
 * <p>
 * While not used in this example, you could put Armor, Potion, Item specific methods
 */

package arena;

import java.util.Random;

abstract class Item {

    protected int variation;
    protected int base;
    protected Random rand = new Random();
    private String name;

    /**
     * Item constructor.
     *
     * @param name      Name of our item
     * @param id        id of our item
     * @param type      Our item type @see ItemClass
     * @param variation The variation in damage/armor our item has, potions are not impacted by this
     * @param base      Our base (min) value for our item
     */
    Item(String name, int id, ItemClass type, int variation, int base) {
        this.name = name;
        this.variation = variation;
        this.base = base;
    }

    /**
     * Actual Abstract Factory.
     *
     * @param name      Name of our item
     * @param id        id of our item
     * @param variation variation of our item
     * @param base      Minimum value for our item
     * @param type      Type of item (this is what determines the class) @see ItemClass
     * @return Returns armor, weapon, or potion based on type provided
     */
    static Item getItem(String name, int id, int variation, int base, ItemClass type) {
        switch (type) {
            case ARMOR:
                Armor a = new Armor(name, id, ItemClass.ARMOR, variation, base);
                a.generateNewArmorValue();
                return a;
            case WEAPON:
                Weapon w = new Weapon(name, id, ItemClass.WEAPON, variation, base);
                w.generateNewWeaponValue();
                return w;
            case POTION:
                return new Potion(name, id, ItemClass.POTION, variation, base);
            default:
        }

        return null;
    }

    /**
     * Gets the name of our item.
     *
     * @return Name of our item
     */
    public String getName() {
        return name;
    }

    /**
     * Additional variation between 0-variation.
     *
     * @return 0-variation
     */
    protected int getRandomVariation() {
        return rand.nextInt(variation + 1);
    }

    public int getBase() {
        return base;
    }
}

/**
 * Armor class for our player.
 */
class Armor extends Item {
    /**
     * Constructor @see Item for constructor params.
     */
    Armor(String name, int id, ItemClass type, int variation, int base) {
        super(name, id, type, variation, base);
    }

    /**
     * Generates a new value for armor based on variation + base.
     */
    void generateNewArmorValue() {
        base = base + getRandomVariation();
    }
}

/**
 * Weapon class for player.
 */
class Weapon extends Item {
    /**
     * Constructor @see Item for constructor params.
     */
    Weapon(String name, int id, ItemClass type, int variation, int base) {
        super(name, id, type, variation, base);
    }

    /**
     * Generates a new value for weapon damage based on variation + base.
     */
    void generateNewWeaponValue() {
        base = base + getRandomVariation();
    }
}

class Potion extends Item {
    /**
     * Constructor @see Item for constructor params.
     */
    Potion(String name, int id, ItemClass type, int variation, int base) {
        super(name, id, type, variation, base);
    }

}