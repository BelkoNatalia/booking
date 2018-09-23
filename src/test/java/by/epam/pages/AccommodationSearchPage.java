package by.epam.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class AccommodationSearchPage extends AbstractPage {
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(xpath = "//div[@class='sr_header ']/h1")
	private WebElement headerTitle;
	
	@FindBy(xpath = "//div[@id='sort_by']//a[@data-category='price']/..")
	private WebElement btnLowestPriceFirst;
	
	@FindBys(@FindBy(xpath = "//td[@class='roomPrice  sr_discount   ']//strong"))
	private List<WebElement> linkWithPrice;

	public AccommodationSearchPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		logger.info("Accommodation search page opened");
	}

	public String getTextWithSizeSearch() {
		String text = headerTitle.getText();
		return text;
	}

	public void sortPacesByPrice() {
		btnLowestPriceFirst.click();
	}

	public List<String> getInfoAboutPrices() {
		List<String> textsList = new ArrayList<String>();
		for(WebElement webElement: linkWithPrice) {
			String text = webElement.getText();
			textsList.add(text);
		}
		return textsList;
	}
}
