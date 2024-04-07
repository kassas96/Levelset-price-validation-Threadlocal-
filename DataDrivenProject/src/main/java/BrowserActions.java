

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserActions {
    public static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();
    public static void setWebDriver (Browser browser){
        switch (browser){
            case chrome :
                drivers.set(new ChromeDriver());
                break;
            case firefox :
                drivers.set(new FirefoxDriver());
                break;
        }
    }
    public static void scrollDown()  {
        JavascriptExecutor js = (JavascriptExecutor) drivers.get();
        js.executeScript("scrollBy(0,200)");

    }
    public static void windowMaxmize (){
        drivers.get().manage().window().maximize();
    }

    public static void quitDriver (){
        drivers.get().quit();
    }


    public enum Browser{
        chrome,
        firefox
    }
}
