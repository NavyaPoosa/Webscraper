package demo;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestCases {

    ChromeDriver driver;

    //creating an object of object mapper class
    ObjectMapper mapper = new ObjectMapper();

    //get the user directory
    String userDir = System.getProperty("user.dir");

    // create an arraylist of hashmap
    ArrayList<HashMap<String, Object>> list = new ArrayList<>();

    @BeforeClass
    public void setup() {
        System.out.println("Constructor: Driver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        System.out.println("Successfully created Driver");
    }

    @Test
    public void testcase01() throws InterruptedException {
        System.out.println("Start Test case: Testcase01");

        // get the url
        driver.get("https://www.scrapethissite.com/pages/");

        // locate Hockey Teams: Forms, Searching and Pagination and click on it ans assert whether it is clicked or not
        WebElement hockeyElement = SeleniumWrapper.findElement_WebScraper(By.xpath("//a[@href='/pages/forms/']"),
                driver);

        Boolean testflow01 = SeleniumWrapper.click_WebScraper(hockeyElement, driver);
        Assert.assertEquals(testflow01, true, "Hockey Teams: Forms, Searching and Pagination link is not clicked");
        

        // for loop to iterate through 4 pages
        for (int k = 1; k <= 4; k++) {
            // locate page click
            WebElement pageclick = SeleniumWrapper
                    .findElement_WebScraper(By.xpath("//a[normalize-space()='" + k + "']"), driver);

            // Scroll the page till the pageclick and check it is enabled 
            SeleniumWrapper.javaScroll_WebScraper(pageclick, driver);
            if (pageclick.isEnabled()) {
                pageclick.click();
                Thread.sleep(3000);

            // store wins, years, names in a list of webElement
                List<WebElement> win = SeleniumWrapper
                        .findElements_WebScraper(By.xpath("//section[contains(@id,'hockey')]//td[6]"), driver);
                List<WebElement> year = SeleniumWrapper.findElements_WebScraper(
                        By.xpath("//section[contains(@id,'hockey')]//td[6]/preceding-sibling::td[4]"), driver);
                List<WebElement> name = SeleniumWrapper.findElements_WebScraper(
                        By.xpath("//section[contains(@id,'hockey')]//td[6]/preceding-sibling::td[5]"), driver);

                //Using for loop collect the text of win, year and name
                for (int i = 0; i < win.size(); i++) {
                    HashMap<String, Object> obj = new HashMap<>();
                    String wintext = win.get(i).getText();
                    String yeartext = year.get(i).getText();
                    String nametext = name.get(i).getText();
                    String epoch = SeleniumWrapper.epochTime_WebScraper();

                //if win% is less than 40% then insert win,year and name values to the hashmap 
                //add hashmap to a list 
                    int number = (int) (Double.parseDouble(wintext) * 100);
                    if (number < 40) {
                        obj.put("Name", nametext);
                        obj.put("Year", yeartext);
                        obj.put("Win %", wintext);
                        obj.put("Epoch", epoch);
                        list.add(obj);

                    }
                }
            }
        }
        // creating a new file with name JSONFromMap1.json
        File filename = new File(userDir+"\\src\\test\\resources\\JSONFromMap1.json");
       
        // Converting map to a JSON payload as string
        SeleniumWrapper.convertJSON(list, mapper);

        // Writing JSON on a file
        SeleniumWrapper.writeJSON(list, mapper, filename);

        // Checking if the specified file exists or not
        Boolean fileExists = SeleniumWrapper.fileExits(filename);
        Assert.assertEquals(fileExists, true, "File not exists");

        // Checking the file contains text or not 
        Boolean fileTextPresent = SeleniumWrapper.fileisEmptyorNot(filename);
        Assert.assertEquals(fileTextPresent, true, "File not exists");
    }

    @Test
    public void testcase02() throws InterruptedException {
        System.out.println("Start Test case: Testcase01");

        // get the url
        driver.get("https://www.scrapethissite.com/pages/");

        // locate Oscar Winning Films: AJAX and Javascript and click on it 

        WebElement oscarElement = SeleniumWrapper.findElement_WebScraper(By.xpath("//a[@href='/pages/ajax-javascript/']"), driver);
        Boolean testflow01 = SeleniumWrapper.click_WebScraper(oscarElement, driver);
        Assert.assertEquals(testflow01, true, "Oscar Winning Films: AJAX and Javascript link is not clicked");

        List<WebElement> years = SeleniumWrapper.findElements_WebScraper(By.xpath("//div[@class='col-md-12 text-center']/a"), driver);
        
        // click on every year displayed on the screen and collect the data
        for(WebElement year : years){
       if(year.isEnabled()){   
        year.click();
        Thread.sleep(3000);
    
        // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
        // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='col-md-12 text-center text-muted']")));

        // store tiles, nominations, awards, activeYear in a list of webElement
        List<WebElement> titles = SeleniumWrapper.findElements_WebScraper(By.xpath("//tbody[@id='table-body']/tr/child::td[@class='film-title']"), driver);
        List<WebElement> nominations = SeleniumWrapper.findElements_WebScraper(By.xpath("//tbody[@id='table-body']/tr/child::td[@class='film-nominations']"), driver);
        List<WebElement> awards = SeleniumWrapper.findElements_WebScraper(By.xpath("//tbody[@id='table-body']/tr/child::td[@class='film-awards']"), driver);
         WebElement activeYear = SeleniumWrapper.findElement_WebScraper(By.xpath("//a[@class='year-link active']"), driver);

        //Using for loop collect the text of win, year and name         
        for (int i = 0; i < titles.size(); i++) {
          HashMap<String, Object> obj = new HashMap<>();
          String titleText = titles.get(i).getText();
          String nominationText = nominations.get(i).getText();
          String awardText = awards.get(i).getText();
          String epoch = SeleniumWrapper.epochTime_WebScraper();
          String yearText = activeYear.getText();

        //First title in every page is a best picuture
        //declare a variable isWinner and assign true for it 
          Boolean isWinner;
          if(i==0){
               isWinner = true;
          }
          else{
               isWinner = false;
          }
          //adding values to the hashmap
          obj.put("Year", yearText);
          obj.put("Title", titleText);
          obj.put("Nominations", nominationText);
          obj.put("Awards", awardText);
          obj.put("Epoch", epoch);
          obj.put("BestPicture",isWinner);
          //adiing hashmap to the list 
          list.add(obj);
        }
       }
        }

        // creating a new file with name JSONFromMap2.json
        File filename = new File(userDir+"\\src\\test\\resources\\JSONFromMap2.json");

        // Converting map to a JSON payload as string
        SeleniumWrapper.convertJSON(list, mapper);

        // Writing JSON on a file
        SeleniumWrapper.writeJSON(list, mapper, filename);

        // Checking if the specified file exists or not
        Boolean fileExists = SeleniumWrapper.fileExits(filename);
        Assert.assertEquals(fileExists, true, "File not exists");

        //  // Checking the file contains text or not 
        Boolean fileTextPresent = SeleniumWrapper.fileisEmptyorNot(filename);
        Assert.assertEquals(fileTextPresent, true, "File is Empty");
    }
    @AfterClass
    public void endTest() {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }
}
