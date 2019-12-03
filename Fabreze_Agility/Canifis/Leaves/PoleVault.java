package Fabreze.bots.Fabreze_Agility.Canifis.Leaves;

import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class PoleVault extends LeafTask {

    private Area roof6 = new Area.Rectangular(new Coordinate(3489, 3478, 3), new Coordinate(3503, 3469, 3));

    @Override
    public void execute(){
        GameObject polevault = GameObjects.newQuery().names("Pole-vault").actions("Vault").results().nearest();
        if (Players.getLocal() != null && polevault != null && polevault.isVisible() && polevault.interact("Vault")){
            Execution.delayUntil(() -> roof6.contains(Players.getLocal()), () -> Players.getLocal().isMoving(), 4000);
            Execution.delay(100, 1200);
        }
        else {
            Camera.concurrentlyTurnTo(polevault);
        }
    }
}
