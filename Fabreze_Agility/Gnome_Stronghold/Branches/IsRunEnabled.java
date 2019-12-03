package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Branches;

import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves.ToggleRun;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsRunEnabled extends BranchTask {

    private IsAtStart isAtStart = new IsAtStart();
    private ToggleRun toggleRun = new ToggleRun();

    @Override
    public boolean validate(){
        return Traversal.getRunEnergy()>60 && !Traversal.isRunEnabled();
    }

    @Override
    public TreeTask successTask(){return toggleRun; }

    @Override
    public TreeTask failureTask(){ return isAtStart; }
}
