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

public class HotelTypesPage extends AbstractPage{
	
	private final Logger logger = LogManager.getRootLogger();
	
	@FindBys(@FindBy(xpath = "//p[@class='bui-card__title']"))
	private List<WebElement> hotelTypesList;  

	public HotelTypesPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(this.driver, this);
	}

	@Override
	public void openPage() {
		logger.info("Main page opened");
	}

	public List<String> getHotelTypesListOnPage() {
		List<String> hotelsList = new ArrayList<String>();
		for (WebElement hotelType: hotelTypesList) {
			String text = hotelType.getText().trim();
			hotelsList.add(text);
		}
		return  hotelsList;
	}

}
