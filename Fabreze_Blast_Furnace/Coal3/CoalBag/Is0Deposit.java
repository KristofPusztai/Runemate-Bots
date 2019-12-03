package Fabreze.bots.Fabreze_Blast_Furnace.Coal3.CoalBag;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Is0Deposit extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private IsInvBagFull isInvBagFull = new IsInvBagFull();
    private Is1Deposit is1Deposit = new Is1Deposit();


    @Override
    public boolean validate(){
        return bot.depositedcoalamt ==0 ;
    }
    @Override
    public TreeTask successTask() { return isInvBagFull; }

    @Override
    public TreeTask failureTask() { return is1Deposit; }
}
