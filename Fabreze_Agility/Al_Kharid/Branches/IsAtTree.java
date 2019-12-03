package Fabreze.bots.Fabreze_Agility.Al_Kharid.Branches;

import Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves.InteractTropicalTree;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtTree extends BranchTask {

    private IsAtBeams isAtBeams = new IsAtBeams();
    private InteractTropicalTree interactTropicalTree = new InteractTropicalTree();

    private Area roof4 = new Area.Polygonal(new Coordinate(3318, 3165, 1), new Coordinate(3313, 3165, 1), new Coordinate(3315, 3163, 1), new Coordinate(3315, 3161, 1), new Coordinate(3313, 3161, 1), new Coordinate(3313, 3160, 1), new Coordinate(3318,3160, 1 ), new Coordinate(3318, 3165, 1));

    @Override
    public boolean validate(){
        return roof4.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactTropicalTree; }

    @Override
    public TreeTask failureTask() { return isAtBeams; }
}
