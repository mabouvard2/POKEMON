package view;

import entite.Entite;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import launch.Launch;


public class FenetrePrincipale {




    @FXML
    private Pane zoneDeJeu;

    public void initialize (){
        Launch.getLeManager().getListeEntite().addListener(new ListChangeListener<Entite>() {
            @Override
            public void onChanged(Change<? extends Entite> change) {
                 change.next();
                 for (Entite entite : change.getAddedSubList()){
                     ImageView entiteAfficher = new ImageView();
                     entiteAfficher.setImage(new Image(getClass().getResource(entite.getImage()).toExternalForm()));
                     entiteAfficher.layoutXProperty().bind(entite.xProperty());
                     entiteAfficher.layoutYProperty().bind(entite.yProperty());
                     entiteAfficher.setFitWidth(entite.getMaxWidth());
                     entiteAfficher.setFitHeight(entite.getMaxHeight());
                     zoneDeJeu.getChildren().add(entiteAfficher);

                 }
            }
        });

        for (Entite entite : Launch.getLeManager().getListeEntite()) {
            ImageView entiteAfficher = new ImageView();
            entiteAfficher.setImage(new Image(getClass().getResource(entite.getImage()).toExternalForm()));
            entiteAfficher.layoutXProperty().bind(entite.xProperty());
            entiteAfficher.layoutYProperty().bind(entite.yProperty());
            entiteAfficher.setFitWidth(entite.getMaxWidth());
            entiteAfficher.setFitHeight(entite.getMaxHeight());
            zoneDeJeu.getChildren().add(entiteAfficher);
        }

    }





}
