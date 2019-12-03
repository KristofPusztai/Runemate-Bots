package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.SafeLogOut;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasCBalls extends BranchTask {

    private CannonMain bot = (CannonMain) Environment.getBot();

    private SafeLogOut safeLogOut = new SafeLogOut();
    private IsPoisoned isPoisoned = new IsPoisoned();

    @Override
    public boolean validate(){
        if (!RuneScape.isLoggedIn()){
            return true;
        }
        else {
            return !bot.cannonballcheck || Inventory.contains("Cannonball");
        }
    }


    @Override
    public TreeTask successTask() { return isPoisoned; }

    @Override
    public TreeTask failureTask() { return safeLogOut; }

}
