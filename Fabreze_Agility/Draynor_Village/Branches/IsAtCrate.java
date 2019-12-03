package Fabreze.bots.Fabreze_Agility.Draynor_Village.Branches;

import Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves.InteractCrate;
import Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves.MoveToStart;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtCrate extends BranchTask {

    private InteractCrate interactCrate = new InteractCrate();
    private MoveToStart move = new MoveToStart();

    private Area roof7 = new Area.Rectangular(new Coordinate(3096, 3256, 3), new Coordinate(3101, 3261, 3));

    @Override
    public boolean validate(){ return roof7.contains(Players.getLocal()); }
    @Override
    public TreeTask successTask() { return interactCrate; }

    @Override
    public TreeTask failureTask() { return move; }
}
