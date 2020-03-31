package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import main.arena.BossList;
import main.arena.Combat;
import main.arena.Npc;
import main.arena.Player;
import main.arena.PlayerClass;
import org.junit.Before;
import org.junit.Test;

public class WhiteBox {

    Combat combat;
    Npc npc0;
    Npc npc1;
    Npc npc2;
    Player playerMage;
    Player playerWarrior;
    Player playerRogue;

    /**
     * Reset our health and make sure appropriate assigns.
     */
    @Before
    public void setUp() {
        playerMage = Player.getPlayer(PlayerClass.MAGE);
        playerWarrior = Player.getPlayer(PlayerClass.WARRIOR);
        playerRogue = Player.getPlayer(PlayerClass.ROGUE);

        npc0 = BossList.getNpc(0);
        npc1 = BossList.getNpc(1);
        npc2 = BossList.getNpc(2);

        npc0.resetHealth();
        npc1.resetHealth();
        npc2.resetHealth();

        playerRogue.resetHealth();
        playerMage.resetHealth();
        playerWarrior.resetHealth();
    }

    @Test
    public void mageBossZeroCombatTest() {
        combat = new Combat(playerMage);
        combat.replaceEnemy(npc0);

        // First boss health should be 45
        assertEquals(45, combat.getEnemyHealth(), 0.1);

        // Delta is high because boss can crit, doubling their base damage
        // + 30%. Maximum possible value (16+(16*.30))*2 = ~42 - 4 (armor) = 38
        assertEquals(16, combat.damagePlayer(), 22);
        assertEquals(16, combat.damagePlayer(), 22);
        assertEquals(16, combat.damagePlayer(), 22);
        assertEquals(16, combat.damagePlayer(), 22);

        // Max possible variation for mage (wand) is +10 (5 from base, 5 from variation)
        assertEquals(14, combat.damageEnemy(), 10);

        assertEquals(50, combat.potionPlayer(), 0.1);
    }

    @Test
    public void warriorBossOneCombatTest() {
        combat = new Combat(playerWarrior);
        combat.replaceEnemy(npc1);

        // Second boss health should be 65
        assertEquals(65, combat.getEnemyHealth(), 0.1);

        // Delta is high because boss can crit, doubling their base damage
        // + 30%. Maximum possible value (19+(19*.30))*2 = ~50 - 10 (armor) = 40

        assertEquals(19, combat.damagePlayer(), 21);


        // Max possible variation for warrior (axe) is +6 (3 from base, 3 from variation)
        assertEquals(10, combat.damageEnemy(), 6);

        assertTrue(combat.potionPlayer() < 50);
    }

    @Test
    public void rogueBossTwoCombatTest() {
        combat = new Combat(playerRogue);
        combat.replaceEnemy(npc2);

        // Second boss health should be 65
        assertEquals(95, combat.getEnemyHealth(), 0.1);

        // Delta is high because boss can crit, doubling their base damage
        // + 30%. Maximum possible value (23+(23*.30))*2 = ~60 - 7 (armor) = 53
        for (int i = 0; i < 5; i++) {
            assertEquals(23, combat.damagePlayer(), 30);
        }

        // Max possible variation for rogue (sword) is +8 (4 from base, 4 from variation)
        assertEquals(12, combat.damageEnemy(), 8);

        assertEquals(50, combat.potionPlayer(), 0.1);
    }

    @Test
    public void playerDeathTest() {
        combat = new Combat(playerMage);
        combat.replaceEnemy(npc2);

        for (int i = 0; i < 10; i++) {
            combat.damagePlayer();
        }

        assertTrue(combat.playerDead());
    }

    @Test
    public void enemyDeathTest() {
        combat = new Combat(playerMage);
        combat.replaceEnemy(npc2);

        for (int i = 0; i < 10; i++) {
            combat.damageEnemy();
        }

        assertTrue(combat.enemyDead());
    }

    @Test
    public void bossCountTest() {
        assertEquals(3, BossList.getBossCount(), 0.1);
    }

    @Test
    public void bossNameTest() {
        assertEquals("Doggo of Doom", npc0.getName());
        assertEquals("Chicken of Chaos", npc1.getName());
        assertEquals("Sloth of Slaughter", npc2.getName());
    }
}