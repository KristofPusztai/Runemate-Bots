package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.logger.BotLogger;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GrabOre extends LeafTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private PredefinedPath path = PredefinedPath.create(new Coordinate(1940, 4962, 0), new Coordinate(1948, 4957, 0));

    @Override
    public void execute(){
        GameObject bank = GameObjects.newQuery().names("Bank chest").actions("Use").results().nearest();
        SpriteItem ore = Bank.newQuery().names(bot.Ore).results().first();

        if (Bank.isOpen() && ore != null){
            ore.interact("Withdraw-All");
            Execution.delayUntil(() -> Inventory.contains(bot.Ore), 2000);
        }
        else if (Bank.isOpen() && !Bank.contains(bot.Ore)){
            ClientUI.showAlert(BotLogger.Level.INFO, "No Ore Found In Bank");
            bot.stop("No Ore Found In Bank");
        }
        else if (bank != null && bank.isVisible()){
            bank.interact("Use");
            Execution.delayUntil(Bank::isOpen, 2000);
        }
        else{
            path.setStepDeviation(1);
            path.step();
            if (Players.getLocal()!= null){
                Execution.delayUntil(() -> !Players.getLocal().isMoving());
            }
        }
    }
}
