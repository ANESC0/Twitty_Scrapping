public class Profil {

    /* Attributs */

    private String pseudo;
    private String arobase;
    private String description;
    private String localisation;
    private String nbAbonnement;
    private String nbAbonnes;
    private String dateNaiss;
    private String aRejoinTwitter;



    /* Constructeur */
    public Profil() {
        this.pseudo = "";
        this.arobase= "";
        this.description = "";
        this.localisation = "";
        this.nbAbonnement = "";
        this.nbAbonnes = "";
        this.aRejoinTwitter = "";
        this.dateNaiss = "";

    }


    /* Methodes */

    /*Cette methode converti le a rejoin twitter en date
     *@param art correspond au a rejoin twitter du profil
     * @return correspond au a rejoin twitter final sans le texte devant au format MM/YYYY
     */

    public String aRejoinTwitterConvert(String aRT) {
        String nbMois = "";
        String date1 = aRT;
        int taille = date1.length();
        String mois = date1.substring(21, taille - 5);
        String annee1 = date1.substring(taille - 4);

        switch (mois) {
            case "janvier":
                nbMois = "01";
                break;
            case "février":
                nbMois = "02";

                break;
            case "mars":
                nbMois = "03";

                break;
            case "avril":
                nbMois = "04";

                break;
            case "mai":
                nbMois = "05";

                break;
            case "juin":
                nbMois = "06";

                break;
            case "juillet":
                nbMois = "07";

                break;
            case "août":
                nbMois = "08";

                break;
            case "septembre":
                nbMois = "09";
                break;
            case "octobre":
                nbMois = "10";
                break;
            case "novembre":
                nbMois = "11";

                break;
            case "décembre":
                nbMois = "12";
                break;

        }
        return (nbMois + "-" + annee1);
    }

    public String getArobase() {
        return arobase;
    }

    public void setArobase(String arobase) {
        this.arobase = arobase;
    }


    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getNbAbonnement() {
        return nbAbonnement;
    }

    public void setNbAbonnement(String nbAbonnement) {
        this.nbAbonnement = nbAbonnement;
    }

    public String getNbAbonnes() {
        return nbAbonnes;
    }

    public void setNbAbonnes(String nbAbonnes) {
        this.nbAbonnes = nbAbonnes;
    }

    public String getaRejoinTwitter() {
        return aRejoinTwitter;
    }

    public void setDateNaiss(String text) {
        this.dateNaiss = text;
    }

    public String getDateNaiss() {
        return this.dateNaiss;
    }


    public void setaRejoinTwitter(String aRejoinTwitter) {
        this.aRejoinTwitter = aRejoinTwitter;
    }


    /**
     * Ici, c'est la partie pour adapter les nombres récupérer(sous forme de String) en nombre de types int
     *
     * @param param la chaine de caractère à adapter
     * @return le nombre adapter de la chaine de caractère
     */
    public int adaptationNbFollow(String param) {

        int res = 0;

        if (!isNumeric(param)) {
            int l1 = 0;
            int l2 = 0;
            String[] charVirgule = {"a", "b"}; //tableau exemple supprimé si utilisé

            boolean virgule = false;

            String[] lastChar = param.split(" ");
            if (lastChar[0].contains(",")) {
                virgule = true;
                charVirgule = lastChar[0].split(",");
                l1 = charVirgule[0].length();
                l2 = charVirgule[1].length();

            }
            if (lastChar.length != 1) {

                switch (lastChar[1]) {
                    case "M":
                        if (virgule) {
                            int p1 = Integer.parseInt(charVirgule[0]);
                            int p2 = Integer.parseInt(charVirgule[1]);

                            res = p1 * 1000000;
                            res = res + (p2 * 100000);
                        } else {
                            res = Integer.parseInt(lastChar[0]);
                            res = res * 1000000;
                        }

                        break;
                    case "k":
                        if (virgule) {
                            int p1 = Integer.parseInt(charVirgule[0]);
                            int p2 = Integer.parseInt(charVirgule[1]);

                            res = p1 * 1000;
                            res = res + (p2 * 100);

                        } else {
                            res = Integer.parseInt(lastChar[0]);
                            res = res * 1000;
                        }

                        break;
                    default:
                        res = Integer.parseInt(lastChar[0]);
                        break;
                }

            } else {
                res = Integer.parseInt(lastChar[0]);
            }
        } else {
            String replaced = param.replaceAll(" ", "");
            res = Integer.parseInt(replaced);
        }

        return res;
    }

    public static boolean isNumeric(String str) {
        return str.matches("[0-9 ]+[\\\\.]?[0-9 ]*");  //match un nombre sans espace avant et après
    }

}
