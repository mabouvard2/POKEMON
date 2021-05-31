package view;

import model.PokemonBase;
import model.AttaquePokemon;
import model.DefensePokemon;
import model.FeuTypePokemon;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.util.*;
import static java.lang.Math.abs;


/**
 * Contrôleur de page de jeu
 */
public class GamePlayController {

    /**
     * Pour connaître le tour en cours, c'est quel joueur, true = ordinateur, false = joueur
     */
    @FXML
    private boolean TourOrdi = false;
    /**
     * La file d'attente d'événements pour enregistrer l'historique
     * des événements de clic, pour la validation et l'exécution d'actions
     */
    @FXML
    public static ArrayList<VBox> buttonEventQueue = new ArrayList<>();
    /**
     * Le tableau pour stocker les cartes GUI VBox pour le pokemon.
     */
    @FXML
    private VBox[][] CartesJoueur;
    /**
     * Le tableau de classe pokemon pour stocker la classe pokemons des deux joueurs, il s'agit d'un tableau
     * générique donc il peut stocker tous les types de pokémon.
     */
    @FXML
    private PokemonBase[][] PokemonsJoueur;
    /**
     * L'image pour le pokemon, sera affichée sur playersCards VBox.
     */
    @FXML
    private ImageView[][] ImageCarte;
    /**
     * C'est l'affichage des détails individuels des Pokémon
     **/
    @FXML
    private Label[] DetailPokemon;
    /**
     * Les détails du Pokémon (Nom, HP), cela sera affiché sur les cartes.
     */
    @FXML
    private Label[][] PokemonHpCarte;
    /**
     * Tous les boutons actionnables
     */
    @FXML
    private Button[] buttons;
    /**
     * Pour connaître le bouton actuel cliqué
     */
    @FXML
    private String BoutonClic;
    /**
     * Fenêtre de la page de jeu
     */
    @FXML
    public SplitPane GameplayPagePane;
    /**
     * La partie supérieure / inférieure du volet de jeu
     */
    @FXML
    public VBox gameplayZonePane, ActionButtonPane;
    /**
     * Le HBox qui comprendra les pokémons du joueur
     */
    @FXML
    public HBox player1group, player2group;
    /**
     * grille pour mettre en page les details du pokemon.
     */
    @FXML
    public GridPane PokemonPropertiesPane;
    /**
     * Le haut et le bas de tous les boutons actionnables dans un volet.
     */
    @FXML
    public HBox ButtonHBox, ButtonHBox2;
    /**
     * Tous les boutons actionnables
     */
    @FXML
    public Button BoutonAttaque, BoutonRecharge, BoutonEntrainement, BoutonRun;
    /**
     * Étiquette de détails Pokemon
     */
    @FXML
    public Label pokemonName, type, niveau, experience, energie, couleurEnergie, pointAttaque, pointDefense, status;
    /**
     * C'est la carte GUI
     */
    @FXML
    public VBox player1card1, player1card2, player1card3, player1card4, player1card5, player1card6;
    /**
     * C'est la carte GUI
     */
    @FXML
    public VBox player2card1, player2card2, player2card3, player2card4, player2card5, player2card6;
    /**
     * L'étiquette du joueur, hp du pokemon
     */
    @FXML
    public Label player1card1Hp, player1card2Hp, player1card3Hp, player1card4Hp, player1card5Hp, player1card6Hp;
    /**
     * L'étiquette du joueur, hp du pokemon
     */
    @FXML
    public Label player2card1Hp, player2card2Hp, player2card3Hp, player2card4Hp, player2card5Hp, player2card6Hp;
    /**
     * ImageView pour l'image pokemon du joueur
     */
    @FXML
    public ImageView player1card1Image, player1card2Image, player1card3Image, player1card4Image, player1card5Image, player1card6Image;
    /**
     * ImageView pour l'image pokemon du joueur
     */
    @FXML
    public ImageView player2card1Image, player2card2Image, player2card3Image, player2card4Image, player2card5Image, player2card6Image;


    /*
    lorsque l'utilisateur clique sur quelque chose, il sera ajouté à la file d'attente d'événements
    afin que l'historique de l'événement cliqué soit enregistré pour que l'animation sache
    sur quoi l'utilisateur a cliqué avant le clic actuel.
     */
    /**
     * @param cardIndex , la position de la carte cliquée dans le tableau
     */
    @FXML
    private void buttonEventHandler(int[] cardIndex)  {

        String returnedLog = "Joueur 1: \n";
        // quel est le clic actuel
        switch (BoutonClic){
            case "normal": // aucun bouton n'est cliqué, ceci est utilisé pour vérifier les statistiques de Pokémon.
                detailPokemon(cardIndex);
                break;

            case "attaque":
                // vérifier que le joueur a choisi son propre pokemon, ajouter à la file d'attente des événements pour les prochains appels
                int[][] pokemonIndexes = attaqueVerify(cardIndex);
                if(pokemonIndexes[0][0] != -1){
                    returnedLog += attaque( pokemonIndexes[0] , pokemonIndexes[1] );
                    BoutonClic = "normal";
                    clearText(returnedLog);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "recharge":
                returnedLog += recharge(cardIndex);
                BoutonClic = "normal";
                clearText(returnedLog);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "entrainement":
                returnedLog += entrainement(cardIndex);
                BoutonClic = "normal";
                clearText(returnedLog);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

            case "navigateToMenuPage":
                navigateToMenuPage();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;

        }
        // mettre à jour les détails du Pokemon
        majDetailPokemon();
    }
    /**
     * @param cardIndex , la position de la carte cliquée dans le tableau
     * @return l'index de la carte du joueur cliqué et l'index de la carte cible
     * cliquée (adversaire) sur le tableau
     */
    @FXML
    private int[][] attaqueVerify(int[] cardIndex){
        /*
        c'est pour vérifier si l'utilisateur clique sur pokemon adversaire (dans le premier clic),
        l'utilisateur doit d'abord choisir son propre pokemon pour effectuer l'attaque.
        si la taille de la file d'attente est de 2, cela signifie que l'utilisateur a cliqué deux fois sur quelque chose
        si le premier index pokemon passé (cardIndex) est 0 (player1), cela signifie
        la première file d'attente d'événements n'est pas valide, donc lui demande à nouveau.
         */
        if(buttonEventQueue.size() == 2 && cardIndex[0]==0
                || (cardIndex[0]==1 && buttonEventQueue.size() == 0)) {

            clearText("Veuillez re-choisir le pokémon!");

            buttonEventQueue = new ArrayList<>();
            return new int[][]{{-1},{-1}} ;

        } else if ( cardIndex[0] == 0 && buttonEventQueue.size() == 0) {
            buttonEventQueue.add(CartesJoueur[cardIndex[0]][cardIndex[1]]);
        } else if ( cardIndex[0] == 1 && buttonEventQueue.size() == 1) {
            buttonEventQueue.add(CartesJoueur[cardIndex[0]][cardIndex[1]]);
        }

        // supprimer les doublons
        if( buttonEventQueue.size() == 2 ) {

            if ( buttonEventQueue.get(0).getId().equals(buttonEventQueue.get(1).getId()) ) {
                buttonEventQueue.remove(0);
            }

        }
        // le premier élément ajouté est le pokémon du joueur
        int[] deuxiemeCardIndex = new int[]{-1};
        int[] premiereCardIndex = new int[]{-1};

        // obtenir le premier événement dans la file
        if (getCardIndex(buttonEventQueue.get(0).getId())[0] == 0) {
            clearText(" Tu as choisis " + PokemonsJoueur[cardIndex[0]][cardIndex[1]].getNom());
            //attends l'action du prochain bouton
            if( buttonEventQueue.size() == 2 ) {

                if ( getCardIndex(buttonEventQueue.get(1).getId())[0] == 1 ) {

                    disableButton(true);
                    premiereCardIndex = getCardIndex(buttonEventQueue.get(0).getId());
                    deuxiemeCardIndex = getCardIndex(buttonEventQueue.get(1).getId());

                    disableButton(false);
                    buttonEventQueue = new ArrayList<>();

                }
            }
        }

        return new int[][]{
                premiereCardIndex,
                deuxiemeCardIndex
        };

    }


    /**
     * @param indexPokemonFrom , la carte du joueur.
     * @param indexPokemonTo , l'attaque de la carte cible.
     * @return un journal de toutes les actions effectuées.
     */
    @FXML
    private String attaque(int[] indexPokemonFrom, int[] indexPokemonTo) {

        String journalPokemon="";

        PokemonBase pokemonAttaquant =  PokemonsJoueur[indexPokemonFrom[0]][indexPokemonFrom[1]];
        PokemonBase pokemonSubi = PokemonsJoueur[indexPokemonTo[0]][indexPokemonTo[1]];

        if (/*si c'est inactif*/ pokemonAttaquant.getTourRestant() > 0  ) {

            buttonEventQueue = new ArrayList<>();
            // Si l'ordinateur fait face à cette erreur on appel en récursif jusqu'à ce qu'elle réussisse
            if (TourOrdi) {
                journalPokemon += TourOrdi();
            } else{
                journalPokemon = "Pokemon est inactif pendant: " + pokemonAttaquant.getTourRestant() + " tours.";
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }else {
            // regarde si le pokemon est KO
            if ( pokemonAttaquant.getHp() < 0 ) {

                if (TourOrdi) {
                    journalPokemon += TourOrdi();
                }

            }

            else {

                String classType = pokemonAttaquant.getClass().getName();

                if ( classType.contains("Attaque") ) {

                    AttaquePokemon attaquePokemon = (AttaquePokemon) pokemonAttaquant;
                    journalPokemon = attaquePokemon.launchAttaque(pokemonSubi);

                } else if (classType.contains("Feu")) {

                    FeuTypePokemon feuTypePokemon = (FeuTypePokemon) pokemonAttaquant;
                    journalPokemon = feuTypePokemon.FeuTypeLaunchAttaque(pokemonSubi);

                } else {

                    // la défense et d'autres types de pokémons partagent la même fonction de lancement.
                    journalPokemon = pokemonAttaquant.launchAttaque(pokemonSubi);
                }

                // exécuter l'effet et appeler le tour de l'ordinateur.
                if (!journalPokemon.contains(" Pas assez d'énergie.")) {
                    if (TourOrdi) {
                        // du point de vue de l'ordinateur, lorsque l'ordinateur a terminé, cela signifie qu'un tour est terminé.
                        majTourJeu();
                    }

                    TourOrdi = !TourOrdi;
                    attaqueEffect(indexPokemonFrom, indexPokemonTo);

                } else if ( TourOrdi ) {

                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    // l'ordinateur tombe dans cette erreur d'énergie insuffisante
                    // s'appelle à nouveau récursivement.
                    journalPokemon += TourOrdi();

                }

                majDetailPokemon();
            }
        }

        return journalPokemon;
    }


    /**
     * @param indexPokemonFrom , carte du joueur.
     * @param indexPokemonTo , l'attaque de la carte cible.
     */
    @FXML
    private void attaqueEffect(int[] indexPokemonFrom, int[] indexPokemonTo) {

        VBox attackingCard = CartesJoueur[indexPokemonFrom[0]][indexPokemonFrom[1]];
        VBox receivingCard = CartesJoueur[indexPokemonTo[0]][indexPokemonTo[1]];
        PokemonBase attackingPokemon = PokemonsJoueur[indexPokemonFrom[0]][indexPokemonFrom[1]];

        double spacing = 10;

        new AnimationTimer() {

            private boolean animation = false;
            double currentX = 0, currentY = 0;
            // n = index, t = target, f = from
            // outputX = (nt - nf)*(space + width)
            // outputY = (height/2)+space
            double targetXIndex = indexPokemonTo[1] - indexPokemonFrom[1];
            double targetXSpaceWidth = spacing + attackingCard.getWidth();
            double outputX = targetXIndex * targetXSpaceWidth;

            double targetY = (receivingCard.getHeight()/2);
            double outputY = TourOrdi ? ( targetY + spacing ) : -(targetY + spacing);

            double pixelPerFrameX = outputX/10, pixelPerFrameY = outputY/10;


            private boolean oneRoundDone = false;
            private long lastSecond = -1;
            @Override
            public void handle(long now) {

                disableButton(true);
                long secondPassed = 1000000000;
                currentX += pixelPerFrameX;
                currentY -= pixelPerFrameY;

                attackingCard.setTranslateX(currentX);
                attackingCard.setTranslateY(currentY);

                if(!animation) {
                    if ( abs(currentX) > abs(outputX) || abs(currentY) > abs(outputY) ) {

                        pixelPerFrameX = -pixelPerFrameX;
                        pixelPerFrameY = -pixelPerFrameY;

                        oneRoundDone = true;

                        if (lastSecond < 0) {
                            lastSecond = now;
                        }
                    }

                    else if ( oneRoundDone ) {


                        // blinking effect
                        if (now - lastSecond < secondPassed * 0.0375) {
                            receivingCard.setVisible(false);
                        }
                        if (now - lastSecond > secondPassed * 0.075 && now - lastSecond < secondPassed * 0.1125) {
                            receivingCard.setVisible(true);
                        }

                        if (now - lastSecond > secondPassed * 0.1125 && now - lastSecond < secondPassed * 0.15) {
                            receivingCard.setVisible(false);
                        }

                        if (now - lastSecond > secondPassed * 0.15 && now - lastSecond < secondPassed * 0.2) {
                            receivingCard.setVisible(true);
                        }

                        if ( abs(currentX) <= 5 && abs(currentY) <= 5 ) {

                            currentY = 0;
                            pixelPerFrameX = 0;
                            pixelPerFrameY = 0;
                            oneRoundDone = false;
                            animation = true;
                            receivingCard.setVisible(true);

                        }
                    }
                }


                if ( animation ) {

                    majActionJeu();

                    if ( now - lastSecond > secondPassed * 1.5 && now - lastSecond < secondPassed * 2 ) {

                        if ( TourOrdi ) {
                            clearText("L'ordinateur pense...");
                        }

                    }

                    if ( now - lastSecond > secondPassed * 2 ) {

                        lastSecond = -1;
                        this.stop();

                        if ( TourOrdi ) {
                            TourOrdi();
                        }

                        disableButton(false);

                    }
                }
            }
        }.start();
    }


    /**
     * @param indexPokemon ,
     * @return un journal de toutes les actions effectuées.
     */
    @FXML
    private String recharge(int[] indexPokemon) {

        String pokemonReturnedLog = "";
        PokemonBase pokemonSelectionne = PokemonsJoueur[indexPokemon[0]][indexPokemon[1]];


        if(/*si c'est inactif*/ pokemonSelectionne.getTourRestant() > 0 ) {

            buttonEventQueue = new ArrayList<>();

            if ( TourOrdi ) {

                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pokemonReturnedLog += TourOrdi();

            }

            else{
                pokemonReturnedLog = "Pokemon est inactif pendant: " + pokemonSelectionne.getTourRestant() + " tours.";
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }


        }

        else {

            if ( pokemonSelectionne.getHp() < 0 ) {

                if (TourOrdi) {
                    pokemonReturnedLog += TourOrdi();
                }

            }

            else {

                String carteDessin = pokemonSelectionne.generateString(new String[]{"rouge", "bleu", "jaune"});
                boolean recharged = false;

                if ( !TourOrdi ) {
                    majTourJeu();
                }

                TourOrdi = !TourOrdi;


                if ( pokemonSelectionne.getColor().equals("aucune")
                        || pokemonSelectionne.getColor().equals(carteDessin) ) {

                    rechargeEffect(indexPokemon, true);
                    pokemonSelectionne.setEnergie(pokemonSelectionne.getEnergie() + 5);
                    recharged = true;

                }

                String showCard = String.format("Carte dessinée : %s", carteDessin);

                if ( recharged ) {
                    //ControllerUtil.playEffect(getClass().getResource("resources/fxml/assets/recharge.mp3"));
                    pokemonReturnedLog += String.format("%s\n%s a été rechargé avec succès! (%s)"
                            , showCard, pokemonSelectionne.getNom(), pokemonSelectionne.getColor());
                } else {
                    rechargeEffect(indexPokemon, false);
                    pokemonReturnedLog += String.format("%s\n%s n'a pas été rechargé avec succès! (%s)"
                            , showCard, pokemonSelectionne.getNom(), pokemonSelectionne.getColor());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        return pokemonReturnedLog;
    }


    /**
     * @param indexPokemon ,
     * @param show, afficher l'animation si la recharge réussit.
     */
    @FXML
    private void rechargeEffect(int[] indexPokemon, boolean show) {

        new AnimationTimer() {
            private long lastSecond = -1;
            @Override
            public void handle(long now) {

                long secondPassed = 1000000000;

                if ( lastSecond < 0 ) {
                    lastSecond = now;
                    disableButton(true);
                }

                if( show ) {

                    if (now - lastSecond < secondPassed * 0.0375) {
                        CartesJoueur[indexPokemon[0]][indexPokemon[1]].setVisible(false);
                    }
                    if (now - lastSecond > secondPassed * 0.075 && now - lastSecond < secondPassed * 0.1125) {
                        CartesJoueur[indexPokemon[0]][indexPokemon[1]].setVisible(true);
                    }

                    if (now - lastSecond > secondPassed * 0.1125 && now - lastSecond < secondPassed * 0.15) {
                        CartesJoueur[indexPokemon[0]][indexPokemon[1]].setVisible(false);
                    }

                    if (now - lastSecond > secondPassed * 0.15 && now - lastSecond < secondPassed * 0.2) {
                        CartesJoueur[indexPokemon[0]][indexPokemon[1]].setVisible(true);
                    }

                }


                if ( now - lastSecond > secondPassed * 1.5 && now - lastSecond < secondPassed * 2 ) {

                    if (TourOrdi) {
                        clearText("L'ordinateur pense...");
                    }

                }


                if( now - lastSecond > secondPassed * 2 ) {

                    lastSecond = -1;
                    majActionJeu();
                    this.stop();

                    if(TourOrdi){
                        TourOrdi();
                    }

                    disableButton(false);

                }
            }
        }.start();
    }


    /**
     * @param indexPokemon ,
     * @return un journal de toutes les actions effectuées
     */
    @FXML
    private String entrainement(int[] indexPokemon) {

        String pokemonReturnedLog = "";
        PokemonBase selectedPokemon = PokemonsJoueur[indexPokemon[0]][indexPokemon[1]];

        if (/*if it is idle*/ selectedPokemon.getTourRestant() > 0 ) {

            buttonEventQueue = new ArrayList<>();

            if (TourOrdi) {
                pokemonReturnedLog += TourOrdi();
            } else {
                pokemonReturnedLog += "Pokemon est inactif pendant: " + selectedPokemon.getTourRestant() + " tours.";
            }

        }

        else if ( selectedPokemon.getHp() < 0 ) {
            if (TourOrdi) {
                pokemonReturnedLog += TourOrdi();
            }
        }

        else {

            if ( selectedPokemon.getEnergie() < 5 ) {

                pokemonReturnedLog += selectedPokemon.getNom() + " il n'a pas assez d'energie pour etre entrainer";
                if (TourOrdi) {
                    pokemonReturnedLog += TourOrdi();
                }

            }

            else {

                selectedPokemon.expPlus();
                selectedPokemon.setEnergie(selectedPokemon.getEnergie() - 5);

                if(TourOrdi){
                    majTourJeu();
                }

                TourOrdi = !TourOrdi;

                entrainementEffet(indexPokemon);
                pokemonReturnedLog += selectedPokemon.getNom() + " a augmenté son expérience de 10!";

            }
        }
        return  pokemonReturnedLog;
    }


    /**
     * @param indexPokemon ,
     */
    @FXML
    private void entrainementEffet(int[] indexPokemon) {

        VBox selectedCard = CartesJoueur[indexPokemon[0]][indexPokemon[1]];

        new AnimationTimer() {

            private long lastSecond = -1;
            @Override
            public void handle(long now) {

                long secondPassed = 1000000000;

                if ( lastSecond < 0 ) {
                    lastSecond = now;
                    disableButton(true);
                }

                if (now - lastSecond < secondPassed * 0.0375) {
                    selectedCard.setVisible(false);
                }
                if (now - lastSecond > secondPassed * 0.075 && now - lastSecond < secondPassed * 0.1125) {
                    selectedCard.setVisible(true);
                }
                if (now - lastSecond > secondPassed * 0.1125 && now - lastSecond < secondPassed * 0.15) {
                    selectedCard.setVisible(false);
                }
                if(now - lastSecond > secondPassed * 0.15 && now - lastSecond < secondPassed * 0.2) {
                    selectedCard.setVisible(true);
                }
                if (now - lastSecond > secondPassed * 1.5 && now - lastSecond < secondPassed * 2) {
                    if (TourOrdi) {
                        clearText("L'ordinateur pense ...");
                    }
                }
                if(now - lastSecond > secondPassed * 2) {

                    lastSecond = -1;
                    this.stop();
                    majActionJeu();
                    if(TourOrdi){
                        TourOrdi();
                    }
                    disableButton(false);

                }
            }
        }.start();
    }

    /**
     * Pour accéder à la page de menu
     */
    @FXML
    private void navigateToMenuPage(){
        ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/MenuPage.fxml"));
        //ControllerUtil.playBackgroundMusic(getClass().getResource("resources/fxml/assets/theme.mp3"));
    }


    /**
     * @return un journal de toutes les actions effectuées
     */
    @FXML
    private String TourOrdi() {

        disableButton(true);
        int action = (int) Math.floor(Math.random() * 100);
        int montantCarte = 6;
        int[] indexPokemonFrom;
        int[] indexPokemonTo;


        do {
            indexPokemonFrom = new int[]{
                    1,
                    (int) Math.floor(Math.random() * montantCarte)
            };
            indexPokemonTo = new int[]{
                    0,
                    (int) Math.floor(Math.random() * montantCarte)
            };
            // si les index générés ne sont pas pokemon vivants, régénérez à nouveau.
        } while (PokemonsJoueur[indexPokemonFrom[0]][indexPokemonFrom[1]].getHp() <= 0
                || PokemonsJoueur[indexPokemonTo[0]][indexPokemonTo[1]].getHp() <= 0
        );

        String returnedLog = "";

        // l'attaque a 50% de chances
        if ( action >= 0 && action <= 50 ) {
            returnedLog += attaque(indexPokemonFrom, indexPokemonTo);
        }
        // la recharge a 25% de chance
        else if ( action>50 && action <=75 ) {
            returnedLog += recharge(indexPokemonFrom);
        }
        // le train a 25% de chance
        else {
            returnedLog += entrainement(indexPokemonFrom);
        }

        clearText("Ordinateur: \n"+returnedLog);

        // mettre à jour les détails de Pokemon
        majDetailPokemon();
        disableButton(false);

        return returnedLog;
    }


    /**
     * Le minuteur d'animation qui fera l'effet de révélation lorsque l'utilisateur
     * atterrit pour la première fois page de jeu.
     */
    @FXML
    private void revealEffect() {

        new AnimationTimer() {

            private long lastSecond = -1;
            @Override
            public void handle(long now) {

                long secondPassed = 1000000000;

                if(lastSecond<0){
                    lastSecond = now;
                }
                if(now - lastSecond > secondPassed*0.6){
                    player1card1.setVisible(true);
                    player2card6.setVisible(true);
                }
                if(now - lastSecond > secondPassed*1.2){
                    player1card2.setVisible(true);
                    player2card5.setVisible(true);
                }
                if(now - lastSecond > secondPassed*1.8){
                    player1card3.setVisible(true);
                    player2card4.setVisible(true);
                }
                if(now - lastSecond > secondPassed*2.4){
                    player1card4.setVisible(true);
                    player2card3.setVisible(true);
                }
                if(now - lastSecond > secondPassed*3){
                    player1card5.setVisible(true);
                    player2card2.setVisible(true);
                }
                if(now - lastSecond > secondPassed*3.6){

                    player1card6.setVisible(true);
                    player2card1.setVisible(true);
                    //nettoyer les animations
                    disableButton(false);
                    lastSecond = -1;
                    this.stop();

                }
            }
        }.start();
    }


    /**
     * @param promptText, la chaîne qui sera affichée sur l'étiquette du volet de détails
     */
    @FXML
    private void clearText(String promptText) {

        for( Label label: DetailPokemon ) {
            label.setText("");
        }

        //if there is any promptText
        energie.setText(promptText);
    }


    /**
     * @param cardID , l'id fx de la carte
     * @return l'index du pokemon de la carte dans le tableau
     */
    @FXML
    private int[] getCardIndex(String cardID) {

        int[] carteJoueur = new int[3];
        String[] indexCarteJoueur;

        // getting card index based on the id
        if ( cardID.contains("player1") ) {
            indexCarteJoueur = cardID.split("player1card");
        } else {
            indexCarteJoueur = cardID.split("player2card");
            carteJoueur[0] = 1;
        }

        carteJoueur[1]=Integer.parseInt(indexCarteJoueur[1]) - 1;

        return carteJoueur;
    }


    /**
     * @param cardIndex ,l'index du pokemon de la carte dans le tableau
     */
    @FXML
    private void detailPokemon(int[] cardIndex) {

        String className = PokemonsJoueur[cardIndex[0]][cardIndex[1]].getClass().getName();
        PokemonBase selectedPokemon = PokemonsJoueur[cardIndex[0]][cardIndex[1]];

        String classType;

        if ( className.contains("Attaque") ) {
            classType = "Attaque";
        } else if ( className.contains("Defense") ) {
            classType = "Defense";
        } else {
            classType = "Feu";
        }

        String pointAttaque = classType.equals("Attaque") ?
                Integer.toString(selectedPokemon.getAttaque())
                :"-";

        String pointDefense= classType.equals("Defense") ?
                Integer.toString(selectedPokemon.getResistancePoints())
                :"-";

        DetailPokemon[0].setText("Nom: " + selectedPokemon.getNom());
        DetailPokemon[1].setText("Type: " + classType);
        DetailPokemon[2].setText("Niveau: " + Integer.toString(selectedPokemon.getNiveau()));
        DetailPokemon[3].setText("Experience: "+ Integer.toString(selectedPokemon.getExp()));
        DetailPokemon[4].setText("Energie: " + Integer.toString(selectedPokemon.getEnergie()));
        DetailPokemon[5].setText("Couleur: " + selectedPokemon.getColor());
        DetailPokemon[6].setText("Point d'attaque: " + pointAttaque);
        DetailPokemon[7].setText("Point de defense: " + pointDefense);
        DetailPokemon[8].setText("Status: " + selectedPokemon.getStatus());

    }

    /**
     * Effectuer des actions dans cette fonction à la fin du tour de jeu
     */
    @FXML
    private void majTourJeu() {

        for( PokemonBase[] player: PokemonsJoueur ) {

            for ( PokemonBase pokemon : player ) {

                if( pokemon.getTourRestant() > 0 ) {
                    pokemon.setTourRestant(pokemon.getTourRestant() - 1);
                }
                if( pokemon.getTourRestant() == 0 ) {
                    pokemon.setStatus("active");
                }

            }
        }
    }


    /**
     * Lorsqu'il y a une action (attaque, recharge ...)
     */
    @FXML
    private void majActionJeu() {

        // verifie si un des joueurs ne gagne pas
        for ( int i = 0; i < PokemonsJoueur.length; i++ ) {

            int count = 0;

            for ( int j = 0; j < PokemonsJoueur[i].length; j++ ) {

                if( PokemonsJoueur[i][j].getHp() <= 0 ) {
                    CartesJoueur[i][j].setVisible(false);
                }
                if(PokemonsJoueur[i][j].getHp() <= 0) {
                    count ++;
                }
                if( count >= 3 ) {
                    //ControllerUtil.playBackgroundMusic(getClass().getResource("resources/fxml/assets/gameover.mp3"));
                    ControllerSingleton.getInstance().switchToScene(getClass().getResource("/fxml/GameOverPage.fxml"));
                    break;
                }
            }
        }
    }


    /**
     * Actualisez tous les détails de Pokemon sur leur carte graphique
     */
    @FXML
    private void majDetailPokemon() {

        for ( int i = 0; i < PokemonHpCarte.length; i++ ) {
            for ( int j = 0; j < PokemonHpCarte[i].length; j++ ) {
                PokemonHpCarte[i][j].setText(
                        "\n"
                                +PokemonsJoueur[i][j].getNom()
                                +"\n"+ "HP: "+Integer.toString(PokemonsJoueur[i][j].getHp())
                );
            }
        }
    }


    /**
     * @param disable, true pour désactiver tous les boutons
     */
    @FXML
    private void disableButton(boolean disable) {
        for(Button button: buttons){
            button.setDisable(disable);
        }
    }


    /**
     * initialiser l'image de la carte Pokemon
     */
    @FXML
    private void initializePokemonCardImage() {
        // si aucune entrée n'est spécifiée, on la génére
        int imageIndex = 6;
        for( ImageView[] playersCard: ImageCarte ) {

            for( ImageView cardImage: playersCard ) {
                Image image =  new Image(getClass().getResource("/images/pokemon"+imageIndex+".png").toString());
                cardImage.setImage(image);
                imageIndex +=1;

            }

        }
    }

    /**
     * initialiser les Pokémons des joueurs
     */
    @FXML
    private void initializePlayersPokemons() {

        // générer un pokemon s'il ne charge pas la partie sauvegardée
        PokemonsJoueur = new PokemonBase[][]{{
                new FeuTypePokemon("Dracaufeu"),
                new AttaquePokemon("Kyogre"),
                new DefensePokemon("Zamazenta"),
                new AttaquePokemon("Rayquaza"),
                new DefensePokemon("Laggron"),
                new DefensePokemon("Groudon"),
        },{
                new FeuTypePokemon("Typhlosion"),
                new AttaquePokemon("Latios"),
                new DefensePokemon("Heatran"),
                new AttaquePokemon("Zacian"),
                new DefensePokemon("Latias"),
                new DefensePokemon("Articodin"),
        }};

    }


    /**
     * initialiser la classe Players Card VBox
     */
    @FXML
    private void initializePlayersCardVBox() {

        double width = 20;
        double height = 30;
        double pokemonCardWidthRatio = 0.142;
        double pokemonCardHeightRatio = 0.3;

        CartesJoueur = new VBox[][] {
                {
                        player1card1,player1card2,player1card3, player1card4,player1card5,player1card6
                },
                {
                        player2card1,player2card2,player2card3,player2card4,player2card5,player2card6
                }
        };


        for ( int i = 0; i < PokemonsJoueur.length; i++ ) {

            for ( int j = 0; j < PokemonsJoueur[i].length; j++ ) {

                // afficher une couleur différente sur la base de la bordure sur la couleur pokemon
                if ( !PokemonsJoueur[i][j].getColor().equals("aucune") ) {
                    
                    String couleur = null;
                    if(PokemonsJoueur[i][j].getColor().equals("rouge"))
                    {
                        couleur="red";
                    }
                    if(PokemonsJoueur[i][j].getColor().equals("bleu"))
                    {
                        couleur="blue";
                    }
                    if(PokemonsJoueur[i][j].getColor().equals("jaune"))
                    {
                        couleur="yellow";
                    }
                    CartesJoueur[i][j].setStyle("-fx-border-radius:10;-fx-border-color:"+ couleur +";"
                        +"-fx-border-width:5;"
                     );

                } else{
                    CartesJoueur[i][j].setStyle("-fx-border-radius:10;");
                }
            }
        }


        // défini pour toutes les carte pokemon les largeurs et hauteurs
        for ( VBox[] player: CartesJoueur ) {

            for ( VBox card: player ) {
                card.setMinWidth(width*pokemonCardWidthRatio);
                card.setMinHeight(height*pokemonCardHeightRatio);
                card.setVisible(false);
            }

        }
    }


    /**
     * initialiser la classe Players Card VBox et ajouter MouseEvent
     */
    @FXML
    private void initializePlayersCardVBoxMouseEvent() {

        double width = 20;
        double height = 30;
        double pokemonCardWidthRatio = 0.142;
        double pokemonCardHeightRatio = 0.3;

        for ( int i = 0; i < CartesJoueur.length; i++ )
            for (int j = 0; j < CartesJoueur[i].length; j++) {

                VBox card = CartesJoueur[i][j];
                // au survol de la souris, entrez -> hoverEffect
                int a = i;
                int b = j;
                //au survol d'un pokemon text en violet
                card.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> PokemonHpCarte[a][b].setTextFill(Color.PURPLE));

                // à la sortie du survol de la souris -> supprimer hoverEffect
                card.addEventHandler(MouseEvent.MOUSE_EXITED, event -> PokemonHpCarte[a][b].setTextFill(Color.BLACK));

                // sur clic de souris -> afficher les statistiques
                card.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> buttonEventHandler(getCardIndex(card.getId())));

                card.setMinWidth(width * pokemonCardWidthRatio);
                card.setMinHeight(height * pokemonCardHeightRatio);
            }
    }


    /**
     * initialiser l'image de la classe Players Card VBox
     */
    @FXML
    private void initializePlayersCardVBoxImage() {

        ImageCarte = new ImageView[][] {
                {
                        player1card1Image,player1card2Image,
                        player1card3Image,player1card4Image,
                        player1card5Image,player1card6Image,
                },{
                player2card1Image,player2card2Image,
                player2card3Image,player2card4Image,
                player2card5Image,player2card6Image,
        }
        };

    }


    /**
     * initialiser les étiquettes du volet de détails en bas à gauche
     */
    @FXML
    private void initializeLabels() {

        DetailPokemon = new Label[] {
                pokemonName,type,niveau,
                experience,energie,couleurEnergie,
                pointAttaque,pointDefense,status
        };

        PokemonHpCarte = new Label[][] {
                {
                        player1card1Hp, player1card2Hp,
                        player1card3Hp,player1card4Hp,
                        player1card5Hp,player1card6Hp
                },{
                player2card1Hp, player2card2Hp,
                player2card3Hp,player2card4Hp,
                player2card5Hp,player2card6Hp
        }
        };

    }


    /**
     * initialiser tous les boutons actionnables
     */
    @FXML
    private void initializeButtons() {

        double width = 50;
        double height = 20;

        double bottomPaneHeightRatio = 0.3;
        double PokemonPropertiesPaneWidthRatio = 0.7;

        buttons = new Button[]{ BoutonAttaque, BoutonRecharge, BoutonEntrainement, BoutonRun };

        // disable button, enable when all cards are revealed.
        for ( Button button: buttons ) {
            button.setDisable(true);
        }

        ActionButtonPane.setStyle("-fx-background-color: #4eb9f7;");

        ButtonHBox.setMinHeight(height*bottomPaneHeightRatio*0.5);
        ButtonHBox.setSpacing(width*(1-PokemonPropertiesPaneWidthRatio)*0.125);
        ButtonHBox2.setMinHeight(height*bottomPaneHeightRatio*0.5);
        ButtonHBox2.setSpacing(width*(1-PokemonPropertiesPaneWidthRatio)*0.125);

        BoutonAttaque.setMinWidth(width*(1-PokemonPropertiesPaneWidthRatio)*0.25);
        BoutonRecharge.setMinWidth(width*(1-PokemonPropertiesPaneWidthRatio)*0.25);
        BoutonEntrainement.setMinWidth(width*(1-PokemonPropertiesPaneWidthRatio)*0.25);
        BoutonRun.setMinWidth(width*(1-PokemonPropertiesPaneWidthRatio)*0.25);


        BoutonAttaque.addEventFilter( MouseEvent.MOUSE_CLICKED, event -> {

            BoutonClic = BoutonClic.equals("attaque") ? "normal" : "attaque";

            if ( BoutonClic.equals("normal") ) {
                clearText("Cliquez sur n'importe quel Pokémon pour voir ses statistiques!");
            } else {
                clearText("Attaque: veuillez sélectionner l'un de vos pokémon"
                        + "\n" + "Cliquez à nouveau sur le bouton d'attaque pour annuler"
                );
            }
        });


        BoutonRecharge.addEventFilter( MouseEvent.MOUSE_CLICKED, event -> {

            BoutonClic = BoutonClic.equals("recharge") ? "normal" : "recharge";

            if ( BoutonClic.equals("normal") ) {
                clearText("Cliquez sur n'importe quel Pokémon pour voir ses statistiques!");
            } else {
                clearText("Recharge: veuillez sélectionner l'un de vos pokémon"
                        + "\n" + "Cliquez à nouveau sur le bouton Recharge pour annuler"
                );
            }
        });


        BoutonEntrainement.addEventFilter( MouseEvent.MOUSE_CLICKED, event -> {

            BoutonClic = BoutonClic.equals("entrainement") ? "normal" : "entrainement";

            if ( BoutonClic.equals("normal") ) {
                clearText("Cliquez sur n'importe quel Pokémon pour voir ses statistiques!");
            } else {
                clearText("Entrainement: Veuillez sélectionner l'un de vos pokémon"
                        + "\n" + "Cliquez à nouveau sur le bouton entrainement pour annuler"
                );
            }
        });

        BoutonRun.addEventFilter( MouseEvent.MOUSE_CLICKED, event -> {

            BoutonClic = BoutonClic.equals("navigateToMenuPage") ? "normal" : "navigateToMenuPage";

            navigateToMenuPage();
        });
    }


    /**
     * Initialiser le volet de la fenêtre de la page de jeu
     */
    @FXML
    private void initializePane() {

        double width = 800;
        double height = 600;

        double bottomPaneHeightRatio = 0.3;
        double PokemonPropertiesPaneWidthRatio = 0.7;

        GameplayPagePane.setMinWidth(width);
        GameplayPagePane.setMinHeight(height);
        GameplayPagePane.setMaxWidth(width);
        GameplayPagePane.setMaxHeight(height);
        GameplayPagePane.setStyle(
                "-fx-background-image:url("+getClass().getResource("/images/background.jpg")+");" +
                        "-fx-background-repeat:no-repeat;" +
                        "-fx-background-size:100% 100%;"
        );
        PokemonPropertiesPane.setMinWidth(width*PokemonPropertiesPaneWidthRatio);
        PokemonPropertiesPane.setMinHeight(height*bottomPaneHeightRatio);
        PokemonPropertiesPane.setStyle(
                "-fx-background-color: #38b3fb;"
        );
        player1group.setMinHeight(height*(1-bottomPaneHeightRatio)*0.5);
        player2group.setMinHeight(height*(1-bottomPaneHeightRatio)*0.5);
    }


    /**
     * Initialiser le contrôleur de page de jeu
     */
    @FXML
    public void initialize() {

        BoutonClic = "normal";

        this.initializePlayersPokemons();
        this.initializePlayersCardVBox();
        this.initializePlayersCardVBoxMouseEvent();
        this.initializePlayersCardVBoxImage();
        this.initializeLabels();
        this.initializeButtons();
        this.initializePane();
        this.initializePokemonCardImage();
        // play music
        //ControllerUtil.playBackgroundMusic(getClass().getResource("resources/fxml/assets/battle.mp3"));
        majDetailPokemon();
        revealEffect();
        clearText("Cliquez sur n'importe quel Pokémon pour afficher ses statistiques / cliquez sur n'importe quel bouton pour effectuer une action!");

    }


}

