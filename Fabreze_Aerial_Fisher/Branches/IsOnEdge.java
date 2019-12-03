package Fabreze.bots.Fabreze_Aerial_Fisher.Branches;

import Fabreze.bots.Fabreze_Aerial_Fisher.GUI.MolchIslandBorderCoords;
import Fabreze.bots.Fabreze_Aerial_Fisher.Leaves.Fish;
import Fabreze.bots.Fabreze_Aerial_Fisher.Leaves.MoveToEdge;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsOnEdge extends BranchTask{

    private Fish fish = new Fish();
    private MoveToEdge moveToEdge = new MoveToEdge();

    private Player player = Players.getLocal();

    @Override
    public boolean validate(){return MolchIslandBorderCoords.islandbordercoords.contains(player.getPosition());
    }

    @Override
    public TreeTask failureTask() {
        return moveToEdge;
    }

    @Override
    public TreeTask successTask() {
        return fish;
    }
}
