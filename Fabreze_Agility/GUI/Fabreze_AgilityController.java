package Fabreze.bots.Fabreze_Agility.GUI;

import Fabreze.bots.Fabreze_Agility.Canifis.Branches.IsOnRoof1;
import Fabreze.bots.Fabreze_Agility.MarksOfGrace.IsHealthLow;
import Fabreze.bots.Fabreze_Agility.Fabreze_Agility_Main;
import Fabreze.bots.Fabreze_Agility.Gnome_Stronghold.Branches.IsRunEnabled;
import Fabreze.bots.Fabreze_Agility.Varrock.Branches.IsAtClothesLine;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.core.LoopingThread;
import com.runemate.game.api.script.framework.tree.BranchTask;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Fabreze_AgilityController implements Initializable {

    @FXML
    private ComboBox<Course> course;
    @FXML
    private Button setlocation;
    @FXML
    private Label setlabel;
    @FXML
    public Label xptracker;
    @FXML
    public Label experiencepercent;
    @FXML
    public ProgressBar experiencebar;
    @FXML
    public Label level;
    @FXML
    public Label clock;
    @FXML
    public Label stopatlabel;
    @FXML
    public Button update;
    @FXML
    public TextField stopat;
    @FXML
    private CheckBox eatfood;
    @FXML
    private CheckBox markofgrace;

    public StopWatch stopwatch = new StopWatch();

    public BranchTask startclass = null;
    public String stopatvalue = "0";
    public int xpgain = 0;
    public int xp = Skill.AGILITY.getExperienceAsPercent();

    Fabreze_Agility_Main bot;
    public Fabreze_AgilityController (Fabreze_Agility_Main bot){this.bot = bot;}

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stopwatch.start();
        new LoopingThread(() -> Platform.runLater(() -> clock.setText(stopwatch.getRuntimeAsString())), 1000).start();
        Execution.delayUntil(() -> Skill.AGILITY.getCurrentLevel() != -1);
        experiencebar.setProgress(xp/100.0);
        experiencepercent.setText(xp + "%");
        level.setText(Skill.AGILITY.getCurrentLevel() +"");
        course.setItems(FXCollections.observableArrayList(new Course(new IsRunEnabled(),"Gnome Stronghold"),
                                                          new Course(new IsHealthLow(new Fabreze.bots.Fabreze_Agility.Draynor_Village.Branches.IsAtStart()), "Draynor Village"),
                                                          new Course(new IsHealthLow(new Fabreze.bots.Fabreze_Agility.Al_Kharid.Branches.IsAtStart()), "Al Kharid"),
                                                          new Course(new IsHealthLow(new IsAtClothesLine()), "Varrock"),
                                                          new Course(new IsHealthLow(new IsOnRoof1()), "Canifis")
        ));
        course.setConverter(new CourseConverter());
        setlocation.setOnAction(e -> {
            startclass = course.getValue().getclass();
            setlabel.setText("Course set to: " + course.getValue().getname());
            setlocation.setDisable(true);
        });
        update.setOnAction(e -> {
            stopatvalue = stopat.getText();
            stopatlabel.setText("Stopping at " + stopatvalue);
        });
        eatfood.setOnAction(e -> bot.eatfood = !bot.eatfood);
        markofgrace.setOnAction(e -> bot.markofgrace = !bot.markofgrace);
    }
}
