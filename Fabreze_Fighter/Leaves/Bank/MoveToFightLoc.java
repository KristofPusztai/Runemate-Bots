package Fabreze.bots.Fabreze_Fighter.Leaves.Bank;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.logger.BotLogger;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class MoveToFightLoc extends LeafTask {
    private FighterMain bot = (FighterMain) Environment.getBot();

    @Override
    public void execute(){

        if (Bank.isOpen()){
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(), 2000);
            return;
        }
        Player player = Players.getLocal();
        WebPath path = Traversal.getDefaultWeb().getPathBuilder().buildTo(bot.location);
        if (player != null && path != null){
            path.step();
            Execution.delayUntil(() -> !player.isMoving(), player::isMoving, 2000);
        }
        else {
            ClientUI.showAlert(BotLogger.Level.WARN, "WebPath to Cannon is Null");
            Execution.delayUntil(() ->path!= null);
        }
    }
}
