package Fabreze.bots.Fabreze_Agility.Draynor_Village.Branches;

import Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves.InteractTightRope;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtTightRope extends BranchTask {

    private IsAtTightRope2 isAtTightRope2 = new IsAtTightRope2();
    private InteractTightRope interactTightRope = new InteractTightRope();

    private Area roof1 = new Area.Rectangular(new Coordinate(3102, 3277, 3), new Coordinate(3097, 3281, 3));

    @Override
    public boolean validate(){
        return roof1.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactTightRope; }

    @Override
    public TreeTask failureTask() { return isAtTightRope2; }
}
