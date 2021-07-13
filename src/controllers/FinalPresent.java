package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;


public class FinalPresent {

    private final LinkedList<File> files;
    private final DataCollector dataCollector;
    private HashMap<File, Integer> changes;
    private int changesVal;
    private final Stage primaryStage;

    @FXML
    private ListView<String> finalResults;

    @FXML
    private Button restartBut;

    public FinalPresent(LinkedList<File> files, DataCollector dataCollector, Stage primaryStage){
        this.files = files;
        this.dataCollector = dataCollector;
        this.primaryStage = primaryStage;
    }

    public void initialize(){
        changes = new HashMap<>();

        for(File file : files){
            changesVal = 0;
            System.out.println(file);
            try {

               //DEBUGGING System.out.println(Arrays.toString(Files.readAllBytes(file.toPath())));

                Files.write(file.toPath(), Utils.changerEngine(Files.readAllBytes(file.toPath()),
                        Utils.stringToIntArray(dataCollector.getByteStringTo()),
                        Utils.stringToIntArray(dataCollector.getByteStringWith()), this)
                );
                changes.put(file, changesVal);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println(dataCollector.getByteStringTo());
        System.out.println(dataCollector.getByteStringWith());
        for(File file : files){
            finalResults.getItems().add(file.getName() + "\t\t\tChanges: "+changes.get(file));
        }

        restartBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Utils.getAnimationEntry(restartBut));
        restartBut.addEventHandler(MouseEvent.MOUSE_EXITED, Utils.getAnimationExit(restartBut));

        restartBut.setOnAction(e -> goBack());
    }

    public void addChange(){
        this.changesVal++;
    }

    private void goBack(){
        try {
            FXMLLoader loaderBack = new FXMLLoader(getClass().getResource("/fxml/mainApp.fxml"));
            Scene mainScene = new Scene(loaderBack.load());
            ((MainApp)loaderBack.getController()).setPrimaryStage(primaryStage);
            mainScene.getStylesheets().add((Objects.requireNonNull(getClass().getResource("/stylesheets/style.css"))).toExternalForm());
            mainScene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(mainScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
