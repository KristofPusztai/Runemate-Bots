package Fabreze.bots.Fabreze_Agility.MarksOfGrace;

import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsRunEnabled extends BranchTask {

    BranchTask nextclass;

    private ToggleRun toggleRun = new ToggleRun();

    public IsRunEnabled(BranchTask nextclass){
        this.nextclass = nextclass;
    }

    @Override
    public boolean validate(){
        return Traversal.getRunEnergy()>60 && !Traversal.isRunEnabled();
    }
    @Override
    public TreeTask successTask() { return toggleRun; }

    @Override
    public TreeTask failureTask() { return new IsMarksOfGrace(nextclass); }
}
