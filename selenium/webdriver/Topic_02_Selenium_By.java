package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Selenium_By {
//	- Bước 1: Mở browser lên
//	- Bước 2: Nhập vào Url
//	- Bước 3: Click vào My account để mở trang login ra
//	- Bước 4: Click Login
//	- Bước 5: Verify lỗi hiển thị
//	- Bước 6: Đóng browser
	
	//     Khai báo 1 biến để đại diện cho thư viện Selenium Webdriver
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		//		Bước 1: Mở browser lên
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//		Bấm cho maxmize browser lên
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_() {
		//		- Bước 2: Nhập vào Url
		driver.get("http://live.techpanda.org/index.php/customer/account/login/");
		//		- Bước 3: Click vào My account để mở trang login ra
		//	HTML của element (Email textbox)
		//		
		//		<input type="email" autocapitalize="none"
		//				autocorrect="off" spellcheck="false" 
		//				name="login[username]" value="" 
		//				id="email" class="input-text required-entry validate-email" 
		//				title="Email Address">
				// HTML của element (Email textbox)
				// input thẻ element này (tagname)
				// attribute name - type autocapitalize autocorrect spellcheck name value id class title
				// attribute value - email none off .....
		
				// Xpath
				// tagname[@attribute-name='attribute-value'] --> tìm thấy duy nhất thì mới dùng

				// input[@name='login[username]'] --> *
				// input[@id='email'] --> *
				// input[@title='Email Address'] --> *
		
				// CSS Format: 	tagname[attribute-name='attribute-value']
		// Tìm 1 element
			// ID
		driver.findElement(By.id("email"));
		
		// Class : New user form
		//Không lấy toàn bộ chuỗi mà lấy 1 phần ( new
		 // 1 - giá trị không có khoảng trắng ---> lấy hết
		//  2 -  giá trị có khoảng trắng ---> lấy một phần
		// Cách gõ code ít bị lỗi:
			// Không bị dư kí tự: ()
			// Không bị thiếu kí tự: ;
			// ctrl + space --> show laij suggest
		driver.findElement(By.className("new-users"));
		
		// Không bị lỗi cú pháp
		// code đúng
		// code tối ưu sau
		
		// Name
		driver.findElement(By.name("login[username]"));
		
		// Tagname -  tìm xem có bao nhiêu element trên màn hình
		driver.findElements(By.tagName("a"));
		
		// LinkText - lấy tuyệt đối
		driver.findElement(By.linkText("SEARCH TERMS"));
		
		// Partial linktext (link) - text tương đối hoặc tuyệt đối đều được
		driver.findElement(By.partialLinkText("SEARCH TERMS"));
		driver.findElement(By.partialLinkText("SEARCH"));
		driver.findElement(By.partialLinkText("TERMS"));
		driver.findElement(By.partialLinkText("CH TER"));
		
		// CSS -  cover được cả 6 loại trên
		driver.findElement(By.cssSelector("input[name='login[username]']"));
		driver.findElement(By.cssSelector("input[id='email']"));
		driver.findElement(By.cssSelector("input[title='Email Address']"));
		
		// Xpath
		driver.findElement(By.xpath("//input[@name='login[username]']"));
		driver.findElement(By.xpath("//input[@id='email']"));
		driver.findElement(By.xpath("//input[@title='Email Address']"));
	}
	@AfterClass
	public void afterClass() {
		//		- Bước 6: Đóng browser
		driver.quit();
	}
}
