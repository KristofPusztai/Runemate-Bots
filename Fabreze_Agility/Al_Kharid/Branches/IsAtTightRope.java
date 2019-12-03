package Fabreze.bots.Fabreze_Agility.Al_Kharid.Branches;

import Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves.InteractTightRope;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtTightRope extends BranchTask {

    private IsAtCable isAtCable = new IsAtCable();
    private InteractTightRope interactTightRope = new InteractTightRope();

    private Area roof1 = new Area.Rectangular(new Coordinate(3278, 3192, 3), new Coordinate(3271, 3180, 3));
    @Override
    public boolean validate(){
        return roof1.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactTightRope; }

    @Override
    public TreeTask failureTask() { return isAtCable; }
}
