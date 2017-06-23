package pgObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegStep3PgObj {
public WebDriver driver;
	
	public RegStep3PgObj(WebDriver driver) {
		this.driver = driver;
	}
	public By attach_btn = By.id("popupBtn");
	public By add_txt = By.id("count");
	public By add_btn = By.id("add");
	public By skill_count = By.xpath(".//*[@id='skill_group_1']/input");
	public By  finish_btn = By.xpath(".//*[@id='step-2']/div/div/a");
}
