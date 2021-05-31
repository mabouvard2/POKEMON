package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Une classe singleton pour agir comme intermédiaire et assistant avec le contrôleur
 */
public class ControllerSingleton {
    static MediaPlayer audioclip;

    private static ControllerSingleton instance = null;

    /**
     * La classe de scène pour la fenêtre javafx
     */
    private  Stage stage;

    /**
     * Pour calculer la résolution d'écran de l'utilisateur
     */
    private Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

    private ControllerSingleton() {

    }

    public static ControllerSingleton getInstance(){
        if(instance == null){
            instance = new ControllerSingleton();
        }
        return instance;
    }




    /**
     * @param url la classe URL du fichier fxml, utilisez getClass.getResources().
     */
    public void switchToScene(URL url) {

        Parent root = null;
        try {
            // definition de la classe parente sur l'url transmise.
            root = FXMLLoader.load(url);

        } catch ( IOException e ) {
            e.printStackTrace();
        }
        // utilise la classe de scene actuelle et change la scene root
        stage.getScene().setRoot(root);

    }

    /**
     * @return retourne la largeur de l'écran de l'user
     */
    public double getScreenWidth() {
        return primaryScreenBounds.getWidth();
    }

    /**
     * @return retourne la hauteur de l'écran de l'user
     */
    public double getScreenHeight() {
        return primaryScreenBounds.getHeight();
    }

    public void setStage(Stage pStage) {
        this.stage=pStage;

    }
}
