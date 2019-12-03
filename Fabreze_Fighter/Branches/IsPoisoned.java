package Fabreze.bots.Fabreze_Fighter.Branches;

import com.runemate.game.api.hybrid.local.hud.interfaces.Health;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPoisoned extends BranchTask {

    private HasAnti hasAnti = new HasAnti();
    private IsHealthLow isHealthLow = new IsHealthLow();

    @Override
    public boolean validate(){

        return Health.isPoisoned();
    }
    @Override
    public TreeTask successTask() { return hasAnti; }

    @Override
    public TreeTask failureTask() { return isHealthLow; }

}
