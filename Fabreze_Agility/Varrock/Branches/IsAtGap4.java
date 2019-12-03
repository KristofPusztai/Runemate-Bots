package Fabreze.bots.Fabreze_Agility.Varrock.Branches;

import Fabreze.bots.Fabreze_Agility.Varrock.Leaves.InteractGap4;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtGap4 extends BranchTask {

    private InteractGap4 interactGap4 = new InteractGap4();
    private IsAtLedge isAtLedge = new IsAtLedge();

    private Area roof6 = new Area.Rectangular(new Coordinate(3218, 3393, 3), new Coordinate(3232,3403,3));

    @Override
    public boolean validate(){
        return roof6.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactGap4; }

    @Override
    public TreeTask failureTask() { return isAtLedge; }
}
