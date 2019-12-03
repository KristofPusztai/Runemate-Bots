package Fabreze.bots.Fabreze_Aerial_Fisher.Leaves;

import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Displays Client UI Location Alert
 */
public class DisplayLocationAlert extends LeafTask {

    private Player player = Players.getLocal();
    private Area molchisland = new Area.Rectangular(new Coordinate(1376, 3625, 0), new Coordinate(1360, 3640, 0));

    @Override
    public void execute() {
        ClientUI.showAlert("Must start bot on Molch Island");
        Execution.delayUntil(() -> molchisland.contains(player));
    }
}
