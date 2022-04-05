import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.Scanner;


public class MainConnect {


    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "Code/projetBotTwitter/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        Scanner sc= new Scanner(System.in);
        String user=sc.nextLine();

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


        //confirme la connexion

        Thread.sleep(1000);
        WebElement weVerif4 = driver.findElement(By.xpath("//div[2]/div/div/div/span/span"));
        new Actions(driver).moveToElement(weVerif4).click().perform();


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
                recherche.sendKeys(Keys.ENTER);
                trouve=true;
            }catch (NoSuchElementException e){
                Thread.sleep(1000);
            }
        }
        /**ons actionProvider = new Actions(driver);
        Action keydown = actionProvider.keyDown(Keys.ENTER).sendKeys("a").build();
        keydown.perform();*/
        //WebElement textbox = driver.findElement(By.xpath("//div[2]/div/input"));
        //new Actions(driver).moveToElement(textbox).click().perform();

       // WebDriverWait(driver, Duration.ofSeconds(10))
               // .until(ExpectedConditions.elementToBeClickable(By.xpath("//a/h3")));
        //
        //new WebDriverWait(driver, Duration.ofSeconds(3))
            //
        //   .until(driver -> driver.findElement(By.name("q")));

// clique dans la categorie personnes de recherche
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
    }
}

