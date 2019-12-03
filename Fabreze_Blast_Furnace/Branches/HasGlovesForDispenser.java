package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.EquipIceGloves;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabBars;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HasGlovesForDispenser extends BranchTask {

    private GrabBars grabBars = new GrabBars();
    private EquipIceGloves equipIceGloves = new EquipIceGloves();

    @Override
    public boolean validate(){
        return Equipment.contains("Ice gloves");
    }
    @Override
    public TreeTask successTask() { return grabBars; }

    @Override
    public TreeTask failureTask() { return equipIceGloves; }

}
