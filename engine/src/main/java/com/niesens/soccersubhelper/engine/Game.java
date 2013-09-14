package com.niesens.soccersubhelper.engine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Game {

    private AgeGroup ageGroup;
    private List<Player> players;
    private List<List<Player>> gameSubstitutions;
    private int substitutionTimeMinutes;

    private boolean playTimeAppliedToPlayers;

    public Game(AgeGroup ageGroup, List<Player> players) {
        if (ageGroup == null) {
            throw new IllegalArgumentException("Invalid ageGroup: " + ageGroup);
        }
        if ((players.size() < ageGroup.getGameNumOfPlayers()) || (players.size() > ageGroup.getGameNumOfPlayers() * 2)) {
            throw new IllegalArgumentException("Invalid number of players: " + players);
        }

        this.ageGroup = ageGroup;
        this.players = players;

        this.playTimeAppliedToPlayers = false;

        Collections.sort(players, Player.PlayTimeComparator);
        buildGameSubstitutions();
   }

    public void buildGameSubstitutions() {
        gameSubstitutions = new ArrayList<List<Player>>();
        switch (ageGroup) {
            case U6: buildGameSubstitutionsU6(); break;
            case U8: buildGameSubstitutionsU8(); break;
            default: throw new IllegalArgumentException("Invalid ageGroup: " + ageGroup);
        }
   }

    private void buildGameSubstitutionsU6() {
        switch (players.size()) {
            case 3: buildGameSubstitutionsU6With3Players(); break;
            case 4: buildGameSubstitutionsU6With4Players(); break;
            case 5: buildGameSubstitutionsU6With5Players(); break;
            case 6: buildGameSubstitutionsU6With6Players(); break;
            default: throw new IllegalArgumentException("Invalid number of players: " + players);
        }
        substitutionTimeMinutes = AgeGroup.U6.getGameLengthMinutes() / gameSubstitutions.size();

    }

    private void buildGameSubstitutionsU6With3Players() {
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(2))));
    }

    private void buildGameSubstitutionsU6With4Players() {
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(2))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(1), players.get(2), players.get(3))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(2), players.get(3), players.get(0))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(3), players.get(0), players.get(1))));

        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(2))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(1), players.get(2), players.get(3))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(2), players.get(3), players.get(0))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(3), players.get(0), players.get(1))));
    }

    private void buildGameSubstitutionsU6With5Players() {
    }

    private void buildGameSubstitutionsU6With6Players() {
    }

    private void buildGameSubstitutionsU8() {
        switch (players.size()) {
            case 4: buildGameSubstitutionsU8With4Players(); break;
            case 5: buildGameSubstitutionsU8With5Players(); break;
            case 6: buildGameSubstitutionsU8With6Players(); break;
            case 7: buildGameSubstitutionsU8With7Players(); break;
            case 8: buildGameSubstitutionsU8With8Players(); break;
            default: throw new IllegalArgumentException("Invalid number of players: " + players);
        }
        substitutionTimeMinutes = AgeGroup.U8.getGameLengthMinutes() / gameSubstitutions.size();

    }

    private void buildGameSubstitutionsU8With4Players() {
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(2), players.get(3))));
    }

    private void buildGameSubstitutionsU8With5Players() {
    }

    private void buildGameSubstitutionsU8With6Players() {
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(2), players.get(3), players.get(4), players.get(5))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(4), players.get(5))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(2), players.get(3))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(1), players.get(3), players.get(4), players.get(5))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(2), players.get(3), players.get(5))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(2), players.get(4))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(3), players.get(5))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(2), players.get(3), players.get(4))));
    }

    private void buildGameSubstitutionsU8With7Players() {
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(2), players.get(3))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(4), players.get(5), players.get(6))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(4), players.get(1), players.get(2), players.get(3))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(1), players.get(0), players.get(5), players.get(6))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(5), players.get(4), players.get(2), players.get(3))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(2), players.get(1), players.get(0), players.get(6))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(6), players.get(4), players.get(5), players.get(3))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(3), players.get(1), players.get(0), players.get(2))));
    }

    private void buildGameSubstitutionsU8With8Players() {
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(2), players.get(3))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(2), players.get(3), players.get(4), players.get(5))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(4), players.get(5), players.get(6), players.get(7))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(6), players.get(7), players.get(0), players.get(1))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(0), players.get(1), players.get(4), players.get(5))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(4), players.get(5), players.get(6), players.get(7))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(6), players.get(7), players.get(2), players.get(3))));
        gameSubstitutions.add(new ArrayList<Player>(Arrays.asList(players.get(2), players.get(3), players.get(0), players.get(1))));
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }
    
    public List<List<Player>> getGameSupstitutions() {
        return gameSubstitutions;
    }

    public int getSubstitutionTimeMinutes() {
        return substitutionTimeMinutes;
    }

    /**
     * Updates the players time based on this game.
     */
    public void updatePlayerTimes() {
        if (playTimeAppliedToPlayers) { return;}

        for (List<Player> period : gameSubstitutions) {
            for (Player player : period) {
                player.addPlayTimeMinutes(substitutionTimeMinutes);
            }
        }

        playTimeAppliedToPlayers = true;
    }

}