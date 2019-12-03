package Fabreze.bots.Fabreze_Blast_Furnace.Coal3;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal3.CoalBag.Is0Deposit;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal3.NonCoalBag.Is4CoalDeposited;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCoalBag3 extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private Is4CoalDeposited is4CoalDeposited = new Is4CoalDeposited();
    private Is0Deposit is0Deposit = new Is0Deposit();

    @Override
    public boolean validate(){
        return bot.coalbag;
    }
    @Override
    public TreeTask successTask() { return is0Deposit; }

    @Override
    public TreeTask failureTask() { return is4CoalDeposited; }
}
