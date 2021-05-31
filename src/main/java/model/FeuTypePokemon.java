package model;

public class FeuTypePokemon extends PokemonBase{
    /**
     * @param nom nom du pokemon
     */

    public FeuTypePokemon(String nom)
    {
        super(nom);
    }

    /**
     * @param cible cible a attaquer
     * @return deroulement de l'action
     */
    public String FeuTypeLaunchAttaque(PokemonBase cible){
        String returnString ="";
        returnString += super.launchAttaque(cible);

        if(!returnString.contains("Pas assez energie.")){
            if(this.pileOuFace()){
                cible.setBruler();
                return returnString + "\n" + cible.getNom() + " est brulé! pendant " + cible.getTourRestant() + "tours.";
            }
            cible.setParalyse();
            return returnString + "\n" + cible.getNom() + " est paralisé! pendant " + cible.getTourRestant() + "tours.";
        }
        return returnString;
    }
}
