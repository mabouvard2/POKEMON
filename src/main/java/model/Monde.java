package model;

import entite.Entite;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Monde {

    private ObservableList<Entite> lesEntites = FXCollections.observableArrayList();

    public void ajouterEntite(Entite e){

        lesEntites.add(e);
    }

    public ObservableList<Entite> getLesEntites(){
        return FXCollections.unmodifiableObservableList(lesEntites);
    }
}
