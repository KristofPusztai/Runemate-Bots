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

public class InteractGap3 extends LeafTask {
    private Area roof6 = new Area.Rectangular(new Coordinate(3218, 3393, 3), new Coordinate(3232,3403,3));
    PredefinedPath path = PredefinedPath.create(new Coordinate(3186,3391, 3), new Coordinate(3194, 3394,3), new Coordinate(3201, 3395, 3), new Coordinate(3208, 3395, 3));
    @Override
    public void execute(){
        GameObject gap3 = GameObjects.newQuery().names("Gap").actions("Leap").on(new Coordinate(3209, 3397, 3)).results().nearest();
        if (Players.getLocal() != null){
            final Callable<Boolean> playermovingcondition = () -> Players.getLocal().isMoving();
            if (gap3 != null && gap3.isVisible()){
                if (gap3.interact("Leap")){
                    Execution.delayUntil(() -> roof6.contains(Players.getLocal()),playermovingcondition, 2000 );
                }
            }

            else {
                path.setStepDeviation(1);
                path.step();
                Camera.concurrentlyTurnTo(gap3);
                if (Camera.getZoom()>0.1){
                    Camera.setZoom(0.05, 0.05);
                }
            }
        }
    }

}
