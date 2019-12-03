package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DisplayCoolingError extends LeafTask {
    @Override
    public void execute(){
        ClientUI.showAlert("Select Cooling Method");
        Execution.delayUntil((() -> ((BlastFurnaceMain) Environment.getBot()).bucketofwater || ((BlastFurnaceMain) Environment.getBot()).icegloves));
    }
}
