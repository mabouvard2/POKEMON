package createurs;

import entite.Adversaire;
import entite.HerbeHaute;
import entite.Personnage;
import model.Monde;

public class CreateurEntiteSimple extends CreateurEntite {


    public Personnage creerPersonnage(Monde m){
        Personnage perso = new Personnage(10,10,"/images/personnage.png");
        m.ajouterEntite(perso);
        return perso;

    }
    public void creerHerbe(Monde m){

        for (int x=0;x<600;x=x+30 ){
            for (int y=0;y<600;y=y+30) {
                m.ajouterEntite(new HerbeHaute(x,y,"/images/herbe.jpeg"));
            }

        }
    }
    public  void creerAdversaire(Monde m){
        m.ajouterEntite(new Adversaire(240,140,"/images/kirby.png"));
    }
}
