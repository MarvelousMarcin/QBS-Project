package controllers;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Results {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button acceptBut;

    @FXML
    private Label infoLabel;

    private Pane contentPane;

    private final DataCollector dataCollector;

    private LinkedList<File> files;

    private Stage primaryStage;

    public Results(DataCollector dataCollector){
        this.dataCollector = dataCollector;
    }

    public void initialize(){

        files = new LinkedList<>();

        try (Stream<Path> stream = Files.find(Paths.get(dataCollector.getDirectory()), 10,
                (path, attr) -> path.getFileName().toString().endsWith("."+dataCollector.getExtension()) ))
        {
            stream.forEach(e -> files.add(e.toFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(files.isEmpty()){
            infoLabel.setText("Sorry, we could not find any files!");
            infoLabel.setTranslateY(100);
            infoLabel.setTextFill(Color.RED);
            acceptBut.setText("Go back!");
            listView.setVisible(false);

            acceptBut.setOnAction(e -> goBack());
        }
        else {
            for (File file : files) {
                listView.getItems().add(file.toString());
            }
            acceptBut.setOnAction(e -> showFinalResults());
        }
        acceptBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Animations.QUESTION_BUT.animateEntry(acceptBut));
        acceptBut.addEventHandler(MouseEvent.MOUSE_EXITED, Animations.QUESTION_BUT.animateExit(acceptBut));
    }

    public void showFinalResults(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FinalPresent.fxml"));
        loader.setControllerFactory(e -> new FinalPresent(files,dataCollector));
        try {
            Parent root = loader.load();
            contentPane.getChildren().clear();
            contentPane.getChildren().setAll(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setContentPane(Pane contentPane){
        this.contentPane = contentPane;
    }

    private void goBack(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainApp.fxml"));
        try {
            Scene mainScene = new Scene(loader.load());
            mainScene.getStylesheets().add((Objects.requireNonNull(getClass().getResource("/stylesheets/style.css"))).toExternalForm());
            mainScene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(mainScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

}
