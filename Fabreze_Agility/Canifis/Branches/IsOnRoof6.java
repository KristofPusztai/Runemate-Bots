package Fabreze.bots.Fabreze_Agility.Canifis.Branches;

import Fabreze.bots.Fabreze_Agility.Canifis.Leaves.Gap6;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOnRoof6 extends BranchTask {

    private Area roof6 = new Area.Rectangular(new Coordinate(3489, 3478, 3), new Coordinate(3503, 3468, 3));
    private Area bugspot = new Area.Absolute(new Coordinate(3487, 3476, 3));

    private IsOnRoof7 is = new IsOnRoof7();
    private Gap6 gap6 = new Gap6();

    @Override
    public boolean validate(){
        return roof6.contains(Players.getLocal()) || bugspot.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return gap6; }

    @Override
    public TreeTask failureTask() { return is; }
}
