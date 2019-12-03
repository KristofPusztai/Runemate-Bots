package Fabreze.bots.Fabreze_Blast_Furnace.Coal2.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Branches.IsCoalInInventory;
import Fabreze.bots.Fabreze_Blast_Furnace.Branches.IsCoalInInventory1;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInvBagFull extends BranchTask {

    private IsCoalInInventory isCoalInInventory = new IsCoalInInventory();
    private IsCoalInInventory1 isCoalInInventory1 = new IsCoalInInventory1();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).coalbagamt > 0;
    }
    @Override
    public TreeTask successTask() { return isCoalInInventory1; }

    @Override
    public TreeTask failureTask() { return isCoalInInventory; }
}
