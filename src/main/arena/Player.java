/**
 * Player class to hold all our information regarding the player.
 *
 * @author Joshua Kronemeyer
 * @date 3/30/2020
 * <p>
 * Assignment 5 Design Patterns
 * Uses Abstract Factory design to setup and return approriate class of player
 */

package main.arena;

public abstract class Player {
    private final int maxhealth;
    Item playerWeapon;
    Item playerArmor;
    private int health;
    private int potionCount = 3;

    /**
     * Our player.
     *
     * @param health       PLayer health
     * @param playerWeapon Player weapon
     * @param playerArmor  PLayer armor
     */
    Player(int health, Item playerWeapon, Item playerArmor) {
        this.health = health;
        this.maxhealth = health;
        this.playerWeapon = playerWeapon;
        this.playerArmor = playerArmor;
    }

    /**
     * Sets up players based on class.
     *
     * @param playerClass @see PlayerClass
     * @return The approriate class for the player
     */
    public static Player getPlayer(PlayerClass playerClass) {
        switch (playerClass) {
            case MAGE:
                return new Mage();
            case WARRIOR:
                return new Warrior();
            case ROGUE:
                return new Rogue();
            default:
        }

        return null;
    }

    /**
     * Potion Count.
     *
     * @return Player potion count
     */
    public int getPotionCount() {
        return potionCount;
    }

    /**
     * Subtracts one from potion count.
     */
    public void removePotion() {
        potionCount--;
    }

    /**
     * Gets health of player.
     *
     * @return health of player
     */
    public int getHealth() {
        return health;
    }

    /**
     * Resets health of player back to max.
     */
    public void resetHealth() {
        health = maxhealth;
    }

    /**
     * Subtracts health from damage.
     *
     * @param damage subtraction amount
     */
    public void damageHealth(int damage) {
        health -= damage;
    }

    /**
     * Restores health to player up to MAX_HEALTH.
     *
     * @param restoreAmount Add to health amount
     */
    public void restoreHealth(int restoreAmount) {
        if ((health + restoreAmount) > maxhealth) {
            health = maxhealth;
        } else {
            health += restoreAmount;
        }
    }
}

class Mage extends Player {
    Mage() {
        super(85, ItemList.getItem(5), ItemList.getItem(2));
    }
}

class Warrior extends Player {

    Warrior() {
        super(100, ItemList.getItem(3), ItemList.getItem(0));
    }
}

class Rogue extends Player {

    Rogue() {
        super(90, ItemList.getItem(1), ItemList.getItem(4));
    }
}
