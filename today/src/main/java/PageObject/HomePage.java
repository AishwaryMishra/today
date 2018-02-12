package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import baseTest.baseTestClass;
import baseTest.config;
import helpers.LoggerHelper;
import helpers.WaitHelper;

import org.apache.log4j.Logger;

public class HomePage {
	
	WebDriver driver;
	private final Logger log=LoggerHelper.getLogger(HomePage.class);
	WaitHelper waithelper;
	
	String Tshirts = "T-shirts";
	String Blouses = "Blouses";
	String CasualDresses = "Casual Dresses";
	String EveningDresses="Evening Dresses";
	String SummerDresses="Summer Dresses";
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[1]/a")
	public WebElement womenMenu;
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[2]/a")
	public WebElement dressesMenu;
	
	
	@FindBy(xpath="//*[@id='block_top_menu']/ul/li[3]/a")
	public WebElement tshirtsMenu;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		waithelper = new WaitHelper(driver);
		waithelper.waitForElement(driver, womenMenu,new config(baseTestClass.OR).getExplicitWait());
	}
	
	public void mouseOver(String data){
		log.info("doing mouse over on :"+data);
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]"))).build().perform();
	}
	
	public ProductCategoryPage clickOnIntem(String data){
		log.info("clickin on :"+data);
		driver.findElement(By.xpath("//*[contains(text(),'"+data+"')]")).click();
		return new ProductCategoryPage(driver);
	}
	
	public ProductCategoryPage clickOnMenu(WebElement element){
		log.info("clickin on : "+element.getText());
		element.click();
		return new ProductCategoryPage(driver);
	}

	
}
