package Fabreze.bots.Fabreze_Blast_Furnace.Coal1.NonCoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal0.IsOreInInventory;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal1.NonCoalBag.Is1CoalDeposited;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.MoveToDispenser;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is1OreDeposited extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private IsOreInInventory isOreInInventory = new IsOreInInventory();
    private MoveToDispenser moveToDispenser = new MoveToDispenser();

    @Override
    public boolean validate(){
        return bot.isoredeposited;
    }
    @Override
    public TreeTask successTask() { return moveToDispenser; }

    @Override
    public TreeTask failureTask() { return isOreInInventory; }

}
