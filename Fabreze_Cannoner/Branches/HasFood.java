package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.SafeLogOut;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasFood extends BranchTask {

    private CannonMain bot = (CannonMain) Environment.getBot();

    private HasCBalls hasCBalls = new HasCBalls();
    private SafeLogOut safeLogOut = new SafeLogOut();

    @Override
    public boolean validate(){
        if (!RuneScape.isLoggedIn()){
            return true;
        }
        else {
            SpriteItem food = Inventory.newQuery().actions("Eat").results().first();
            return !bot.foodcheck || food != null;
        }
    }
    @Override
    public TreeTask successTask() { return hasCBalls; }

    @Override
    public TreeTask failureTask() { return safeLogOut; }

}
