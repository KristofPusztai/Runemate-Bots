package Fabreze.bots.Fabreze_Agility.Varrock.Branches;

import Fabreze.bots.Fabreze_Agility.Varrock.Leaves.InteractGap;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtGap extends BranchTask {

    private InteractGap interactGap = new InteractGap();
    private IsAtWall isAtWall = new IsAtWall();

    private Area roof2 = new Area.Rectangular(new Coordinate(3208, 3413, 3), new Coordinate(3201, 3417, 3));

    @Override
    public boolean validate(){
        return roof2.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactGap; }

    @Override
    public TreeTask failureTask() { return isAtWall; }
}
