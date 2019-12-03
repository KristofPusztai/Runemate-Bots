package Fabreze.bots.Fabreze_Agility.GUI;

import com.runemate.game.api.script.framework.tree.BranchTask;

public class Course {

    private String name;
    private BranchTask startclass;

    public Course(BranchTask startclass, String name){
        this.name = name;
        this.startclass = startclass;
    }
    String getname(){
        return name;
    }

    BranchTask getclass(){
        return startclass;
    }
}
