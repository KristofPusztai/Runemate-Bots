package Fabreze.bots.Fabreze_Agility.Draynor_Village.Branches;

import Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves.InteractGap;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtGap extends BranchTask {

    private IsAtCrate isAtCrate = new IsAtCrate();
    private InteractGap interactGap = new InteractGap();

    private Area roof6 = new Area.Rectangular(new Coordinate(3088, 3255, 3), new Coordinate(3094, 3255, 3));

    @Override
    public boolean validate(){
        return roof6.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactGap; }

    @Override
    public TreeTask failureTask() { return isAtCrate; }
}
