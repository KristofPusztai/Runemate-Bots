package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Equipment;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EquipGoldsmithGauntlets extends LeafTask {

    PredefinedPath path = PredefinedPath.create(new Coordinate(1940, 4962, 0), new Coordinate(1948, 4957, 0));

    @Override
    public void execute(){

        GameObject bank = GameObjects.newQuery().names("Bank chest").actions("Use").results().nearest();
        SpriteItem goldsmithgauntlets = Inventory.newQuery().names("Goldsmith gauntlets").results().first();

        if (Bank.isOpen() && !Inventory.contains("Goldsmith gauntlets") && Bank.withdraw("Goldsmith gauntlets", 1)){
            Execution.delayUntil(() -> Inventory.contains("Goldsmith gauntlets"), 2000);
        }
        else if (Bank.isOpen() && Inventory.contains("Goldsmith gauntlets") && Bank.close()){
            Execution.delayUntil(() -> !Bank.isOpen(), 2000);
        }
        else if (Inventory.contains("Goldsmith gauntlets") && !Bank.isOpen() && goldsmithgauntlets != null){
            if (goldsmithgauntlets.click()){
                Execution.delayUntil(() -> Equipment.contains("Goldsmith gauntlets"), 3000);
            }
        }
        else if (bank != null && bank.isVisible() && bank.interact("Use")){
            Execution.delayUntil(Bank::isOpen, 2000);
        }
        else {
            path.setStepDeviation(1);
            path.step();
            if (Players.getLocal()!= null){
                Execution.delayUntil(() -> !Players.getLocal().isMoving());
            }
        }
    }
}
