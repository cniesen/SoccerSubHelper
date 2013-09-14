package com.niesens.soccersubhelper.cli;

import com.niesens.soccersubhelper.engine.AgeGroup;
import com.niesens.soccersubhelper.engine.Game;
import com.niesens.soccersubhelper.engine.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            showHelp();
            return;
        }

        List<Player> players;
        Game game;

        if (args[0].equals("u6")) {
            if (args.length < 4 || args.length > 7) {
                System.out.println("Invalid number of players.  For U6 between 3 and 6 players are required");
                return;
            } else {
                players = getPlayersFromArg(args);
                game = new Game(AgeGroup.U6, players);
            }
        } else if (args[0].equals("u8")) {
            if (args.length < 5 || args.length > 9) {
                System.out.println("Invalid number of players.  For U8 between 4 and 8 players are required");
                return;
            } else {
                players = getPlayersFromArg(args);
                game = new Game(AgeGroup.U8, players);
            }

        } else {
            System.out.println("Invalid age-group \"" + args[0] + "\" for league game.  Specify u6 or u8.");
            return;
        }

        printGameSubstitutionList(game);
        game.updatePlayerTimes();
        printPlayerTimes(players);
    }

    private static void showHelp() {
        System.out.println("Usage: <age-group> <player-name> <player-name> ... <player-name>");
        System.out.println();
        System.out.println("  <age-group> \"u6\" or \"u8\" depending on which league the game is in");
        System.out.println("  <player-name> space separated list of player names");
    }

    private static List<Player> getPlayersFromArg(String[] args) {
        List<Player> playerList = new ArrayList<Player>();

        for (int i=1; i < args.length; i++) {
            playerList.add(new Player(args[i]));
        }

        return playerList;
    }

    private static void printGameSubstitutionList(Game game) {
        int numberOfPlayers = game.getGameSupstitutions().get(0).size();
        int numberOfPeriods = game.getGameSupstitutions().size();

        for (int column=0; column < numberOfPeriods; column++) {
            System.out.format(" %1$-15s ", column * game.getSubstitutionTimeMinutes());
            if(column == (numberOfPeriods / 2) - 1) {
                System.out.print("|");
            }
        }
        System.out.println();

        for (int column=0; column < numberOfPeriods; column++) {
            System.out.print(" --------------- ");
            if(column == (numberOfPeriods / 2) - 1) {
                System.out.print("|");
            }
        }
        System.out.println();

        for (int row=0; row < numberOfPlayers; row++) {
            for (int column=0; column < numberOfPeriods; column++) {
                System.out.format(" %1$-15s ", game.getGameSupstitutions().get(column).get(row).getName());
                if(column == (numberOfPeriods / 2) - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    private static void printPlayerTimes(List<Player> players) {
        System.out.println();
        System.out.println("Total play times in minutes:");
        Collections.sort(players, Player.PlayTimeComparator);
        for (Player player : players) {
            System.out.format("  %1$-15s%2$-3s%n", player.getName(), player.getPlayTimeMinutes());
        }
    }
}