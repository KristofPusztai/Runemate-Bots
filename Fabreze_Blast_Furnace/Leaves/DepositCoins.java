package Fabreze.bots.Fabreze_Blast_Furnace.Leaves;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.GameObject;
import com.runemate.game.api.hybrid.input.Keyboard;
import com.runemate.game.api.hybrid.local.hud.interfaces.*;
import com.runemate.game.api.hybrid.region.GameObjects;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class DepositCoins extends LeafTask {
    @Override
    public void execute(){

        if (Bank.isOpen()){
            Bank.close();
            Execution.delayUntil(() -> !Bank.isOpen(),2000);
        }

        GameObject coincoffer = GameObjects.newQuery().names("Coffer").actions("Use").results().nearest();
        InterfaceContainer coincontainer = InterfaceContainers.getAt(219);
        int coinamount = ((BlastFurnaceMain) Environment.getBot()).cofferamt;

        if (coincontainer != null){
            InterfaceComponent depositcoins = coincontainer.getComponent(1).getChild(1);
            if (depositcoins != null){
                depositcoins.click();
                Execution.delayUntil(() -> ChatDialog.getText() != null && ChatDialog.getText().contains("Deposit how much?") ,3000);
            }
        }
        else if (ChatDialog.getText() != null && ChatDialog.getText().contains("Deposit how much?") && Keyboard.type(Integer.toString(coinamount), true)){
                Execution.delayUntil(() -> ChatDialog.getContinue() != null, 2000);
        }
        else if (ChatDialog.getContinue() != null){
            Keyboard.typeKey(32);
            Execution.delayUntil(() -> ChatDialog.getContinue() == null, 2000);
        }
        else {
            if (coincoffer != null){
                coincoffer.interact("Use");
                Execution.delayUntil(() -> InterfaceContainers.getAt(219) != null, 2000);
            }
        }
    }
}
