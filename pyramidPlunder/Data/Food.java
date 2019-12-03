package com.darksage.bots.pyramidPlunder.Data;

/**
 * Created by Team Regal
 */
public enum Food {

    SHARK("Sharks", "Shark"),
    MONKFISH("Monkfish", "Monkfish"),
    KARAMBWUAN("Karambwuan", "Cooked karambwuan"),
    SWORDFISH("Swordfish", "Swordfish"),
    TUNA("Tunas", "Tuna"),
    LOBSTER("Lobsters", "Lobster");

    private String choice;
    private String foodName;

    Food(String choice, String foodName) {
        this.choice = choice;
        this.foodName = foodName;
    }

    public String getChoice() {
        return choice;
    }

    public String getFoodName() {
        return foodName;
    }

    public static String[] getNames() {
        String[] names = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            names[i] = values()[i].getChoice();
        }
        return names;
    }

    public static Food getValue(String food) {
        Food[] names = Food.values();
        for (int i = 0; i < names.length; i++) {
            if (names[i].choice.equals(food)) {
                return names[i];
            }
        }
        return null;
    }
}
