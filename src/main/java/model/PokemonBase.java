package model;


/**
 * The base class for all types of pokemon
 */
public class PokemonBase {

    /**
     * Attribut du Pokemon
     */
    private int hp, energie, exp, niveau;
    /**
     * Le nombre de tours ou il reste le status
     */
    private int tourRestant;
    /**
     * Attribut du Pokemon
     */
    private String couleur, status, nom;


    /**
     * @param nom nom du Pokemon
     */
    PokemonBase(String nom) {
        this.nom = nom;
        this.hp = generateInt(50, 80);
        this.energie = generateInt(20, 50);
        this.couleur = generateString(new String[]{"rouge", "bleu", "jaune", "aucune"});
        this.status = "active";
        this.tourRestant = 0;
        this.niveau = 0;
        this.exp = 0;
    }


    /**
     * @param cible la cible
     * @return le deroulement de l'action
     */
    public String launchAttaque(PokemonBase cible) {

        int energieUse = 1;

        String returnString = "";

        if ( this.energie > 0 ) {


            int pointAttaque = 15;
            // on regarde si l'energie est suffisante pour mettre un critique
            if ( this.energie - energieUse > 2 ) {

                if ( this.getClass().getName().equals(cible.getClass().getName()) ) {
                    pointAttaque = 26;
                    energieUse = 6;
                }

            }
            this.energie -= energieUse;
            // si le Pokémon cible n'est pas actif, ajoute 5 point d'attaque
            if(!cible.getStatus().equals("active")){
                returnString += "\n" + "Le Pokémon cible est "+cible.getStatus()+", COUP CRITIQUE!";
                pointAttaque += 5;

            }

            String classType = cible.getClass().getName();

            if ( classType.contains("Defense") ) {
                DefensePokemon defenseTypePokemon = (DefensePokemon)cible;
                returnString += "\n" + defenseTypePokemon.defenseTypeLaunchDefense(pointAttaque);
            } else {
                returnString += "\n" + cible.defense(pointAttaque);
            }

            expPlus();

            if ( pointAttaque > 29 ) {
                return returnString + "\n" +"COUP CRITIQUE!";
            }


        } else {
            returnString += "Pas assez energie.";
        }
        return returnString;

    }

    /**
     * @param cible cible a attaquer
     * @param pointAttaque dommages au pokémon cible
     * @return deroulement des actions
     */
    String launchAttaque(PokemonBase cible, int pointAttaque){
        int energieUse = 1;
        String returnString = "";

        if ( this.energie > 0 ) {


            // vérifier s'il y a assez d'énergie pour mettre un crit
            if (this.energie - energieUse >= 2) {
                //si les pokemons sont de la meme especes
                if (this.getClass().getName().equals(cible.getClass().getName())) {
                    returnString += "\n" + "COUP CRITIQUE!";
                    pointAttaque += 12;
                    energieUse = 6;
                }

            }
            this.energie -= energieUse;

            if ( !cible.getStatus().equals("active") ) {
                returnString += "\n" + "Le Pokémon cible est  "+cible.getStatus()+", COUP CRITIQUE!";
                pointAttaque += 6;
            }

            String classType = cible.getClass().getName();

            if ( classType.contains("Defense") ) {
                DefensePokemon defenseTypePokemon = (DefensePokemon) cible;
                returnString += "\n" + defenseTypePokemon.defenseTypeLaunchDefense(pointAttaque);
            } else {
                returnString += "\n" + cible.defense(pointAttaque);
            }

            expPlus();


        } else {
            returnString += "Pas assez energie.";
        }

        return returnString;

    }


    /**
     * @param pointAttaque point d'attaque
     * @return deroulement
     */
    String defense(int pointAttaque) {
        this.setHp(this.getHp()- pointAttaque);
        return this.getNom()+" dégats subis "+pointAttaque;
    }


    /**
     * Exp Pokemon's + 1
     */
    public void expPlus() {
        this.exp += 10;
        if(this.exp == 20){
            NiveauPlus();
        }
    }


    /**
     * Niveau Pokemon + 1
     */
    public void NiveauPlus() {
        this.niveau += 1;
        this.exp = 0;
        this.hp += 4;
        this.energie += 8;
    }


    /**
     * le statut de ce Pokémon est sur brulé et augmente les tours restants de status.
     */
    void setBruler() {
        this.setStatus("brulé");
        // On mets 2 tour de brulure
        this.setTourRestant(this.getTourRestant()+2);
    }

    /**
     * le statut de ce pokémon est sur paralysé et augmente les tours restants de status.
     */
    void setParalyse() {
        this.setStatus("paralisé");
        // On mets 3 tour de paralysie
        this.setTourRestant(this.getTourRestant()+3);
    }

    /**
     * @param from début
     * @param to fin
     * @return un nombre entier entre le debut et la fin
     */
    public int generateInt(int from, int to) {
        return (int)((Math.random()*(to-from+1))+from);
    }


    /**
     * @param generatorList Une liste d'une chaîne
     * @return l'un des éléments de la liste
     */
    public String generateString(String[] generatorList) {
        int random = (int)(Math.random()*generatorList.length);
        return generatorList[random];
    }


    /**
     * Fonction aléatoire (pile ou face)
     * @return vrai = tête, faux = queue
     */
    boolean pileOuFace() {
        return Math.floor(Math.random()*2)==1;
    }


    /**
     * getter nom
     * @return nom du Pokemon
     */
    public String getNom() {
        return nom;
    }


    /**
     * setter nom
     * @param nom nouveau nom du pokemon
     */
    public void setName(String nom) {
        this.nom = nom;
    }


    /**
     * getter hp
     * @return hp du pokemon
     */
    public int getHp() {
        return hp;
    }


    /**
     * setter hp
     * @param newHp nouveau nombre d'hp
     */
    private void setHp(int newHp) {
        this.hp = newHp;
    }


    /**
     * getter energie
     * @return energie du Pokemon
     */
    public int getEnergie() {
        return energie;
    }


    /**
     * setter energie
     * @param energie nouvelle valeur d'energie
     */
    public void setEnergie(int energie) {
        this.energie = energie;
    }


    /**
     * getter exp
     * @return exp du Pokemon
     */
    public int getExp() {
        return exp;
    }


    /**
     * setter exp
     * @param exp nouvelle valeur d'exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }


    /**
     * getter niveau
     * @return niveau du Pokemon
     */
    public int getNiveau() {
        return niveau;
    }

    /**
     * setter niveau
     * @param niveau nouvelle valeur pour niveau
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }


    /**
     * getter tourRestant
     * @return nombre de tour de status restant
     */
    public int getTourRestant() {
        return tourRestant;
    }


    /**
     * setter tourRestant
     * @param tourRestant ajout d'un nombre de tours restants pour un status
     */
    public void setTourRestant(int tourRestant) {
        this.tourRestant = tourRestant;
    }


    /**
     * getter couleur pokemon
     * @return couleur du Pokemon
     */
    public String getColor() {
        return couleur;
    }


    /**
     * setter couleur pokemon
     * @param couleur nouvelle couleur du pokemon
     */
    public void setColor(String couleur) {
        this.couleur = couleur;
    }


    /**
     * getter statut
     * @return status du Pokemon
     */
    public String getStatus() {
        return status;
    }


    /**
     * setter status
     * @param status nouveau statut
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * getter point attaque
     * @return point d'attaque du Pokemon
     */
    public int getAttaque(){
        return 0;
    }

    /**
     * getter point defense
     * @return point defense du Pokemon
     */
    public int getResistancePoints() {
        return 0;
    }
}
