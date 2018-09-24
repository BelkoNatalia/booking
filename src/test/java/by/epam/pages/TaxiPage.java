package by.epam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TaxiPage extends AbstractPage {
	
	private final Logger logger = LogManager.getRootLogger();
	
	@FindBy(xpath = "//a[@aria-label= 'pickup date input field']")
	private WebElement tableDate;
	
	@FindBy(xpath = "//a[@aria-label= 'pickup time input field']")
	private WebElement tableTime;
	
	@FindBy(xpath = "//button[@class='rw-time-picker__confirm']")
	private WebElement btnChoice;
	
	@FindBy(xpath = "//button[@class='rw-btn__primary rw-search__btn']")
	private WebElement btnSearch;
	
	@FindBy(id = "pickupLocation")
	private WebElement txtPlaceFrom;
	
	@FindBy(id = "passengers")
	private WebElement dropDownMenuPassengers;
	
	@FindBy(id = "pickupHour")
	private WebElement dropDownMenuHour;
	
	@FindBy(id = "dropoffLocation")
	private WebElement txtPlaceTo;

	public TaxiPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		logger.info("Taxi page opened");
		
	}

	public void fillPlaceFrom(String placeFrom) {
		txtPlaceFrom.clear();
		txtPlaceFrom.sendKeys(placeFrom);
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String xpathPlaceFrom = "//div[contains(text(),'" + placeFrom + "') ]";
		WebElement elementPlaceFrom = driver.findElement(By.xpath(xpathPlaceFrom));
		elementPlaceFrom.click();
	}

	public void fillPlaceTo(String placeTo) {
		txtPlaceTo.clear();
		txtPlaceTo.sendKeys(placeTo);
		
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String xpathPlaceTo = "//div[contains(text(),'" + placeTo + "') ]";
		WebElement elementPlaceTo = driver.findElement(By.xpath(xpathPlaceTo));
		elementPlaceTo.click();
	}

	public void fillDate(String day, String monthYear) {
		tableDate.click();
		try {
			Thread.sleep(2500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String xpathMonthYear = "//caption[text()='" + monthYear + "']";

		while (driver.findElements(By.xpath(xpathMonthYear)).isEmpty()) {
			WebElement next = driver.findElement(By.xpath("//a[@class='next-month']"));
			next.click();
		}
		
		String xpathDay = "//td/a[text()='" + day + "']";
		WebElement elementDay = driver.findElement(By.xpath(xpathDay));
		elementDay.click();

	}

	public void fillTime(String hour) {
		tableTime.click();
		Select dropdown = new Select(dropDownMenuHour);
		dropdown.selectByVisibleText(hour);
		btnChoice.click();
	}

	public void fillPassengers(String passengers) {
		Select dropdown = new Select(dropDownMenuPassengers);
		dropdown.selectByVisibleText(passengers);
	}

	public void pressSearch() {
		btnSearch.click();
	}

	
}
