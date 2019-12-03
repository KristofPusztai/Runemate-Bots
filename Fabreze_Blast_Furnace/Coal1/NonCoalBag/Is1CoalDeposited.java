package Fabreze.bots.Fabreze_Blast_Furnace.Coal1.NonCoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Branches.IsCoalInInventory1;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is1CoalDeposited extends BranchTask {

    private Is1OreDeposited is1OreDeposited = new Is1OreDeposited();
    private IsCoalInInventory1 isCoalInInventory1 = new IsCoalInInventory1();

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public boolean validate(){
        return bot.depositedcoalamt >= 1;
    }
    @Override
    public TreeTask successTask() { return is1OreDeposited; }

    @Override
    public TreeTask failureTask() { return isCoalInInventory1; }

}
