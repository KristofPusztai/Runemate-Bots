package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Branches;

import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves.InteractObstacleNet2;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtObstacleNet2 extends BranchTask {

    private InteractObstacleNet2 interactObstacleNet2 = new InteractObstacleNet2();
    private IsAtPipe isAtPipe = new IsAtPipe();

    private Area atobstaclenet2 = new Area.Rectangular(new Coordinate(2488, 3420, 0), new Coordinate(2483, 3425, 0));

    @Override
    public boolean validate() {
        return atobstaclenet2.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return interactObstacleNet2;
    }

    @Override
    public TreeTask failureTask() {
        return isAtPipe;
    }
}
