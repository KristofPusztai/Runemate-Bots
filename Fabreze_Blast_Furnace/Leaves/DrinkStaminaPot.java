package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DrinkStaminaPot extends LeafTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public void execute(){
        if (Bank.isOpen()){
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(), 2000);
            return;
        }

        Traversal.drinkStaminaEnhancer(true);

        SpriteItem vial = Inventory.newQuery().names("Vial").results().first();
        if (bot.dropvial && vial != null && vial.interact("Drop")){
            Execution.delayUntil(() -> !vial.isValid(), 2000);
        }
    }
}
