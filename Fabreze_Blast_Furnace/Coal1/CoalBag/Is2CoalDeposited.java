package Fabreze.bots.Fabreze_Blast_Furnace.Coal1.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.MoveToDispenser;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is2CoalDeposited extends BranchTask {
    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private IsCoalBagFull isCoalBagFull = new IsCoalBagFull();
    private MoveToDispenser moveToDispenser = new MoveToDispenser();

    @Override
    public boolean validate(){
        return bot.depositedcoalamt >= 2;
    }
    @Override
    public TreeTask successTask() { return moveToDispenser; }

    @Override
    public TreeTask failureTask() { return isCoalBagFull; }
}
