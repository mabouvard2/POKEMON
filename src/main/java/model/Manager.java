package model;

import Deplaceur.Deplaceur;
import boucleur.Boucleur;
import boucleur.BoucleurSimple;

import createurs.CreateurEntiteSimple;
import entite.Entite;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import Deplaceur.DeplaceurSimple;
import javafx.collections.ObservableList;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import view.ControllerSingleton;

import java.util.List;

import static com.sun.glass.events.MouseEvent.*;
import static javax.swing.SwingConstants.TOP;

public class Manager {


        private Monde leMonde = new Monde();
        private CreateurEntiteSimple leCreateur = new CreateurEntiteSimple();
        private Boucleur leBoucleur = new BoucleurSimple();
        private Deplaceur leDeplaceur = new DeplaceurSimple();
        private Entite perso;
        public Manager(){
            leCreateur.creerHerbe(leMonde);
            perso=leCreateur.creerPersonnage(leMonde);
            leCreateur.creerAdversaire(leMonde);

            /*
            leBoucleur.addListenner(this);
            leBoucleur.setActif(true);
            new Thread(leBoucleur).start();
            */

        }
        public void stopBoucleur(){
            leBoucleur.setActif(false);
        }

        public ObservableList<Entite> getListeEntite(){
            return leMonde.getLesEntites();
        }

        public void deplace(KeyCode code) {
            switch (code){
                case Z:

                    if (perso.getY()==10){
                        break;
                    }
                    leDeplaceur.moveUP(perso);
                    if (perso.getX()>=230 && perso.getX()<=250 && perso.getY()>=130 && perso.getY()<=150){
                        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/GamePlay.fxml"));
                    }
                    break;
                case S:
                    if (perso.getY()==580){
                        break;
                    }
                    leDeplaceur.moveDOWN(perso);
                    if (perso.getX()>=230 && perso.getX()<=250 && perso.getY()>=130 && perso.getY()<=150){
                        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/GamePlay.fxml"));
                    }
                        break;
                case Q:
                    if (perso.getX()==10){
                        break;
                    }
                    leDeplaceur.moveLEFT(perso);
                    if (perso.getX()>=230 && perso.getX()<=250 && perso.getY()>=130 && perso.getY()<=150){
                        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/GamePlay.fxml"));
                    }
                    break;
                case D:
                    if (perso.getX()==580){
                        break;
                    }
                    leDeplaceur.moveRIGHT(perso);
                    if (perso.getX()>=230 && perso.getX()<=250 && perso.getY()>=130 && perso.getY()<=150){
                        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/GamePlay.fxml"));
                    }
                    break;
            }
        }
}

