package Fabreze.bots.Fabreze_Motherlode_Miner.Leaves;

import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Varbit;
import com.runemate.game.api.hybrid.local.Varbits;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class MoveToSack extends LeafTask {

    private Area nearsack = Area.rectangular(new Coordinate(3750, 5662,0), new Coordinate(3747, 5657, 0));
    private Player player = Players.getLocal();
    private PredefinedPath tosack = PredefinedPath.create(new Coordinate(3749, 5673, 0), new Coordinate(3750, 5666, 0), new Coordinate(3749, 5659, 0));
    private Varbit sack = Varbits.load(5558);


    @Override
    public void execute(){
        if (!nearsack.contains(player)){
            tosack.setStepDeviation(1);
            tosack.step();
            Execution.delayUntil(() -> !player.isMoving() && sack.getValue() != 0
                                    || !player.isMoving() && !nearsack.contains(player)
                                    || GameObjects.newQuery().names("Broken strut").actions("Hammer").results().size() == 2, 20000);
        }
    }
}
