package Fabreze.bots.Fabreze_Agility.Canifis.Branches;

import Fabreze.bots.Fabreze_Agility.Canifis.Leaves.Gap1;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOnRoof1 extends BranchTask {

    private Area roof1 = new Area.Rectangular(new Coordinate(3505, 3492, 2), new Coordinate(3509, 3497, 2));
    private Area bugspot = new Area.Absolute(new Coordinate(3505, 3489, 2));

    private IsOnRoof2 isOnRoof2 = new IsOnRoof2();
    private Gap1 gap1 = new Gap1();

    @Override
    public boolean validate(){
        return roof1.contains(Players.getLocal()) || bugspot.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return gap1; }

    @Override
    public TreeTask failureTask() { return isOnRoof2; }
}
