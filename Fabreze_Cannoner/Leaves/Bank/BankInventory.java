package Fabreze.bots.Fabreze_Cannoner.Leaves.Bank;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.location.navigation.Landmark;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.logger.BotLogger;
import com.runemate.game.api.script.framework.tree.LeafTask;


public class BankInventory extends LeafTask {
    private CannonMain bot = (CannonMain) Environment.getBot();

    @Override
    public void execute(){

        Player player = Players.getLocal();
        GameObject bank = GameObjects.newQuery().actions("Bank").results().nearest();

        if (Bank.isOpen()){
            Bank.depositInventory();
            Execution.delayUntil(Inventory::isEmpty, 2000);
        }
        else if (player != null && bank != null && bank.isVisible()){
            if (bank.interact("Bank")){
                Execution.delayUntil(Bank::isOpen, player::isMoving, 2000 );
                if (Bank.isOpen()){
                    bot.bank = true;
                }
            }
        }
        else{

            WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(Landmark.BANK);

            if (path!= null && player != null){
                path.step(Path.TraversalOption.PREFER_VIEWPORT);
                Execution.delayUntil(() -> !player.isMoving(), player::isMoving, 2000);
            }
            else {
                ClientUI.showAlert(BotLogger.Level.WARN, "WebPath to Bank is Null");
                Execution.delayUntil(() ->path!= null);
            }
        }
    }
}
