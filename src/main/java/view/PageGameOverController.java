package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 * Contrôleur de la page Game Over
 */
public class PageGameOverController {

    /**
     * La classe VBox pour la fenêtre game over sur la page
     */
    @FXML
    public VBox GameOverPagePane;
    /**
     * La classe ImageView pour le logo pokemon au milieu de la fenêtre
     */
    @FXML
    public ImageView menuLogo;
    /**
     * La classe ImageView pour l'image qui est fixée derrière le bouton
     */
    @FXML
    public ImageView returnButtonImage;
    /**
     * Le bouton
     */
    @FXML
    public Button returnButton;


    /**
     * Une fonction pour naviguer vers la page de menu
     */
    @FXML
    public void navigateToMenuPage() {
        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/MenuPage.fxml"));

    }


    /**
     * Initialiser le contrôleur de game over sur la page
     */
    @FXML
    public void initialize() {

        double widthRatio = 10;
        double heightRatio = 24;
        double imageWidthRatio = 3.25;

        double width = ControllerSingleton.getInstance().getScreenWidth();
        double height = ControllerSingleton.getInstance().getScreenHeight();

        GameOverPagePane.setMinWidth(width);
        GameOverPagePane.setMinHeight(height);
        GameOverPagePane.setMaxWidth(width);
        GameOverPagePane.setMaxHeight(height);

        GameOverPagePane.setStyle(
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
