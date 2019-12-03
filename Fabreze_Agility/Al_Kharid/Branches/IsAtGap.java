package Fabreze.bots.Fabreze_Agility.Al_Kharid.Branches;

import Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves.InteractGap;
import Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves.MoveToStart;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtGap extends BranchTask {

    private InteractGap interactGap = new InteractGap();
    private MoveToStart moveToStart = new MoveToStart();

    private Area roof7 = new Area.Polygonal(new Coordinate(3301, 3187, 3), new Coordinate(3302, 3187, 3), new Coordinate(3304, 3186, 3), new Coordinate(3305, 3188, 3), new Coordinate(3305, 3189, 3), new Coordinate(3301, 3193, 3), new Coordinate(3299, 3192, 3), new Coordinate(3298, 3191, 3), new Coordinate(3298, 3190, 3), new Coordinate(3301, 3187, 3));

    @Override
    public boolean validate(){ return roof7.contains(Players.getLocal()); }
    @Override
    public TreeTask successTask() { return interactGap; }

    @Override
    public TreeTask failureTask() { return moveToStart; }
}
