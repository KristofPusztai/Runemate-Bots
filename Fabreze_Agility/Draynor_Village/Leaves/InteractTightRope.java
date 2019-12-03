package Fabreze.bots.Fabreze_Agility.Draynor_Village.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractTightRope extends LeafTask {

    private Area roof2 = new Area.Rectangular(new Coordinate(3090, 3276, 3), new Coordinate(3092, 3276, 3));

    @Override
    public void execute(){
        GameObject tightrope = GameObjects.newQuery().names("Tightrope").actions("Cross").results().nearest();
        if (tightrope != null && tightrope.isVisible() && Players.getLocal() != null){
            if (tightrope.interact("Cross")){
                Execution.delayUntil(() -> roof2.contains(Players.getLocal()),() -> Players.getLocal().isMoving(), 2000 );
            }
        }
    }
}
