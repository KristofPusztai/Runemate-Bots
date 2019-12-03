package Fabreze.bots.Fabreze_Blast_Furnace.Coal2;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal2.CoalBag.Is0Deposit;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal2.NonCoalBag.Is2CoalDeposited;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCoalBag2  extends BranchTask {

    private Is2CoalDeposited is2CoalDeposited = new Is2CoalDeposited();
    private Is0Deposit is0Deposit = new Is0Deposit();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).coalbag;
    }
    @Override
    public TreeTask successTask() { return is0Deposit; }

    @Override
    public TreeTask failureTask() { return is2CoalDeposited; }

}
