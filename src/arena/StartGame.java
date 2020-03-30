/**
 * Simple Arena Combat Game.
 *
 * @author Joshua Kronemeyer
 * @date 3/30/2020
 * <p>
 * Assignment 5 Design Patterns
 * Utilizes AbstractFactories mostly with some elements of Object Pools and Mediator
 */

package arena;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartGame {

    /**
     * Our main loop for the game.
     *
     * @param args Console arguments
     * @throws IOException Console reader exception
     */
    public static void main(String[] args) throws IOException {
        // Our arena will hold the player, enemy, and is responsible for combat
        Combat arena = null;
        Player player = null;

        // Get our players input for their class
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Choose class:\n1: Mage\n2: Warrior\n3: Rogue");

        while (player == null) {
            switch (in.readLine()) {
                case "1":
                case "mage":
                    player = Player.getPlayer(PlayerClass.MAGE);
                    break;

                case "2":
                case "warrior":
                    player = Player.getPlayer(PlayerClass.WARRIOR);
                    break;

                case "3":
                case "rogue":
                    player = Player.getPlayer(PlayerClass.ROGUE);
                    break;
                default:
                    System.out.println("Input not recognized, try again. "
                                       + "Enter a number or class name from the list");
            }
        }

        // Tell the arena it has a new player
        arena = new Combat(player);
        System.out.printf("\"Entering the arena is a powerful hero wielding %s and "
                          + "wearing %s\"\n\n *The crowd cheering wildly*\n\n",
                           player.playerWeapon.getName(), player.playerArmor.getName());

        // Simple iteration through enemy list
        for (int i = 0; i < BossList.getBossCount(); i++) {
            // Get our enemy name for immersion
            String enemyName = BossList.getNpc(i).getName();
            System.out.printf("\"Their opponent is the fierce %s\"\n\n"
                              + "*Crowd booing* \n\n %s looks visibly irritated\n\n",
                                enemyName, enemyName);

            // Tell our arena a new enemy has entered the fight
            arena.replaceEnemy(BossList.getNpc(i));

            // Our player gains 20 HP after each round
            player.restoreHealth(20);

            // Main Combat Loop
            while (!arena.enemyDead() && !arena.playerDead()) {

                // Basic Stat Info
                System.out.printf("Player HP: %d\n Enemy HP: %d\n",
                                    player.getHealth(), arena.getEnemyHealth());
                System.out.printf("Choose your action\n1. Attack\n"
                                  + "2. %dHP Health Potion (%d)\n3."
                                  + "Quit\n", ItemList.getItem(6).getBase(),
                                                        player.getPotionCount());

                // Combat Menu
                switch (in.readLine().toLowerCase()) {
                    case "1":
                    case "attack":
                        // Player attacks monster, monster attacks player
                        System.out.printf("Our hero strikes %s viciously for %d damage\n\n",
                                enemyName, arena.damageEnemy());
                        if (!arena.enemyDead()) {
                            System.out.printf("%s retaliates with a hard hitting "
                                                + "blow of %d damage\n\n",
                                    enemyName, arena.damagePlayer());
                        }
                        break;

                    case "2":
                    case "health potion":
                    case "potion":
                        // Player healths for potion amount if poitions are available
                        // Monster attacks player
                        if (player.getPotionCount() <= 0) {
                            System.out.println("Our hero reaches for their potion belt,"
                                    + "only to find they are out of potions!\n\n");
                            System.out.printf("%s sees this for an opportunity to attack"
                                            + "and hits our hero for %d damage\n\n",
                                                enemyName, arena.damagePlayer());
                        }
                        System.out.printf("Our hero heals themselves for %d health\n\n",
                                arena.potionPlayer());
                        System.out.printf("Just after finishing the potion, %s hits"
                                            + "our hero for %d damage\n\n",
                                enemyName, arena.damagePlayer());
                        break;
                    case "3":
                    case "q":
                    case "quit":
                        // Player has quit, terminate our application
                        System.out.printf("Our hero lays their %s at the feet of"
                                        + "%s admitting defeat\n\n",
                                player.playerWeapon.getName(), enemyName);
                        System.exit(0);
                        break;
                    default:
                }

                // This is to provide a message to the problem,
                // termination of this loop is in the arguments of the while loop above
                if (arena.enemyDead()) {
                    System.out.printf("It's over folks! Our hero has bested %s"
                            + "and moves onto the next round!\n\n", enemyName);
                } else if (arena.playerDead()) {
                    System.out.printf("A stunning upset! %s has defeated our hero!", enemyName);
                }
            }
        }

        // Woo, you won!
        System.out.println("Congratulations, you have beaten the game! That was easy, right?");

    }

}
