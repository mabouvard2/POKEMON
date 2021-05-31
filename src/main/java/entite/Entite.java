package entite;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public abstract class Entite {

    protected int maxWidth = 30;
    protected int maxHeight = 30;

    protected DoubleProperty x = new SimpleDoubleProperty();
    public double getX() {return x.get();}
    public  DoubleProperty xProperty(){return x;}
    public void setX(double x){this.x.set(x);}

    protected DoubleProperty y = new SimpleDoubleProperty();
    public double getY() {return y.get();}
    public  DoubleProperty yProperty(){return y;}
    public void setY(double y){this.y.set(y);}


    protected String image;

    public Entite(double x, double y,String image){
        this.x.set(x);
        this.y.set(y);
        this.image=image;
    }

    public String getImage(){
        return image;
    }

    public int getMaxWidth(){
        return maxWidth;
    }
    public int getMaxHeight(){
        return maxHeight;
    }


}
