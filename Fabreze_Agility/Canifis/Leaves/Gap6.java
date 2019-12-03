package Fabreze.bots.Fabreze_Agility.Canifis.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class Gap6 extends LeafTask {

    private Area roof7 = new Area.Rectangular(new Coordinate(3509, 3475, 2), new Coordinate(3515, 3482, 2));
    private Area roof6 = new Area.Rectangular(new Coordinate(3489, 3478, 3), new Coordinate(3503, 3468, 3));
    private Area bugspot = new Area.Absolute(new Coordinate(3487, 3476, 3));

    @Override
    public void execute(){
        GameObject gap = GameObjects.newQuery().names("Gap").actions("Jump").on(new Coordinate(3503, 3476, 3)).results().nearest();
        if (bugspot.contains(Players.getLocal()) && roof6.getRandomCoordinate().click()){
            Execution.delayUntil(() -> roof6.contains(Players.getLocal()), 4000);
        }
        else if (Players.getLocal() != null && gap != null && gap.isVisible() && gap.interact("Jump")){
            Execution.delayUntil(() -> roof7.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 4000);
            Execution.delay(100, 1200);
        }
        else {
            if (Camera.getZoom() > 0.09312){
                Camera.setZoom(Random.nextDouble(0.0, 0.092),0.001);
            }
            Camera.concurrentlyTurnTo(270, Camera.getPitch(), 0.05);

        }
    }
}
