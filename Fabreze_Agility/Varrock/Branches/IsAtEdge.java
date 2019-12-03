package Fabreze.bots.Fabreze_Agility.Varrock.Branches;

import Fabreze.bots.Fabreze_Agility.Varrock.Leaves.InteractEdge;
import Fabreze.bots.Fabreze_Agility.Varrock.Leaves.MoveToStart;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtEdge extends BranchTask {

    private MoveToStart move = new MoveToStart();
    private InteractEdge interactEdge = new InteractEdge();

    private Area roof8 = new Area.Rectangular(new Coordinate(3240, 3410, 3), new Coordinate(3236, 3415, 3));

    @Override
    public boolean validate(){
        return roof8.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactEdge; }

    @Override
    public TreeTask failureTask() { return move; }

}
