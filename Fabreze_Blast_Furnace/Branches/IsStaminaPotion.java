package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.util.Regex;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsStaminaPotion extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private IsBucketOfWaterSelected isBucketOfWaterSelected = new IsBucketOfWaterSelected();
    private HasStaminaInInvy hasStaminaInInvy = new HasStaminaInInvy();

    @Override
    public boolean validate(){
        //Run stamina toggle checks
        int runenergy = Traversal.getRunEnergy();
        if (!Traversal.isRunEnabled() && (runenergy >= 50 || (Traversal.isStaminaEnhanced() && runenergy >= 10)) && Traversal.toggleRun()){
            Execution.delayUntil(Traversal::isRunEnabled, 2000);
        }
        //Validation starts here
        if (bot.isoredeposited && bot.depositedcoalamt >= bot.coalcost && !Inventory.contains(Regex.getPatternForContainsString("Stamina pot"))){
            return false;
        }
        else {return bot.staminapot;}
    }
    @Override
    public TreeTask successTask() { return hasStaminaInInvy; }

    @Override
    public TreeTask failureTask() { return isBucketOfWaterSelected; }
}
