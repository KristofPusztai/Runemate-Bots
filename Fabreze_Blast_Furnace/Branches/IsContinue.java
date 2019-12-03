package Fabreze.bots.Fabreze_Blast_Furnace.Branches;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Leaves.ContinueHandler;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.hybrid.local.hud.interfaces.DepositBox;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsContinue extends BranchTask {

    private BlastFurnaceMain bot = (BlastFurnaceMain) Environment.getBot();

    private IsAtBlastFurnace isAtBlastFurnace = new IsAtBlastFurnace();
    private ContinueHandler continueHandler = new ContinueHandler();

    @Override
    public boolean validate(){
        return DepositBox.isOpen() || (bot.leveluptrigger && ChatDialog.getContinue() != null);
    }
    @Override
    public TreeTask successTask() { return continueHandler; }

    @Override
    public TreeTask failureTask() { return isAtBlastFurnace; }

}
