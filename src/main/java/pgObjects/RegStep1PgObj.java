package pgObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegStep1PgObj {
	public WebDriver driver;

	public RegStep1PgObj(WebDriver driver) {
		this.driver = driver;
	}
	
	public By firstName_txt = By.id("firstName");
	public By lastName_txt = By.id("lastName");
	public By next_btn = By.xpath(".//*[@id='step-1']/div/div/a");

}
