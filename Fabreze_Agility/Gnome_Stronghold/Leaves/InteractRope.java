package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.hybrid.util.calculations.Random;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractRope extends LeafTask {

    private Area isatbranch2= new Area.Rectangular(new Coordinate(2483, 3421, 2), new Coordinate(2488, 3418, 2));

    @Override
    public void execute(){
        GameObject rope = GameObjects.newQuery().names("Balancing rope").actions("Walk-on").results().nearest();

        if (Players.getLocal() != null && rope != null && rope.isVisible()){
            if (rope.interact("Walk-on")){
                Execution.delayUntil(() -> isatbranch2.contains(Players.getLocal()),() -> Players.getLocal().isMoving(), 5000);
            }
        }
        else {
            Camera.concurrentlyTurnTo(rope, Random.nextDouble(0.3, 0.845));
            Camera.setZoom(0.05, 0.05);
        }
    }
}
