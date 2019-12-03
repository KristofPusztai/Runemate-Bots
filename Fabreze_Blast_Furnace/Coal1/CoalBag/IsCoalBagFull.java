package Fabreze.bots.Fabreze_Blast_Furnace.Coal1.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Branches.IsCoalInInventory1;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.WithdrawCoal;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCoalBagFull extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private WithdrawCoal withdrawCoal = new WithdrawCoal();
    private IsCoalInInventory1 isCoalInInventory1 = new IsCoalInInventory1();

    @Override
    public boolean validate(){
        return bot.coalbagamt > 0;
    }
    @Override
    public TreeTask successTask() { return withdrawCoal; }

    @Override
    public TreeTask failureTask() { return isCoalInInventory1; }

}
