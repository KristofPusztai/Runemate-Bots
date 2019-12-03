package Fabreze.bots.Fabreze_Blast_Furnace.GUI;

import javafx.util.StringConverter;

public class BarConverter extends StringConverter<Bar> {

    @Override
    public String toString(Bar bar) {
        return bar.getname();
    }

    @Override
    public Bar fromString(String name) {
        return null;
    }
}
