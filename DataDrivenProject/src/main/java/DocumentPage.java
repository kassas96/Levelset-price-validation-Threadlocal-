

import java.util.ArrayList;
import java.util.Map;

public class DocumentPage {
    public static String priceSelector="//span[@class='price-amount' and contains(text(),%s)]/parent::div/parent::div/div[@class='left']";
    public static String docNameSelector ="//div[@class='left' and contains (text(),'%s')]";
    public static String expectedSelector="input#create";

    public static String priceByDocSelector="//div[@class='left' and contains (text(),'%s')]/following-sibling::div/span[@class='price-amount']";
    UiActions uiActions;

    public DocumentPage() {
        uiActions=new UiActions();
    }

    public void navigateToDocPage(){

        uiActions.clickOnButton(HomePage.getpaidxpath, UiActions.locType.xpath, String.format(docNameSelector,"Exchange a Waive"),UiActions.locType.xpath,UiActions.Condition.clickable);
        // uiActions.clickOn(Home2.getpaidxpath, UiActions.locType.xpath,true, expectedSelector,UiActions.locType.xpath);
        //  uiActions.clickOnButton(Home2.getpaidxpath, UiActions.locType.xpath, "516",UiActions.locType.xpath,UiActions.Condition.clickable);
    }

    public Map<String,String> getActualPrices(){
        ArrayList<String> docName=new ArrayList<>();
        docName.add("File a Lien");
        docName.add("Send a Warning");
        docName.add("Release a Lien");
        docName.add("Exchange a Waive");
        docName.add("Send a Payment Document");
        docName.add("Need a Notice");

        Map<String,String> actual=  uiActions.getAllDocAndPrices(docName);
        return actual;
    }



}
