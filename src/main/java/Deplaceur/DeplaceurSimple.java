package Deplaceur;

import entite.Entite;

public class DeplaceurSimple extends Deplaceur{
   @Override
    public void moveUP(Entite e) {
        e.setX(e.getX());
        e.setY(e.getY()-10);
    }
    public void moveDOWN(Entite e) {
        e.setX(e.getX());
        e.setY(e.getY()+10);
    }
    public void moveLEFT(Entite e) {
        e.setX(e.getX()-10);
        e.setY(e.getY());
    }
    public void moveRIGHT(Entite e) {
        e.setX(e.getX()+10);
        e.setY(e.getY());
    }
}
