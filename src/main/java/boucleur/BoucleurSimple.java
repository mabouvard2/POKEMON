package boucleur;

import javafx.beans.InvalidationListener;

public class BoucleurSimple extends Boucleur{


    public void run(){
        while (actif)
        beep();
        try {
            Thread.sleep(1000);

        }catch (InterruptedException e) {
            actif =false;
        }


    }

    @Override
    public void addListener(InvalidationListener invalidationListener) {

    }

    @Override
    public void removeListener(InvalidationListener invalidationListener) {

    }
}
