package com.niesens.soccersubhelper.engine;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        assertEquals(1, game.getGameSupstitutions().size());
        assertEquals(playerList, game.getGameSupstitutions().get(0));
        assertEquals(32, game.getSubstitutionTimeMinutes());

        game.updatePlayerTimes();
        assertEquals(32, playerList.get(0).getPlayTimeMinutes());
        assertEquals(32, playerList.get(1).getPlayTimeMinutes());
        assertEquals(32, playerList.get(2).getPlayTimeMinutes());
    }

    @Test
    public void testU6players4() {
        List<Player> playerList = generatePlayerList(4);
        Game game = new Game(AgeGroup.U6, playerList);
        assertEquals(8, game.getGameSupstitutions().size());
        assertEquals(playerList.subList(0,3), game.getGameSupstitutions().get(0));
        assertEquals(playerList.subList(1,4), game.getGameSupstitutions().get(1));
        assertEquals(4,game.getSubstitutionTimeMinutes());

        game.updatePlayerTimes();
        assertEquals(24, playerList.get(0).getPlayTimeMinutes());
        assertEquals(24, playerList.get(1).getPlayTimeMinutes());
        assertEquals(24, playerList.get(2).getPlayTimeMinutes());
        assertEquals(24, playerList.get(3).getPlayTimeMinutes());
    }

    @Test
    public void testU6players5() {
        List<Player> playerList = generatePlayerList(5);
        Game game = new Game(AgeGroup.U6, playerList);
        assertEquals(1, game.getGameSupstitutions().size());
        assertEquals(playerList, game.getGameSupstitutions().get(0));
        assertEquals(0, game.getSubstitutionTimeMinutes());

        game.updatePlayerTimes();
        assertEquals(0, playerList.get(0).getPlayTimeMinutes());
        assertEquals(0, playerList.get(1).getPlayTimeMinutes());
        assertEquals(0, playerList.get(2).getPlayTimeMinutes());
        assertEquals(0, playerList.get(3).getPlayTimeMinutes());
    }

    @Test
    public void testU6players6() {
        List<Player> playerList = generatePlayerList(6);
        Game game = new Game(AgeGroup.U6, playerList);
        assertEquals(1, game.getGameSupstitutions().size());
        assertEquals(playerList, game.getGameSupstitutions().get(0));
        assertEquals(32, game.getSubstitutionTimeMinutes());

        game.updatePlayerTimes();
        assertEquals(16, playerList.get(0).getPlayTimeMinutes());
        assertEquals(16, playerList.get(1).getPlayTimeMinutes());
        assertEquals(16, playerList.get(2).getPlayTimeMinutes());
        assertEquals(16, playerList.get(3).getPlayTimeMinutes());
    }

    @Test
    public void testU8players4() {
        List<Player> playerList = generatePlayerList(4);
        Game game = new Game(AgeGroup.U8, playerList);
        assertEquals(1, game.getGameSupstitutions().size());
        assertEquals(playerList, game.getGameSupstitutions().get(0));
    }

    @Test
    public void testU8players8() {
        List<Player> playerList = generatePlayerList(8);
        Game game = new Game(AgeGroup.U8, playerList);
        assertEquals(8, game.getGameSupstitutions().size());
        for (int i=0; i < 8; i=i+2) {
            assertEquals(playerList.subList(0,4),game.getGameSupstitutions().get(i));
            assertEquals(playerList.subList(4,8),game.getGameSupstitutions().get(i + 1));
        }
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

}