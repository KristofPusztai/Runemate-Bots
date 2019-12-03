package Fabreze.bots.Fabreze_Agility.Varrock.Branches;

import Fabreze.bots.Fabreze_Agility.Varrock.Leaves.InteractGap3;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtGap3 extends BranchTask {

    private InteractGap3 interactGap3 = new InteractGap3();
    private IsAtGap4 isAtGap4 = new IsAtGap4();

    private Area roof5 = new Area.Rectangular(new Coordinate(3182,3382, 3), new Coordinate(3208, 3403, 3));

    @Override
    public boolean validate(){
        return roof5.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactGap3; }

    @Override
    public TreeTask failureTask() { return isAtGap4; }
}
