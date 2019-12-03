package Fabreze.bots.Fabreze_Agility.Canifis.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Gap7 extends LeafTask {

    Area end = new Area.Absolute(new Coordinate(3510, 3485, 0));

    @Override
    public void execute(){
        GameObject gap = GameObjects.newQuery().names("Gap").actions("Jump").on(new Coordinate(3510, 3483, 2)).results().nearest();
        if (Players.getLocal() != null && gap != null && gap.isVisible() && gap.interact("Jump")){
            Execution.delayUntil(() -> end.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 4000);
            Execution.delay(100, 1200);
        }
        else {
            Camera.concurrentlyTurnTo(gap);
        }
    }
}
