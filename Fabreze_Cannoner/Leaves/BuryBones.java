package Fabreze.bots.Fabreze_Cannoner.Leaves;

import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class BuryBones extends LeafTask {
    @Override
    public void execute(){
        SpriteItem bones = Inventory.newQuery().names(Regex.getPatternsForContainsStrings("bones", "Bones")).results().first();

        if (bones != null && bones.interact("Bury")){
            Execution.delayUntil(() -> !bones.isValid(), 1000);
        }
    }
}
