package Fabreze.bots.Fabreze_Minnow_Fisher.GUI;

import javafx.beans.property.SimpleStringProperty;

public class Break {
    private SimpleStringProperty number;
    private SimpleStringProperty startsat;
    private SimpleStringProperty endsat;
    Break(String number, String startsat, String endsat){
        this.number = new SimpleStringProperty(number);
        this.startsat = new SimpleStringProperty(startsat);
        this.endsat = new SimpleStringProperty(endsat);
    }
    public String getNumber(){
        return number.get();
    }

    public String getStartsat(){
        return startsat.get();
    }

    public String getEndsat(){ return endsat.get(); }

    void setStartat(String newstartsat){
        this.startsat.set(newstartsat);
    }
    void setEndsat (String newendat){this.endsat.set(newendat);}
}
