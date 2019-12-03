package Fabreze.bots.Fabreze_Cannoner.Branches;

import Fabreze.bots.Fabreze_Cannoner.CannonMain;
import Fabreze.bots.Fabreze_Cannoner.Leaves.SpecAttack;
import Fabreze.bots.Fabreze_Cannoner.Leaves.InteractMonster;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsInCB extends BranchTask {

    private CannonMain bot = (CannonMain) Environment.getBot();

    private InteractMonster interactMonster = new InteractMonster();
    private SpecAttack specAttack = new SpecAttack();

    private Player player = Players.getLocal();

    @Override
    public boolean validate(){

        if (player != Players.getLocal() && Players.getLocal()!= null){//Updates player var after a logout
            player = Players.getLocal();
        }

        Npc target = Npcs.newQuery().targeting(player).actions("Attack").results().nearest();

        return bot.monsters.isEmpty() || (player.getTarget() != null && target != null);
    }
    @Override
    public TreeTask successTask() { return specAttack; }

    @Override
    public TreeTask failureTask() { return interactMonster; }

}
