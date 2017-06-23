package pgObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegStep2PgObj {
	public WebDriver driver;
	
	public RegStep2PgObj(WebDriver driver) {
		this.driver = driver;
	}
	
	
	public By email_txt = By.id("email");
	public By pnoneNo_txt = By.id("phone");
	// Select gender_slc = new Select(driver.findElement(By.id("gender")));
	public By gender_slc = By.id("gender");
	public By company_txt = By.xpath("companyName");
	public By addLine1_txt = By.id("address1");
	public By addLine2_txt = By.id("address2");
	public By next_btn = By.xpath(".//*[@id='step-2']/div/div/a");
}
