package Fabreze.bots.Fabreze_Blast_Furnace.Coal2.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal0.IsOreInInventory;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.MoveToDispenser;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOreDeposited extends BranchTask {

    private IsOreInInventory isOreInInventory = new IsOreInInventory();
    private MoveToDispenser moveToDispenser = new MoveToDispenser();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).isoredeposited;
    }
    @Override
    public TreeTask successTask() { return moveToDispenser; }

    @Override
    public TreeTask failureTask() { return isOreInInventory; }
}
