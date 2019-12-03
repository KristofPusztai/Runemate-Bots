package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Branches;

import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves.InteractTreeBranch2;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtTreeBranch2 extends BranchTask {

    private InteractTreeBranch2 interactTreeBranch2 = new InteractTreeBranch2();
    private IsAtObstacleNet2 isAtObstacleNet2 = new IsAtObstacleNet2();

    private Area isatbranch2= new Area.Rectangular(new Coordinate(2483, 3421, 2), new Coordinate(2488, 3418, 2));

    @Override
    public boolean validate() {
        return isatbranch2.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return interactTreeBranch2;
    }

    @Override
    public TreeTask failureTask() {
        return isAtObstacleNet2;
    }
}
