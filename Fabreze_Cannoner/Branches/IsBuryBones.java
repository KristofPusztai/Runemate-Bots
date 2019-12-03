package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.BuryBones;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsBuryBones extends BranchTask {
    private CannonMain bot = (CannonMain) Environment.getBot();


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
