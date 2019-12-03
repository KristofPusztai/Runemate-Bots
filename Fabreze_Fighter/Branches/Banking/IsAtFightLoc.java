package Fabreze.bots.Fabreze_Fighter.Branches.Banking;

import Fabreze.bots.Fabreze_Fighter.Branches.HasFood;
import Fabreze.bots.Fabreze_Fighter.FighterMain;
import Fabreze.bots.Fabreze_Fighter.Leaves.Bank.MoveToFightLoc;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtFightLoc extends BranchTask {
    private FighterMain bot = (FighterMain) Environment.getBot();

    private HasFood hasFood = new HasFood();
    private MoveToFightLoc moveToFightLoc = new MoveToFightLoc();

    @Override
    public boolean validate(){
        return bot.location.isLoaded();
    }
    @Override
    public TreeTask successTask() { return hasFood; }

    @Override
    public TreeTask failureTask() { return moveToFightLoc; }
}
