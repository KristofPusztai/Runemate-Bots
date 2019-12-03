package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsGoldsmithGauntletSelected extends BranchTask {

    private IsGoldsmithGauntletEquipped isGoldsmithGauntletEquipped = new IsGoldsmithGauntletEquipped();
    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public boolean validate(){
        return bot.goldsmithgauntlets;
    }
    @Override
    public TreeTask successTask() { return isGoldsmithGauntletEquipped; }

    @Override
    public TreeTask failureTask() { return bot.coalclass; }

}
