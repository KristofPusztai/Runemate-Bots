package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Branches;

import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves.InteractObstacleNet;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtObstacleNet extends BranchTask {

    private IsAtTreeBranch isAtTreeBranch = new IsAtTreeBranch();
    private InteractObstacleNet interactObstacleNet = new InteractObstacleNet();

    private Area endoflog = new Area.Rectangular(new Coordinate(2477, 3429, 0), new Coordinate(2471, 3426, 0));

    @Override
    public boolean validate(){
        return endoflog.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask(){return interactObstacleNet; }

    @Override
    public TreeTask failureTask(){ return isAtTreeBranch; }
}
