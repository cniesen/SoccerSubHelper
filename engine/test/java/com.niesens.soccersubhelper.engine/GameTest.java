package com.niesens.soccersubhelper.engine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import org.apache.commons.collections.MapIterator;
import org.apache.commons.collections.map.MultiKeyMap;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GameTest {

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidGameLevel() {
        List<Player> playerList = new ArrayList<Player>();
        Game game = new Game(null, playerList);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testInvalidPlayers() {
        Game game = new Game(AgeGroup.U8, generatePlayerList(AgeGroup.U8.getGameNumOfPlayers() - 1));
    }

    @Test
    public void testU6players3() {
        List<Player> playerList = generatePlayerList(3);
        Game game = new Game(AgeGroup.U6, playerList);
        assertEquals(1, game.getGameSubstitutions().size());
        assertEquals(playerList, game.getGameSubstitutions().get(0));
        assertEquals(32, game.getSubstitutionTimeMinutes());

        game.updatePlayerTimes();
        assertEquals(32, playerList.get(0).getPlayTimeMinutes());
        assertEquals(32, playerList.get(1).getPlayTimeMinutes());
        assertEquals(32, playerList.get(2).getPlayTimeMinutes());

        testUniquePlayers(playerList);
        testPlayerMix(new GameTestGame(AgeGroup.U6, playerList, game.getGameSubstitutions()));
    }

    @Test
    public void testU6players4() {
        List<Player> playerList = generatePlayerList(4);
        Game game = new Game(AgeGroup.U6, playerList);
        assertEquals(8, game.getGameSubstitutions().size());
        assertEquals(playerList.subList(0,3), game.getGameSubstitutions().get(0));
        assertEquals(playerList.subList(1,4), game.getGameSubstitutions().get(1));
        assertEquals(4,game.getSubstitutionTimeMinutes());

        game.updatePlayerTimes();
        assertEquals(24, playerList.get(0).getPlayTimeMinutes());
        assertEquals(24, playerList.get(1).getPlayTimeMinutes());
        assertEquals(24, playerList.get(2).getPlayTimeMinutes());
        assertEquals(24, playerList.get(3).getPlayTimeMinutes());

        testUniquePlayers(playerList);
        testPlayerMix(new GameTestGame(AgeGroup.U6, playerList, game.getGameSubstitutions()));
    }

    @Test
    public void testU6players5() {
        List<Player> playerList = generatePlayerList(5);
        Game game = new Game(AgeGroup.U6, playerList);
        assertEquals(8, game.getGameSubstitutions().size());
        assertEquals(4, game.getSubstitutionTimeMinutes());

        testUniquePlayers(playerList);
        testPlayerMix(new GameTestGame(AgeGroup.U6, playerList, game.getGameSubstitutions()));
    }

    @Test
    public void testU6players6() {
        List<Player> playerList = generatePlayerList(6);
        Game game = new Game(AgeGroup.U6, playerList);
        assertEquals(8, game.getGameSubstitutions().size());
        assertEquals(4, game.getSubstitutionTimeMinutes());

        testUniquePlayers(playerList);
        testPlayerMix(new GameTestGame(AgeGroup.U6, playerList, game.getGameSubstitutions()));
    }

    @Test
    public void testU8players4() {
        List<Player> playerList = generatePlayerList(4);
        Game game = new Game(AgeGroup.U8, playerList);
        assertEquals(1, game.getGameSubstitutions().size());
        assertEquals(40, game.getSubstitutionTimeMinutes());
        assertEquals(playerList, game.getGameSubstitutions().get(0));

        testUniquePlayers(playerList);
        testPlayerMix(new GameTestGame(AgeGroup.U8, playerList, game.getGameSubstitutions()));
    }

    @Test
    public void testU8players5() {
        List<Player> playerList = generatePlayerList(5);
        Game game = new Game(AgeGroup.U8, playerList);
        assertEquals(8, game.getGameSubstitutions().size());
        assertEquals(5, game.getSubstitutionTimeMinutes());

        testUniquePlayers(playerList);
        testPlayerMix(new GameTestGame(AgeGroup.U8, playerList, game.getGameSubstitutions()));
    }

    @Test
    public void testU8players6() {
        List<Player> playerList = generatePlayerList(6);
        Game game = new Game(AgeGroup.U8, playerList);
        assertEquals(8, game.getGameSubstitutions().size());
        assertEquals(5, game.getSubstitutionTimeMinutes());

        testUniquePlayers(playerList);
        testPlayerMix(new GameTestGame(AgeGroup.U8, playerList, game.getGameSubstitutions()));
    }

    @Test
    public void testU8players7() {
        List<Player> playerList = generatePlayerList(7);
        Game game = new Game(AgeGroup.U8, playerList);
        assertEquals(8, game.getGameSubstitutions().size());
        assertEquals(5, game.getSubstitutionTimeMinutes());

        testUniquePlayers(playerList);
        testPlayerMix(new GameTestGame(AgeGroup.U8, playerList, game.getGameSubstitutions()));
    }

    @Test
    public void testU8players8() {
        List<Player> playerList = generatePlayerList(8);
        Game game = new Game(AgeGroup.U8, playerList);
        assertEquals(8, game.getGameSubstitutions().size());
        assertEquals(5, game.getSubstitutionTimeMinutes());

        testUniquePlayers(playerList);
        testPlayerMix(new GameTestGame(AgeGroup.U8, playerList, game.getGameSubstitutions()));
    }

    @Test
    public void testUpdatePlayerTime() {
        List<Player> playerList = generatePlayerList(8);
        Game game = new Game(AgeGroup.U8, playerList);
        assertEquals(0, playerList.get(0).getPlayTimeMinutes());
        game.updatePlayerTimes();
        assertEquals(20, playerList.get(0).getPlayTimeMinutes());
    }

    private List<Player> generatePlayerList(int numberOfPlayers) {
        List<Player> playerList = new ArrayList<Player>();

        if(numberOfPlayers >= 1) {playerList.add(new Player("Anton"));}
        if(numberOfPlayers >= 2) {playerList.add(new Player("Beate"));}
        if(numberOfPlayers >= 3) {playerList.add(new Player("Christian"));}
        if(numberOfPlayers >= 4) {playerList.add(new Player("Dagmar"));}
        if(numberOfPlayers >= 5) {playerList.add(new Player("Emil"));}
        if(numberOfPlayers >= 6) {playerList.add(new Player("Frida"));}
        if(numberOfPlayers >= 7) {playerList.add(new Player("Gerd"));}
        if(numberOfPlayers >= 8) {playerList.add(new Player("Helen"));}

        return playerList;
    }

    /**
     * Check that no player needs to be cloned
     */
    private void testUniquePlayers(List<Player> players) {
        Set<Player> playersSet = new HashSet<Player>(players);
        assertEquals("Players are not unique", players.size(), playersSet.size());
    }

    /**
     * Check that players are nicely mixed with each other
     */
    private void testPlayerMix(Game game) {
        MultiKeyMap uniqueLineups = new MultiKeyMap();
        for (List<Player> players : game.getGameSubstitutions()) {
            Collections.sort(players, Player.NameComparator);
            if (game.getAgeGroup() == AgeGroup.U6) {
                Integer count = uniqueLineups.containsKey(players.get(0), players.get(1), players.get(2))
                        ? (Integer) uniqueLineups.get(players.get(0), players.get(1), players.get(2)) + 1 : 1;
                uniqueLineups.put(players.get(0), players.get(1), players.get(2), count);
            } else {
                Integer count = uniqueLineups.containsKey(players.get(0), players.get(1), players.get(2), players.get(3))
                        ? (Integer) uniqueLineups.get(players.get(0), players.get(1), players.get(2), players.get(3)) + 1 : 1;
                uniqueLineups.put(players.get(0), players.get(1), players.get(2), players.get(3), count);
            }
        }

        int playerCombination = combination(game.getPlayers().size(), game.getAgeGroup().getGameNumOfPlayers());
        if (playerCombination <= game.getGameSubstitutions().size()) {
            assertTrue(playerCombination <= uniqueLineups.size());
        } else {
            SortedSet<Integer> combinationCounts = new TreeSet<Integer>();
            MapIterator it = uniqueLineups.mapIterator();
            while (it.hasNext()) {
                it.next();
                combinationCounts.add((Integer) it.getValue());
            }
            assertTrue(combinationCounts.size() < 3);

            if (combinationCounts.size() == 1) {
                // make sure that possible lineup variations are used
                assertTrue(uniqueLineups.size() == game.getGameSubstitutions().size());
            } else if (combinationCounts.size() == 2) {
                // make sure that occurrence spread is not greater than 1
                assertEquals(1, combinationCounts.last() - combinationCounts.first());
            } else {
                // occurrences not evenly spread out
                assertTrue(combinationCounts.size() > 2);
            }
        }
    }

    /**
     * "Calculate" the combination (poolSize choose chooseSize)
     */
    private int combination(int poolSize, int chooseSize) {
        if (poolSize == 3 && chooseSize == 3) {
            return 1;
        } else if (poolSize == 4 && chooseSize == 3) {
            return 4;
        } else if (poolSize == 5 && chooseSize == 3) {
            return 10;
        } else if (poolSize == 6 && chooseSize == 3) {
            return 20;
        } else if (poolSize == 4 && chooseSize == 4) {
            return 1;
        } else if (poolSize == 5 && chooseSize == 4) {
            return 5;
        } else if (poolSize == 6 && chooseSize == 4) {
            return 15;
        } else if (poolSize == 7 && chooseSize == 4) {
            return 35;
        } else if (poolSize == 8 && chooseSize == 4) {
            return 70;
        } else {
            throw new IllegalArgumentException();
        }
    }

    /********************************************************************************
     *  Testing the test code                                                       *
     *******************************************************************************/

    @Test
    public void testTestingCode_uniquePlayers() {
        List<Player> players = generatePlayerList(8);
        testUniquePlayers(players);
    }

    @Test(expected=AssertionError.class)
    public void testTestingCode_nonUniquePlayers() {
        // test unique players
        List<Player> players = generatePlayerList(8);
        players.set(0, players.get(7));
        testUniquePlayers(players);
    }

    @Test
    public void testTestingCode_goodPlayerMix() {
        List<List<Player>> gameSubstitutions = new ArrayList<List<Player>>();
        List<Player> players = generatePlayerList(6);

        List<Player> playerSet1 = new ArrayList<Player>();
        playerSet1.add(players.get(0));
        playerSet1.add(players.get(1));
        playerSet1.add(players.get(2));
        gameSubstitutions .add(playerSet1);

        List<Player> playerSet2 = new ArrayList<Player>();
        playerSet2.add(players.get(3));
        playerSet2.add(players.get(4));
        playerSet2.add(players.get(5));
        gameSubstitutions .add(playerSet2);

        List<Player> playerSet3 = new ArrayList<Player>();
        playerSet3.add(players.get(0));
        playerSet3.add(players.get(1));
        playerSet3.add(players.get(3));
        gameSubstitutions .add(playerSet3);

        List<Player> playerSet4 = new ArrayList<Player>();
        playerSet4.add(players.get(4));
        playerSet4.add(players.get(5));
        playerSet4.add(players.get(2));
        gameSubstitutions .add(playerSet4);

        testPlayerMix(new GameTestGame(AgeGroup.U6, players, gameSubstitutions));
    }

    @Test(expected=AssertionError.class)
    public void testTestingCode_badPlayerMix() {
        List<List<Player>> gameSubstitutions = new ArrayList<List<Player>>();
        List<Player> players = generatePlayerList(6);

        List<Player> playerSet1 = new ArrayList<Player>();
        playerSet1.add(players.get(0));
        playerSet1.add(players.get(1));
        playerSet1.add(players.get(2));
        gameSubstitutions .add(playerSet1);

        List<Player> playerSet2 = new ArrayList<Player>();
        playerSet2.add(players.get(3));
        playerSet2.add(players.get(4));
        playerSet2.add(players.get(5));
        gameSubstitutions .add(playerSet2);

        List<Player> playerSet3 = new ArrayList<Player>();
        playerSet3.add(players.get(0));
        playerSet3.add(players.get(1));
        playerSet3.add(players.get(2));
        gameSubstitutions .add(playerSet3);

        List<Player> playerSet4 = new ArrayList<Player>();
        playerSet4.add(players.get(3));
        playerSet4.add(players.get(4));
        playerSet4.add(players.get(5));
        gameSubstitutions .add(playerSet4);

        testPlayerMix(new GameTestGame(AgeGroup.U6, players, gameSubstitutions));
    }

    private class GameTestGame extends Game {
        private List<List<Player>> gameSubstitutions;

        public GameTestGame(AgeGroup ageGroup, List<Player> players, List<List<Player>> gameSubstitutions) {
            super(ageGroup, players);
            this.gameSubstitutions = gameSubstitutions;
        }

        public List<List<Player>> getGameSubstitutions() {
            return gameSubstitutions;
        }
    }
}