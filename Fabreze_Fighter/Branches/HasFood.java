package Fabreze.bots.Fabreze_Fighter.Branches;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import Fabreze.bots.Fabreze_Fighter.Leaves.SafeLogOut;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.RuneScape;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasFood extends BranchTask {

    private FighterMain bot = (FighterMain) Environment.getBot();

    private SafeLogOut safeLogOut = new SafeLogOut();
    private IsPoisoned isPoisoned = new IsPoisoned();

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
    public TreeTask successTask() { return isPoisoned; }

    @Override
    public TreeTask failureTask() { return safeLogOut; }

}
