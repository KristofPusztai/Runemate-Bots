package Fabreze.bots.Fabreze_Motherlode_Miner;

import Fabreze.bots.Fabreze_Motherlode_Miner.Branches.IsGem;
import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.BreakhandlerLogOut;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class OnBreak extends BranchTask {

    private MotherlodeMain bot = (MotherlodeMain) Environment.getBot();

    private BreakhandlerLogOut breakhandlerLogOut = new BreakhandlerLogOut();
    private IsGem isGem = new IsGem();

    @Override
    public boolean validate(){
        return bot.onbreak;
    }
    @Override
    public TreeTask successTask() { return breakhandlerLogOut; }

    @Override
    public TreeTask failureTask() { return isGem; }
}
