package com.darksage.bots.pyramidPlunder.Data;

/**
 * Created by Team Regal
 */
public enum PyramidReturn {

    TRUE("True", true),
    FALSE("False", false);

    private String choice;
    private boolean isSceptre;

    PyramidReturn(String choice, boolean isSceptre) {
        this.choice = choice;
        this.isSceptre = isSceptre;
    }

    public String getChoice() {
        return choice;
    }

    public boolean isSceptre() {
        return isSceptre;
    }

    public static String[] getChoices() {
        String[] names = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            names[i] = values()[i].getChoice();
        }
        return names;
    }

    public static PyramidReturn getData(String type) {
        PyramidReturn[] names = PyramidReturn.values();
        for (int i = 0; i < names.length; i++) {
            if (names[i].getChoice().equals(type)) {
                return names[i];
            }
        }
        return null;
    }
}
