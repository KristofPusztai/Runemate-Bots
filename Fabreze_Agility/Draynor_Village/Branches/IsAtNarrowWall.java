package Fabreze.bots.Fabreze_Agility.Draynor_Village.Branches;

import Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves.InteractNarroWall;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtNarrowWall extends BranchTask {

    private InteractNarroWall interactNarroWall = new InteractNarroWall();
    private IsAtWallJump isAtWallJump = new IsAtWallJump();

    private Area roof3 = new Area.Rectangular(new Coordinate(3094, 3267, 3), new Coordinate(3089, 3265, 3));

    @Override
    public boolean validate(){
        return roof3.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactNarroWall; }

    @Override
    public TreeTask failureTask() { return isAtWallJump; }
}
