package boucleur;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

import java.util.ArrayList;
import java.util.List;

public abstract class Boucleur implements Runnable, Observable {

    private List<InvalidationListener> lesObservateurs = new ArrayList<>();
    protected boolean actif = false;

    public boolean isActif() {
        return actif;
    }

    public void setActif(boolean actif) {
        this.actif = actif;
    }

    public List<InvalidationListener> getLesObservateurs() {
        return lesObservateurs;
    }

    public void setLesObservateurs(List<InvalidationListener> lesObservateurs) {
        this.lesObservateurs = lesObservateurs;
    }

    public void addListenner(InvalidationListener listener){
        lesObservateurs.add(listener);
    }

    public void removeListenner(InvalidationListener listener){
        lesObservateurs.remove(listener);
    }

    public void beep(){

        lesObservateurs.forEach(o -> Platform.runLater(() ->o.invalidated(this)));
    }


}
