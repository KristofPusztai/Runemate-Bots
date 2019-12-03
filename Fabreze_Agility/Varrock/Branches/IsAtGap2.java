package Fabreze.bots.Fabreze_Agility.Varrock.Branches;

import Fabreze.bots.Fabreze_Agility.Varrock.Leaves.InteractGap2;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtGap2 extends BranchTask {

    private InteractGap2 interactGap2 = new InteractGap2();
    private IsAtGap3 isAtGap3 = new IsAtGap3();

    private Area roof4 = new Area.Rectangular(new Coordinate(3192, 3406, 3), new Coordinate(3198, 3402, 3));

    @Override
    public boolean validate(){
        return roof4.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactGap2; }

    @Override
    public TreeTask failureTask() { return isAtGap3; }
}
