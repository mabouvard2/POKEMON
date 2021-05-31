package view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public class StartPageController {

        /**
         * The VBox class for load page pane window
         */
        @FXML
        public VBox LoadPagePane;
        /**
         * The ImageView class for pokemon logo in the middle of the window
         */
        @FXML
        public ImageView menuLogo;
        /**
         * Coming soon image
         */
        @FXML
        public ImageView comingSoonImage;
        /**
         * The image that's fixed beside the button
         */
        @FXML
        public ImageView returnButtonImage;
        /**
         * The return button
         */
        @FXML
        public Button returnButton;

        //private URL clickingEffect = getClass().getResource("resources/fxml/assets/mouseClick.mp3");


        /**
         * A function to navigate to menu
         */
        @FXML
        public void returnToMenu() {
            ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/startPage.fxml"));
        }


        /**
         * Initialize load page controller
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
                    "-fx-background-image:url("+getClass().getResource("/images/comingSoon.jpg")+");" +
                            "-fx-background-repeat:no-repeat;" +
                            "-fx-background-size:cover;"
            );

            menuLogo.setFitWidth(width/imageWidthRatio);

            returnButton.setPrefWidth(width/widthRatio);
            returnButton.setPrefHeight(height/heightRatio);
            returnButtonImage.setFitHeight(returnButton.getPrefHeight()*2);

        }


    }