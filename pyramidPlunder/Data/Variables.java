package com.darksage.bots.pyramidPlunder.Data;

import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;

import java.util.regex.Pattern;

/**
 * Created by Team Regal
 */
public class Variables {

    /*
     * Areas and Coordinates
     */

    public static Area.Circular BANK_AREA = new Area.Circular(new Coordinate(2799, 5169, 0), 4);
    public static final Area.Rectangular CASTLE_WARS = new Area.Rectangular(new Coordinate(2436, 3095, 0), new Coordinate(2443, 3082, 0));
    //create circular with new coordinate
    public static final Area.Rectangular BANK_CHEST = new Area.Rectangular(new Coordinate(2443, 3082, 0), new Coordinate(2442, 3084, 0));

    /*
     * Constants
     */

    public static final Pattern ANTIDOTE = Pattern.compile("Antidote++");
    public static final String MUMMY = "Guardian mummy";
    public static final String START = "Start-minigame";
    public static final String LEAVE = "Leave Tomb";
    public static final String SCEPTRE_SPELL = "Jalsavrah";
    public static Pattern RING_DUELING = Pattern.compile("Ring of dueling");
    public static Pattern SCEPTRE = Pattern.compile("Pharaoh's sceptre");

    private int currentRoom;
    private int finalRoom = 0;
    private int currentLevel;
    private String food;
    private Pattern potion;
    public static final Pattern goeldnRecharge = Pattern.compile("Golden ");
    public static final Pattern stoneRecharge = Pattern.compile("Stone ");
    public static final Pattern potteryRecharge = Pattern.compile("Pottery ");
    public static final int GOLD_ITEMS = 6;
    public static final int STONE_ITEMS = 12;
    public static final int POTTERY_ITEMS = 24;

    private boolean northDoor;
    private boolean eastDoor;
    private boolean southDoor;
    private boolean westDoor;
    private boolean isUiSet;
    private boolean isLocalBanking = true;
    private boolean isSceptreUsed = false;
    private boolean isSceptreCharged;
    private boolean isBanked;

    private String[] loots = {"Stone scarab", "Golden scarab", "Stone seal", "Golden seal", "Stone statuette", "Golden statuette"};

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public int getFinalRoom() {
        return finalRoom;
    }

    public void setFinalRoom(int finalRoom) {
        this.finalRoom = finalRoom;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public boolean isNorthDoor() {
        return northDoor;
    }

    public void setNorthDoor(boolean northDoor) {
        this.northDoor = northDoor;
    }

    public boolean isEastDoor() {
        return eastDoor;
    }

    public void setEastDoor(boolean eastDoor) {
        this.eastDoor = eastDoor;
    }

    public boolean isSouthDoor() {
        return southDoor;
    }

    public void setSouthDoor(boolean southDoor) {
        this.southDoor = southDoor;
    }

    public boolean isWestDoor() {
        return westDoor;
    }

    public void setWestDoor(boolean westDoor) {
        this.westDoor = westDoor;
    }

    public boolean isUiSet() {
        return isUiSet;
    }

    public void setUiSet(boolean uiSet) {
        isUiSet = uiSet;
    }

    public String[] getLoots() {
        return loots;
    }

    public void setLoots(String[] loots) {
        this.loots = loots;
    }

    public boolean isLocalBanking() {
        return isLocalBanking;
    }

    public void setLocalBanking(boolean localBanking) {
        isLocalBanking = localBanking;
    }

    public boolean isSceptreUsed() {
        return isSceptreUsed;
    }

    public void setSceptreUsed(boolean sceptreUsed) {
        isSceptreUsed = sceptreUsed;
    }

    public boolean isBanked() {
        return isBanked;
    }

    public void setBanked(boolean banked) {
        isBanked = banked;
    }

    public boolean isSceptreCharged() {
        return isSceptreCharged;
    }

    public void setSceptreCharged(boolean sceptreCharged) {
        isSceptreCharged = sceptreCharged;
    }

    public Pattern getPotion() {
        return potion;
    }

    public void setPotion(Pattern potion) {
        this.potion = potion;
    }
}
