package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.local.hud.interfaces.DepositBox;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class ContinueHandler extends LeafTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    @Override
    public void execute(){
        if (DepositBox.isOpen()){
            DepositBox.close();
            Execution.delayUntil(() -> !DepositBox.isOpen(), 1000);
            return;
        }
        ChatDialog.Continue cont = ChatDialog.getContinue();
        if (cont != null && cont.select()){
            Execution.delayUntil(() -> ChatDialog.getContinue() == null, 2000);
            if (ChatDialog.getContinue() == null){
                bot.leveluptrigger = false;
            }
        }
    }
}
