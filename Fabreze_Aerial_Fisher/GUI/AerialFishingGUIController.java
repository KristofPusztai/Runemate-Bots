package Fabreze.bots.Fabreze_Aerial_Fisher.GUI;

import Fabreze.bots.Fabreze_Aerial_Fisher.AerialFisherMain;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.core.LoopingThread;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("WeakerAccess")
public class AerialFishingGUIController implements Initializable {

    public static int invcheck;

    private int fishingxp = Skill.FISHING.getExperienceAsPercent(); //fishing xp on start of bot
    private int hunterxp = Skill.HUNTER.getExperienceAsPercent(); //hunter xp on start of bot
    private int cookingxp = Skill.COOKING.getExperienceAsPercent();
    public int fishingxpgain = 0;
    public int huntingxpgain = 0;
    public int cookingxpgain = 0;

    @FXML
    public Label clock;
    @FXML
    public Label fishingexperiencepercent;
    @FXML
    public ProgressBar fishingexperiencebar;
    @FXML
    public Label fishingxptracker;
    @FXML
    public Label fishinglevel;
    @FXML
    public TextField stopat;
    @FXML
    public Button update;
    @FXML
    public Label stopatlabel;
    @FXML
    public CheckBox efficientfishing;
    @FXML
    public Label huntinglevel;
    @FXML
    public Label hunterexperiencepercent;
    @FXML
    public ProgressBar hunterexperiencebar;
    @FXML
    public Label hunterxptracker;
    @FXML
    public CheckBox fishchunks;
    @FXML
    public ProgressBar cookingexperiencebar;
    @FXML
    public Label cookingxptracker;
    @FXML
    public Label cookingexperiencepercent;
    @FXML
    public Label cookinglevel;

    public String stopatvalue = "0";

    private StopWatch stopwatch = new StopWatch(); //Creates stopwatch instance for runtime

    AerialFisherMain bot;

    public AerialFishingGUIController(AerialFisherMain mainclass){
        this.bot = mainclass;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stopwatch.start();
        new LoopingThread(() -> Platform.runLater(() -> clock.setText(stopwatch.getRuntimeAsString())), 1000).start(); //Starts runtime timer
        new LoopingThread(() -> Platform.runLater(() -> fishingxptracker.setText(Math.round(fishingxpgain * 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts fishing xp Tracker, updates every second
        new LoopingThread(() -> Platform.runLater(() -> hunterxptracker.setText(Math.round(huntingxpgain * 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts hunter xp Tracker, updates every second
        new LoopingThread(() -> Platform.runLater(() -> cookingxptracker.setText(Math.round(cookingxpgain * 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS)) + "/Hr")), 1000).start();
        Execution.delayUntil(() -> Skill.FISHING.getCurrentLevel() != -1);
        fishingexperiencepercent.setText(fishingxp + "%"); // Sets fishing progress bar text on start
        fishingexperiencebar.setProgress(fishingxp/100.0); // Sets fishing progress bar progress on start
        hunterexperiencepercent.setText(hunterxp + "%");//Sets hunter progress bar text on start
        hunterexperiencebar.setProgress(hunterxp/100.0);//Sets hunter progress bar progress on start
        cookingexperiencepercent.setText(cookingxp + "%");
        cookingexperiencebar.setProgress(cookingxp/100.0);
        fishinglevel.setText(Skill.FISHING.getCurrentLevel() +"");
        huntinglevel.setText(Skill.HUNTER.getCurrentLevel() + "");
        cookinglevel.setText(Skill.COOKING.getCurrentLevel() + "");
        update.setOnAction(e -> {
            stopatvalue = stopat.getText();
            stopatlabel.setText("Stopping at " + stopatvalue);
        });
        efficientfishing.setOnAction(e -> {
            if (!fishchunks.isDisable() && invcheck != 1){
                invcheck = 1;
                fishchunks.setDisable(true);
            }
            else{
                invcheck = 0;
                fishchunks.setDisable(false);
            }

        });
        fishchunks.setOnAction(e -> {
            bot.fishchunks = !bot.fishchunks;
            if (!efficientfishing.isDisable()){
                efficientfishing.setDisable(true);
            }
            else {efficientfishing.setDisable(false);}
        });
    }
    //initialize runtime and xp progress-bar UI
}
