package Fabreze.bots.Fabreze_Agility.Canifis.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Gap2 extends LeafTask {

    private Area roof3 = new Area.Rectangular(new Coordinate(3492, 3504, 2), new Coordinate(3487, 3499, 2));

    @Override
    public void execute(){
        GameObject gap = GameObjects.newQuery().names("Gap").actions("Jump").on(new Coordinate(3496, 3504, 2)).results().nearest();
        if (Players.getLocal() != null && gap != null && gap.isVisible() && gap.interact("Jump")){
            Execution.delayUntil(() -> roof3.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 4000);
            Execution.delay(100, 1200);
        }
        else {
            Camera.concurrentlyTurnTo(gap);
        }
    }
}
