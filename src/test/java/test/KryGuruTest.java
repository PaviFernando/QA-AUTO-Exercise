package test;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



import utilities.BaseClass;

public class KryGuruTest extends BaseClass {
	final static Logger log = Logger.getLogger(KryGuruTest.class.getName());
	
	@BeforeMethod
	public void openBrwsr(){
		commonUtils.getUrl(prop.getProperty("url"));
		log.info("url is :"+prop.getProperty("url"));
		driver.manage().window().maximize();
		try {
			commonUtils.takeSnapShot("HomePage.png") ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void loginTest() {
		//start of login test
		commonUtils.click(kyGuruHomePgObj.loginPage_btn);
		String userName = simpleExcelReaderExample.getStrValue("Login_credentials.xlsx",1, "UserName");
		String passWord = simpleExcelReaderExample.getStrValue("Login_credentials.xlsx",1, "Password");
		commonUtils.sendKeys(kryDemoLoginPgObj.userName_txt,userName );
		commonUtils.sendKeys(kryDemoLoginPgObj.password_txt,passWord );
		commonUtils.click(kryDemoLoginPgObj.submit_btn);
		commonUtils.assertValue(commonUtils.getCurrentUrl(), "http://krypton.guru/demopage.html",
				"Enterd Credentials are Wrong");
		try {
			commonUtils.takeSnapShot("LoginSuccess.png") ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

		@Test
		public void registrationTest() {
		commonUtils.click(kyGuruHomePgObj.regForm_btn);
		//Step 1 data field 
		String firstName = simpleExcelReaderExample.getStrValue("RegistrationDetails.xlsx",1, "FirstName");
		String lastName = simpleExcelReaderExample.getStrValue("RegistrationDetails.xlsx",1, "LastName");
		commonUtils.sendKeys(regStep1PgObj.firstName_txt,firstName );
		commonUtils.sendKeys(regStep1PgObj.lastName_txt,lastName );
		commonUtils.click(regStep1PgObj.next_btn);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Step 2 data field 
		String email = simpleExcelReaderExample.getStrValue("RegistrationDetails.xlsx",1, "Email");
		String phoneNo = simpleExcelReaderExample.getStrValue("RegistrationDetails.xlsx",1, "PhoneNumber");
		String gender = simpleExcelReaderExample.getStrValue("RegistrationDetails.xlsx", 1, "Gender");
	
		commonUtils.sendKeys(regStep2PgObj.email_txt,email);
		commonUtils.sendKeys(regStep2PgObj.pnoneNo_txt, phoneNo);
		
		//commonUtils.selectFrmDrpDwn(regStep2PgObj.gender_slc, gender);
		// company details
		/*--------------------------------------------Due to not visible ---------------------------------------------	
		String comName = simpleExcelReaderExample.getStrValue("RegistrationDetails.xlsx",1, "CompanyName");
		String add1 = simpleExcelReaderExample.getStrValue("RegistrationDetails.xlsx",1, "AddressLine1");
		String add2 = simpleExcelReaderExample.getStrValue("RegistrationDetails.xlsx",1, "AddressLine2");
		//Scroll to page down
		commonUtils.scrollTo(100);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//boolean x =	commonUtils.isElementsVisibleOnPage(regStep2PgObj.company_txt);
	    //commonUtils.sendKeys(regStep2PgObj.company_txt,comName);
		//commonUtils.sendKeys(regStep2PgObj.addLine1_txt, add1);
		//commonUtils.sendKeys(regStep2PgObj.addLine2_txt, add2);

		------------------------------------------------------------------------------------------------------------*/
		commonUtils.click(regStep2PgObj.next_btn);
		
		// Step3 
		commonUtils.click(regStep3PgObj.attach_btn);
		this.switchToWindow();
		String skills = simpleExcelReaderExample.getStrValue("RegistrationDetails.xlsx",1, "Skills");
		int NoOfSkils = simpleExcelReaderExample.getNoOfData(skills, ",");
		//split the string and add to list
		List<String> items = Arrays.asList(skills.split("\\s*,\\s*"));
	
		String NoOfSkil = Integer.toString(--NoOfSkils);
		commonUtils.sendKeys(regStep3PgObj.add_txt, NoOfSkil);
		commonUtils.click(regStep3PgObj.add_btn);
		try {
			Thread.sleep(WAITTIME);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    //iterate through the list
		commonUtils.sendKeys(regStep3PgObj.skill_count, items.get(0));
	        for(int i=2;i<=NoOfSkils+1;i++){
	        	int xx= i-1;
	            driver.findElement(By.xpath(".//*[@id='skill_group"+i+"']/input")).sendKeys(items.get(xx));
	        }
	    
	        try {
				Thread.sleep(WAITTIME);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		commonUtils.click(regStep3PgObj.finish_btn);
		commonUtils.assertValue(commonUtils.getText(regStep4PgObj.success_msg),"Success! Information Saved successfully!",  
				"Data is not added successfuly");
		log.info(commonUtils.getText(regStep4PgObj.success_msg));
		try {
			commonUtils.takeSnapShot("successMassage.png") ;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
		//switch to window
		public void switchToWindow(){
    	// Store the current window handle
    	String winHandleBefore = driver.getWindowHandle();

    	
    	for(String winHandle : driver.getWindowHandles()){
    	    driver.switchTo().window(winHandle);
    	}
    	//File uploard
    	WebElement fileInput = driver.findElement(By.id("file"));
    	WebElement uploard = driver.findElement(By.id("done"));
    	String path = (String) prop.get("dataFile");
    	log.info(prop.get("dataFile").toString());
    	File file = new File(path);
    	String absolutePath = file.getAbsolutePath();
		fileInput.sendKeys(absolutePath);
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		uploard.click();
    	driver.close();
    	driver.switchTo().window(winHandleBefore);

    }

	@BeforeMethod(alwaysRun = true)
	private void beforeMethod(Method method) {
		System.out.println("Test Method----> " + this.getClass().getSimpleName() + "::" + method.getName());
	}

	@AfterMethod(alwaysRun = true)
	private void afterMethod() {
		System.out.println("_________________________________________________");
	}
	
	

}
