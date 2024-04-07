

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.assertNotNull;

public class UiActions {
    public WebDriver driver;
    public UiActions() {
        driver=BrowserActions.drivers.get();
    }

    public void clickOnButton(String elementSelector, locType loc, String expectedElementSelector, locType locExpected, Condition cond) {
        WebElement button = driver.findElement(generateElement(elementSelector, loc));
        try {
            explicitWait(elementSelector,loc, Condition.clickable);
            button.click();

            if (explicitWait(expectedElementSelector, locExpected, cond)==null) {
//Or use this condition  if (!isElementPresent(expectedElementSelector, locExpected)) {
                Actions actions = new Actions(driver);
                actions.doubleClick(button).perform();
            }

            explicitWait(expectedElementSelector, locExpected, cond);
        } catch (Exception e) {
            try{
                JavascriptExecutor executor = (JavascriptExecutor)driver;
                executor.executeScript("arguments[0].click();", button);
            }
            catch(Exception c) {
                Assert.fail("Couldn't click because of" + c.getMessage());
            }

        }
        assertNotNull(explicitWait(expectedElementSelector, locExpected, cond));
    }

    public boolean isElementPresent(String elementSelector, locType loc) {
        try {
            driver.findElement(generateElement(elementSelector, loc));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public WebElement explicitWait(String expectedElementSelector, locType locExpected ,Condition cond){
        try {
            WebElement element=null;
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            switch (cond){
                case clickable:
                    element=   wait.until(ExpectedConditions.elementToBeClickable(generateElement(expectedElementSelector,locExpected)));
                    return element;
                case visibile:
                    element= wait.until(ExpectedConditions.visibilityOfElementLocated(generateElement(expectedElementSelector,locExpected)));
                    return element;

                default:
                    element = null;
                    Assert.fail("Wrong condition");
            }
            return element ;
        } catch (Exception e) {
            return null;
        }

    }
    public By generateElement(String elementSelector, locType loc ){
        By by=null;

        switch (loc){
            case id:
                by=  new By.ById(elementSelector);
                break;
            case name:
                by=  new By.ByName(elementSelector);
                break;
            case css:
                by=  new By.ByCssSelector(elementSelector);
                break;
            case xpath:
                by=  new By.ByXPath(elementSelector);
                break;

        }
        return by;
    }

    public void navigateToPage(String url, String selector, locType l,Condition condition){
        driver.get(url);
        By by=generateElement(selector,l);
        try {
            explicitWait(selector, l, condition);
        }
        catch (Exception e){
            Assert.fail("page failed to load");
        }
    }

    public Map<String,String> getAllDocAndPrices(ArrayList<String> dcocumentList){
        Map<String,String> mapper=new HashMap<>();
        for(int i=0;i<dcocumentList.size();i++) {
            WebElement documentName = driver.findElement(By.xpath(String.format(DocumentPage.docNameSelector, dcocumentList.get(i))));
            WebElement price = driver.findElement(By.xpath(String.format(DocumentPage.priceByDocSelector, dcocumentList.get(i))));

            mapper.put(documentName.getText(), price.getText());
        }

        return mapper;
    }




    public enum locType{
        id,
        name,
        xpath,
        css

    }

    public enum Condition{
        visibile,
        clickable

    }

}
