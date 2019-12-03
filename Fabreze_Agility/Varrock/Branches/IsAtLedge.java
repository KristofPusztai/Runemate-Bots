package Fabreze.bots.Fabreze_Agility.Varrock.Branches;

import Fabreze.bots.Fabreze_Agility.Varrock.Leaves.InteractLedge;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtLedge extends BranchTask {

    private InteractLedge interactLedge = new InteractLedge();
    private IsAtEdge isAtEdge = new IsAtEdge();

    private Area roof7 = new Area.Rectangular(new Coordinate(3240, 3403,3), new Coordinate(3236, 3408, 3));

    @Override
    public boolean validate(){
        return roof7.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactLedge; }

    @Override
    public TreeTask failureTask() { return isAtEdge; }
}
