package com.niesens.soccersubhelper.engine;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;

public class Player {
    private String name;
    private int playTimeMinutes;
    private List<Integer> playTimeHistory;

    public Player(String name) {
        if (StringUtils.isBlank(name)) {
            throw new IllegalArgumentException("Invalid name: " + name);
        }

        this.name = name;
        this.playTimeMinutes = 0;
    }

    public String getName() {
        return name;
    }

    public void setPlayTimeMinutes(int playTimeMinutes) {
        this.playTimeMinutes = playTimeMinutes;
    }

    public int getPlayTimeMinutes() {
        return playTimeMinutes;
    }

    public void addPlayTimeMinutes(int playTimeMinutes) {
        this.playTimeMinutes = this.playTimeMinutes + playTimeMinutes;
    }


    /**
     * Comparator to order players by play time from least to most.
     */
    public static Comparator<Player> PlayTimeComparator  = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getPlayTimeMinutes() - p2.getPlayTimeMinutes();
        }
    };
}