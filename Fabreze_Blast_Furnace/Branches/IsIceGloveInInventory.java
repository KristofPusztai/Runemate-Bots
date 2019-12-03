package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabIceGloves;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsIceGloveInInventory extends BranchTask {

    private GrabIceGloves grabIceGloves = new GrabIceGloves();
    private IsGoldsmithGauntlet isGoldsmithGauntlet = new IsGoldsmithGauntlet();

    @Override
    public boolean validate(){
        return Inventory.contains("Ice gloves") || Equipment.contains("Ice gloves");
    }
    @Override
    public TreeTask successTask() { return isGoldsmithGauntlet; }

    @Override
    public TreeTask failureTask() { return grabIceGloves; }
}
