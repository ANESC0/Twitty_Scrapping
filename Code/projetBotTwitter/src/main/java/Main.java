import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.*;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class Main  {



    public static void main(String[] args) {

        /**
         * Pour faire fonctionner seleninum chez vous. Il vous faudra installer le chrome driver sur le site
         *
         * https://chromedriver.storage.googleapis.com/index.html?path=97.0.4692.71/
         *
         * Par la suite vous devais changer votre chemin d'acces en fonction de la ou sera votre chromedriver.exe
         *
         * Pour trouver le chemin d'acces shift+clic droit sur le chromeDriver et appuyer sur la touche copier en tant que chemin d'acces
         *
         * changer ensuite le chemin d'acces dans le setProperties. Une page chrome devrais se lancer
         */

         Twitty tw=new Twitty("rebeudeter");
        tw.scrapping();

        //Main m = new Main();

        /* connexion **/

        //ConnexionTwitter ct = new ConnexionTwitter(driver);






    }

}


/**
 import views.Accueil;
 import views.BotJDBC;

 import javax.swing.*;

 public class Main extends JFrame {

 public Main() {
 this.setTitle("TwittyBot");
 this.setVisible(true);

 BotJDBC botJDBC = new BotJDBC();

 JMenuBar menuBar = new JMenuBar();
 JMenu menuAccueil = new JMenu("Accueil");
 JMenu menuPolitique = new JMenu("Politique de confidentialité");
 menuBar.add(menuAccueil);
 menuBar.add(menuPolitique);
 this.setJMenuBar(menuBar);

 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 this.setContentPane(new Accueil(botJDBC));
 this.setExtendedState(JFrame.MAXIMIZED_BOTH);
 //this.setUndecorated(true);
 this.pack();
 }

 public static void main(String[] args){
 Main m = new Main();
 }

 }
 */

