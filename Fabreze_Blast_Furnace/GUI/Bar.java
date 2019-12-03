package Fabreze.bots.Fabreze_Blast_Furnace.GUI;

import com.runemate.game.api.script.framework.tree.BranchTask;

public class Bar {
    private String name;
    private String ores;
    private BranchTask coalclass;
    private int coalcost;

    public Bar(String name, String ores, BranchTask coalclass, int coalcost){
        this.name = name;
        this.ores = ores;
        this.coalclass = coalclass;
        this.coalcost = coalcost;
    }
    String getname(){
        return name;
    }

    String getOres(){ return ores; }

    BranchTask getCoalclass(){ return coalclass; }

    int getCoalCost(){ return coalcost;}
}

