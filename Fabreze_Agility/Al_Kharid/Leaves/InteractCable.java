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

public class InteractCable extends LeafTask {

    private Area roof3 = new Area.Rectangular(new Coordinate(3283, 3176, 3), new Coordinate(3302, 3160, 3));


    @Override
    public void execute(){
        GameObject cable = GameObjects.newQuery().names("Cable").actions("Swing-across").results().nearest();
        if (cable != null && cable.isVisible() && Players.getLocal() != null){
            if (cable.interact("Swing-across")){
                Execution.delayUntil(() -> roof3.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 2000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(cable, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
