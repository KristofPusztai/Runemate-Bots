package Fabreze.bots.Fabreze_Motherlode_Miner.GUI;

import javafx.util.StringConverter;

public class Converter extends StringConverter<Location> {

    @Override
    public String toString(Location location) {
        return location.getName();
    }

    @Override
    public Location fromString(String name) {
        return null;
    }
}
