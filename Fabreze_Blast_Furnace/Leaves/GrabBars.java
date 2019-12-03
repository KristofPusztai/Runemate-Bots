package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GrabBars extends LeafTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public void execute(){
        GameObject oredispenser = GameObjects.newQuery().names("Bar dispenser").results().nearest();
        InterfaceContainer orecomponent = InterfaceContainers.getAt(270);

            //fix for bucket selected making bot stuck
        if (Inventory.isItemSelected() && Inventory.getSelectedItem() != null){
            Inventory.getSelectedItem().click();
            Execution.delayUntil(() -> !Inventory.isItemSelected(), 2000);
            return;

            //if statements for getting bars
        }

        if (orecomponent != null && orecomponent.getComponent(12).getActions().isEmpty() && orecomponent.getComponent(14) != null){
            Execution.delay(200, 1000);
            Keyboard.typeKey(32);
            Execution.delayUntil(() -> Inventory.containsAnyOf("Iron bar", "Steel bar", "Mithril bar", "Gold bar", "Runite bar", "Adamantite bar"), 2000);
            bot.isoredeposited = false;
            bot.iscoalneeded = false;
            if (bot.bar.equals("Steel bars")){
                --bot.depositedcoalamt;
            }
            else{
                bot.depositedcoalamt = bot.depositedcoalamt - bot.coalcost;
            }
        }
        else if (orecomponent != null && !orecomponent.getComponent(12).getActions().isEmpty()){
            orecomponent.getComponent(12).click();
            Execution.delayUntil(() -> orecomponent.getComponent(12).getActions().isEmpty(), 2000);
        }
        else if (oredispenser != null && oredispenser.isVisible()){
            oredispenser.interact("Take");
            Execution.delayUntil(() -> InterfaceContainers.getAt(270) != null, 2000);
        }
        else {
            Camera.concurrentlyTurnTo(oredispenser);
        }

    }
}
