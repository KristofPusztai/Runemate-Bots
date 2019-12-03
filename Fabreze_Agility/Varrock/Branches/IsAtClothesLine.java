package Fabreze.bots.Fabreze_Agility.Varrock.Branches;

import Fabreze.bots.Fabreze_Agility.Varrock.Leaves.InteractClothesLine;
import com.runemate.game.api.hybrid.location.Area;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class IsAtClothesLine extends BranchTask {

    private InteractClothesLine interactClothesLine = new InteractClothesLine();
    private IsAtGap isAtGap = new IsAtGap();

    private Area roof1 = new Area.Rectangular(new Coordinate(3219, 3419, 3), new Coordinate(3214, 3410, 3));

    @Override
    public boolean validate(){
        return roof1.contains(Players.getLocal());
    }
    @Override
    public TreeTask successTask() { return interactClothesLine; }

    @Override
    public TreeTask failureTask() { return isAtGap; }
}
