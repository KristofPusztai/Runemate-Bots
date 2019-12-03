package Fabreze.bots.Fabreze_Agility.Canifis.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Gap4 extends LeafTask {

    private Area roof5 = new Area.Rectangular(new Coordinate(3478, 3482, 2), new Coordinate(3484, 3487, 2));

    @Override
    public void execute(){
        GameObject gap = GameObjects.newQuery().names("Gap").actions("Jump").on(new Coordinate(3478, 3491, 3)).results().nearest();
        if (Players.getLocal() != null && gap != null && gap.isVisible() && gap.interact("Jump")){
            Execution.delayUntil(() -> roof5.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 4000);
            Execution.delay(100, 1200);
        }
        else {
            Camera.concurrentlyTurnTo(gap);
        }
    }
}

