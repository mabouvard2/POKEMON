package entite;

public class Personnage extends Entite {

    public Personnage(double x, double y, String  image){
        super(x,y,image);
        maxHeight=40;
        maxWidth=40;
    }
}
