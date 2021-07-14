package controllers;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;



import java.io.File;
import java.io.IOException;


public class MainApp {

    @FXML
    private Pane contentPane;

    @FXML
    private Button submitBut;

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

    @FXML
    private Label errorExtLabel;

    @FXML
    private Label errorDirLabel;

    @FXML
    private Label errorByteOneLabel;

    @FXML
    private Label errorByteTwoLabel;

    private Stage primaryStage;

    private final DirectoryChooser directoryChooser = new DirectoryChooser();

    private DataCollector dataCollector;

    private String directory = null;
    private String extension = null;
    private String byteStringTo = null;
    private String byteStringWith = null;
    private static String OS = null;

    public void initialize(){

        //Minimize function doesn't work on macOS
        if(!isWindows()){
            minimizeBut.setVisible(false);
        }


        EventHandler<ActionEvent> nextButAction = actionEvent -> {

            if(checkIfAllFilled()){

                directory = textFieldDir.getText();
                extension = textFieldExt.getText();
                byteStringTo = textFieldByteOne.getText();
                byteStringWith = textFieldByteTwo.getText();

                dataCollector = new DataCollector.Builder().setDirectory(directory).setExtension(extension).setByteStringTo(byteStringTo).setByteStringWith(byteStringWith).build();
                FXMLLoader loaderResult = new FXMLLoader(getClass().getResource("/fxml/Results.fxml"));
                loaderResult.setControllerFactory(e -> new Results(dataCollector));
                try {
                    Parent root = loaderResult.load();
                    contentPane.getChildren().clear();
                    contentPane.getChildren().add(root);
                    ((Results)(loaderResult.getController())).setContentPane(contentPane);
                    ((Results)(loaderResult.getController())).setPrimaryStage(primaryStage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };


        //Animations and action handling of all buttons
        pickDirectoryBut.setOnAction(e -> pickDirectory());
        pickDirectoryBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Utils.getAnimationEntry(pickDirectoryBut));
        pickDirectoryBut.addEventHandler(MouseEvent.MOUSE_EXITED, Utils.getAnimationExit(pickDirectoryBut));

        exitBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Utils.getAnimationEntry(exitBut));
        exitBut.addEventHandler(MouseEvent.MOUSE_EXITED, Utils.getAnimationExit(exitBut));
        exitBut.setOnAction(e -> exitAction());

        minimizeBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Utils.getAnimationEntry(minimizeBut));
        minimizeBut.addEventHandler(MouseEvent.MOUSE_EXITED, Utils.getAnimationExit(minimizeBut));
        minimizeBut.setOnAction(e -> ( (Stage) ( (Button) e.getSource() ).getScene().getWindow() ).setIconified(true));

        questionBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Utils.getAnimationEntry(questionBut));
        questionBut.addEventHandler(MouseEvent.MOUSE_EXITED, Utils.getAnimationExit(questionBut));
        questionBut.setOnAction(e -> showHelper());

        submitBut.addEventHandler(MouseEvent.MOUSE_ENTERED, Utils.getAnimationEntry(submitBut));
        submitBut.addEventHandler(MouseEvent.MOUSE_EXITED, Utils.getAnimationExit(submitBut));
        submitBut.addEventHandler(ActionEvent.ACTION, nextButAction);

    }

    private void exitAction(){
        System.exit(0);
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    /**
     * Method allows user to choose the directory
     */
    private void pickDirectory(){
        //After clicking pick directory button, the button will be turned off for 4s
        //Prevents double clicking on it and showing couple of windows
        new Thread(() -> {
            Platform.runLater(() -> pickDirectoryBut.setDisable(true));
            try {
                Thread.sleep(4000);
            } catch(InterruptedException ignored) {

            }
            Platform.runLater(() -> pickDirectoryBut.setDisable(false));
        }).start();

        File dir = directoryChooser.showDialog(null);
        if(dir != null) {
            textFieldDir.setText(dir.getAbsolutePath());
        }
    }

    /**
     * @return true or false, depend if every textFiled is filled in correct way
     */
    public boolean checkIfAllFilled(){
        boolean ifFilled = true;
        if(textFieldDir.getText().equals("")){
            errorDirLabel.setText("You need to enter directory!");
            ifFilled = false;
        }
        else{
            errorDirLabel.setText("");
        }

        if(textFieldExt.getText().equals("")){
            errorExtLabel.setText("You need to enter extension!");
            ifFilled = false;
        }
        else{
            errorExtLabel.setText("");
        }

        if(textFieldByteOne.getText().equals("")){
            errorByteOneLabel.setText("You need to enter first byte string!");
            ifFilled = false;
        }
        else if(Utils.checkByteString(textFieldByteOne.getText())){
            errorByteOneLabel.setText("You need to enter correct string!");
            ifFilled = false;
        }
        else{
            errorByteOneLabel.setText("");
        }

        if(textFieldByteTwo.getText().equals("")){
            errorByteTwoLabel.setText("You need to enter second byte string!");
            ifFilled = false;
        }
        else if(Utils.checkByteString(textFieldByteTwo.getText())){
            errorByteTwoLabel.setText("You need to enter correct string!");
            ifFilled = false;
        }
        else{
            errorByteTwoLabel.setText("");
        }

        return ifFilled;
    }

    public Pane getContentPane() {
        return contentPane;
    }

    public void showHelper(){
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Helper.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.getIcons().add(new Image("/img/icon.png"));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @return Name of operating system
     */
    public static String getOsName() {
        if(OS == null) { OS = System.getProperty("os.name"); }
        return OS;
    }
    public static boolean isWindows() {
        return getOsName().startsWith("Windows");
    }

}
