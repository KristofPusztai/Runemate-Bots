package Fabreze.bots.pyramidPlunder;

import com.RegalAPI.regal_bot.RegalTreeBot;
import com.RegalAPI.utility.LimitChecker;
import com.darksage.bots.pyramidPlunder.Data.Variables;
import com.darksage.bots.pyramidPlunder.InPyramid.Branches.CheckTimerRunning;
import com.darksage.bots.pyramidPlunder.UI.PyramidSettings;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.Environment;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.scene.layout.VBox;

import java.util.stream.IntStream;

/**
 * Created by Team Regal
 */
public class PyramidPlunder extends RegalTreeBot {

    public Variables vars = new Variables();
    private PyramidSettings settings;
    private long resetPeriod = LimitChecker.TimeUnit.DAYS.getMilli() * 7; //THIS WILL MAKE THE HOURS RESET EVERY 7 DAYS
    private long timeLimitPerPeriod = LimitChecker.TimeUnit.HOURS.getMilli() * 2; //THIS WILL MAKE THE TOTAL LIMIT 2 HOUR EVERY 7 DAYS
    private LimitChecker limit;
    private boolean whiteListed = IntStream.of(13344, 65327, 73829, 33322,25539).anyMatch(x -> x == Environment.getForumId());

    @Override
    public Skill[] setSkillsToTrack() {
        return new Skill[]{Skill.THIEVING};
    }

    @Override
    public VBox setSettingsVBox() {
        settings = new PyramidSettings(this);
        return settings;
    }

    @Override
    public TreeTask setRootTask() {
        return new CheckTimerRunning();
    }

    @Override
    public boolean isSafeToLogout() {
        setPlayer(Players.getLocal());
        return Variables.BANK_AREA.contains(getPlayer()) || Variables.CASTLE_WARS.contains(getPlayer());
    }

    public void onStart(String... strings) {
        setLoopDelay(300, 600);
        if (getMetaData().getHourlyPrice().doubleValue() <= 0) { //IF THIS IS NOT A PREMIUM BOT
            limit = new LimitChecker(this, resetPeriod);
            ClientUI.showAlert("<a href=\"https://www.runemate.com/community/resources/regal-pyramid-plunder.1389/\">You are using the time-limited Lite version.  Click here to view the premium and limitless Regal Pyramid Plunder</a>.");
            ClientUI.showAlert("You have " + limit.getTimeLeft(timeLimitPerPeriod) + " of Regal Pyramid Plunder Lite time left, which will reset " + limit.getResetDate());
            if (!whiteListed && limit.exceedsTimeLimit(timeLimitPerPeriod)) {
                ClientUI.showAlert("You are out of free usage time, please consider using the premium version!  This limit will reset on " + limit.getResetDate());
            }
        }
        getLogger().info("Welcome to Regal Pyramid Plunder! Please select your desired settings.");
    }

    @Override
    public void onStop() {
        if (getMetaData().getHourlyPrice().doubleValue() <= 0) {
            if (limit.getRuntime() > LimitChecker.TimeUnit.MINUTES.getMilli()) {
                limit.setTotalTime();
                ClientUI.showAlert("You have " + limit.getTimeLeft(timeLimitPerPeriod) + " of time left, which will reset " + limit.getResetDate());
            }
        } else {
            ClientUI.showAlert("Thanks for using Regal Pyramid Plunder!");
        }
    }

    public boolean checkTimeLimit() {
        if (!whiteListed && limit != null && limit.exceedsTimeLimit(LimitChecker.TimeUnit.HOURS.getMilli())) {
            getUi().setStopBot(true);
            return true;
        }
        return false;
    }
}
