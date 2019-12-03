package Fabreze.bots.Fabreze_Agility.Draynor_Village.Branches;

import Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves.InteractWallJump;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtWallJump extends BranchTask {

    private InteractWallJump interactWallJump = new InteractWallJump();
    private IsAtGap isAtGap = new IsAtGap();

    private Area roof5 = new Area.Rectangular(new Coordinate(3088, 3261, 3), new Coordinate(3088, 3257, 3));

    @Override
    public boolean validate(){
        return roof5.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactWallJump; }

    @Override
    public TreeTask failureTask() { return isAtGap; }
}
