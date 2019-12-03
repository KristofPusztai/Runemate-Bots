package Fabreze.bots.Fabreze_Minnow_Fisher.Leaves;

import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Displays Client UI Error not to move to platform
 */
public class DisplayError extends LeafTask {

    private Player player = Players.getLocal();
    private Area minnowplatform = new Area.Rectangular(new Coordinate(2607, 3446, 0), new Coordinate(2621, 3440, 0));

    @Override
    public void execute() {
        ClientUI.showAlert("Start Bot on Minnow Platform");
        Execution.delayUntil(() -> minnowplatform.contains(player));
    }
}
