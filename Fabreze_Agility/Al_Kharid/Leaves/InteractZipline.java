package Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves;

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

public class InteractZipline extends LeafTask {

    PredefinedPath path = PredefinedPath.create(new Coordinate(3289, 3165, 3),new Coordinate(3296, 3165, 3), new Coordinate(3301, 3164, 3));
    private Area edge = new Area.Rectangular(new Coordinate(3295, 3176, 3), new Coordinate(3283, 3160, 3));
    private Area roof4 = new Area.Rectangular(new Coordinate(3313, 3160, 1), new Coordinate(3318, 3165, 1));

    @Override
    public void execute(){
        GameObject zipline = GameObjects.newQuery().names("Zip line").actions("Teeth-grip").results().nearest();
        if (Players.getLocal() != null){
            final Callable<Boolean> Playermovecondition = () -> Players.getLocal().isMoving();
            if (zipline != null && zipline.isVisible()){
                if (zipline.interact("Teeth-grip")){
                    Execution.delayUntil(() -> roof4.contains(Players.getLocal()), Playermovecondition, 2000);
                }
            }
            else if (edge.contains(Players.getLocal())){
                path.setStepDeviation(1);
                path.step();
                Execution.delay(900, 1500);
                Execution.delayUntil(() -> !Players.getLocal().isMoving(), Playermovecondition, 2000);
            }

            else {
                Camera.concurrentlyTurnTo(zipline, Random.nextDouble(0.345, 0.849));
                if (Camera.getZoom()>0.1){
                    Camera.setZoom(0.05, 0.05);
                }
            }
        }
    }

}
