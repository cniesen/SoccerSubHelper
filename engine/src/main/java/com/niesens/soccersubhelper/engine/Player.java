package com.niesens.soccersubhelper.engine;

import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

public class Player {
    private String name;
    private int playTimeMinutes;

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
     * Comparator to order players alphabetically by name.
     */
    public static Comparator<Player> NameComparator = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return (p1.getName().compareTo(p2.getName()));
        }
    };

    /**
     * Comparator to order players by play time from least to most.
     */
    public static Comparator<Player> PlayTimeComparator  = new Comparator<Player>() {
        @Override
        public int compare(Player p1, Player p2) {
            return p1.getPlayTimeMinutes() - p2.getPlayTimeMinutes();
        }
    };

    public String toString() {
        return name;
    }
}