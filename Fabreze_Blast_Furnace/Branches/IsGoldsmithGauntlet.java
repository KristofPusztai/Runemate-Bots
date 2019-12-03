package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsGoldsmithGauntlet extends BranchTask {

    private IsGoldsmithGauntletSelected isGoldsmithGauntletSelected = new IsGoldsmithGauntletSelected();
    private IsIceGloveEquipped isIceGloveEquipped = new IsIceGloveEquipped();

    @Override
    public boolean validate(){
        return ((BlastFurnaceMain) Environment.getBot()).goldsmithgauntlets;
    }
    @Override
    public TreeTask successTask() { return isGoldsmithGauntletSelected; }

    @Override
    public TreeTask failureTask() { return isIceGloveEquipped; }

}
