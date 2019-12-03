package Fabreze.bots.Fabreze_Agility.Draynor_Village.Branches;

import Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves.InteractTightRope2;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtTightRope2 extends BranchTask {

    private InteractTightRope2 interactTightRope2 = new InteractTightRope2();
    private IsAtNarrowWall isAtNarrowWall = new IsAtNarrowWall();

    private Area roof2 = new Area.Rectangular(new Coordinate(3090, 3276, 3), new Coordinate(3092, 3276, 3));

    @Override
    public boolean validate(){
        return roof2.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactTightRope2; }

    @Override
    public TreeTask failureTask() { return isAtNarrowWall; }
}
