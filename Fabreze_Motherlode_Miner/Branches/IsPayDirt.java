package Fabreze.bots.Fabreze_Motherlode_Miner.Branches;

import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.BankfromSack;
import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.DepositInHopper;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsPayDirt extends BranchTask {

    private BankfromSack bankfromSack = new BankfromSack();
    private DepositInHopper depositInHopper = new DepositInHopper();
    @Override
    public boolean validate(){
        return Inventory.contains("Pay-dirt");
    }

    @Override
    public TreeTask successTask(){return depositInHopper;}
    public TreeTask failureTask(){return bankfromSack;}
}
