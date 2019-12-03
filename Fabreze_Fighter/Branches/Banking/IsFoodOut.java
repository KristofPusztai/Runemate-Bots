package Fabreze.bots.Fabreze_Fighter.Branches.Banking;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import Fabreze.bots.Fabreze_Fighter.Leaves.Bank.GrabItems;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsFoodOut extends BranchTask {

    private FighterMain bot = (FighterMain) Environment.getBot();

    private IsPotOut isPotOut = new IsPotOut();
    private GrabItems grabItems = new GrabItems();

    @Override
    public boolean validate(){
        SpriteItem food = Inventory.newQuery().actions("Eat").results().first();

        return bot.bank || (bot.bankfood && food == null);
    }
    @Override
    public TreeTask successTask() { return grabItems; }

    @Override
    public TreeTask failureTask() { return isPotOut; }
}
