package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.Branches.Banking.IsBanking;
import Fabreze.bots.Fabreze_Cannoner.Leaves.ContinueHandler;
import com.runemate.game.api.hybrid.local.hud.interfaces.ChatDialog;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsContinue extends BranchTask {

    private ContinueHandler continueHandler = new ContinueHandler();
    private IsBanking isBanking = new IsBanking();

    @Override
    public boolean validate(){
        return ChatDialog.getContinue() != null;
    }


    @Override
    public TreeTask successTask() { return continueHandler;}

    @Override
    public TreeTask failureTask() { return isBanking; }

}
