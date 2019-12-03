package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GrabCoins extends LeafTask {
    PredefinedPath path = PredefinedPath.create(new Coordinate(1940, 4962, 0), new Coordinate(1948, 4957, 0));

    @Override
    public void execute(){
        GameObject bank = GameObjects.newQuery().names("Bank chest").actions("Use").results().nearest();

        if (Bank.isOpen()){
            if (Inventory.getEmptySlots() > 1){
                if (!Bank.contains("Coins")){
                    Environment.getBot().stop("No Coins found In Bank");
                }
                else {
                    Bank.withdraw("Coins", ((BlastFurnaceMain)Environment.getBot()).cofferamt );
                    Execution.delayUntil(() -> Inventory.contains("Coins"), 2000);
                    Bank.close();
                }
            }
            else {Bank.deposit(Inventory.getItems().random(), 1);
            }
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
