package Fabreze.bots.Fabreze_Cannoner.Leaves;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.cognizant.RegionPath;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Deploy extends LeafTask {
    private CannonMain bot = (CannonMain) Environment.getBot();


    private Player player = Players.getLocal();

    @Override
    public void execute(){

        Coordinate cannonlocation = bot.cannon;

        WebPath path1 = Traversal.getDefaultWeb().getPathBuilder().buildTo(cannonlocation);
        RegionPath path2 = RegionPath.buildTo(cannonlocation);

        SpriteItem cannonbase = Inventory.newQuery().actions("Set-up").names("Cannon base").results().first();

        if (Area.singular(cannonlocation).contains(player)){

            if (cannonbase == null){
                ClientUI.showAlert("No Cannon base found in inventory");
                Execution.delayUntil(() -> Inventory.contains("Cannon base"));
            }
            else if (cannonbase.interact("Set-up")){
                Execution.delayUntil(() ->!Inventory.containsAnyOf("Cannon base", "Cannon barrels", "Cannon furnace", "Cannon stand"), () -> player.isMoving(), 7000);
                bot.firing = false;
            }
        }
        else if (path1 != null){
            path1.step(Path.TraversalOption.PREFER_VIEWPORT);
            Execution.delayUntil(() -> !player.isMoving());
        }
        else if (path2 != null){
            path2.step(Path.TraversalOption.PREFER_VIEWPORT);
            Execution.delayUntil(() -> !player.isMoving());
        }
    }

}
