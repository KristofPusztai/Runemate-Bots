package com.darksage.bots.pyramidPlunder.Data;

/**
 * Created by Team Regal
 */
public enum Sceptre {

    CHARGES_03("Pharaoh's sceptre (3)", false),
    CHARGES_02("Pharaoh's sceptre (2)", false),
    CHARGES_01("Pharaoh's sceptre (1)", true),
    CHARGES_00("Pharaoh's sceptre", true);

    private String sceptre;
    private boolean isRechargeNeeded;

    Sceptre(String sceptre, boolean isRechargeNeeded) {
        this.sceptre = sceptre;
        this.isRechargeNeeded = isRechargeNeeded;
    }

    public String getSceptre() {
        return sceptre;
    }

    public boolean isRechargeNeeded() {
        return isRechargeNeeded;
    }

    public static boolean isRechargeRequired(String sceptre) {
        Sceptre[] names = Sceptre.values();
        for (int i = 0; i < names.length; i++) {
            if (names[i].getSceptre().equals(sceptre))
                return names[i].isRechargeNeeded();
        }
        return false;
    }
}
