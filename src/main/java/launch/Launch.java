package launch;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Manager;
import view.ControllerSingleton;
import view.ControllerSingleton;

import java.io.File;

public class Launch extends Application {
	    private static Manager leManager = new Manager();
    @Override
    public void start(Stage primaryStage) throws Exception{
        double width = 400;
        double height = 300;

        // initialise
        ControllerSingleton controlerView = ControllerSingleton.getInstance();
        controlerView.setStage(primaryStage);

        // Charger la page de menu fxml comme page de destination
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuPage.fxml"));

        Scene scene = new Scene(root, width, height);
      //  scene.getStylesheets().addAll(this.getClass().getResource("/fxml/CSSPage.css").toExternalForm());


        // parametre de taille
        primaryStage.setX(400);
        primaryStage.setY(300);
        primaryStage.setWidth(width);
        primaryStage.setHeight(height);

        // enter full screen and remove full screen exit hint
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Pokemon");

        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                leManager.deplace(event.getCode());
            }
        });

	primaryStage.setScene(scene);
        primaryStage.show();
        AudioClip audioClip = new AudioClip(getClass().getClassLoader().getResource("music/musique.mp3").toString());
        audioClip.play();


    }

    public static Manager getLeManager(){
        return leManager;
    }
    public void stop() throws Exception {

        super.stop();
    }

    /**
     * @param args environment arguments
     */
    public static void main(String[] args) {
        launch(args);
    }



}

