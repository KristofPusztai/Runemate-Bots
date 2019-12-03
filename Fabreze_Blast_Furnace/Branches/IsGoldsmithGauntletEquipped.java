package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.EquipGoldsmithGauntlets;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsGoldsmithGauntletEquipped extends BranchTask {

    private EquipGoldsmithGauntlets equipGoldsmithGauntlets = new EquipGoldsmithGauntlets();
    private BranchTask CoalClass = ((BlastFurnaceMain)Environment.getBot()).coalclass;

    @Override
    public boolean validate(){
        return Equipment.contains("Goldsmith gauntlets");
    }
    @Override
    public TreeTask successTask() { return CoalClass; }

    @Override
    public TreeTask failureTask() { return equipGoldsmithGauntlets; }

}
