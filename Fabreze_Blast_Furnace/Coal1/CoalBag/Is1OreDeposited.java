package Fabreze.bots.Fabreze_Blast_Furnace.Coal1.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal0.IsOreInInventory;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is1OreDeposited extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private IsOreInInventory isOreInInventory = new IsOreInInventory();
    private Is2CoalDeposited is2CoalDeposited = new Is2CoalDeposited();

    @Override
    public boolean validate(){
        return bot.isoredeposited;
    }
    @Override
    public TreeTask successTask() { return is2CoalDeposited; }

    @Override
    public TreeTask failureTask() { return isOreInInventory; }

}
