package com.niesens.soccersubhelper.engine;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PlayerTest {

    @Test
    public void testPlayTimeComparator() {
        Player player1 = new Player("player1");
        player1.setPlayTimeMinutes(5);
        Player player2 = new Player("player2");
        player2.setPlayTimeMinutes(5);
        Player player3 = new Player("player3");
        player3.setPlayTimeMinutes(10);

        int result = Player.PlayTimeComparator.compare(player1, player2);
        assertEquals(0, result);

        result = Player.PlayTimeComparator.compare(player1, player3);
        assertTrue(result <= -1);

        result = Player.PlayTimeComparator.compare(player3, player1);
        assertTrue(result >= 1);

        List<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player3);
        players.add(player2);
        Collections.sort(players, Player.PlayTimeComparator);
        assertEquals("player3", players.get(2).getName());
    }

}