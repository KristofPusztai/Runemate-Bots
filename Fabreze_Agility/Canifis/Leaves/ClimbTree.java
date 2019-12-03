package Fabreze.bots.Fabreze_Agility.Canifis.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.location.navigation.Path;
import com.runemate.game.api.hybrid.location.navigation.Traversal;
import com.runemate.game.api.hybrid.location.navigation.web.WebPath;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ClimbTree extends LeafTask {

    private Area roof1 = new Area.Rectangular(new Coordinate(3505, 3492, 2), new Coordinate(3509, 3497, 2));

    @Override
    public void execute(){
        GameObject talltree = GameObjects.newQuery().names("Tall tree").actions("Climb").results().nearest();
        WebPath totree = Traversal.getDefaultWeb().getPathBuilder().useTeleports(false).buildTo(talltree);

        if (Players.getLocal() != null && talltree != null && talltree.isVisible() && talltree.interact("Climb")){
            Execution.delayUntil(() -> roof1.contains(Players.getLocal()),() -> Players.getLocal().isMoving(), 4000);
            Execution.delay(100, 1200);
        }
        else {
            if (totree != null && Players.getLocal() != null){
                totree.step();
                Execution.delayUntil(() -> !Players.getLocal().isMoving());
            }
        }
    }
}
