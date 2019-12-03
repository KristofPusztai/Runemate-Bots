package Fabreze.bots.Fabreze_Agility.Varrock.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.basic.PredefinedPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

import java.util.concurrent.Callable;

public class InteractGap4 extends LeafTask {

    PredefinedPath path = PredefinedPath.create(new Coordinate(3226, 3402, 3), new Coordinate(3231,3402, 3));
    private Area dangerzone = new Area.Rectangular(new Coordinate(3218, 3393, 3), new Coordinate(3220, 3403, 3));
    private Area roof7 = new Area.Rectangular(new Coordinate(3240, 3403,3), new Coordinate(3236, 3408, 3));

    @Override
    public void execute(){
        GameObject gap4 = GameObjects.newQuery().names("Gap").actions("Leap").on(new Coordinate(3233, 3402, 3)).results().nearest();
        if (Players.getLocal() != null) {
            final Callable<Boolean> playermovingcondition = () -> Players.getLocal().isMoving();
            if (gap4 != null && gap4.isVisible()) {
                if (gap4.interact("Leap")) {
                    Execution.delayUntil(() -> roof7.contains(Players.getLocal()), playermovingcondition, 2000);
                    Execution.delay(900);
                }
            } else if (dangerzone.contains(Players.getLocal())) {
                path.setStepDeviation(1);
                path.step();
                Execution.delayUntil(() -> !Players.getLocal().isMoving() || gap4 != null && gap4.isVisible(), playermovingcondition, 2000);
            } else {
                Camera.concurrentlyTurnTo(gap4, Random.nextDouble(0.345, 0.849));
                if (Camera.getZoom() > 0.1) {
                    Camera.setZoom(0.05, 0.05);
                }
            }
        }
    }


}
