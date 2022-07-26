package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SuppressWarnings("unused")
public class Topic_06_Textbox_TextArea {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String firstName, lastName, employeeID, editFirstName, editLastName;
	String immigrationNumber, comments;

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			//Mac
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		} else { 
			//Windown
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		firstName = "Adam";
		lastName ="Levie";
		editFirstName = "Tom";
		editLastName = "Cruise";
		immigrationNumber = "774703475";
		comments = "79 Madeira Way\nMaderia Beach\nFL 33708 USA";
		
	}

	@Test
	public void TC_01_Textbox_TextArea() {
		driver.get("https://opensource-demo.orangehrmlive.com/index.php");
		
		// Input user/ password textbox
		driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
		driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
		
		//Click login button
		driver.findElement(By.cssSelector("input#btnLogin")).click();
//		sleepInsecond(10);
		
		// Open Add Employee screen
		driver.get("https://opensource-demo.orangehrmlive.com/index.php/pim/addEmployee");
		
		// Input data to screen
		driver.findElement(By.cssSelector("input#firstName")).sendKeys(firstName);
		driver.findElement(By.cssSelector("input#lastName")).sendKeys(lastName);
		
		// Save value of Employee ID vao bien
		// lay ra gia tri
		// Gan vao bien
		employeeID = driver.findElement(By.cssSelector("input#employeeId")).getAttribute("value");
		
		//Click save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		
		// Tab: go 1 phan cua ham
		// Enter: go ra 1 phan roi nhan Enter de lay ra toan bo ten ham
		// Control + space: goi y code
		
		// Verify required fields are disabled
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		// verify actual the same expected value
		
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"),firstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"),lastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"),employeeID);
		
		//Click to save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInsecond(3);
		
		// Verify required fields are Enabled
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertTrue(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		//Edit cac fields: FirstName/LastName
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).sendKeys(editFirstName);
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).clear();
		driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).sendKeys(editLastName);
		
		// Click Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInsecond(3);
		
		// Verify required fields are disabled
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).isEnabled());
		Assert.assertFalse(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).isEnabled());
		
		// verify actual the same expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpFirstName")).getAttribute("value"),editFirstName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmpLastName")).getAttribute("value"),editLastName);
		Assert.assertEquals(driver.findElement(By.cssSelector("input#personal_txtEmployeeId")).getAttribute("value"),employeeID);
		
		// Open Immigration tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();
		
		//Click to Add button
		driver.findElement(By.cssSelector("input#btnAdd")).click();
		
		//Enter to Immigration number textbox and comments textare
		driver.findElement(By.cssSelector("input#immigration_number")).sendKeys(immigrationNumber);
		driver.findElement(By.cssSelector("textarea#immigration_comments")).sendKeys(comments);
		sleepInsecond(3);	
		//Click Save button
		driver.findElement(By.cssSelector("input#btnSave")).click();
		sleepInsecond(3);
		
		//Click to Passport link
		driver.findElement(By.xpath("//a[text()='Passport']")).click();
		
		//Verify actual value the same expected value
		Assert.assertEquals(driver.findElement(By.cssSelector("input#immigration_number")).getAttribute("value"), immigrationNumber);
		Assert.assertEquals(driver.findElement(By.cssSelector("textarea#immigration_comments")).getAttribute("value"), comments);
		
	}


//	@Test
//	public void TC_02_() {
//		
//		
//	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	// Sleep cung ( static wait)
	public void sleepInsecond(long timeInsecond) {
		try {
			Thread.sleep(timeInsecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
