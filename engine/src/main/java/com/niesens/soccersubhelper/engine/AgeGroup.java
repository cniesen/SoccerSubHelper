package com.niesens.soccersubhelper.engine;

public enum AgeGroup {
    U6(32, 3),
    U8(40, 4);

    private final int gameLengthMinutes;
    private final int gameNumOfPlayers;

    AgeGroup(int gameLengthMinutes, int gameNumOfPlayers) {
        this.gameLengthMinutes = gameLengthMinutes;
        this.gameNumOfPlayers = gameNumOfPlayers;
    }

    public int getGameLengthMinutes() {
        return gameLengthMinutes;
    }

    public int getGameNumOfPlayers() {
        return gameNumOfPlayers;
    }
}