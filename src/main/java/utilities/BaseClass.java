package utilities;

import pgObjects.KryDemoLoginPgObj;
import pgObjects.KryGuruHomePgObj;
import pgObjects.RegStep1PgObj;
import pgObjects.RegStep2PgObj;
import pgObjects.RegStep3PgObj;
import pgObjects.RegStep4PgObj;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseClass {

	public WebDriver driver = null;
	public CommonUtils commonUtils = null;
	DesiredCapabilities caps = null;
	public Properties prop = null;
	public static long WAITTIME;
	public KryGuruHomePgObj kyGuruHomePgObj;
	public KryDemoLoginPgObj kryDemoLoginPgObj;
	public RegStep1PgObj regStep1PgObj;
	public RegStep2PgObj regStep2PgObj;
	public RegStep3PgObj regStep3PgObj;
	public RegStep4PgObj regStep4PgObj;
	public SimpleExcelReaderExample simpleExcelReaderExample;
	final static Logger log = Logger.getLogger(BaseClass.class.getName());
	public static String localBrowser = "";

	@BeforeClass(alwaysRun = true)
	protected void setUp() throws MalformedURLException {
		caps = new DesiredCapabilities();
		// The below conditions is to run the desktop tests
		if (localBrowser.equals("firefox")) {
			driver = new FirefoxDriver();
			includePageObjects();
		}
		if (localBrowser.equals("chrome")) {
			CommonUtils.setupChromeDriver(prop.get(Params.CHROME_DRIVER_PATH).toString());
			driver = new ChromeDriver();
			includePageObjects();
		}

	}

	public void includePageObjects() {
		kyGuruHomePgObj = new KryGuruHomePgObj(driver);
		kryDemoLoginPgObj = new KryDemoLoginPgObj(driver);
		kryDemoLoginPgObj = new KryDemoLoginPgObj(driver);
		regStep1PgObj = new RegStep1PgObj(driver);
		regStep2PgObj = new RegStep2PgObj(driver);
		regStep3PgObj = new RegStep3PgObj(driver);
		regStep4PgObj = new RegStep4PgObj(driver);
		commonUtils = new CommonUtils(driver);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.close();
		driver.quit();
	}

	@BeforeSuite(alwaysRun = true)
	public void readConfig(final ITestContext testContext) throws InterruptedException {
		prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream("src/main/resources/environment.properties");
			prop.load(input);
			localBrowser = prop.getProperty("localBrowser");
			WAITTIME = Long.parseLong(prop.getProperty("threadSleep"));
			simpleExcelReaderExample = new SimpleExcelReaderExample(prop.getProperty(Params.EXCEL_FILE_PATH));
			log.info(localBrowser + "is the local browser selected to run the test");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}