package com.darksage.bots.pyramidPlunder.Data;

/**
 * Created by Team Regal
 */
public enum NamesActions {

    SPEARTRAP("Speartrap", "Pass", 2365, 1, 0),
    DOORS("An anonymous looking door", "Search", 2371, 0, 2),
    TOMB_DOOR("Tomb Door", "Pick-lock", 0, 0, 0),
    URN("Urn", "Check for Snakes", 0, 0, 0),
    SARCOPHAGUS("Sarcophagus", "Open", 0, 0, 0),
    CHEST("Grand Gold Chest", "Search", 2363, 1, 0);

    private String name;
    private String action;
    private int varbitIndex;
    private int varbitStart;
    private int varbitGoal;

    NamesActions(String name, String action, int varbitIndex, int varbitStart, int varbitGoal) {
        this.name = name;
        this.action = action;
        this.varbitIndex = varbitIndex;
        this.varbitStart = varbitStart;
        this.varbitGoal = varbitGoal;
    }

    public String getName() {
        return name;
    }

    public String getAction() {
        return action;
    }

    public int getVarbitIndex() {
        return varbitIndex;
    }

    public int getVarbitStart() {
        return varbitStart;
    }

    public int getVarbitGoal() {
        return varbitGoal;
    }

}