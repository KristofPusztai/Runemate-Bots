package Fabreze.bots.Fabreze_Blast_Furnace.Coal3.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal2.CoalBag.IsCoalBagFull;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.MoveToDispenser;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is3CoalDeposited extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private MoveToDispenser moveToDispenser = new MoveToDispenser();
    private IsCoalBagFull isCoalBagFull = new IsCoalBagFull();

    @Override
    public boolean validate(){
        return bot.depositedcoalamt >= 3;
    }
    @Override
    public TreeTask successTask() { return moveToDispenser; }

    @Override
    public TreeTask failureTask() { return isCoalBagFull; }
}
