import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;

import java.util.*;
import java.util.concurrent.*;

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

    public ChromeDriver connexionTwitter(String user) throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "Code/projetBotTwitter/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();

    /*
    options.addArguments("--disable-gpu");
    options.addArguments("--headless");
        options.addArguments("--window-size=1400,800");*/
        WebDriver driver = new ChromeDriver(options);


        String email = Compte.EMAIL;
        String mdp = Compte.MDP;
        String nomUtil =Compte.NOM_UTILISATEUR;

        // page twitter de connection


        driver.get("https://twitter.com/i/flow/login");
        Thread.sleep(3000);

        try {

            WebElement name = driver.findElement(By.xpath("//label/div/div[2]"));
            new Actions(driver).moveToElement(name).click().perform();
            new Actions(driver).sendKeys(email).perform();

            WebElement co = driver.findElement(By.xpath("//div[6]/div/span/span"));
            new Actions(driver).moveToElement(co).click().perform();

            Thread.sleep(3000);

            WebElement weMdp = driver.findElement(By.xpath("//div[3]/div/label/div/div[2]/div/input"));
            new Actions(driver).moveToElement(weMdp).click().perform();
            new Actions(driver).sendKeys(mdp).perform();

            WebElement connexion = driver.findElement(By.xpath("//div[2]/div/div/div/span/span"));
            new Actions(driver).moveToElement(connexion).click().perform();

            //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        }catch (Exception e) {

            try {
                System.out.println("vérification requise");

                // entre le nom d'utilisateur dans la case

                WebElement weVerif1 = driver.findElement(By.xpath("//div[@id='layers']/div/div/div/div/div/div/div[2]/div[2]/div/div/div[2]/div[2]/div/div/div[2]/label/div/div[2]/div/input"));
                new Actions(driver).moveToElement(weVerif1).click().perform();
                new Actions(driver).sendKeys(nomUtil).perform();

                //clique sur suivant

                //oriente le curseur sur le boutton confirmer
                Thread.sleep(500);
                WebElement weVerif2 = driver.findElement(By.xpath("//div[2]/div[2]/div[2]/div/div/div/div"));
                new Actions(driver).moveToElement(weVerif2).click().perform();

                Thread.sleep(500);
                // on oriente et on clique pour mettre le mot de passe
                WebElement weVerif3 = driver.findElement(By.xpath("//div[3]/div/label/div/div[2]/div/input"));
                new Actions(driver).moveToElement(weVerif3).click().perform();
                new Actions(driver).sendKeys(mdp).perform();
                Thread.sleep(100);
                weVerif3.sendKeys(Keys.ENTER);

                Thread.sleep(1000);

                //driver.navigate().to("https://twitter.com/"+user);
            } catch(Exception ex){
                System.out.println("verication impossible");


            }
        }
        boolean trouve=false;
        while(!trouve) {
            try {
                WebElement recherche = driver.findElement(By.xpath("//div[2]/div/input"));
                new Actions(driver).moveToElement(recherche).click().perform();
                new Actions(driver).sendKeys("@" + user).perform();
                Thread.sleep(400);
                recherche.sendKeys(Keys.ENTER);
                trouve=true;
            }catch (NoSuchElementException e){
                Thread.sleep(1000);
            }
        }

        Thread.sleep(1500);
        trouve=false;
        while (!trouve) {
            try {
                WebElement recherche1 = driver.findElement(By.xpath("//a[contains(.,'Personnes')]"));
                new Actions(driver).moveToElement(recherche1).click().perform();
                trouve=true;
            }catch (NoSuchElementException e){Thread.sleep(1000);}
        }

        Thread.sleep(2000);

        try {
            WebElement searchUtil= driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/section/div/div/div[1]"));
            new Actions(driver).moveToElement(searchUtil,10,3).click().build().perform();
        }catch (NoSuchElementException e){
            System.out.println("Recherche: Utilisateur non trouve");
        }
        return (ChromeDriver) driver;
    }








    public void scrapping(String user) {

        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
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
            }
            if (desc == false) {

                localisation = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div/span[1]/span/span"));
            }
            System.out.println("localisation : " + localisation.getText());
            pro.setLocalisation(localisation.getText());

        } catch (Exception e) {
            local = false;
            System.out.println("localisation avec écritures spéciaux ou inexistante");
        }

        // nbabonnement

        try {
            if ((desc == true) & (local == true)) {
                nbAbonnement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[1]/a/span[1]/span"));

            }
            if ((desc == false) & (local == true)) {
                nbAbonnement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div[1]/a/span[1]/span"));

            }
            if ((desc == false) & (local == false)) {
                nbAbonnement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div[1]/a/span[1]/span"));
            }

            if ((desc == true) & (local == false)) {
                nbAbonnement = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[5]/div[1]/a/span[1]/span"));
            }
            System.out.println("nombre d'abonnements : " + nbAbonnement.getText());
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

            if (desc == true & local == true) {
                dateNaiss = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[4]/div/span[2]"));

            }
            if (desc == false & local == true) {
                dateNaiss = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[3]/div/span[2]"));

            }
            if (desc == false & local == false) {
                dateNaiss = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[3]/div/span[1]"));
            }

            if (desc == true & local == false) {
                dateNaiss = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[4]/div/span[1]"));
            }
            if (dateNaiss.getText().substring(0,1).equals("N")){
                System.out.println("date de naissance : " + dateNaiss.getText());

            }else{
                dtNaiss=false;

            }
            pro.setDateNaiss(dateNaiss.getText());

        } catch (Exception e){
            System.out.println("le profil n'a pas mis sa date de naissance en public");
            dtNaiss=false;

        }

        // a rejoin twitter


        try {
            if (desc == true && local == true && dtNaiss==true) {
                aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[4]/div/span[3]/span"));
            }
            if (desc == false && local == true && dtNaiss==true) {
                aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[3]/div/span[3]/span"));
            }
            if (desc == true && local == false && dtNaiss==true) {
                aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[4]/div/span[2]/span"));
            }
            if (desc == false && local == false && dtNaiss==true) {
                aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[3]/div/span[2]/span"));
            }


            if (desc == false && local == false && dtNaiss==false) {
                    aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div/span/span"));
                }
                if (desc == false && local == true && dtNaiss==false) {
                    aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[3]/div/span[2]/span"));

                }
                if ((desc == true) && (local == false) && (dtNaiss==false)) {
                    aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div[1]/div/div[4]/div/span/span"));
                }
            if ((desc == true) && (local == true) && (dtNaiss==false)) {
                aRejoinTwitter = driver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[4]/div/span[2]/span"));
            }
            System.out.println(aRejoinTwitter.getText());
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
                                         /*enregistrement
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


         } catch(NoSuchWindowException e){
             System.out.println("Arret du programme. (fenetre fermee)");
         } catch(WebDriverException e){
             System.out.println("Page non accessible");
         }
         catch (Exception e) {
            e.printStackTrace();
         }

*/
         }

    public void scrappFollower(WebDriver web) throws InterruptedException {

        //click sur abonnees
        WebElement weabo = web.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/div[2]/div/div/div/div/div[5]/div[2]/a"));
        new Actions(web).moveToElement(weabo).click().perform();

        Thread.sleep(1500);

        //recup divs des abonnees
        String xpaDivAbonnees="//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div[1]/div/section/div/div";
        List<WebElement> divsAbonnees=web.findElement(By.xpath(xpaDivAbonnees)).findElements(By.xpath("*"));
        for (WebElement e:divsAbonnees) {
            System.out.println(e);
            //verif dernier div vide present
            if(!e.findElements(By.xpath("./div/div/div")).isEmpty()){
                new Actions(web).moveToElement(e, 50, 1).click().build().perform();
                int attempts=0;
                while (attempts<5) {
                    try {
                        Thread.sleep(1000);
                        if (!e.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div[2]/div/div/div/span")).getText().equalsIgnoreCase(this.user)) {
                            String xpaNomUtilisateur = "/html/body/div[1]/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/div/div[2]/div[2]/div/div/div[1]/div/div/span[1]/span";
                            WebElement weNomUtil = web.findElement(By.xpath(xpaNomUtilisateur));
                            System.out.println(weNomUtil.getText());
                        }
                        web.navigate().back();
                        break;
                    }catch (StaleElementReferenceException ex){}
                    attempts++;
                }
            }
        }

        /*for (int i=1;i<18;i++){
            String xpaDivAbonnees="//*[@id=\"react-root\"]/div/div/div[2]/main/div/div/div/div[1]/div/section/div/div";
            if(!web.findElements(By.xpath(xpaDivAbonnees)).isEmpty()) {
                WebElement divAbonnee = web.findElement(By.xpath(xpaDivAbonnees));
                System.out.println(divAbonnee);
                new Actions(web).moveToElement(divAbonnee, 50, 1).click().build().perform();


                web.navigate().back();
            }
        }*/

        /*
        // xpath si le premier abonnee est lke compte sur lequel le bt s est connecte

        String xpathFstAD = "/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[1]/div/div/div/div[2]/div[1]/div/div/div[2]/div/a/div/div/span";

                           //  /html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[2]/div/div/div/div[2]/div[1]/div/div/div[2]/div/a/div/div/span
        String xpathFstSD = "/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[1]/div/div/div/div[2]/div/div/div/div[2]/div/a/div/div/span";

        // xpatch si le premier est un utilisateur random

        String xpath1AD = "/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div/div[2]/div/a/div/div/span";

                        //   /html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[1]/div/div/div/div[2]/div[1]/div[1]/div/div[2]/div/a/div/div/span
        String xpath1SD = "/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[1]/div/div/div/div[2]/div/div[1]/div/div[2]/div/a/div/div/span";

        WebElement el1 = null;
        int i = 1;
        if (web.findElements(By.xpath(xpathFstAD)).isEmpty()) {
            if (web.findElements(By.xpath(xpathFstSD)).isEmpty()) {

                  System.out.println("ça fonctionne");
                if (!web.findElements(By.xpath(xpath1AD)).isEmpty()) {
                    System.out.println("ça fonctionne6");
                    el1 = web.findElement(By.xpath(xpath1AD));
                } else {
                    System.out.println("ça fonctionne2");
                    el1 = web.findElement(By.xpath(xpath1SD));


                }


            }

        }
        System.out.println("ça fonctionne3");
        i += 1;
        int limite = this.numbersToInt(nbAbonnes.getText());
        String[] tabFollower = new String[100];
        tabFollower[0] = el1.getText();
        System.out.print(tabFollower[0]);



        for (i = 2; i < limite; i++) {

            if (web.findElements(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[" + i + "]/div/div/div/div[2]/div/div[1]/div/div[2]/div/a/div/div/span")).isEmpty()) {

                el1 = web.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[" + i + "]/div/div/div/div[2]/div/div[1]/div/div[2]/div/a/div/div/span"));
                tabFollower[i - 1] = el1.getText();

            }
            if (web.findElements(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[" + i + "]/div/div/div/div[2]/div[1]/div[1]/div/div[2]/div/a/div/div/span")).isEmpty()) {
                el1 = web.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/main/div/div/div/div/div/section/div/div/div[" + i + "]/div/div/div/div[2]/div[1]/div[1]/div/div[2]/div/a/div/div/span"));
                tabFollower[i - 1] = el1.getText();

            }
        }


        for (int j = 0; j < tabFollower.length; i++) {
            System.out.println(tabFollower[j]);
        }
*/

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