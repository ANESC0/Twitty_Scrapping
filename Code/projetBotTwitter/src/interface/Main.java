import views.Accueil;

import javax.swing.*;

public class Main extends JFrame {

    public Main() {
        this.setTitle("TwittyBot");
        this.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        JMenu menuAcceuil = new JMenu("Acceuil");
        JMenu menuPolitique = new JMenu("Politique de confidentialité");
        menuBar.add(menuAcceuil);
        menuBar.add(menuPolitique);
        this.setJMenuBar(menuBar);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Accueil());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.pack();

    }

    public static void main(String[] args){
        Main m = new Main();
    }

}
