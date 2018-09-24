package by.epam.steps;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import by.epam.driver.DriverSingleton;
import by.epam.pages.AccommodationSearchPage;
import by.epam.pages.BookingMainPage;
import by.epam.pages.HotelTypesPage;

public class Steps {
	private WebDriver driver;
	private BookingMainPage bookingMainPage;
	private AccommodationSearchPage accommodationSearchPage;
	private HotelTypesPage hotelTypesPage;
	
	private final Logger logger = LogManager.getRootLogger();

	public void initBrowser() {
		driver = DriverSingleton.getDriver();
	}

	public void closeDriver() {
		DriverSingleton.closeDriver();
	}
	
	public void openStartPage() {
		bookingMainPage = new BookingMainPage(driver);
		bookingMainPage.openPage();
	}

	public void choosePlace(String place) {
		bookingMainPage.inputPlace(place);
	}

	public void chooseStartEndDateForLiving(int startDay, String startMonthYear, int endDay, String endMonthYear) {
		bookingMainPage.chooseStartEndDateForLiving(startDay, startMonthYear, endDay, endMonthYear);
		
	}

	public void fillGuestsCount(String roomsCount, String adultCount, String childrenCount) {
		bookingMainPage.fillGuestsCount(roomsCount, adultCount, childrenCount);
	}

	public int getCountRoomsList() {
		bookingMainPage.checkPrice();
		accommodationSearchPage = new AccommodationSearchPage(driver);
		return getNumberAccommodationOptionsFound();
	}

	public List<String> getCheapRoomsWithCostOnFirstPage() {
		bookingMainPage.checkPrice();
		accommodationSearchPage = new AccommodationSearchPage(driver);
		accommodationSearchPage.sortPacesByPrice();
		List<String> priceList = accommodationSearchPage.getInfoAboutPrices();
		return priceList;
	}

	public void applyFilterByHotelAndBreakfast() {
		bookingMainPage.checkPrice();
		accommodationSearchPage = new AccommodationSearchPage(driver);
		accommodationSearchPage.applyFilterHotel();
		accommodationSearchPage.applyFilterBreakfast();
	}

	public int getNumberAccommodationOptionsFound() {
		String text = accommodationSearchPage.getTextWithSizeSearch();
		String[] textArray = text.split(" ");
		int countRoomsList = Integer.valueOf(textArray[2]);
		return countRoomsList;
	}

	public List<String> getHotelNameList(int pageCountForSearch) {
		bookingMainPage.checkPrice();
		accommodationSearchPage = new AccommodationSearchPage(driver);
		List<String> allHotelNameList = new ArrayList<String>();
		List<String> hotelNameListOnPage = accommodationSearchPage.getHotelNameListOnPage();
		allHotelNameList.addAll(hotelNameListOnPage);
		int i= 1;
		while (i!=pageCountForSearch) {
			accommodationSearchPage.goToNextPage();
			allHotelNameList.addAll( accommodationSearchPage.getHotelNameListOnPage() );
			i++;
		}
		return allHotelNameList;
	}

	public void goToHotelTypePage() {
		bookingMainPage.goToHotelTypePage();
	}

	public List<String> getHotelsTypes() {
		hotelTypesPage = new HotelTypesPage(driver);
		List<String> hotelTypesList = hotelTypesPage.getHotelTypesListOnPage();
		return hotelTypesList;
	}
}