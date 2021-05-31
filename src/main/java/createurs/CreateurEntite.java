package createurs;

import entite.Personnage;
import model.Monde;

import java.util.Random;

public abstract class CreateurEntite {


    public abstract Personnage creerPersonnage(Monde m);
    public abstract void creerHerbe(Monde m);
    public  abstract void creerAdversaire(Monde m);


}
