

public class HomePage {
    String homeUrl ="https://www.levelset.com/";

    public static String  getpaidxpath="//li[@class='top-level-link ml-sm-05 mr-sm-05 relative ml-0'] /a[text()='Get paid ']";
    UiActions uiActions;

    public HomePage() {
        uiActions=new UiActions();
    }
    public void navigateToHome(){
        uiActions.navigateToPage(homeUrl,getpaidxpath,UiActions.locType.xpath,UiActions.Condition.visibile);
    }
}
