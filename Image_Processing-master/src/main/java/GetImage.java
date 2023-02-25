import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.Objects;

public class GetImage {
    public String imageUrl;
    private static final int milliSecond = 1000;
    public GetImage(String Name) throws InterruptedException {

        System.setProperty(
                "webdriver.chrome.driver",
                "C:\\\\Users\\\\User\\\\Downloads\\\\chromedriver\\\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        driver.get(Name);
        driver.manage().window().maximize();

        List<WebElement> allLinks = driver.findElements(By.tagName("a"));
        for (WebElement link: allLinks){
            String str = link.getAttribute("href");
            String helpLink = "https://www.facebook.com/help/?ref=pf";
            if (str.equals(helpLink)){
                ErrorWindow errorWindow = new ErrorWindow("You have entered an incorrect user name.");
                driver.close();
            }
        }

        Thread.sleep(milliSecond*2);  // this is because sometimes the internet connection is unstable, so it's wait 2 second.
        List<WebElement> list = driver.findElements(By.tagName("image"));
        WebElement myImage = list.get(0);
        imageUrl = myImage.getAttribute("xlink:href");
        String url1 = driver.getCurrentUrl();

        Actions act = new Actions(driver);
        act.moveToElement(myImage).click().build().perform();

        String url2 = driver.getCurrentUrl();

        if(Objects.equals(url1, url2)) {
            ErrorWindow errorWindow = new ErrorWindow("There is no picture on this profile");
            driver.close();
        }

        imageUrl = myImage.getAttribute("xlink:href");
        driver.close();
    }
}
