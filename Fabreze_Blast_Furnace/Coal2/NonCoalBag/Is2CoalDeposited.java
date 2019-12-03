package Fabreze.bots.Fabreze_Blast_Furnace.Coal2.NonCoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Branches.IsCoalInInventory1;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal0.IsOreDeposited;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is2CoalDeposited extends BranchTask {

    private IsOreDeposited isOreDeposited = new IsOreDeposited();
    private IsCoalInInventory1 isCoalInInventory1 = new IsCoalInInventory1();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).depositedcoalamt >= 2;
    }
    @Override
    public TreeTask successTask() { return isOreDeposited; }

    @Override
    public TreeTask failureTask() { return isCoalInInventory1; }

}
