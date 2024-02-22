package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class HomePage extends BasePage {

	JavascriptExecutor js = (JavascriptExecutor) driver;

	public HomePage(WebDriver driver) {
		super(driver);
	}

	// LOCATOR

	@FindBy(xpath = "//a[normalize-space()='New Bikes']")
	private WebElement newBikeDropDown;

	@FindBy(xpath = "//span[normalize-space()='Upcoming Bikes']")
	private WebElement upComingBikeOption;

	@FindBy(xpath = "//select[@id='makeId']")
	private WebElement manufacturers;

	@FindBy(xpath = "//*[@data-track-label='view-more-models-button']")
	private WebElement viewMore;

	@FindBy(xpath = "//a[@data-track-label='model-name']")
	private List<WebElement> ListOfModelName;

	@FindBy(xpath = "//*[@id=\"modelList\"]/li/div/div[3]/div[1]")
	private List<WebElement> ListOfBikePrices;

	@FindBy(xpath = "//*[@id=\"modelList\"]/li/div/div[3]/div[2]")
	private List<WebElement> ListOfBikeLaunchDate;

	@FindAll(@FindBy(xpath = "//div[@class='p-15 pt-10 mke-ryt rel']/parent::div"))
	private List<WebElement> bikeDiv;

	// ACTIONS

	public void hoverOnNewBike() {
		Actions act = new Actions(driver);
		act.moveToElement(newBikeDropDown).build().perform();
	}

	public void clickOnUpcomingBike() {
		borderElement(upComingBikeOption);
		upComingBikeOption.click();
	}

	public void selectManufacturer(String manufacturer) {
		Select manufacturerDrpDown = new Select(manufacturers);
		manufacturerDrpDown.selectByVisibleText(manufacturer);
	}

	public void viewMore() {

		borderElement(viewMore);
		js.executeScript("arguments[0].scrollIntoView();", viewMore);

		js.executeScript("arguments[0].click();", viewMore);
	}

	List<String[]> bikeDetails = new ArrayList<>();
	String[] bike;
	double priceDouble = 0.0;
	WebElement currentBike;

	public List<String[]> getDetailsOfBikes() throws InterruptedException {
		int size = ListOfBikePrices.size();

		for (int i = 0; i < size; i++) {
			bike = new String[3];

			currentBike = bikeDiv.get(i);

			js.executeScript("arguments[0].scrollIntoView();", currentBike);
			

			String price = ListOfBikePrices.get(i).getText();
			String[] words = price.split(" ");

			if (price.contains("Lakh"))
				priceDouble = Double.parseDouble(words[1]);
			else {
				String notLakh = words[1].replaceAll(",", "");
				borderElement(currentBike);
				priceDouble = Double.parseDouble(notLakh);
				priceDouble = priceDouble / 100000;
			}
			if (priceDouble <= 4.0) {
				bike[0] = ListOfModelName.get(i).getText();
				bike[1] = ListOfBikePrices.get(i).getText();
				bike[2] = ListOfBikeLaunchDate.get(i).getText();
				bikeDetails.add(bike);
				printBike(bike);

				// Screenshots.captureScreen(currentBike, bike[0].trim(), "Bikes");
				Thread.sleep(2000);
			}

		}
		return bikeDetails;

	}

	public void printBike(String[] bike) {
		for (String b : bike) {
			System.out.println(b);
		}
	}

	// Assert Methods
	public boolean newBikesAssert() {
		return newBikeDropDown.isDisplayed();
	}

	public boolean upcomingBikesAssert() {
		return upComingBikeOption.isDisplayed();
	}

	public boolean manufacturerAssert() {
		return manufacturers.isDisplayed();
	}
}
