package Fabreze.bots.Fabreze_Blast_Furnace.Coal3.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal2.CoalBag.IsCoalBagFull;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is2CoalDeposited extends BranchTask {

    private IsCoalBagFull isCoalBagFull = new IsCoalBagFull();
    private HasBagFilled hasBagFilled = new HasBagFilled();
    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public boolean validate(){
        return bot.depositedcoalamt >= 2;
    }
    @Override
    public TreeTask successTask() { return hasBagFilled; }

    @Override
    public TreeTask failureTask() { return isCoalBagFull; }
}
