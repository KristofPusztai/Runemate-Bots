package Fabreze.bots.Fabreze_Aerial_Fisher;

import Fabreze.bots.Fabreze_Aerial_Fisher.Branches.IsCormorantEquipped;
import Fabreze.bots.Fabreze_Aerial_Fisher.Leaves.DisplayLocationAlert;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

//import path.to.your.IsCormorantEquipped
//import path.to.your.DisplayLocationAlert

/**
 * NOTES:
 * Is player on Molch Island
 */
public class IsOnMolchIsland extends BranchTask {

    private IsCormorantEquipped iscormorantequipped = new IsCormorantEquipped();
    private DisplayLocationAlert displaylocationalert = new DisplayLocationAlert();

    private Player player = Players.getLocal();
    private Area molchisland = new Area.Rectangular(new Coordinate(1376, 3625, 0), new Coordinate(1360, 3640, 0));

    @Override
    public boolean validate() {
        return molchisland.contains(player);
    }

    @Override
    public TreeTask failureTask() {
        return displaylocationalert;
    }

    @Override
    public TreeTask successTask() {
        return iscormorantequipped;
    }
}
