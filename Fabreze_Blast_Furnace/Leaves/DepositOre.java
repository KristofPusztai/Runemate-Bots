package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DepositOre extends LeafTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public void execute(){

        if (Bank.isOpen()){
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(),2000);
        }

        GameObject conveyorbelt = GameObjects.newQuery().names("Conveyor belt").actions("Put-ore-on").results().nearest();
        if (conveyorbelt != null && conveyorbelt.isVisible()){
            if ( conveyorbelt.interact("Put-ore-on")){
                Execution.delayUntil(() -> !Inventory.contains(bot.Ore),20000);
                if (!Inventory.contains(bot.Ore)){
                    bot.isoredeposited = true;
                }
            }
        }
        else {
            Camera.concurrentlyTurnTo(Random.nextInt(0, 21), Random.nextDouble(0.00, 0.408));
        }
    }
}