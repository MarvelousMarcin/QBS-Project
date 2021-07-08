package controllers;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;


import javax.swing.plaf.basic.BasicTreeUI;
import java.io.File;
import java.io.IOException;


public class MainApp {

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Pane contentPane;

    @FXML
    private Button submitBut;

    @FXML
    private Label directoryLabel;

    @FXML
    private Label extensionLabel;

    @FXML
    private Label stringToChangeLabel;

    @FXML
    private Label stringNewLabel;

    @FXML
    private TextField textFieldDir;

    @FXML
    private Button exitBut;

    @FXML
    private Button minimizeBut;

    @FXML
    private Button questionBut;

    @FXML
    private Button pickDirectoryBut;

    @FXML
    private TextField textFieldByteTwo;

    @FXML
    private TextField textFieldExt;

    @FXML
    private TextField textFieldByteOne;

    private Stage primaryStage;

    private final DirectoryChooser directoryChooser = new DirectoryChooser();

    private DataCollector dataCollector;

    private String directory;
    private String extension;
    private String byteStringTo;
    private String byteStringWith;


    public void initialize(){

        EventHandler<ActionEvent> nextButAction = actionEvent -> {
            directory = textFieldDir.getText();
            extension = textFieldExt.getText();
            byteStringTo = textFieldByteOne.getText();
            byteStringWith = textFieldByteTwo.getText();

            dataCollector = new DataCollector.Builder().setDirectory(directory).setExtension(extension).setByteStringTo(byteStringTo).setByteStringWith(byteStringWith).build();
            System.out.println(dataCollector);
            FXMLLoader loaderResult = new FXMLLoader(getClass().getResource("/fxml/Results.fxml"));
            loaderResult.setControllerFactory(e -> new Results(dataCollector));

            try {
                Parent root = loaderResult.load();
                contentPane.getChildren().clear();
                contentPane.getChildren().add(root);

            } catch (IOException e) {
                e.printStackTrace();
            }



        };

        pickDirectoryBut.setOnAction(e -> pickDirectory());
        pickDirectoryBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Animations.PICK_DIR.animateEntry(pickDirectoryBut));
        pickDirectoryBut.addEventHandler(MouseEvent.MOUSE_EXITED, Animations.PICK_DIR.animateExit(pickDirectoryBut));

        exitBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Animations.EXIT_BUT.animateEntry(exitBut));
        exitBut.addEventHandler(MouseEvent.MOUSE_EXITED, Animations.EXIT_BUT.animateExit(exitBut));
        exitBut.setOnAction(e -> exitAction());

        minimizeBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Animations.MINI_BUT.animateEntry(minimizeBut));
        minimizeBut.addEventHandler(MouseEvent.MOUSE_EXITED, Animations.MINI_BUT.animateExit(minimizeBut));
        minimizeBut.setOnAction(e -> minimize());

        questionBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Animations.QUESTION_BUT.animateEntry(questionBut));
        questionBut.addEventHandler(MouseEvent.MOUSE_EXITED, Animations.QUESTION_BUT.animateExit(questionBut));

        submitBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Animations.NEXT_BUT.animateEntry(submitBut));
        submitBut.addEventHandler(MouseEvent.MOUSE_EXITED, Animations.NEXT_BUT.animateExit(submitBut));
        submitBut.addEventHandler(ActionEvent.ACTION, nextButAction);

    }


    private void minimize(){
        primaryStage.setIconified(true);
    }

    private void exitAction(){
        System.exit(0);
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    private void pickDirectory(){
        File dir = directoryChooser.showDialog(null);
        if(dir != null){
            textFieldDir.setText(dir.getAbsolutePath());
        }else{

        }
    }


}
