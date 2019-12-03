package Fabreze.bots.Fabreze_Blast_Furnace.Coal0;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.MoveToDispenser;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOreDeposited extends BranchTask {

    private MoveToDispenser moveToDispenser = new MoveToDispenser();
    private IsOreInInventory isOreInInventory = new IsOreInInventory();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).isoredeposited;
    }
    @Override
    public TreeTask successTask() { return moveToDispenser; }

    @Override
    public TreeTask failureTask() { return isOreInInventory; }

}
