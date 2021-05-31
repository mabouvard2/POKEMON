package model;

public class DefensePokemon extends PokemonBase{
    /**
     * L'attribut de defense pour pokemon l'AttaquePokemon
     */
    private int defense;

    /**
     * @param nom nom du pokemon
     */
    public DefensePokemon(String nom) {
        super(nom);
        this.defense = this.generateInt(5,10);
    }

    /**
     * @param attaque attaque de l'autre pokemon
     * @return deroulement
     */
    String defenseTypeLaunchDefense(int attaque) {
        //si pileOuFace est vrai alors resistance=this.getResistancePoints() sinon il est égal a 0
        int resistance = pileOuFace() ? this.getResistancePoints() :0;
        attaque -= attaque - resistance < 0 ? attaque : resistance;

        String returnString = "";
        returnString += super.defense(attaque);

        if ( resistance == 0 ) {
            return returnString + "\n" + "effet de type déclenché";
        }


        return returnString + "\n" + "effet de type ne s'est pas déclenché";

    }

    /**
     * Override augmentation du niveau du pokemonbase: defense += 7
     */
    @Override
    public void NiveauPlus() {
        this.defense += 7;
        super.NiveauPlus();
    }


    /**
     * getter de l'attaque
     * @return defense du pokemon
     */
    @Override
    public int getResistancePoints() {
        return defense;
    }


}

