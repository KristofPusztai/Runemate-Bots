package Fabreze.bots.Fabreze_Blast_Furnace.Coal3.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Branches.IsCoalInInventory;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasBagFilled extends BranchTask {

    private IsOreDeposited isOreDeposited = new IsOreDeposited();
    private IsCoalInInventory isCoalInInventory = new IsCoalInInventory();

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public boolean validate(){
        return bot.coalbagamt > 0 || bot.isoredeposited;
    }
    @Override
    public TreeTask successTask() { return isOreDeposited; }

    @Override
    public TreeTask failureTask() { return isCoalInInventory; }
}
