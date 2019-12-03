package Fabreze.bots.Fabreze_Agility.Canifis.Branches;

import Fabreze.bots.Fabreze_Agility.Canifis.Leaves.Gap4;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOnRoof4 extends BranchTask {

    private Area roof4 = new Area.Rectangular(new Coordinate(3479, 3499, 3), new Coordinate(3475, 3492, 3));

    private IsOnRoof5 isOnRoof5 = new IsOnRoof5();
    private Gap4 gap4 = new Gap4();

    @Override
    public boolean validate(){
        return roof4.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return gap4; }

    @Override
    public TreeTask failureTask() { return isOnRoof5; }
}
