import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class PriceTest {

    DocumentPage documentPage2;
    HomePage home2;

    @BeforeTest
    public void setup(){
        BrowserActions.setWebDriver(BrowserActions.Browser.chrome);
        BrowserActions.windowMaxmize();
        home2=new HomePage();
        home2.navigateToHome();
        documentPage2 = new DocumentPage();
        documentPage2.navigateToDocPage();
        BrowserActions.scrollDown();

    }

    @DataProvider(name="getDocmentPrice", parallel = true)
    public Object[][] getDocmentPriceUsingJson(){
        return new Object[][]{
                {"File a Lien","$449"},
                { "Send a Warning","$59"},
                {"Release a Lien","$149"},
                {"Exchange a Waiver","Free"},
                {"Send a Payment Document","Free"},
                {"Need a Notice","$29"}
        };
    }
    @Test(dataProvider ="getDocmentPrice" )
    void testDocumentPriceUsingJsonDataDriven(String docName,Object expectedPrice) {

        Map<String, String> actual;
        actual = documentPage2.getActualPrices();


        for (Map.Entry<String, String> entry : actual.entrySet()) {
            String name = entry.getKey();
            String actualPrice = entry.getValue();
            if (name.equals(docName)) {
                Assert.assertEquals(actualPrice, expectedPrice);
                System.out.println(name + "'s price: " + actualPrice);
            }
        }

    }






    @DataProvider(name = "testDocumentPriceUsingExcelDataDriven", parallel = true)
    public Object[][] testDocumentPriceUsingExcelDataDriven() throws IOException {

        String excelFilePath=".\\data\\data driven.xlsx";
        FileInputStream inputStream = new FileInputStream(excelFilePath);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum()-4;
        int cols = sheet.getRow(0).getLastCellNum();

        Object[][] arrayExcelData = new Object[rows][cols];

        for (int r = 0; r < rows; r++) {
            XSSFRow row = sheet.getRow(r);
            if (row == null) {
                continue;
            } else {
                for (int c = 0; c < cols; c++) {
                    XSSFCell cell = row.getCell(c);

                    switch (cell.getCellType()) {
                        case STRING:
                            arrayExcelData[r][c] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            arrayExcelData[r][c] = cell.getNumericCellValue();
                            break;
                        case BOOLEAN:
                            arrayExcelData[r][c] = cell.getBooleanCellValue();
                            break;
                    }
                }
            }
        }

        workbook.close();
        inputStream.close();

        return arrayExcelData;
    }

    @Test(dataProvider = "testDocumentPriceUsingExcelDataDriven")
    void testDocumentPriceUsingDataDriven(String docName, Object expectedPrice) {

        String expectedPriceString;

        Map<String, String> actual = documentPage2.getActualPrices();

        for (Map.Entry<String, String> entry : actual.entrySet()) {
            String name = entry.getKey();
            String actualPrice = entry.getValue();

            if (name.equals(docName)) {
                System.out.println(name + "'s price: " + actualPrice);

                if (expectedPrice instanceof String) {
                    expectedPriceString = (String) expectedPrice;
                    Assert.assertEquals(actualPrice, expectedPriceString);
                } else {
                    expectedPriceString = String.valueOf(expectedPrice);        // Convert price to a string
                    Assert.assertEquals(actualPrice+".0", "$"+expectedPriceString);
                }
            }
        }

    }



    @AfterTest
    public void tearDown(){
        BrowserActions.quitDriver();
    }
}
