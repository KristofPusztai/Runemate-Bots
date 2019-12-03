package Fabreze.bots.Fabreze_Blast_Furnace.Coal0;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.DepositOre;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.GrabOre;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOreInInventory extends BranchTask {

    private GrabOre grabOre = new GrabOre();
    private DepositOre depositOre = new DepositOre();

    @Override
    public boolean validate(){
        return Inventory.contains(((BlastFurnaceMain) Environment.getBot()).Ore);
    }
    @Override
    public TreeTask successTask() { return depositOre; }

    @Override
    public TreeTask failureTask() { return grabOre; }

}
