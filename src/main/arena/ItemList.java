/**
 * Item list of constants for items you may use
 *
 * @author Joshua Kronemeyer
 * @date 3/30/2020
 * <p>
 * Assignment 5 Design Patterns
 * AbstractFactory is used here to return the appropriate item based on type provided, while still
 * maintaining a class type of Item.
 * <p>
 * An object pool of already generated items we can use
 */

package main.arena;

import java.util.HashMap;

public class ItemList {

    private static final HashMap<Integer, Item> items = new HashMap<>() {{
            put(0, Item.getItem("Beeeg Armor", 0, 3, 10, ItemClass.ARMOR));
            put(1, Item.getItem("Medium Armor", 1, 3, 7, ItemClass.ARMOR));
            put(2, Item.getItem("Light Armor", 2, 2, 4, ItemClass.ARMOR));

            put(3, Item.getItem("Axe of Blinding Truth", 3, 3, 10, ItemClass.WEAPON));
            put(4, Item.getItem("Sword of Obliteration", 4, 4, 12, ItemClass.WEAPON));
            put(5, Item.getItem("Wand of the Phoenix", 5, 5, 14, ItemClass.WEAPON));

            put(6, Item.getItem("First Aid Potion", 6, 0, 50, ItemClass.POTION));
        }
    };

    static Item getItem(int id) {
        return items.get(id);
    }
}
