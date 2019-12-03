package Fabreze.bots.Fabreze_Cannoner.Branches.Banking;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.Bank.BankInventory;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInventoryFull extends BranchTask {

    private CannonMain bot = (CannonMain) Environment.getBot();

    private BankInventory bankInventory = new BankInventory();
    private IsFoodOut isFoodOut = new IsFoodOut();

    @Override
    public boolean validate(){
        boolean bankwithfood = false;
        if (bot.bankinventory && Inventory.isFull()){
            SpriteItem food = Inventory.newQuery().actions("Eat").results().first();
            bankwithfood = (food == null || !bot.eatfoodtoloot);
        }
        return bankwithfood;
    }
    @Override
    public TreeTask successTask() {
        if (!bot.bank){
            bot.bank = true;
        }
        return bankInventory;
    }

    @Override
    public TreeTask failureTask() { return isFoodOut; }
}
