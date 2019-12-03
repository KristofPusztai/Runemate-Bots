package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsIceGloveSelected extends BranchTask {

    private IsIceGloveInInventory isIceGloveInInventory = new IsIceGloveInInventory();
    private IsGoldsmithGauntletSelected isGoldsmithGauntletSelected = new IsGoldsmithGauntletSelected();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).icegloves;
    }
    @Override
    public TreeTask successTask() { return isIceGloveInInventory; }

    @Override
    public TreeTask failureTask() { return isGoldsmithGauntletSelected; }

}
