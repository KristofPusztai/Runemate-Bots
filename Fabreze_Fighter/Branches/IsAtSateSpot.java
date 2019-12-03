package Fabreze.bots.Fabreze_Fighter.Branches;

import Fabreze.bots.Fabreze_Fighter.FighterMain;
import Fabreze.bots.Fabreze_Fighter.Leaves.MoveToSafe;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


public class IsAtSateSpot extends BranchTask {

    private FighterMain bot = (FighterMain) Environment.getBot();
    private Player player = Players.getLocal();

    private MoveToSafe moveToSafe = new MoveToSafe();
    private IsTelegrab isTelegrab = new IsTelegrab();


    @Override
    public boolean validate(){

        if (player != Players.getLocal() && Players.getLocal()!= null){//Updates player var after a logout
            player = Players.getLocal();
        }

        return !bot.safezone || bot.safespotloc == null || bot.safespotloc.equals(player.getPosition());
    }


    @Override
    public TreeTask successTask() { return isTelegrab; }

    @Override
    public TreeTask failureTask() { return moveToSafe; }

}
