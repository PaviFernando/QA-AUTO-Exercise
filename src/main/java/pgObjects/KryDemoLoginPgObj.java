package pgObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.CommonUtils;

public class KryDemoLoginPgObj {
	public WebDriver driver;

	public KryDemoLoginPgObj(WebDriver driver) {
		this.driver = driver;
	}

	public By userName_txt = By.id("username");
	public By  password_txt = By.id("password");
	public By  submit_btn = By.id("submit");
	public By  error_msg = By.id("submit");
	
	
}
