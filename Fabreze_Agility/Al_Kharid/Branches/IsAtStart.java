package Fabreze.bots.Fabreze_Agility.Al_Kharid.Branches;

import Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves.InteractRoughWall;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtStart extends BranchTask {

    private IsAtTightRope isAtTightRope = new IsAtTightRope();
    private InteractRoughWall interactRoughWall = new InteractRoughWall();

    private Area start = new Area.Absolute(new Coordinate(3273, 3195, 0));
    @Override
    public boolean validate(){
        return start.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactRoughWall; }

    @Override
    public TreeTask failureTask() { return isAtTightRope; }
}
