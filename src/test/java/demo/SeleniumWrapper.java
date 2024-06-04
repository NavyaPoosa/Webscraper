package demo;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SeleniumWrapper {

 public static Boolean click_WebScraper(WebElement ele, WebDriver driver){
        Boolean success;
        try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                wait.until(ExpectedConditions.elementToBeClickable(ele));
                Thread.sleep(2000);
                ele.click();
                success = true;
        }catch(Exception e){
            System.out.println("Exception occured while clicking:");
            e.printStackTrace();
            success = false;
        }
        return success;
    }

    public static WebElement findElement_WebScraper(By locator, WebDriver driver){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            return element;
            
        }
        catch(Exception e){
                System.out.println("Exception Occured!" + e. getMessage());
                return null;
                    }

}
public static void javaScroll_WebScraper(WebElement ele, WebDriver driver){
    try{
       JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", ele); 
        Thread.sleep(2000);
        
    }
    catch(Exception e){
            System.out.println("Exception Occured!" + e. getMessage());
                }

}

 public static List<WebElement> findElements_WebScraper(By locator, WebDriver driver){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
            List<WebElement> elements = driver.findElements(locator);
            return elements;
            
        }
        catch(Exception e){
                System.out.println("Exception Occured!" + e. getMessage());
                return null;
                    }
        
    } 
    public static String epochTime_WebScraper(){
        long currentTimestamp = System.currentTimeMillis();
        String epoch = String.valueOf(currentTimestamp);
        return epoch;
    }

    public static void convertJSON(ArrayList<HashMap<String,Object>> list, ObjectMapper mapper){
          try {
            String employeePrettyJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(list);
            System.out.println(employeePrettyJson);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void writeJSON(ArrayList<HashMap<String,Object>> list, ObjectMapper mapper, File filename){
     
      try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(filename, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
  }

  public static Boolean fileExits(File file){
   
    // Checking if the specified file exists or not
    if (file.exists())
        return true;
    else
        return false;  
  }

  public static Boolean fileisEmptyorNot(File file){
 
   //checking file is empty or not 
    if(file.length()>0)
        return true;    
    else
        return false;
}
}
