package by.epam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BookingMainPage extends AbstractPage {
	private final Logger logger = LogManager.getRootLogger();
	private final String BASE_URL = "https://www.booking.com/index.ru.html?label=gen173nr-1FCAEoggJCAlhYSDNYBGgliAEBmAEhuAEGyAEP2AEB6AEB-AELkgIBeagCAw&sid=af80a8f30babe186c94ed4d5206da12a&click_from_logo=1";
//	private final String BASE_URL = "https://www.booking.com/index.ru.html?label=gen173nr-1BCAEoggJCAlhYSDNYBGgliAEBmAEhuAEGyAEP2AEB6AEBkgIBeagCAw;sid=bd4f4e1d3fc3352ca4d43caf22aeef50;keep_landing=1&sb_price_type=total&";

	@FindBy(id = "ss")
	private WebElement txtPlace;

	@FindBy(xpath = "//div[@data-calendar2-type='checkin']")
	private WebElement calendar;

	@FindBy(xpath = "//span[@class='xp__guests__count']")
	private WebElement guestsCount;

	@FindBy(id = "no_rooms")
	private WebElement roomsCount;

	@FindBy(id = "group_adults")
	private WebElement adultsCount;

	@FindBy(id = "group_children")
	private WebElement childrenCount;

	@FindBy(xpath = "//button[@data-sb-id='main']")
	private WebElement btnSearch;

	public BookingMainPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		driver.navigate().to(BASE_URL);
		logger.info("Main page opened");
	}

	public void inputPlace(String place) {
		txtPlace.clear();
		txtPlace.sendKeys(place);
	}

	public void chooseStartEndDateForLiving(int startDay, String startMonthYear, int endDay, String endMonthYear) {
		calendar.click();
		String xpathStart = "//div[text()='" + startMonthYear + "']";

		while (driver.findElements(By.xpath(xpathStart)).isEmpty()) {
			WebElement next = driver.findElement(By.xpath("//div[@data-bui-ref='calendar-next']"));
			next.click();
		}

		String xpathStartDay = "//div[text()='" + startMonthYear +"']/..//td[text()=" + startDay + "]";
		WebElement elementStartDay = driver.findElement(By.xpath(xpathStartDay));
		elementStartDay.click();

		String xpathEnd = "//div[text()='" + endMonthYear + "']";

		while (driver.findElements(By.xpath(xpathEnd)).isEmpty()) {
			WebElement next = driver.findElement(By.xpath("//div[@data-bui-ref='calendar-next']"));
			next.click();
		}

		String xpathEndDay = "//div[text()='" + endMonthYear + "']/..//td[text()=" + endDay + "]";
		WebElement elementEndDay = driver.findElement(By.xpath(xpathEndDay));
		elementEndDay.click();
	}

	public void fillGuestsCount(String roomsCount, String adultCount, String childrenCount) {
		guestsCount.click();
		Select roomsDropdown = new Select(this.roomsCount);
		roomsDropdown.selectByVisibleText(roomsCount);

		Select adultsDropdown = new Select(this.adultsCount);
		adultsDropdown.selectByVisibleText(adultCount);

		Select childrenDropdown = new Select(this.childrenCount);
		childrenDropdown.selectByVisibleText(childrenCount);

	}

	public void checkPrice() {
		btnSearch.click();
	}
}
