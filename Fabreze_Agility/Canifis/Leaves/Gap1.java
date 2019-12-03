package Fabreze.bots.Fabreze_Agility.Canifis.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Gap1 extends LeafTask {

    private Area roof2 = new Area.Rectangular(new Coordinate(3503, 3504, 2), new Coordinate(3497, 3506, 2));
    private Area bugspot = new Area.Absolute(new Coordinate(3505, 3489, 2));
    private Area roof1 = new Area.Rectangular(new Coordinate(3505, 3492, 2), new Coordinate(3509, 3497, 2));

    @Override
    public void execute(){
        GameObject gap = GameObjects.newQuery().names("Gap").actions("Jump").on(new Coordinate(3505, 3498, 2)).results().nearest();
        if (bugspot.contains(Players.getLocal()) && roof1.getRandomCoordinate().click()){
            Execution.delayUntil(() -> roof1.contains(Players.getLocal()), 4000);
        }
        else if (Players.getLocal() != null && gap != null && gap.isVisible() && gap.interact("Jump")){
            Execution.delayUntil(() -> roof2.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 4000);
            Execution.delay(100, 1200);
        }
        else {
            Camera.concurrentlyTurnTo(0);
        }
    }
}
