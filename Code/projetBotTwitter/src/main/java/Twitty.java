import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Twitty {

    private static final long DUREE_CHARGEMENT = 500;

    /* Attributs */
    private String user;
    private Profil pro = new Profil();
    private boolean desc=true;
    private boolean local=true;
    private boolean lien=true;
    private boolean dtNaiss=true;
    private WebElement pseudo=null;
    private WebElement description=null;
    private WebElement localisation=null;
    private WebElement nbAbonnement=null;
    private WebElement nbAbonnes=null;
    private WebElement dateNaiss;
    private WebElement aRejoinTwitter=null;
    private WebDriver driver;




    /* Constructeur */

    public Twitty(String utilisateur){
        this.user=utilisateur;


    }


    /* Methodes */
    //public Twitty()


    public void scrapping() {

        System.setProperty("webdriver.chrome.driver", "Code/projetBotTwitter/chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        /*
        options.addArguments("--disable-gpu");
        options.addArguments("--headless");
        options.addArguments("--window-size=1400,800");**/
        driver= new ChromeDriver(options);



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
            if ((desc == true) && (local == true)) {
                nbAbonnes = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[2]/a/span[1]/span"));
                System.out.println("nombre d'abonnés : " + nbAbonnes.getText());
            }
            if ((desc == false) && (local == true)) {
                nbAbonnes = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div[2]/a/span[1]/span"));
                System.out.println("nombre d'abonnés : " + nbAbonnes.getText());

            }
            if ((desc == false) && (local == false)) {
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

        // DateNaissance

        try{

            if ((desc == true) & (local == true)) {
                dateNaiss = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[4]/div/span[2]"));
                System.out.println("date de naissance : " + dateNaiss.getText());
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
            pro.setDateNaiss(dateNaiss.getText());

        } catch (Exception e){
            System.out.println("le profil n'a pas mis sa date de naissance en public");
            dtNaiss=false;

        }

        // a rejoin twitter


        try {
            if (desc == true && local == true && dtNaiss==true) {
                System.out.println("je passe ici 1");
                aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[4]/div/span[3]/span"));
                System.out.println(aRejoinTwitter.getText());
            }
            if ((desc == false) && (local == false)) {
                    System.out.println("je passe ici 2");
                    aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div/span/span"));
                    System.out.println(aRejoinTwitter.getText());
                }
                if ((desc == false) && (local == true)) {
                    System.out.println("je passe ici 3");
                    aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div/span[2]/span"));
                    System.out.println(aRejoinTwitter.getText());

                }
                if ((desc == true) && (local == false)) {
                    System.out.println("je passe ici 4");
                    aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div/span/span"));
                    System.out.println(aRejoinTwitter.getText());
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

                 //verification si bas de la page atteint OU nombre de tweet à recuperer maximum atteint
                 while ((hauteurTot < taillePage) && (nbTweet < nbMaxTweet)) {

                     String xpaTweet = "/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/section/div/div/div[" + i + "]";
                     String xpaDate = xpaTweet + "/div/div/article/div/div/div/div[2]/div[2]/div[1]/div/div/div[1]/a/time";

                     //s'il existe
                     if (!driver.findElements(By.xpath(xpaTweet)).isEmpty()) {
                         if (!driver.findElements(By.xpath(xpaDate)).isEmpty()) {
                             try {
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
                                         try {
                                             lienTweet = driver.findElement(By.xpath(xpaLien)).getAttribute("href");
                                             System.out.println("Lien: " + lienTweet);
                                         } catch (Exception e) {
                                             lienTweet = "ERREUR";
                                             System.out.println("Lien: " + "(Erreur: pas de lien rencontre)");
                                         }

                                         //affichage texte tweet
                                         String xpaDivTweet = xpaTweet + "/div/div/article/div/div/div/div[2]/div[2]/div[2]/div[1]/div";
                                         try {
                                             List<WebElement> childDivTweet = driver.findElement(By.xpath(xpaDivTweet)).findElements(By.xpath("*"));
                                             String contenuTweet = "";
                                             System.out.println("-----------------------------------------------------");
                                             for (WebElement we : childDivTweet) {
                                                 if (!we.getText().isEmpty()) {
                                                     contenuTweet += we.getText() + "" + newLine;
                                                     System.out.println(we.getText() + "" + newLine + "-----------------------------------------------------");
                                                 }
                                             }
                                         //verif images/media sans div en dessous
                                         } catch (Exception e) {
                                             System.out.println("<--MEDIA-->");
                                             System.out.println(newLine + "-----------------------------------------------------");
                                         }


                                         //enregistrement nbLike & nbRT
                                         String xpaNbLikeSpan = xpaTweet + "/div/div/article/div/div/div/div[2]/div[2]/div[2]/div[3]/div/div[3]/div/div/div[2]/span/span/span";
                                         String xpaNbRTSpan = xpaTweet + "/div/div/article/div/div/div/div[2]/div[2]/div[2]/div[3]/div/div[2]/div/div/div[2]/span/span/span";
                                         String nbLike = "0";
                                         String nbRT = "0";
                                         //si xpa vide=0, sinon voir le contenu
                                         if (!driver.findElements(By.xpath(xpaNbLikeSpan)).isEmpty()) {
                                             nbLike = driver.findElement(By.xpath(xpaNbLikeSpan)).getText();
                                             System.out.println("int: " + numbersToInt(nbLike));
                                         }
                                         if (!driver.findElements(By.xpath(xpaNbRTSpan)).isEmpty()) {
                                             nbRT = driver.findElement(By.xpath(xpaNbRTSpan)).getText();
                                             System.out.println("int: " + numbersToInt(nbRT));
                                         }
                                         System.out.println("nbLike: " + nbLike);
                                         System.out.println("nbRT: " + nbRT + newLine);

                                     }
                                     //hauteur totale + hauteur du tweet
                                     try {
                                         hauteurTweet = driver.findElement(By.xpath(xpaTweet)).getSize().getHeight();
                                     } catch (Exception e) {
                                     }
                                     hauteurTot += hauteurTweet;
                                     //scroll de la taille du tweet
                                     js.executeScript("window.scrollTo(0," + hauteurTot + ")");
                                     //mise a jour de la taille de la page
                                     taillePage = (Long) js.executeScript("return document.body.scrollHeight");
                                     Thread.sleep(DUREE_CHARGEMENT);
                                 }
                                 //passage au prochain div[i]
                                 i = i + 1;
                             }catch (NoSuchElementException e){i=1;}
                             //pas de date=PAS UN TWEET
                         } else {
                             //scroll du probleme
                             hauteurTweet = driver.findElement(By.xpath(xpaTweet)).getSize().getHeight();
                             hauteurTot = hauteurTot + hauteurTweet;
                             js.executeScript("window.scrollTo(0," + hauteurTot + ")");
                             i = 1;
                             taillePage = (Long) js.executeScript("return document.body.scrollHeight");
                             Thread.sleep(DUREE_CHARGEMENT);
                         }
                     } else {
                         i = 1;
                     }
                     //verif div[i] maximum atteint
                     if (i > iMax)
                         i = 1;
                 }
             }

/**
         TODO - nbTweetMax,
         - tweets recuperes dans le bon ordre
         - (tweets manquants?)
         - probleme image sans texte
*/

         } catch(NoSuchWindowException e){
             System.out.println("Arret du programme. (fenetre fermee)");
         } catch(WebDriverException e){
             System.out.println("Page non accessible");
         }
         catch (Exception e) {
            e.printStackTrace();
         }


         }

         public int numbersToInt(String number){
            int res;
             // cas 1: #,? k => #?00
             if(number.contains(",")){
                 String[] tTmp={};
                 tTmp=number.split(",");
                 if(number.contains("k")){
                     tTmp[1]=tTmp[1].replace(" k","00");
                 }else if(number.contains("M")){
                     tTmp[1]=tTmp[1].replace(" M","00000");
                 }
                 res=Integer.parseInt(tTmp[0]+tTmp[1]);
             // cas 2: # k => #000
             }else if(number.contains("k")){
                 res=Integer.parseInt(number.replace(" k","000"));
             }else if(number.contains("M")){
                 res=Integer.parseInt(number.replace(" M","000000"));
             // cas 3: ? ??? => ????
             }else if(number.contains(" ")){
                 res=Integer.parseInt(number.replace(" ",""));
             } else{
                 try {
                     res = Integer.parseInt(number);
                 }catch (Exception e){
                     res=000;
                 }
             }
            return res;
         }


}