package Fabreze.bots.Fabreze_Agility.Varrock.Branches;

import Fabreze.bots.Fabreze_Agility.Varrock.Leaves.InteractWall;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtWall extends BranchTask {

    private InteractWall interactWall = new InteractWall();
    private IsAtGap2 isAtGap2 = new IsAtGap2();

    private Area roof3 = new Area.Rectangular(new Coordinate(3197, 3416, 1), new Coordinate(3194, 3416, 1));

    @Override
    public boolean validate(){
        return roof3.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactWall; }

    @Override
    public TreeTask failureTask() { return isAtGap2; }
}
