package Fabreze.bots.Fabreze_Minnow_Fisher;

import Fabreze.bots.Fabreze_Minnow_Fisher.Leaves.DisplayError;
import Fabreze.bots.Fabreze_Minnow_Fisher.Leaves.Fish;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


//import path.to.your.IsFishVisible
//import path.to.your.DisplayError

/**
 * NOTES:
 * Checks if on Minnow platform
 */
public class OnPlatform extends BranchTask {

    private DisplayError displayerror = new DisplayError();
    private Fish fish = new Fish();

    private Area minnowplatform = new Area.Rectangular(new Coordinate(2606, 3440, 0), new Coordinate(2622, 3446, 0));

    @Override
    public boolean validate() {
        return minnowplatform.contains(Players.getLocal());
    }

    @Override
    public TreeTask failureTask() {
        return displayerror;
    }

    @Override
    public TreeTask successTask() {
        return fish;
    }
}
