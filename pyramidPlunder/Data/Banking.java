package com.darksage.bots.pyramidPlunder.Data;

/**
 * Created by Team Regal
 */
public enum Banking {

    LOCAL("Local", true),
    CASTLE_WARS("Castle Wars", false);

    private String bankName;
    private boolean isLocalBanking;

    Banking(String bankName, boolean isLocalBanking) {
        this.bankName = bankName;
        this.isLocalBanking = isLocalBanking;
    }

    public String getBankName() {
        return bankName;
    }

    public boolean isLocalBanking() {
        return isLocalBanking;
    }

    public static String[] getChoices() {
        String[] names = new String[values().length];
        for (int i = 0; i < names.length; i++) {
            names[i] = values()[i].getBankName();
        }
        return names;
    }

    public static Banking getData(String type) {
        Banking[] names = Banking.values();
        for (int i = 0; i < names.length; i++) {
            if (names[i].getBankName().equals(type)) {
                return names[i];
            }
        }
        return null;
    }
}
