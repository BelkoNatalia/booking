package by.epam.driver;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by Vitali_Shulha on 20-Oct-16.
 */
public class DriverSingleton {

	private static WebDriver driver;
	private static final Logger logger = LogManager.getRootLogger();
	private static final String WEBDRIVER_GECKO_DRIVER = "webdriver.chrome.driver";
	private static final String GECKODRIVER_GECKODRIVER_EXE_PATH = "D:\\Наташка\\JAVA\\chromedriver\\chromedriver.exe";

	private DriverSingleton() {
	};

	public static WebDriver getDriver() {
		if (null == driver) {
			System.setProperty(WEBDRIVER_GECKO_DRIVER, GECKODRIVER_GECKODRIVER_EXE_PATH);
			driver = new ChromeDriver();
//            System.setProperty("webdriver.gecko.driver", "D:\\Наташка\\JAVA\\chromedriver\\geckodriver.exe");
//            driver = new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			logger.info("Browser started");
		}

		return driver;
	}

	public static void closeDriver() {
		driver.quit();
		driver = null;
	}
}