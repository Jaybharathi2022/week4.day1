package week4.day1.assignment;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MergeContact {

	public ChromeDriver setUpDriver() {

		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		return driver;

	}

	public void switchToNewWindow(ChromeDriver driver) {
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(windowHandles);
		driver.switchTo().window(winList.get(1));
	}

	public void switchToParentWindow(ChromeDriver driver, String parentWindow) {

		driver.switchTo().window(parentWindow);
	}

	public void startApp(ChromeDriver driver) {
		// launch url
		driver.get("http://leaftaps.com/opentaps/control/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		// enter username
		driver.findElement(By.id("username")).sendKeys("DemoSalesManager");
		// enter password
		driver.findElement(By.id("password")).sendKeys("crmsfa");
		// click login
		driver.findElement(By.className("decorativeSubmit")).click();
		// click crm/sfa
		driver.findElement(By.linkText("CRM/SFA")).click();
		// click contacts
		driver.findElement(By.linkText("Contacts")).click();
		// Click on Merge Contacts using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge Contacts']")).click();
		// get parent window handle
		String parentWindow = driver.getWindowHandle();
		// Click on Widget of From Contact
		driver.findElement(By.xpath("//input[@id='partyIdFrom']/following::img[@alt='Lookup']")).click();
		switchToNewWindow(driver);

		// Click on First Resulting Contact
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		switchToParentWindow(driver, parentWindow);

		// Click on Widget of To Contact
		driver.findElement(By.xpath("(//input[@id='partyIdFrom']/following::img[@alt='Lookup'])[2]")).click();
		switchToNewWindow(driver);
		// Click on Second Resulting Contact
		driver.findElement(By.xpath("(//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a)[2]")).click();
		switchToParentWindow(driver, parentWindow);
		// Click on Merge button using Xpath Locator
		driver.findElement(By.xpath("//a[text()='Merge']")).click();
		// accept alert
		Alert alert = driver.switchTo().alert();
		alert.accept();
		System.out.println("Title : "+driver.getTitle());
		driver.close();

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MergeContact merge = new MergeContact();
		merge.startApp(merge.setUpDriver());
	}

}
