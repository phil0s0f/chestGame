package com.example.chestgame;

import java.io.Serializable;

public class Game implements Serializable {
    private int countRounds;
    private int countTraps;
    private int countChests;

    public int getCountRounds() {
        return countRounds;
    }

    public void setCountRounds(int countRounds) {
        this.countRounds = countRounds;
    }

    public int getCountTraps() {
        return countTraps;
    }

    public void setCountTraps(int countTraps) {
        this.countTraps = countTraps;
    }

    public int getCountChests() {
        return countChests;
    }

    public void setCountChests(int countChests) {
        this.countChests = countChests;
    }

    public Game(int countRounds, int countTraps, int countChests) {
        this.countRounds = countRounds;
        this.countTraps = countTraps;
        this.countChests = countChests;
    }
}
