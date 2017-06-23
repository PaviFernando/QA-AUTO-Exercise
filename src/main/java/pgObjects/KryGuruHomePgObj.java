package pgObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class KryGuruHomePgObj {
	public WebDriver driver;

	public KryGuruHomePgObj(WebDriver driver) {
		this.driver = driver;
	}
	
	public By loginPage_btn = By.xpath("html/body/div[1]/div/div/div[2]/div[1]/ul/li[1]/a");

	public By regForm_btn = By.xpath("html/body/div[1]/div/div/div[2]/div[1]/ul/li[4]/a");

}
