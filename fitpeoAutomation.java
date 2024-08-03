import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;

public class fitpeoAutomation {

    public static void main(String[] args) throws InterruptedException, AWTException {
        // Using Chrome Driver with Tool Selenium and integrated with Java

        //Properties to enable Webdriver are setup

        //Device : Win 32

        System.setProperty("webdriver.chrome.driver", "E://Selenium folder/chromedriver.exe");

        //webdriver is initialized
        WebDriver driver = new ChromeDriver();

        //opening chrome and navigating to desired web page
        // driver.get("https://www.fitpeo.com/");

        // we can use either methods in open the web page but navigate method has more features
        driver.navigate().to("https://www.fitpeo.com/");

        //maximizing for better view
        driver.manage().window().maximize();

        // need  not to wait but to catch up with web loading speed we are using Thread . sleep to hold for 1sec;
        Thread.sleep(1000);

        // now clicking on revenuq calculato
        driver.findElement(By.xpath("//div[text()='Revenue Calculator']")).click();

        //wait for one sec to catch up with browser even the selenium waits for page to load automatically we for to be more precise use this
        Thread.sleep(1000);

        //scorell for better appearence
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,500)");

        //selecting the slider element and storing in a variable
        WebElement sliderBox = driver.findElement(By.xpath("//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-duk49p']"));
        WebElement slider = sliderBox.findElement(By.xpath("//input[@type='range']"));
        WebElement textFiled =driver.findElement(By.xpath("//input[@type='number']"));

        int width = sliderBox.getSize().getWidth();

        int currentVal = Integer.parseInt(slider.getAttribute("value"));

        int max = 2000; // Maximum value of the slider
        int req = 820; // Required value
        int offset = (int) (((double) width / max )* (req - currentVal));


        Actions act = new Actions(driver);
        act.dragAndDropBy(slider,offset,0).build().perform();

        int textFieldVal =Integer.parseInt( textFiled.getAttribute("value"));

        currentVal = Integer.parseInt(slider.getAttribute("value"));

        if(Objects.equals(textFieldVal,currentVal)){
            System.out.println("Slider has no error");
        }else{
            System.err.println("slider  to text has error");
        }

        act.moveToElement(textFiled).click().build().perform();

        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        //for input
        robot.keyPress(KeyEvent.VK_5);
        robot.keyRelease(KeyEvent.VK_5);
        robot.keyPress(KeyEvent.VK_6);
        robot.keyRelease(KeyEvent.VK_6);
        robot.keyPress(KeyEvent.VK_0);
        robot.keyRelease(KeyEvent.VK_0);
        //for previous output
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);
        robot.keyPress(KeyEvent.VK_BACK_SPACE);
        robot.keyRelease(KeyEvent.VK_BACK_SPACE);

        robot.keyPress(KeyEvent.VK_8);
        robot.keyRelease(KeyEvent.VK_8);
        robot.keyPress(KeyEvent.VK_2);
        robot.keyRelease(KeyEvent.VK_2);
        robot.keyPress(KeyEvent.VK_0);
        robot.keyRelease(KeyEvent.VK_0);

        // checkin val again
        textFieldVal =Integer.parseInt( textFiled.getAttribute("value"));
        currentVal = Integer.parseInt(slider.getAttribute("value"));

        if(Objects.equals(textFieldVal,currentVal)){
            System.out.println("Slider has no error");
        }else{
            System.err.println("slider  to text has error");
        }

        List<WebElement> CPTChecks = driver.findElements(By.xpath("//div[@class='MuiBox-root css-4o8pys']"));

        for(int i =0;i<CPTChecks.size();i++){
            String currentTxt = CPTChecks.get(i).findElement(By.xpath(".//p[@class='MuiTypography-root MuiTypography-body1 inter css-1s3unkt']")).getText();
            if(Objects.equals(currentTxt, "CPT-99453") || Objects.equals(currentTxt, "CPT-99454") || Objects.equals(currentTxt, "CPT-99474")|| Objects.equals(currentTxt, "CPT-99091")){
                WebElement currentCheck =CPTChecks.get(i).findElement(By.xpath(".//input[@class='PrivateSwitchBase-input css-1m9pwf3']"));
                act.moveToElement(currentCheck).click().build().perform();
            }
        }

        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",driver.findElement(By.xpath("//div[@class='MuiBox-root css-pqr6kh']")));

        if(Objects.equals(driver.findElement(By.xpath("/html/body/div[2]/div[1]/header/div/p[4]/p")).getText(), "$110700")){
            System.out.println("SUCESSFULLY test Exicuted");
        }else {
            System.err.println("error in total sum");
        }

        driver.quit();
    }
}
