package Fabreze.bots.Fabreze_Agility.Canifis.Branches;

import Fabreze.bots.Fabreze_Agility.Canifis.Leaves.Gap3;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOnRoof3 extends BranchTask {

    private Area roof3 = new Area.Rectangular(new Coordinate(3492, 3504, 2), new Coordinate(3487, 3499, 2));

    private IsOnRoof4 isOnRoof4 = new IsOnRoof4();
    private Gap3 gap3 = new Gap3();
    @Override
    public boolean validate(){
        return roof3.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return gap3; }

    @Override
    public TreeTask failureTask() { return isOnRoof4; }
}
