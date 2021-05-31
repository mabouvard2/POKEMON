package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

/**
 * Contrôleur de page du menu
 */
public class MenuPageController {

    /**
     * La classe VBox pour le volet de la fenetre de la page menu
     */
    @FXML
    public VBox MenuPagePane;
    /**
     * La classe ImageView pour le logo pokemon au milieu de la fenêtre
     */
    @FXML
    public ImageView menuLogo;
    /**
     * Le bouton pour naviguer vers différentes pages
     */
    @FXML
    public Button startButton, settingButton, loadButton, exitButton;
    /**
     * L'image fixée à côté de chaque correspond à chaque bouton
     */
    @FXML
    public ImageView startButtonImage, settingButtonImage, loadButtonImage, exitButtonImage;




    /**
     * Pour accéder à la page de chargement de sauvegarde du jeu
     */
    @FXML
    public void navigateToStatsPage() {
        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/PageStat.fxml"));
    }
    /**
     * Pour accéder à la page de parametre du jeu
     */
    @FXML
    public void navigateToSettingPage() {
        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/PageReglage.fxml"));
    }
    /**
     * Pour accéder à la page de jeu
     */
    @FXML
    public void navigateToGamePage(){
        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/PagePrincipale.fxml"));
    }

    /**
     * Pour quitter le programme
     */
    @FXML
    public void exitProgram() {
        System.exit(0);
    }

    /**
     * Initialiser le contrôleur de page de menu
     */
    @FXML
    public void initialize() {

        double widthRatio = 10;
        double heightRatio = 24;
        double imageWidthRatio = 3.25;
        double width = ControllerSingleton.getInstance().getScreenWidth();
        double height = ControllerSingleton.getInstance().getScreenHeight();

        MenuPagePane.setMinWidth(width);
        MenuPagePane.setMinHeight(height);
        MenuPagePane.setMaxWidth(width);
        MenuPagePane.setMaxHeight(height);
        MenuPagePane.setStyle(
                "-fx-background-image:url("+getClass().getResource("/images/background.jpg")+");" +
                        "-fx-background-repeat:no-repeat;" +
                        "-fx-background-size:cover;"
        );
        menuLogo.setFitWidth(width/imageWidthRatio);

        startButton.setPrefWidth(width/widthRatio);
        settingButton.setPrefWidth(width/widthRatio);
        loadButton.setPrefWidth(width/widthRatio);
        exitButton.setPrefWidth(width/widthRatio);

        startButton.setPrefHeight(height/heightRatio);
        settingButton.setPrefHeight(height/heightRatio);
        loadButton.setPrefHeight(height/heightRatio);
        exitButton.setPrefHeight(height/heightRatio);

        startButtonImage.setFitHeight(startButton.getPrefHeight() * 2);
        settingButtonImage.setFitHeight(settingButton.getPrefHeight() * 2);
        loadButtonImage.setFitHeight(loadButton.getPrefHeight() * 2);
        exitButtonImage.setFitHeight(exitButton.getPrefHeight() * 2);

    }


}
