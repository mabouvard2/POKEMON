package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 * Contrôleur de page de chargement
 */
public class PageChargementController {

    /**
     * La classe VBox pour le volet de la fenetre de la page de chargement
     */
    @FXML
    public VBox LoadPagePane;
    /**
     * La classe ImageView pour le logo pokemon au milieu de la fenêtre
     */
    @FXML
    public ImageView menuLogo;
    /**
     * image de Coming soon
     */
    @FXML
    public ImageView comingSoonImage;
    /**
     * L'image qui est fixée à côté du bouton
     */
    @FXML
    public ImageView returnButtonImage;
    /**
     * Le bouton de retour
     */
    @FXML
    public Button returnButton;
    /**
     * Une fonction pour naviguer dans le menu
     */
    @FXML
    public void returnToMenu() {
        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/MenuPage.fxml"));
    }


    /**
     * Initialiser le contrôleur de page de chargement
     */
    @FXML
    public void initialize() {

        double widthRatio = 10;
        double heightRatio = 24;
        double imageWidthRatio = 3.25;
        double width = ControllerSingleton.getInstance().getScreenWidth();
        double height = ControllerSingleton.getInstance().getScreenHeight();

        LoadPagePane.setMinWidth(width);
        LoadPagePane.setMinHeight(height);
        LoadPagePane.setMaxWidth(width);
        LoadPagePane.setMaxHeight(height);
        LoadPagePane.setStyle(
                "-fx-background-image:url("+getClass().getResource("/images/background.jpg")+");" +
                        "-fx-background-repeat:no-repeat;" +
                        "-fx-background-size:cover;"
        );

        menuLogo.setFitWidth(width/imageWidthRatio);

        returnButton.setPrefWidth(width/widthRatio);
        returnButton.setPrefHeight(height/heightRatio);
        returnButtonImage.setFitHeight(returnButton.getPrefHeight()*2);

    }

    
}