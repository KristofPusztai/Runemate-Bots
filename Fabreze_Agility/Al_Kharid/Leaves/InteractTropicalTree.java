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

public class InteractTropicalTree extends LeafTask {

    private Area roof5 = new Area.Rectangular(new Coordinate(3318, 3174, 2), new Coordinate(3313, 3178, 2));

    @Override
    public void execute(){
        GameObject tropicaltree = GameObjects.newQuery().names("Tropical tree").actions("Swing-across").results().nearest();
        if (tropicaltree != null && tropicaltree.isVisible() && Players.getLocal() != null){
            if (tropicaltree.interact("Swing-across")){
                Execution.delayUntil(() ->roof5.contains(Players.getLocal()),() -> Players.getLocal().isMoving(), 2000 );
            }
        }
        else {
            Camera.concurrentlyTurnTo(tropicaltree, Random.nextDouble(0.345, 0.849));
            if (Camera.getZoom()>0.1){
                Camera.setZoom(0.05, 0.05);
            }
        }
    }

}
