package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class GrabBucketOfWater extends LeafTask {
    PredefinedPath path = PredefinedPath.create(new Coordinate(1940, 4962, 0), new Coordinate(1948, 4957, 0));

    @Override
    public void execute(){
        GameObject bank = GameObjects.newQuery().names("Bank chest").actions("Use").results().nearest();
        if (Bank.isOpen()){
            Bank.withdraw("Bucket of water", 1);
            Execution.delayUntil(() -> Inventory.contains("Bucket of water"), 2000);
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
