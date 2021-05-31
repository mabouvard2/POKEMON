package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


/**
 * Contrôleur de page de configuration
 */
public class PageReglageController {
    /**
     * puissance de la musique
     */
    @FXML
    private double volume = 0.5;
    /**
     * La classe VBox pour définir la fenêtre du volet de page
     */
    @FXML
    public VBox SettingPagePane;
    /**
     * Le logo pokemon placé au milieu de la fenêtre
     */
    @FXML
    public ImageView menuLogo;
    /**
     * L'image à côté du bouton
     */
    /**
     * Mets moins fort
     */
    @FXML
    public Button baisseVolume;
    /**
     * mets plus fort
     */
    @FXML
    public Button monteVolume;
    @FXML
    public ImageView returnButtonImage;
    /**
     * Le bouton retour pour naviguer
     */
    @FXML
    public Button returnButton;
    /**
     * la valeur courante du volume
     */
    @FXML
    public Label valeurVolume;


    /**
     * Accédez à la page de menu
     */
    @FXML
    public void navigateToMenuPage() {
        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/MenuPage.fxml"));
    }
        /**
         * on appel cette fonction pour baisser le volume
         */
        @FXML
        public void baisseVolume() {

            if ( volume > 0.01 ) {

                volume -= 0.05;
                valeurVolume.setText(volumePourcent(volume));
                ControllerSingleton.audioclip.setVolume(volume);

            }

        }
        /**
         * on appel cette fonction pour augmenter le volume
         */
        public void monteVolume() {

            if (volume < 1) {
                volume += 0.05;
                valeurVolume.setText(volumePourcent(volume));
                ControllerSingleton.audioclip.setVolume(volume);
            }
        }
        /**
         * * @param vol volume actuel
         * @return le pourcentage du volume
         */
        private String volumePourcent(double vol) {
            // retourne sous forme de pourcentage (45%)
            return ((int) Math.round(vol * 100)) + "%";

            }

        /**
         * Pour initialiser le contrôleur de page de configuration
         */
        @FXML
        public void initialize () {

            double widthRatio = 10;
            double heightRatio = 24;
            double imageWidthRatio = 3.25;
            double width = ControllerSingleton.getInstance().getScreenWidth();
            double height = ControllerSingleton.getInstance().getScreenHeight();

            SettingPagePane.setMinWidth(width);
            SettingPagePane.setMinHeight(height);
            SettingPagePane.setMaxWidth(width);
            SettingPagePane.setMaxHeight(height);
            SettingPagePane.setStyle(
                    "-fx-background-image:url(" + getClass().getResource("/images/background.jpg") + ");" +
                            "-fx-background-repeat:no-repeat;" +
                            "-fx-background-size:cover;"
            );

            menuLogo.setFitWidth(width / imageWidthRatio);

            returnButton.setPrefWidth(width / widthRatio);
            returnButton.setPrefHeight(height / heightRatio);
            returnButtonImage.setFitHeight(returnButton.getPrefHeight() * 2);

        }
}