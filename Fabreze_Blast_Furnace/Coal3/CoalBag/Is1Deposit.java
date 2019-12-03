package Fabreze.bots.Fabreze_Blast_Furnace.Coal3.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal2.CoalBag.IsCoalBagFull;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is1Deposit extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private IsCoalBagFull isCoalBagFull = new IsCoalBagFull();
    private Is2CoalDeposited is2CoalDeposited = new Is2CoalDeposited();

    @Override
    public boolean validate(){
        return bot.depositedcoalamt >= 1;
    }
    @Override
    public TreeTask successTask() { return is2CoalDeposited; }

    @Override
    public TreeTask failureTask() { return isCoalBagFull; }
}
