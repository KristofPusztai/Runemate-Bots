package Fabreze.bots.Fabreze_Motherlode_Miner.GUI;

import Fabreze.bots.Fabreze_Motherlode_Miner.MotherlodeMain;
import com.runemate.game.api.client.ClientUI;
import com.runemate.game.api.hybrid.local.Skill;
import com.runemate.game.api.hybrid.location.Coordinate;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.core.LoopingThread;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("WeakerAccess")
public class MotherlodeMinerController implements Initializable {

    MotherlodeMain bot;

    @FXML
    public Label clock;
    @FXML
    public Label experiencepercent;
    @FXML
    public ProgressBar experiencebar;
    @FXML
    public Label xptracker;
    @FXML
    public Label level;
    @FXML
    public Label setlabel;
    @FXML
    public ComboBox<Location> location;
    @FXML
    public Button update;
    @FXML
    public TextField stopat;
    @FXML
    public Label stopatlabel;
    @FXML
    public Button setlocation;
    @FXML
    public CheckBox bankallmode;
    @FXML
    public CheckBox afkmodebtn;
    @FXML
    public Button setsacksize;
    @FXML
    public ComboBox<Integer> sacksize;
    @FXML
    public Label sacksizelbl;
    @FXML
    public Button breakgenerator;
    @FXML
    public TextField numberofbreaks;
    @FXML
    public Slider breaktime;
    @FXML
    public Slider betweenbreaks;
    @FXML
    public Label breaktimelabel;
    @FXML
    public Label betweenbreaklabel;
    @FXML
    public TableView<Break> tableview;
    @FXML
    public TableColumn<Break,String > startsat;
    @FXML
    public TableColumn<Break, String> breaknumber;
    @FXML
    public TableColumn<Break, String> endsat;
    @FXML
    public Tab breaktab;
    @FXML
    public CheckBox dropgems;

    public ObservableList<Break> breaktimes = FXCollections.observableArrayList();


    public String stopatvalue = "0";

    private StopWatch stopwatch = new StopWatch();
    public int xpgain = 0;
    public int xp = Skill.MINING.getExperienceAsPercent(); //xp on start of bot

    public MotherlodeMinerController(MotherlodeMain bot){
        this.bot = bot; //Access to bot thread Mainclass using constructor
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stopwatch.start();
        new LoopingThread(() -> Platform.runLater(() -> clock.setText(stopwatch.getRuntimeAsString())), 1000).start(); //Starts runtime timer
        new LoopingThread(() -> Platform.runLater(() -> xptracker.setText(Math.round(xpgain* 3600.00/stopwatch.getRuntime(TimeUnit.SECONDS))+ "/Hr")), 1000).start(); //Starts xp Tracker, updates every second
        Execution.delayUntil(() -> Skill.MINING.getCurrentLevel() != -1);
        experiencepercent.setText(xp+ "%"); // Sets progress bar text on start
        experiencebar.setProgress(xp/100.0); // Sets progress bar progress on start
        level.setText(Skill.MINING.getCurrentLevel() +"");

        location.setItems(FXCollections.observableArrayList(new Location(new Coordinate(3758, 5678, 0), CoordList.uppernorth, "Upper-North"),
                                                            new Location(new Coordinate(3757, 5673, 0), CoordList.uppersouth, "Upper-South"),
                                                            new Location(new Coordinate(3738, 5688, 0), CoordList.north, "Lower-North"),
                                                            new Location(new Coordinate(3752, 5639, 0), CoordList.south, "Lower-South")));
        location.setConverter(new Converter());
        location.setOnAction(e -> {
            if (bot.getMetaData().getHourlyPrice().doubleValue() == 0 && location.getValue().getName().contains("Upper")){
                setlocation.setDisable(true);
                ClientUI.showAlert("Lite Version Is Limited To Lower Levels!");
            }
            else if (setlocation.isDisabled()){
                setlocation.setDisable(false);
            }
        });
        update.setOnAction(e -> {
            stopatvalue = stopat.getText();
            stopatlabel.setText("Stopping at " + stopatvalue);
        });
        setlocation.setOnAction(e -> {
            if (location.getValue() !=null){
                    setlabel.setText(location.getValue().getName() + " has been set");
                    bot.finalsetlocation = location.getValue().getCoords();
                    bot.coordinate = location.getValue().getCoordinate();
                    setlocation.setDisable(true);
        }
            else{
                setlabel.setText("Must Select a Valid Area");
        }});
        bankallmode.setOnAction(e -> bot.allmode = !bot.allmode);
        sacksize.setItems(FXCollections.observableArrayList(81, 162));
        sacksize.setVisible(false);
        setsacksize.setVisible(false);
        setsacksize.setOnAction(e -> {
            if (sacksize.getValue() != null){
                bot.sacksize = sacksize.getValue();
                sacksizelbl.setText("Sack Size Set to " + bot.sacksize);
            }
            else{sacksizelbl.setText("Must Select a Valid Sack Size");}
        });
        afkmodebtn.setOnAction(e ->{
                if (!setsacksize.isVisible() && !sacksize.isVisible()){
                    setsacksize.setVisible(true);
                    sacksize.setVisible(true);
                    sacksizelbl.setVisible(true);
                }
                else {
                    setsacksize.setVisible(false);
                    sacksize.setVisible(false);
                    sacksize.setValue(null);
                    sacksizelbl.setVisible(false);
                    bot.sacksize = 0;
                    sacksizelbl.setText("");
                }
        });
        numberofbreaks.textProperty().addListener((observable, oldValue, newValue) -> handleInput(newValue));
        breaktime.valueProperty().addListener((observable, oldValue, newValue) -> {
            DecimalFormat df = new DecimalFormat("#.##");
            breaktimelabel.setText(df.format(breaktime.getValue()) + " hrs");
        });
        betweenbreaks.valueProperty().addListener((observable, oldValue, newValue) -> {
            DecimalFormat df = new DecimalFormat("#.##");
            betweenbreaklabel.setText(df.format(betweenbreaks.getValue()) + " hrs");
        });
        breakgenerator.setOnAction(e -> {
            try{
                setBreaktimes(breaktime.getValue(), Integer.valueOf(numberofbreaks.getText()), betweenbreaks.getValue());
            }
            catch (NumberFormatException x){
                ClientUI.showAlert( "Please Specify the # Of Breaks You Would Like To Generate");
            }
        });
        startsat.setCellFactory(TextFieldTableCell.forTableColumn());
        endsat.setCellFactory(TextFieldTableCell.forTableColumn());
        startsat.setOnEditCommit(this::onStartEditChange);
        endsat.setOnEditCommit(this::onEndEditChange);
        if (bot.getMetaData().getHourlyPrice().doubleValue() == 0){
            breaktab.setDisable(true);
        }
        dropgems.setOnAction(e -> bot.dropgem = dropgems.isSelected());
    }
    private void handleInput(String s) { //Textfield Handler

        Matcher matcher = Pattern.compile("[^\\d]").matcher(s);

        if (matcher.find()) {

            // Only allows numeral inputs

            Platform.runLater(() -> numberofbreaks.setText(s.replaceAll("[^\\d]", "")));
        }
    }
    private void setBreaktimes(double timebetweenbreaks, int numberofbreaks, double endtimes){
        breaktimes.clear();
        DecimalFormat df = new DecimalFormat("#.0000");
        double y , x = 0.0000;
        for (int i = 1 ; i <= numberofbreaks; i++){
            Random r = new Random();
            double randomevaluestart = (timebetweenbreaks) * r.nextDouble();
            double randomvalueend = (endtimes) * r.nextDouble();
            x += Double.valueOf(df.format(randomevaluestart));
            y = x+ Double.valueOf(df.format(randomvalueend));
            String breaktime = setToTime(x);
            String endtime = setToTime(y);
            breaktimes.add(new Break(String.valueOf(i), breaktime, endtime));
        }
        startsat.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getStartsat()));
        breaknumber.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNumber()));
        endsat.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getEndsat()));
        tableview.setItems(breaktimes);
    }
    private String setToTime(double time){
        String hours = String.format("%02d", (int)time);
        String minutes = String.format("%02d", (int)((time - (int)time)*60));
        String seconds = String.format("%02d", (int)((((time - (int)time)*60) - (int)((time - (int)time)*60))*60));
        return hours + ":" + minutes + ":" + seconds;
    }
    public void onStartEditChange(TableColumn.CellEditEvent<Break, String> productStringCellEditEvent){
        Break breaktime = tableview.getSelectionModel().getSelectedItem();
        breaktime.setStartat(productStringCellEditEvent.getNewValue());
    }
    public void onEndEditChange(TableColumn.CellEditEvent<Break, String> productStringCellEditEvent){
        Break endat = tableview.getSelectionModel().getSelectedItem();
        endat.setEndsat(productStringCellEditEvent.getNewValue());
    }
}
