import controllers.MainApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/mainApp.fxml"));
        Parent parent;
        try {
            parent = loader.load();
            Scene mainScene = new Scene(parent);
            mainScene.getStylesheets().add((Objects.requireNonNull(getClass().getResource("/stylesheets/style.css"))).toExternalForm());
            mainScene.setFill(Color.TRANSPARENT);

            ((MainApp)(loader.getController())).setPrimaryStage(primaryStage);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setTitle("Byte String Changer");
            primaryStage.getIcons().add(new Image("/img/icon.png"));
            primaryStage.setScene(mainScene);
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
