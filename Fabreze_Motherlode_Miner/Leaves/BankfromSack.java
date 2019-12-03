package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;


/**
 * NOTES:
 * Withdraws ores from mining bag and deposits them in the bank. 
 */
public class BankfromSack extends LeafTask {

    private MotherlodeMain bot = (MotherlodeMain) Environment.getBot();

    private Player player = Players.getLocal();
    private Coordinate bankcoord = new Coordinate(3760, 5665, 0);

    private Varbit bankallmode = Varbits.load(6590);

    @Override
    public void execute() {

        GameObject bank = GameObjects.newQuery().names("Bank chest").actions("Use").results().first();

        if (Bank.isOpen()){
            if (bot.allmode && bankallmode.getValue() != 4){
                InterfaceComponent bankallbutton = Interfaces.getAt(12, 36);
                if (bankallbutton != null){bankallbutton.interact("Default quantity: All");}
            }
            else if (!bot.allmode && bankallmode.getValue() != 0 ){
                InterfaceComponent bank1button = Interfaces.getAt(12,28);
                if (bank1button != null){bank1button.interact("Default quantity: 1");}
            }

            Bank.depositAllExcept("Bronze pickaxe", "Iron pickaxe", "Steel pickaxe", "Black pickaxe", "Mithril pickaxe", "Adamant pickaxe", "Rune pickaxe", "Dragon pickaxe");
        }
        else if (bank != null && bank.isVisible() && bank.interact("Use")){
            Execution.delayUntil(Bank::isOpen, () -> player.isMoving(), 1500, 4000);
        }
        else {
            WebPath tobankchest = Traversal.getDefaultWeb().getPathBuilder().buildTo(bankcoord);
            if (tobankchest !=null){tobankchest.step();
            Execution.delay(900);
            Execution.delayUntil(()-> !player.isMoving());}
        }
    }
}
