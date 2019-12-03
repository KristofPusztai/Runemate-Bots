package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.EquipIceGloves;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsIceGloveEquipped extends BranchTask {

    private EquipIceGloves equipIceGloves = new EquipIceGloves();
    private IsGoldsmithGauntletSelected isGoldsmithGauntletSelected = new IsGoldsmithGauntletSelected();

    @Override
    public boolean validate(){
        return Equipment.contains("Ice gloves");
    }
    @Override
    public TreeTask successTask() { return isGoldsmithGauntletSelected; }

    @Override
    public TreeTask failureTask() { return equipIceGloves; }

}
