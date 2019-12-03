package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.DepositCoins;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabCoins;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class CoinsInInventory extends BranchTask {

    private DepositCoins depositCoins = new DepositCoins();
    private GrabCoins grabCoins = new GrabCoins();

    @Override
    public boolean validate(){
        return Inventory.contains("Coins");
    }
    @Override
    public TreeTask successTask() { return depositCoins; }

    @Override
    public TreeTask failureTask() { return grabCoins; }
}
