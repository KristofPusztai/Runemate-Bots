package Fabreze.bots.Fabreze_Blast_Furnace.GUI;

import Fabreze.bots.Fabreze_Blast_Furnace.BlastFurnaceMain;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal0.IsOreDeposited;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal1.IsCoalBag;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal2.IsCoalBag2;
import Fabreze.bots.Fabreze_Blast_Furnace.Coal3.IsCoalBag3;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.core.LoopingThread;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("ALL")
public class BlastFurnaceGUIController implements Initializable {

    @FXML
    public Label clock;
    @FXML
    private TextField stopat;
    @FXML
    private Button update;
    @FXML
    private Label stopatlabel;
    @FXML
    public Label xppercent;
    @FXML
    public ProgressBar progressbar;
    @FXML
    public Label level;
    @FXML
    public Label xptracker;
    @FXML
    private CheckBox coalbag;
    @FXML
    private CheckBox waterbucket;
    @FXML
    private CheckBox iceglove;
    @FXML
    private CheckBox goldsmith;
    @FXML
    private ComboBox<Bar> ore;
    @FXML
    private Button setore;
    @FXML
    private Label setorelabel;
    @FXML
    private TextField coincoffer;
    @FXML
    private Button setcoin;
    @FXML
    private Label coincofferlabel;
    @FXML
    private Button start;
    @FXML
    private Label startlabel;
    @FXML
    private RadioButton staminapot;
    @FXML
    private RadioButton dropempty;

    public StopWatch stopwatch = new StopWatch();
    public int xpgain = 0;
    public int xp = Skill.SMITHING.getExperienceAsPercent(); //xp on start of bot

    private BlastFurnaceMain mainclass;
    public BlastFurnaceGUIController(BlastFurnaceMain mainclass){
        this.mainclass = mainclass;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        stopwatch.start();
        new LoopingThread(() -> Platform.runLater(() -> clock.setText(stopwatch.getRuntimeAsString())), 1000).start();
        Execution.delayUntil(() -> Skill.SMITHING.getCurrentLevel() != -1);
        progressbar.setProgress(xp/100.0);
        xppercent.setText(xp +"%");
        level.setText(Skill.SMITHING.getCurrentLevel()+"");
        ore.setItems(FXCollections.observableArrayList(new Bar("Iron Bar","Iron ore", new IsOreDeposited(), 0)
                                                       ,new Bar("Steel bars","Iron ore", new IsCoalBag(), 1)
                                                       ,new Bar("Gold bars", "Gold ore", new IsOreDeposited(),0)
                                                       ,new Bar("Mithril bars","Mithril ore", new IsCoalBag2(), 2)
                                                       ,new Bar("Adamantite bars","Adamantite ore", new IsCoalBag3(), 3)
                                                       //,new Bar("Runite bars","Runite ore", new....)
        ));
        ore.setConverter(new BarConverter());
        ore.setOnAction(e -> setore.setDisable(false));
        setcoin.setOnAction(e -> {
            coincofferlabel.setText("Coin Coffer Amount Set to: " + coincoffer.getText());
            mainclass.cofferamt = Integer.parseInt(coincoffer.getText());
            if (mainclass.Ore != null && mainclass.cofferamt != 0 && (mainclass.icegloves || mainclass.bucketofwater)){
                start.setDisable(false);
            }
            else if (!start.isDisable()){
                start.setDisable(true);
            }
        });
        coincoffer.textProperty().addListener((observable, oldValue, newValue) -> handleInput(newValue));
        setore.setOnAction(e -> {
            setorelabel.setText("Set to: " + ore.getValue().getname());
            mainclass.bar = ore.getValue().getname();
            mainclass.Ore = ore.getValue().getOres();
            mainclass.coalclass = ore.getValue().getCoalclass();
            mainclass.coalcost = ore.getValue().getCoalCost();
            setore.setDisable(true);
            ore.setDisable(true);
            if (mainclass.Ore != null && mainclass.cofferamt != 0 && (mainclass.icegloves || mainclass.bucketofwater)){
                start.setDisable(false);
            }
            else if (!start.isDisable()){
                start.setDisable(true);
            }
        });
        coalbag.setOnAction(e -> mainclass.coalbag = !mainclass.coalbag);
        waterbucket.setOnAction(e -> {
            mainclass.bucketofwater = !mainclass.bucketofwater;
            if (!iceglove.isDisable()){iceglove.setDisable(true);}
            else {iceglove.setDisable(false);}

            if (mainclass.Ore != null && mainclass.cofferamt != 0 && (mainclass.icegloves || mainclass.bucketofwater)){
                start.setDisable(false);
            }
            else if (!start.isDisable()){
                start.setDisable(true);
            }
        });
        iceglove.setOnAction(e -> {

            mainclass.icegloves = !mainclass.icegloves;
            if (!waterbucket.isDisable()){waterbucket.setDisable(true);}
            else{waterbucket.setDisable(false);}

            if (mainclass.Ore != null && mainclass.cofferamt != 0 && (mainclass.icegloves || mainclass.bucketofwater)){
                start.setDisable(false);
            }
            else if (!start.isDisable()){
                start.setDisable(true);
            }
        });
        goldsmith.setOnAction(e -> mainclass.goldsmithgauntlets = !mainclass.goldsmithgauntlets);
        update.setOnAction(e -> {
            mainclass.stopatvalue = stopat.getText();
            stopatlabel.setText("Stopping at " + mainclass.stopatvalue);
        });
        start.setOnAction(e -> {
            mainclass.start = true;
            start.setDisable(true);
            startlabel.setText("Started!");
        });
        staminapot.setOnAction(e -> mainclass.staminapot = staminapot.isSelected());
        dropempty.setOnAction(e -> mainclass.dropvial = dropempty.isSelected());
    }
    private void handleInput(String s) { //Textfield Handler

        Matcher matcher = Pattern.compile("[^\\d]").matcher(s);
        int carretpos = coincoffer.getCaretPosition();

        if (matcher.find()) {

            // Only allows numeral inputs

            Platform.runLater(() -> coincoffer.setText(s.replaceAll("[^\\d]", "")));
            Platform.runLater(() -> coincoffer.positionCaret(carretpos));
        }
    }
}
