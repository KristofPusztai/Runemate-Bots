package Fabreze.bots.Fabreze_Agility.Canifis.Branches;

import Fabreze.bots.Fabreze_Agility.Canifis.Leaves.ClimbTree;
import Fabreze.bots.Fabreze_Agility.Canifis.Leaves.Gap7;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOnRoof7 extends BranchTask {

    private Area roof7 = new Area.Rectangular(new Coordinate(3509, 3475, 2), new Coordinate(3515, 3482, 2));

    private Gap7 gap7 = new Gap7();
    private ClimbTree climbTree = new ClimbTree();

    @Override
    public boolean validate(){
        return roof7.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return gap7; }

    @Override
    public TreeTask failureTask() { return climbTree; }
}
