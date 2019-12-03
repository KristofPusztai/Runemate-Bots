package Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class InteractLogBalance extends LeafTask {

    private Area endoflog = new Area.Absolute(new Coordinate(2474, 3429, 0));

    @Override
    public void execute(){
        GameObject log = GameObjects.newQuery().names("Log balance").actions("Walk-across").results().first();

        if (log != null && Players.getLocal() != null && log.interact("Walk-across")){
            Execution.delayUntil(() -> endoflog.contains(Players.getLocal()),() -> Players.getLocal().isMoving(), 10000);
        }
    }
}
