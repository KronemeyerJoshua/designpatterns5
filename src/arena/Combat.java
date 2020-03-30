/*
 * Our main combat loop/arena
 *
 * @author Joshua Kronemeyer
 * @date 3/30/2020
 * <p>
 * Assignment 5 Design Patterns
 * <p>
 * Here we use a mediator-like approach to have the player and enemy classes
 * interact with each other
 */

package arena;

import java.util.Random;

public class Combat {
    private Player player;
    private Npc enemy;

    /**
     * Constructor for Combat.
     *
     * @param player Our player
     */
    Combat(Player player) {
        this.player = player;
    }

    /**
     * Damages player based on enemy damage.
     *
     * @return The amount the player has been hit for
     */
    public int damagePlayer() {
        Random r = new Random();
        int damage = enemy.getDamage();

        // Determine if enemy crit or not based on crit chance / 100
        if (r.nextInt(101) <= enemy.getCritChance()) {
            damage *= 2;
        }

        // Mitigate damage based on player armor
        damage -= player.playerArmor.getBase();

        player.damageHealth(damage);

        return damage;
    }

    /**
     * Damages the enemy based on player weapon stats.
     *
     * @return The amount the enemy has been hit for
     */
    public int damageEnemy() {
        // Each weapon has a +variation of damage that it can do, we use this here
        int value = player.playerWeapon.getBase() + player.playerWeapon.getRandomVariation();
        enemy.damageHealth(value);

        return value;
    }

    /**
     * Is the enemy dead.
     *
     * @return True if dead, false other
     */
    public boolean enemyDead() {
        return (enemy.getHealth() <= 0);
    }

    /**
     * Is the player dead.
     *
     * @return True if dead, false other
     */
    public boolean playerDead() {
        return (player.getHealth() <= 0);
    }

    /**
     * Heal player with potion.
     *
     * @return Heal amount, returns 0 if no potions are available
     */
    public int potionPlayer() {
        // Heal our player if we have potions
        int healAmount = 0;
        if (player.getPotionCount() > 0) {
            healAmount = ItemList.getItem(6).getBase();
            if ( (healAmount + player.getHealth()) > 100)
            {
                healAmount -= (healAmount + player.getHealth()) % 100;
            }
            player.restoreHealth(healAmount);
            player.removePotion();
        }

        return healAmount;
    }

    /**
     * Replaces our enemy we are fighting.
     *
     * @param enemy The new enemy entering our arena
     */
    public void replaceEnemy(Npc enemy) {
        this.enemy = enemy;
    }

    /**
     * Gets the enemies health.
     *
     * @return The enemy health
     */
    public int getEnemyHealth() {
        return enemy.getHealth();
    }
}
