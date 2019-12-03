package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Branches;

import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves.InteractLogBalance;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtStart extends BranchTask {

    private IsAtObstacleNet isAtObstacleNet = new IsAtObstacleNet();
    private InteractLogBalance interactLogBalance = new InteractLogBalance();

    private Area start = new Area.Rectangular(new Coordinate(2471, 3436, 0), new Coordinate(2476, 3438, 0));

    @Override
    public boolean validate(){
        return start.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask(){return interactLogBalance; }

    @Override
    public TreeTask failureTask(){ return isAtObstacleNet; }
}
