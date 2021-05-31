package model;

public class AttaquePokemon extends PokemonBase{
    /**
     * L'attribut d'attaque pour pokemon l'AttaquePokemon
     */
    private int attaque;


    /**
     * @param nom nom du pokemon
     */
    public AttaquePokemon(String nom) {
        super(nom);
        this.attaque = this.generateInt(20, 30);
    }
    /**
     * @param adversaire cible le pokemon a attaquer
     * @return le deroulement dde l'attaque
     */
    public String Attaquelaunch(PokemonBase adversaire) {
        //si pileOuFace est vrai alors attaquePoint=this.getAttaque() sinon il est égal a 27
        int attaquePoint = pileOuFace()?this.getAttaque():27;
        String deroulement = "";

        deroulement += super.launchAttaque(adversaire,attaquePoint);

        if ( !deroulement.contains("Pas assez d'énergie.") ) {

            if ( attaquePoint != 27 ) {
                deroulement += "\n" + "effet de type déclenché";
            } else {
                deroulement += "\n" + "effet de type ne s'est pas déclenché";
            }

        }
        return deroulement;
    }


    /**
     * Override augmentation du niveau du pokemonbase: attaque += 6
     */
    @Override
    public void NiveauPlus() {
        this.attaque += 6;
        super.NiveauPlus();
    }


    /**
     * getter de l'attaque
     * @return l'attaque du pokemon
     */
    @Override
    public int getAttaque() {
        return attaque;
    }


}

