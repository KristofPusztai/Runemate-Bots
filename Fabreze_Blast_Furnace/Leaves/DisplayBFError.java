package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DisplayBFError extends LeafTask {
    private Area blastfurnace = new Area.Rectangular(new Coordinate(1956, 4956, 0), new Coordinate(1935, 4969, 0));

    @Override
    public void execute(){
        ClientUI.showAlert("Move to Blast Furnace");
        Execution.delayUntil(() -> blastfurnace.contains(Players.getLocal()));
    }
}
