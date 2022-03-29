import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Twitty {

    /* Attributs */
    private String user;
    private Profil pro = new Profil();
    private boolean desc=true;
    private boolean local=true;
    private boolean lien=true;
    private WebElement pseudo=null;
    private WebElement description=null;
    private WebElement localisation=null;
    private WebElement nbAbonnement=null;
    private WebElement nbAbonnes=null;
    private WebElement aRejoinTwitter=null;
    private WebDriver driver;




    /* Constructeur */

    public Twitty(String utilisateur){
        this.user=utilisateur;


    }

    //public Twitty()


    public void scrapping() {

        System.setProperty("webdriver.chrome.driver", "Code/projetBotTwitter/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        /*
        options.addArguments("--disable-gpu");
        options.addArguments("--headless");
        options.addArguments("--window-size=1400,800");**/
        driver= new ChromeDriver(options);

        /**
        String email=Compte.EMAIL;
        String mdp=Compte.MDP;
        driver.get("https://twitter.com/i/flow/login");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        WebElement name=driver.findElement(By.xpath("//label/div/div[2]"));
        new Actions(driver).moveToElement(name).click().perform();
        new Actions(driver).sendKeys(email).perform();

        WebElement co= driver.findElement(By.xpath("//div[6]/div/span/span"));
        new Actions(driver).moveToElement(co).click().perform();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement weMdp=driver.findElement(By.xpath("//div[3]/div/label/div/div[2]/div/input"));
        new Actions(driver).moveToElement(weMdp).click().perform();
        new Actions(driver).sendKeys(mdp).perform();

        WebElement connexion=driver.findElement(By.xpath("//div[2]/div/div/div/span/span"));
        new Actions(driver).moveToElement(connexion).click().perform();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
         */


        driver.get("https://www.twitter.com/"+user+"");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        try {
            pseudo = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/div/div/div[2]/div/div/div[1]/div/div/span[1]/span"));
            System.out.println("pseudo de l'utilisateur : " + pseudo.getText());
            pro.setPseudo(pseudo.getText());

        } catch (Exception e) {
            System.out.println("pseudo qui comporte un emoji ou caractere spéciaux");
        }

        /*
         * Description
         */
        try {
            description = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div/div/span"));
            System.out.println("description : " + description.getText());
            pro.setDescription(description.getText());
        } catch (Exception e) {
            System.out.println("Description introuvable");
            desc = false;
        }


        //localisation

        try {
            if (desc == true) {
                localisation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div/span[1]/span/span"));
                System.out.println("localisation : " + localisation.getText());
                pro.setLocalisation(localisation.getText());
            }
            if (desc == false) {

                localisation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div/span[1]/span/span"));
                System.out.println("localisation : " + localisation.getText());
                pro.setLocalisation(localisation.getText());

            }


        } catch (Exception e) {
            local = false;
            System.out.println("localisation avec écritures spéciaux ou inexistante");
        }

        // nbabonnement

        try {
            if ((desc == true) & (local == true)) {
                nbAbonnement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[1]/a/span[1]/span"));
                System.out.println("nombre d'abonnements : " + nbAbonnement.getText());

            }
            if ((desc == false) & (local == true)) {
                nbAbonnement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div[1]/a/span[1]/span"));
                System.out.println("nombre d'abonnements : " + nbAbonnement.getText());

            }
            if ((desc == false) & (local == false)) {
                nbAbonnement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div[1]/a/span[1]/span"));
                System.out.println("nombre d'abonnements : " + nbAbonnement.getText());
            }

            if ((desc == true) & (local == false)) {
                nbAbonnement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[1]/a/span[1]/span"));
                System.out.println("nombre d'abonnements : " + nbAbonnement.getText());
            }
            pro.setNbAbonnement(nbAbonnement.getText());

        } catch (Exception e) {
            System.out.println("le nombre d'abonnement ne s'affiche pas");
        }


        // nbAbonnees

        try {
            if ((desc == true) & (local == true)) {
                nbAbonnes = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[2]/a/span[1]/span"));
                System.out.println("nombre d'abonnés : " + nbAbonnes.getText());
            }
            if ((desc == false) & (local == true)) {
                nbAbonnes = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div[2]/a/span[1]/span"));
                System.out.println("nombre d'abonnés : " + nbAbonnes.getText());

            }
            if ((desc == false) & (local == false)) {
                nbAbonnes = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div[2]/a/span[1]/span"));
                System.out.println("nombre d'abonnés : " + nbAbonnes.getText());
            }

            if ((desc == true) & (local == false)) {
                nbAbonnes = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[2]/a/span[1]/span"));
                System.out.println("nombre d'abonnés : " + nbAbonnes.getText());
            }
            pro.setNbAbonnes(nbAbonnes.getText());

        } catch (Exception e) {
            System.out.println("le nombre d'abonnés ne s'affiche pas");
        }


        // a rejoin twitter


        try {
            if (desc == true && local == true) {
                aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div/span[2]/span"));
                System.out.println(aRejoinTwitter.getText());
            } else {
                if ((desc == false) && (local == false)) {
                    aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div/span/span"));
                    System.out.println(aRejoinTwitter.getText());
                }
                if ((desc == false) && (local == true)) {
                    aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div/span[2]/span"));
                    System.out.println(aRejoinTwitter.getText());

                }
                if ((desc == true) && (local == false)) {
                    aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div/span/span"));
                    System.out.println(aRejoinTwitter.getText());
                }
            }
            pro.setaRejoinTwitter(aRejoinTwitter.getText());

        } catch (Exception e) {
            System.out.println("a rejoin twitter indisponible");
        }

        //  String dateProfil = aRejoinTwitter.getText().substring(1,1);

        /**  String nDate = aRejoinTwitter.getText().substring(21);
         String nYear = aRejoinTwitter.getText().substring(29);
         System.out.println(nDate);
         System.out.println(nYear);
         //
         // Date dateProfil= new SimpleDateFormat("MM/yyyy").parse(nDate)
         */


        /*
         * dans cette partie du code on transforme le " a rejoin twitter en date format
         *  mm/YYYY
         */

        /*int nFollower=Integer.parseInt(nbAbonnes.getText());
        int nFollowing=Integer.parseInt(nbAbonnement.getText());

      */


        /**
         * AJOUT DANS LA BASE DE DONNEE DU PROFIL SCRAPPE
         */





        /**
         * Recuperation des tweets
         */
         try {
             JavascriptExecutor js = (JavascriptExecutor) driver;
             long taillePage = (Long) js.executeScript("return document.body.scrollHeight");
             System.out.println("Taille page: " + taillePage);

             int nbMaxTweet = 50;

             int iMax = 18;
             int i = 1;
             int nbTweet = 0;
             ArrayList<String> listeTweetDate = new ArrayList<>();
             long hauteurTweet = 0;
             long hauteurTot = 0;
             String lienTweet="";


             boolean verifTweet = true;

             String xpaNbTweet="/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[1]/div[1]/div/div/div/div/div[2]/div/div";
             String nbTweetTxt=driver.findElement(By.xpath(xpaNbTweet)).getText();
             if(!nbTweetTxt.equalsIgnoreCase("0 Tweet")) {

                 //verifier si pas de changement d'HTML au cours du temps
                 while ((hauteurTot < taillePage) && (nbTweet < nbMaxTweet)) {


                     String xpaTweet = "/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/section/div/div/div[" + i + "]";
                     String xpaDate = xpaTweet + "/div/div/article/div/div/div/div[2]/div[2]/div[1]/div/div/div[1]/a/time";

                     //si il existe
                     if (!driver.findElements(By.xpath(xpaTweet)).isEmpty()) {
                         if (!driver.findElements(By.xpath(xpaDate)).isEmpty()) {

                             String date = driver.findElement(By.xpath(xpaDate)).getAttribute("datetime");
                             date = date.replace('T', ' ');
                             date = date.replace("Z", "");
                             String newLine = System.getProperty("line.separator");

                             //et si pas encore enregistre
                             if (!listeTweetDate.contains(date)) {
                                 //ET PAS UN RETWEET
                                 String xpaRT = xpaTweet + "/div/div/article/div/div/div/div[1]/div/div/div/div/div[2]/div/div/div/a";
                                 if (driver.findElements(By.xpath(xpaRT)).isEmpty()) {
                                     /*enregistrement*/
                                     listeTweetDate.add(date);

                                     //incrementation et affichage de la date du tweet
                                     nbTweet += 1;
                                     System.out.println("\n Date Tweet " + nbTweet + ": " + date);

                                     String xpaLien = xpaTweet + "/div/div/article/div/div/div/div[2]/div[2]/div[1]/div/div/div[1]/a";
                                     lienTweet = driver.findElement(By.xpath(xpaLien)).getAttribute("href");
                                     System.out.println("Lien: " + lienTweet);


                                     //affichage texte tweet
                                     //TODO problemes images, div en dessous
                                     String xpaDivTweet = xpaTweet + "/div/div/article/div/div/div/div[2]/div[2]/div[2]/div[1]/div";
                                     try {
                                         List<WebElement> childDivTweet = driver.findElement(By.xpath(xpaDivTweet)).findElements(By.xpath("*"));
                                         String contenuTweet = "";
                                         for (WebElement we : childDivTweet) {
                                             contenuTweet += we.getText() + "" + newLine;
                                             System.out.println(we.getText() + "" + newLine + "-----------------------------------------------------");
                                         }
                                     }catch (Exception e) {
                                         System.out.println("<--MEDIA-->");
                                         System.out.println(newLine + "-----------------------------------------------------");
                                     }


                                     //enregistrement nbLike & nbRT
                                     String xpaNbLikeSpan=xpaTweet+"/div/div/article/div/div/div/div[2]/div[2]/div[2]/div[3]/div/div[3]/div/div/div[2]/span/span/span";
                                     String xpaNbRTSpan=xpaTweet+"/div/div/article/div/div/div/div[2]/div[2]/div[2]/div[3]/div/div[2]/div/div/div[2]/span/span/span";
                                     String nbLike="0";
                                     String nbRT="0";
                                     //si xpa vide=0, sinon voir le contenu
                                     if(!driver.findElements(By.xpath(xpaNbLikeSpan)).isEmpty()){
                                        nbLike=driver.findElement(By.xpath(xpaNbLikeSpan)).getText();
                                     }
                                     if(!driver.findElements(By.xpath(xpaNbRTSpan)).isEmpty()){
                                         nbRT=driver.findElement(By.xpath(xpaNbRTSpan)).getText();
                                     }
                                     System.out.println("nbLike: "+nbLike);
                                     System.out.println("nbRT: "+nbRT +newLine);

                                 }
                                 //hauteur totale + hauteur du tweet
                                 hauteurTweet = driver.findElement(By.xpath(xpaTweet)).getSize().getHeight();
                                 hauteurTot += hauteurTweet;
                                 //scroll de la taille du tweet
                                 js.executeScript("window.scrollTo(0," + hauteurTot + ")");
                                 //mise a jour de la taille de la page
                                 taillePage = (Long) js.executeScript("return document.body.scrollHeight");
                             }
                             //passage au prochain div[i]
                             i = i + 1;
                             //pas de date=PAS UN TWEET
                         } else {
                             //scroll du probleme
                             hauteurTweet = driver.findElement(By.xpath(xpaTweet)).getSize().getHeight();
                             hauteurTot = hauteurTot + hauteurTweet;
                             js.executeScript("window.scrollTo(0," + hauteurTot + ")");
                             i = 1;
                             taillePage = (Long) js.executeScript("return document.body.scrollHeight");
                         }
                     } else {
                         i = 1;
                     }
                     //verif div[i] maximum atteint
                     if (i > iMax)
                         i = 1;

                 }
                 System.out.println("taille page:"+taillePage ) ;
                 System.out.println("hauteur totale:"+hauteurTot);
             }

/**
         TODO - nbTweetMax,
         - tweets recuperes dans le bon ordre
         - (tweets manquants?)
         - probleme image sans texte

*/
         } catch (Exception e) {

         e.printStackTrace();
         }


         }


}