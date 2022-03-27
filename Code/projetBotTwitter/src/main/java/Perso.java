public class Perso {
    private final String mdp;
    private final String login;

    public Perso (String log,String mdp) {
        this.mdp = mdp;
        this.login = log;
    }

    public Perso() {
        this.mdp = "Muriel77";
        this.login = "malleret1u";
    }

    public String getMdp() {
        return this.mdp;
    }

    public String getLogin() {
        return this.login;
    }
}
