package Fabreze.bots.Fabreze_Agility.Al_Kharid.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractTightRope2 extends LeafTask {
    private Area roof7 = new Area.Rectangular(new Coordinate(3302, 3187, 3), new Coordinate(3301, 3193, 3));

    @Override
    public void execute(){
        GameObject tightrope2 = GameObjects.newQuery().names("Tightrope").actions("Cross").results().nearest();
        if (tightrope2 != null && tightrope2.isVisible() && Players.getLocal()!= null){
            if (tightrope2.interact("Cross")){
                Execution.delayUntil(() ->roof7.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(tightrope2, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }

    }

}
