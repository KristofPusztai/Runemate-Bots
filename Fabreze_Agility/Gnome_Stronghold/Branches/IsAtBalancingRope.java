package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Branches;

import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves.InteractRope;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtBalancingRope extends BranchTask {

    private IsAtTreeBranch2 isAtTreeBranch2 = new IsAtTreeBranch2();
    private InteractRope interactRope = new InteractRope();

    private Area atrope = new Area.Rectangular(new Coordinate(2472, 3420, 2), new Coordinate(2477, 3419, 2));

    @Override
    public boolean validate() {
        return atrope.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return interactRope;
    }

    @Override
    public TreeTask failureTask() { return isAtTreeBranch2; }
}
