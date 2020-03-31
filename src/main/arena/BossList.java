/** Constant boss list.
 *
 * @author Joshua Kronemeyer
 * @date 3/30/2020
 *
 * <p>Assignment 5 Design Patterns
 * This is an object pool of bosses already created for us to use.
 */

package main.arena;

import java.util.HashMap;

public class BossList {

    private static final HashMap<Integer, Npc> bosses = new HashMap<>() {{
            put(0, new Npc(45, 16, "Doggo of Doom", 0));
            put(1, new Npc(65, 19, "Chicken of Chaos", 1));
            put(2, new Npc(95, 23, "Sloth of Slaughter", 2));
        }
        };

    /**
     * Gets NPC from our pooled list based on id.
     *
     * @param id The id of the enemy we want
     * @return The reference to the enemy
     */
    public static Npc getNpc(int id) {
        return bosses.get(id);
    }

    /**
     * How many enemies we have in our list.
     *
     * @return How many enemies we have in our list
     */
    public static int getBossCount() {
        return bosses.size();
    }
}
