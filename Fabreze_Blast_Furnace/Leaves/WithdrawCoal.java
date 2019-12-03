package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class WithdrawCoal extends LeafTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public void execute(){
        SpriteItem coalbag = Inventory.newQuery().names("Coal bag").results().first();
        if (coalbag != null && coalbag.interact("Empty")){
            Execution.delayUntil(() -> Inventory.contains("Coal"), 3000);
            if (Inventory.contains("Coal")){
                bot.coalbagamt -= Inventory.getQuantity("Coal");
                bot.iscoalneeded = true;
            }
        }
    }
}
