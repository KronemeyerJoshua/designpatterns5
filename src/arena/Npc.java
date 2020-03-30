/**
 * NPC class to hold all our information regarding NPC's.
 *
 * @author Joshua Kronemeyer
 * @date 3/30/2020
 * <p>
 * Assignment 5 Design Patterns
 * Base Template, no design patterns used intentionally here
 */

package arena;

import java.util.Random;

public class Npc {
    private int health;
    private int damage;
    private int critChance = 30;
    private String name;

    /**
     * Constructor for NPC.
     *
     * @param health Health of our NPC
     * @param damage Damage amount of NPC
     * @param name   Name of NPC
     * @param id     ID of NPC
     */
    Npc(int health, int damage, String name, int id) {
        this.health = health;
        this.damage = damage;
        this.name = name;
    }

    /**
     * Returns current health of NPC.
     *
     * @return current health of NPC
     */
    public int getHealth() {
        return health;
    }

    /**
     * Returns varied damage of NPC (30% max addition).
     *
     * @return Damage of NPC
     */
    public int getDamage() {
        Random r = new Random();
        double add = r.nextInt(31) / 100.0;
        return damage + (int) Math.round(damage * add);
    }

    /**
     * Name of NPC.
     *
     * @return name of NPC
     */
    public String getName() {
        return name;
    }

    /**
     * Subtracts health from NPC.
     *
     * @param damage Subtraction value
     */
    public void damageHealth(int damage) {
        health -= damage;
    }

    /**
     * Crit chance of NPC.
     *
     * @return Crit chance of NPC
     */
    public int getCritChance() {
        return critChance;
    }
}
