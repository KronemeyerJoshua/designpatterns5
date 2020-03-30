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
import java.nio.charset.StandardCharsets;

public class StartGame {

    /**
     * Our main loop for the game.
     *
     * String format has to be used to use %n (universal newline char)
     * System.out.println/printf do not recognize %n
     *
     * @param args Console arguments
     * @throws IOException Console reader exception
     */
    public static void main(String[] args) throws IOException {
        // Our arena will hold the player, enemy, and is responsible for combat
        Combat arena = null;
        Player player = null;

        // Get our players input for their class
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in,
                                                StandardCharsets.UTF_8));
        System.out.println( String.format("Choose class:%n1: Mage%n2: Warrior%n3: Rogue"));

        while (player == null) {
            switch (getInput(in)) {
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
        System.out.print( String.format("\"Entering the arena is a powerful hero wielding %s and "
                          + "wearing %s\"%n%n *The crowd cheering wildly*%n%n",
                           player.playerWeapon.getName(), player.playerArmor.getName()));

        // Simple iteration through enemy list
        for (int i = 0; i < BossList.getBossCount(); i++) {
            // Get our enemy name for immersion
            String enemyName = BossList.getNpc(i).getName();
            System.out.printf( String.format("\"Their opponent is the fierce %s\"%n%n"
                              + "*Crowd booing* %n%n %s looks visibly irritated%n%n",
                                enemyName, enemyName) );

            // Tell our arena a new enemy has entered the fight
            arena.replaceEnemy(BossList.getNpc(i));

            // Our player gains 20 HP after each round
            player.restoreHealth(20);

            // Main Combat Loop
            while (!arena.enemyDead() && !arena.playerDead()) {

                // Basic Stat Info
                System.out.printf(String.format( "Player HP: %d%nPlayer Armor: %d%n"
                                    + "Player Base Attack: %d%nEnemy HP: %d%n",
                                    player.getHealth(), player.playerArmor.getBase(),
                                    player.playerWeapon.getBase(), arena.getEnemyHealth() ));
                System.out.printf(String.format( "Choose your action%n1. Attack%n"
                                  + "2. %dHP Health Potion (%d)%n3."
                                  + " Quit%n", ItemList.getItem(6).getBase(),
                                                        player.getPotionCount() ));

                // Combat Menu
                switch (getInput(in)) {
                    case "1":
                    case "attack":
                        // Player attacks monster, monster attacks player
                        System.out.printf(String.format("Our hero strikes %s viciously for %d damage%n%n",
                                enemyName, arena.damageEnemy()));
                        if (!arena.enemyDead()) {
                            System.out.printf(String.format("%s retaliates with a hard hitting "
                                                + "blow of %d damage%n%n",
                                    enemyName, arena.damagePlayer() ));
                        }
                        break;

                    case "2":
                    case "health potion":
                    case "potion":
                        // Player healths for potion amount if poitions are available
                        // Monster attacks player
                        if (player.getPotionCount() <= 0) {
                            System.out.println(String.format("Our hero reaches for their potion belt,"
                                    + "only to find they are out of potions!%n%n"));
                            System.out.printf(String.format("%s sees this for an opportunity to attack"
                                            + "and hits our hero for %d damage%n%n",
                                                enemyName, arena.damagePlayer()));
                        }
                        System.out.printf(String.format("Our hero heals themselves for %d health%n%n",
                                arena.potionPlayer()));
                        System.out.printf(String.format("Just after finishing the potion, %s hits"
                                            + "our hero for %d damage%n%n",
                                enemyName, arena.damagePlayer()));
                        break;
                    case "3":
                    case "q":
                    case "quit":
                        // Player has quit, terminate our application
                        System.out.printf(String.format("Our hero lays their %s at the feet of"
                                        + "%s admitting defeat%n%n",
                                player.playerWeapon.getName(), enemyName));
                        System.exit(0);
                        break;
                    default:
                }

                // This is to provide a message to the problem,
                // termination of this loop is in the arguments of the while loop above
                if (arena.enemyDead() && i < 1)
                {
                    System.out.printf(String.format("It's over folks! Our hero has bested %s"
                            + "and has won the Arena!%n%n", enemyName));
                }
                else if (arena.enemyDead()) {
                    System.out.printf(String.format("It's over folks! Our hero has bested %s"
                            + "and moves onto the next round!%n%n", enemyName));
                } else if (arena.playerDead()) {
                    System.out.printf(String.format("A stunning upset! %s has defeated our hero!%n", enemyName));
                    System.out.println("Game over");
                }
            }
        }

    }

    /**
     * Gets input and makes sure it's not null.
     * @param r Our bufferedReader
     * @return Our input
     */
    public static String getInput(BufferedReader r) {
        String input = "";
        try {
            input = r.readLine();
            if (input != null) {
                input = input.toLowerCase();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return input;
    }

}
