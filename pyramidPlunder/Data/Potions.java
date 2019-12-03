package com.darksage.bots.pyramidPlunder.Data;

/**
 * Created by Team Regal
 */
public enum Potions {

    ANTIDOTE_PLUS_2("Antidote++", "Antidote++"),
    ANTIDOTE_PLUS("Antidote+", "Antidote+"),
    ANTIDOTE_MIX("Antidote+ mix", "Antidote+ mix"),
    ANTIPOISON("Antipoison", "Antipoison"),
    ANTIPOISON_MIX("Antipoison mix", "Antipoison mix"),
    SUPERANTIPOISON("Super Antipoison", "Superantipoison");

    private String choice;
    private String potionPattern;

    Potions(String choice, String potionPattern) {
        this.choice = choice;
        this.potionPattern = potionPattern;
    }

    public String getChoice() {
        return choice;
    }

    public String getPotionPattern() {
        return potionPattern;
    }

    public static String[] getChoices() {
        String[] choices = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            choices[i] = values()[i].getChoice();
        }
        return choices;
    }

    public static Potions getValue(String potion) {
        Potions[] names = Potions.values();
        for (int i = 0; i < names.length; i++) {
            if (names[i].getChoice().equals(potion)) {
                return names[i];
            }
        }
        return null;
    }
}