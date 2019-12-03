package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Withdraw pickaxe from bank, equip if high enough level
 */
public class GetPickaxe extends LeafTask {

    private GameObject bankchest = GameObjects.newQuery().names("Bank chest").actions("Use").on(new Coordinate(3761, 5666, 0)).results().first();
    private Player player = Players.getLocal();
    private Area nearbankchest = new Area.Rectangular(new Coordinate(3759, 5659, 0), new Coordinate(3751, 5672, 0));
    @Override
    public void execute() {
        if (!Bank.isOpen()){
            if (player != null && bankchest.isVisible()){ //bank is visible
                bankchest.interact("Use");
                Execution.delayUntil(Bank::isOpen, 6000);
            }
            else if (!nearbankchest.contains(player)){ //traversal to bank
                WebPath tobankchest = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(nearbankchest.getRandomCoordinate());
                if (tobankchest != null) { //nullcheck
                    tobankchest.step(Path.TraversalOption.MANAGE_RUN);
                }
                else{System.out.println("failed to generate webpath");
                }
            }
        }
        else {
            SpriteItem bankpickaxe = Bank.newQuery().names("Bronze pickaxe", "Iron pickaxe", "Steel pickaxe", "Black pickaxe", "Mithril pickaxe", "Adamant pickaxe", "Rune pickaxe", "Dragon pickaxe").results().first();
            if (bankpickaxe != null && bankpickaxe.interact("Withdraw-1")) {
                Execution.delayUntil(() -> Inventory.containsAnyOf("Bronze pickaxe", "Iron pickaxe", "Steel pickaxe", "Black pickaxe", "Mithril pickaxe", "Adamant pickaxe", "Rune pickaxe", "Dragon pickaxe"), 1500, 3000);
            }
        }
    }
}
