package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Branches;

import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves.InteractTreeBranch;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtTreeBranch extends BranchTask {

    private InteractTreeBranch interactTreeBranch = new InteractTreeBranch();
    private IsAtBalancingRope isAtBalancingRope = new IsAtBalancingRope();

    private Area attreebranch = new Area.Rectangular(new Coordinate(2471, 3424, 1), new Coordinate(2476, 3422, 1));

    @Override
    public boolean validate(){
        return attreebranch.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask(){return interactTreeBranch; }

    @Override
    public TreeTask failureTask(){ return isAtBalancingRope; }
}
