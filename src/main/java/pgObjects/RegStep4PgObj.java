package pgObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegStep4PgObj {
	
	public WebDriver driver;
	
	public RegStep4PgObj(WebDriver driver) {
		this.driver = driver;
	}
	public By success_msg = By.xpath("html/body/div[1]/div[1]/div/div[2]/div[1]/div");
}
