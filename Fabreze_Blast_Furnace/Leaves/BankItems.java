package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
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

public class BankItems extends LeafTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private PredefinedPath path = PredefinedPath.create(new Coordinate(1940, 4962, 0), new Coordinate(1948, 4957, 0));
    @Override
    public void execute(){
        SpriteItem goldsmithgauntlets = Inventory.newQuery().names("Goldsmith gauntlets").results().first();
        if (bot.goldsmithgauntlets && goldsmithgauntlets != null){
            goldsmithgauntlets.interact("Wear");
            Execution.delayUntil(() -> Equipment.contains("Goldsmith gauntlets"), 3000);
        }

        GameObject bank = GameObjects.newQuery().names("Bank chest").actions("Use").results().nearest();
        if (Bank.isOpen()){
            Bank.depositAllExcept("Coal bag", "Bucket of water", "Ice gloves", "Goldsmith gauntlets", "Stamina potion(1)", "Stamina potion(2)", "Stamina potion(3)", "Stamina potion(4)");
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
