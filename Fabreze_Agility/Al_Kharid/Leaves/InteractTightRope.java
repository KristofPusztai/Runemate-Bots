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

public class InteractTightRope extends LeafTask {
    private Area roof2=  new Area.Rectangular(new Coordinate(3272,3172, 3), new Coordinate(3265, 3161, 3));
    private PredefinedPath path = PredefinedPath.create(new Coordinate(3273, 3181, 3));


    @Override
    public void execute(){
        GameObject tightrope = GameObjects.newQuery().names("Tightrope").actions("Cross").results().nearest();
        if (tightrope != null && tightrope.isVisible() && Players.getLocal()!= null){
            if (tightrope.interact("Cross")){
                Execution.delayUntil(() ->roof2.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
            }
        }
        else {
            path.setStepDeviation(1);
            path.step();
            Camera.concurrentlyTurnTo(tightrope, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
