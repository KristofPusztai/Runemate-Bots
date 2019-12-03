package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCoinCofferFull extends BranchTask {

    private CoinsInInventory coinsInInventory = new CoinsInInventory();
    private IsInventoryFull isInventoryFull = new IsInventoryFull();

    Varbit coincoffer = Varbits.load(5357);

    @Override
    public boolean validate(){
        return coincoffer.getValue()>0;
    }
    @Override
    public TreeTask successTask() { return isInventoryFull; }

    @Override
    public TreeTask failureTask() { return coinsInInventory; }

}
