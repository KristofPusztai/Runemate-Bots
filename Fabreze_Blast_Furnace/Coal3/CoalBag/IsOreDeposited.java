package Fabreze.bots.Fabreze_Blast_Furnace.Coal3.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal0.IsOreInInventory;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOreDeposited extends BranchTask {

    private IsOreInInventory isOreInInventory = new IsOreInInventory();
    private Is3CoalDeposited is3CoalDeposited = new Is3CoalDeposited();

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();


    @Override
    public boolean validate(){
        return bot.isoredeposited;
    }
    @Override
    public TreeTask successTask() { return is3CoalDeposited; }

    @Override
    public TreeTask failureTask() { return isOreInInventory; }
}
