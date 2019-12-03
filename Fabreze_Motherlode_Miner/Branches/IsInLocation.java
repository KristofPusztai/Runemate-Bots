package Fabreze.bots.Fabreze_Motherlode_Miner.Branches;

import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.MineVein;
import Fabreze.bots.Fabreze_Motherlode_Miner.Leaves.MoveToLocation;
import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Distance;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;


/**
 * NOTES:
 * Checks player location
 */
public class IsInLocation extends BranchTask {

    private MotherlodeMain bot = (MotherlodeMain) Environment.getBot();

    private MineVein mineVein = new MineVein();
    private MoveToLocation moveto = new MoveToLocation();

    @Override
    public boolean validate() {
        GameObject oreviein = GameObjects.newQuery().names("Ore vein").actions("Mine").types(GameObject.Type.BOUNDARY).surroundingsReachable().within(bot.finalsetlocation).results().nearest(Distance.Algorithm.EUCLIDEAN_SQUARED);

        return bot.finalsetlocation.contains(Players.getLocal()) || (oreviein != null && oreviein.distanceTo(Players.getLocal()) <=12);
    }

    @Override
    public TreeTask failureTask() {
        return moveto;
    }

    @Override
    public TreeTask successTask() {
        return mineVein;
    }
}
