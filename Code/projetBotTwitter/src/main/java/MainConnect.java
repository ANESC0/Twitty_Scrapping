import io.opentelemetry.exporter.logging.SystemOutLogExporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.*;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;


public class MainConnect {


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "Code/projetBotTwitter/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        Scanner sc= new Scanner(System.in);
        String user=sc.nextLine();

    /*
    options.addArguments("--disable-gpu");
    options.addArguments("--headless");
    options.addArguments("--window-size=1400,800");**/
        WebDriver driver = new ChromeDriver(options);


        String email = Compte.EMAIL;
        String mdp = Compte.MDP;
        driver.get("https://twitter.com/i/flow/login");
        driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);

        WebElement name = driver.findElement(By.xpath("//label/div/div[2]"));
        new Actions(driver).moveToElement(name).click().perform();
        new Actions(driver).sendKeys(email).perform();

        WebElement co = driver.findElement(By.xpath("//div[6]/div/span/span"));
        new Actions(driver).moveToElement(co).click().perform();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        WebElement weMdp = driver.findElement(By.xpath("//div[3]/div/label/div/div[2]/div/input"));
        new Actions(driver).moveToElement(weMdp).click().perform();
        new Actions(driver).sendKeys(mdp).perform();

        WebElement connexion = driver.findElement(By.xpath("//div[2]/div/div/div/span/span"));
        new Actions(driver).moveToElement(connexion).click().perform();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        driver.navigate().to("https://twitter.com/"+user);


    }
}

