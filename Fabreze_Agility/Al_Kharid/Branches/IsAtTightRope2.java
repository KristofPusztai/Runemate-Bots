package Fabreze.bots.Fabreze_Agility.Al_Kharid.Branches;

import Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves.InteractTightRope2;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtTightRope2 extends BranchTask {

    private IsAtGap isAtGap = new IsAtGap();
    private InteractTightRope2 interactTightRope2 = new InteractTightRope2();

    private Area roof6 = new Area.Rectangular(new Coordinate(3318, 3180, 3), new Coordinate(3312, 3386, 3));
    @Override
    public boolean validate(){
        return roof6.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactTightRope2; }

    @Override
    public TreeTask failureTask() { return isAtGap; }
}
