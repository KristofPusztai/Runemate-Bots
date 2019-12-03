package Fabreze.bots.Fabreze_Agility.Canifis.Branches;

import Fabreze.bots.Fabreze_Agility.Canifis.Leaves.Gap2;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOnRoof2 extends BranchTask {

    private Area roof2 = new Area.Rectangular(new Coordinate(3503, 3504, 2), new Coordinate(3497, 3506, 2));

    private IsOnRoof3 isOnRoof3 = new IsOnRoof3();
    private Gap2 gap2 = new Gap2();

    @Override
    public boolean validate(){
        return roof2.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return gap2; }

    @Override
    public TreeTask failureTask() { return isOnRoof3; }
}
