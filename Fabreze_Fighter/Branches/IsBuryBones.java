package Fabreze.bots.Fabreze_Fighter.Branches;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import Fabreze.bots.Fabreze_Fighter.Leaves.BuryBones;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsBuryBones extends BranchTask {
    private FighterMain bot = (FighterMain) Environment.getBot();


    private BuryBones buryBones = new BuryBones();
    private IsLoot isLoot = new IsLoot();



    @Override
    public boolean validate(){
        return bot.burybones && Inventory.containsAnyOf(Regex.getPatternsForContainsStrings("bones", "Bones"));
    }
    @Override
    public TreeTask successTask() { return buryBones; }

    @Override
    public TreeTask failureTask() { return isLoot; }
}
