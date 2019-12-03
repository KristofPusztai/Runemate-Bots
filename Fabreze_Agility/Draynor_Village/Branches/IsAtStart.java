package Fabreze.bots.Fabreze_Agility.Draynor_Village.Branches;

import Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves.InteractRoughWall;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtStart extends BranchTask {

    private InteractRoughWall interactRoughWall = new InteractRoughWall();
    private IsAtTightRope isAtTightRope = new IsAtTightRope();

    private Area start = new Area.Absolute(new Coordinate(3103, 3279, 0));

    @Override
    public boolean validate(){
        return start.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactRoughWall; }

    @Override
    public TreeTask failureTask() { return isAtTightRope; }
}
