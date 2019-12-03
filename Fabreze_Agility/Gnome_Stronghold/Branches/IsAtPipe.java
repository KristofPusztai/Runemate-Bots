package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Branches;

import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves.InteractPipe;
import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves.MoveToStart;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtPipe extends BranchTask {

    private InteractPipe interactPipe = new InteractPipe();
    private MoveToStart move = new MoveToStart();

    private Area pipe = new Area.Rectangular(new Coordinate(2488, 3427, 0), new Coordinate(2482, 3431, 0));

    @Override
    public boolean validate() {
        return pipe.contains(Players.getLocal());
    }

    @Override
    public TreeTask successTask() {
        return interactPipe;
    }

    @Override
    public TreeTask failureTask() {
        return move;
    }
}
