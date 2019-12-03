package Fabreze.bots.Fabreze_Cannoner.Branches.Banking;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.Bank.GrabItems;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsCBallOut extends BranchTask {
    private CannonMain bot = (CannonMain) Environment.getBot();

    private GrabItems grabItems = new GrabItems();
    private IsAtCannonLoc isAtCannonLoc = new IsAtCannonLoc();

    @Override
    public boolean validate(){
        return bot.bankcball && !Inventory.contains("Cannonball");
    }
    @Override
    public TreeTask successTask() { return grabItems; }

    @Override
    public TreeTask failureTask() { return isAtCannonLoc; }
}
