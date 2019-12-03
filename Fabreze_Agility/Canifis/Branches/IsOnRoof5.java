package Fabreze.bots.Fabreze_Agility.Canifis.Branches;

import Fabreze.bots.Fabreze_Agility.Canifis.Leaves.PoleVault;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOnRoof5 extends BranchTask {

    private Area roof5 = new Area.Rectangular(new Coordinate(3478, 3482, 2), new Coordinate(3484, 3487, 2));

    private IsOnRoof6 is = new IsOnRoof6();
    private PoleVault poleVault = new PoleVault();

    @Override
    public boolean validate(){
        return roof5.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return poleVault; }

    @Override
    public TreeTask failureTask() { return is; }
}
