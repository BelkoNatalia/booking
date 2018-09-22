package by.epam.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccommodationSearchPage extends AbstractPage {
	private final Logger logger = LogManager.getRootLogger();

	@FindBy(xpath = "//div[@class='sr_header ']/h1")
	private WebElement headerTitle;

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

}
