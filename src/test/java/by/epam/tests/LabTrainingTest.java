package by.epam.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import by.epam.steps.Steps;

public class LabTrainingTest {
	private Steps steps;

	@BeforeMethod(description = "Init browser")
	public void setUp() {
		steps = new Steps();
		steps.initBrowser();
	}

	@Test(description = "first test")
	public void findThreeFreeRoomsNumberForTwoPeopleOnWeekend() {
		steps.openStartPage();
		steps.choosePlace("Минск");
		steps.chooseStartEndDateForLiving(23, "Декабрь 2018", 24, "Декабрь 2018");
		String childrenCount = "0";
		String adultCount = "2";
		String roomsCount = "3";
		steps.fillGuestsCount(roomsCount, adultCount , childrenCount);
		int countRoomsList = steps.getCountRoomsList();
		
		Assert.assertTrue(countRoomsList > 0);
	}
	
	@Test(description = "second test")
	public void findFiveFreeRoomsNumberCostLowerOneHundredForFivePeople() {
		steps.openStartPage();
		steps.choosePlace("Минск");
		steps.chooseStartEndDateForLiving(28, "Октябрь 2018", 29, "Октябрь 2018");
		String childrenCount = "0";
		String adultCount = "2";
		String roomsCount = "1";
		steps.fillGuestsCount(roomsCount, adultCount , childrenCount);
		List<String> priseList = steps.getCheapRoomsWithCostOnFirstPage();
		
		Assert.assertTrue(priseList.size() >= 5);
	}
	
	@Test(description = "third test")
	public void findTwoHotelsWithBreakfastForFivePeople() {
		steps.openStartPage();
		steps.choosePlace("Вильнюс");
		steps.chooseStartEndDateForLiving(20, "Ноябрь 2018", 25, "Ноябрь 2018");
		String childrenCount = "0";
		String adultCount = "5";
		String roomsCount = "1";
		steps.fillGuestsCount(roomsCount, adultCount , childrenCount);
		steps.applyFilterByHotelAndBreakfast();
		
		int countRoomsList = steps.getNumberAccommodationOptionsFound();
	
		Assert.assertTrue(countRoomsList >= 2);
	}
	
	@Test(description = "fourth test")
	public void findInfoAboutHotel() {
		steps.openStartPage();
		steps.choosePlace("Вильнюс");
		steps.chooseStartEndDateForLiving(10, "Октябрь 2018", 25, "Октябрь 2018");
		String childrenCount = "0";
		String adultCount = "2";
		String roomsCount = "1";
		steps.fillGuestsCount(roomsCount, adultCount , childrenCount);
		String hotelName = "Barboros apartamentai";
		int pageCountForSearch = 3;
		List<String> hotelNameList = steps.getHotelNameList(pageCountForSearch);
		
		Assert.assertTrue(hotelNameList.contains(hotelName));
	}
	
	@AfterMethod(description = "Stop Browser")
	public void stopBrowser() {
		steps.closeDriver();
	}

}
