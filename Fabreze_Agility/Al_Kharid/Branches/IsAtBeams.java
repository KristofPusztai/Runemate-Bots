package Fabreze.bots.Fabreze_Agility.Al_Kharid.Branches;

import Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves.InteractBeams;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtBeams extends BranchTask {

    private IsAtTightRope2 isAtTightRope2 = new IsAtTightRope2();
    private InteractBeams interactBeams = new InteractBeams();

    private Area roof5 = new Area.Rectangular(new Coordinate(3318, 3174, 2), new Coordinate(3312, 3179, 2));

    @Override
    public boolean validate(){
        return roof5.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactBeams; }

    @Override
    public TreeTask failureTask() { return isAtTightRope2; }
}
