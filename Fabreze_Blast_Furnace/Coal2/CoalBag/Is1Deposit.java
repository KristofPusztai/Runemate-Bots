package Fabreze.bots.Fabreze_Blast_Furnace.Coal2.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is1Deposit extends BranchTask {

    private IsCoalBagFull isCoalBagFull = new IsCoalBagFull();
    private Is2CoalDeposited is2CoalDeposited = new Is2CoalDeposited();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).depositedcoalamt >= 1;
    }
    @Override
    public TreeTask successTask() { return is2CoalDeposited; }

    @Override
    public TreeTask failureTask() { return isCoalBagFull; }
}
