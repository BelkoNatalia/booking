package by.epam.pages;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

public class SearchResultTaxiPage extends AbstractPage {
	
	private final Logger logger = LogManager.getRootLogger();
	
	@FindBys(@FindBy(xpath = "//div[@class='rw-search-result']"))
	private List<WebElement> webElementCarList;

	public SearchResultTaxiPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		logger.info("SearchResultTaxiPage opened");
	}

	public int getNumberOfOffers() {
		return webElementCarList.size();
	}

}
