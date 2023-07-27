package KGL.KGLProject;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

public class BaseClass {
	public static WebDriver driver;
	@BeforeMethod
	public void openBrowser()
	{
		//step1: Open https://www.amazon.in/ in Chrome Browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
	}

	boolean emptyCart,signIn,signUp;
	public void clickEmptyAmazonCart()
	{
		WebElement cart = driver.findElement(By.xpath("//div[@id='nav-cart-text-container']"));
		cart.click();
		emptyCart= driver.findElement(By.xpath("//*[contains(text(),'Your Amazon Cart is empty')]")).isDisplayed();
		signIn= driver.findElement(By.xpath("//*[contains(text(),'Sign in to your account')]")).isDisplayed();
		signUp= driver.findElement(By.xpath("//*[contains(text(),'Sign up now')]")).isDisplayed();
	}

	public void searchIphone14()
	{
		Select dropDown=new Select(driver.findElement(By.xpath("//select[@id='searchDropdownBox']")));
		dropDown.selectByVisibleText("Electronics");
		WebElement searchBar=driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchBar.sendKeys("iphone 14");
		WebElement searchButton=driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchButton.click();

		WebElement selectIPhone14=driver.findElement(By.xpath("//*[@data-index='3']//h2//span"));
		selectIPhone14.click();
	}
	String priceUI;
	public void addToCartIphone14()
	{
		priceUI=driver.findElement(By.xpath("//*[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']")).getText();
		//System.out.println(priceUI);
		WebElement addToCart=driver.findElement(By.xpath("//span[@id='submit.add-to-cart']"));
		addToCart.click();
	}

	String itemCount;
	String cartSubtotal;
	public void clickCartForSubTotal()
	{
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//span[@class='a-button a-button-base attach-button-large attach-cart-button']")).click();
		itemCount=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span")).getText();
		System.out.println(itemCount);
		cartSubtotal=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span[2]/span")).getText();
		System.out.println(cartSubtotal);
	}

	public void clickOnProduct()
	{
		driver.findElement(By.xpath("//img[contains(@src,'https://m.media-amazon.com/images/I/6')]")).click();
	}

	public void add3AppleWatch()
	{
		driver.findElement(By.xpath("//*[contains(text(),'Visit the Apple Store')]")).click();
		WebElement appleWatch=driver.findElement(By.xpath("//*[contains(text(),'Apple Watch')]"));
		CommonActions.selectUsingJavaScript(appleWatch);
		WebElement watchType=driver.findElement(By.xpath("//*[contains(text(),'Apple Watch Series 8 (GPS  + Cellular)')]"));
		CommonActions.selectUsingJavaScript(watchType);

		driver.findElement(By.xpath("//*[contains(@href,'B0BDKHJJLJ')]")).click();
		Select quantityWatch=new Select(driver.findElement(By.xpath("//select[@name='quantity']")));
		quantityWatch.selectByVisibleText("3");
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//*[@id='attach-sidesheet-view-cart-button']")).click();
	}

	String watchNameCart;
	public void getAddedWatch()
	{
		watchNameCart=driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//span[@class='a-truncate-cut']")).getText();
		//System.out.println(watchNameCart);
		//watchNameCart.contains("Apple Watch Series 8 [GPS + Cellular 41 mm]");
	}

	String dataasin;
	String laptopName;
	public void searchDellLaptop()
	{
		driver.findElement(By.xpath("//div[@id='nav-tools']//*[@id='nav-cart-count-container']")).click();
		//System.out.println("clicked to cart");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Dell Laptop");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		dataasin=driver.findElement(By.xpath("//*[@data-index='5']")).getAttribute("data-asin");
		laptopName=driver.findElement(By.xpath("//*[@data-index='5']//h2//a")).getText();
		driver.findElement(By.xpath("//*[@data-index='5']//h2//span")).click();
	}

	public void add2UnitLaptop()
	{
		Select quantityLaptop=new Select(driver.findElement(By.xpath("//select[@name='quantity']")));
		quantityLaptop.selectByVisibleText("2");
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//*[@id='attach-sidesheet-view-cart-button']")).click();
		//System.out.println("clicked to cart after laptop add");
	}

	String itemCount2,cartSubtotal2,laptop2NameCart,laptop2quantity,watch3NameCart,watch3quantity,
	iphone1NameCart,iphone1quantity,sumTotalItems;
	public void clickCartFinalCart6Item()
	{//validate total item=6 take total quantity and subtotal
		itemCount2=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span")).getText();
		System.out.println(itemCount2);
		cartSubtotal2=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span[2]/span")).getText();
		System.out.println(cartSubtotal2);
		//get Laptop quantity and name validate
		laptop2NameCart=driver.findElement(By.xpath("//*[@data-asin='"+dataasin+"']//span[@class='a-truncate-cut']")).getText().replace("…", "");
		laptop2quantity=driver.findElement(By.xpath("//*[@data-asin='"+dataasin+"']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println("Laptop quantity : "+laptop2quantity);
		//boolean laptopNameMatched=laptopName.contains(laptop2NameCart);
		//System.out.println("*****Laptop in cart validated successfully : "+laptopNameMatched+"*****");

		//get watch quantity and name validate
		watch3NameCart=driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//span[@class='a-truncate-cut']")).getText();
		watch3quantity=driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println("Watch quantity : "+watch3quantity);
		//watch3NameCart.contains("Apple Watch Series 8 [GPS + Cellular 41 mm]");
		//System.out.println("*****Watch in cart validated successfully*****");

		//get iPhone quantity and name validate
		iphone1NameCart=driver.findElement(By.xpath("//*[@data-asin='B0BDK62PDX']//span[@class='a-truncate-cut']")).getText();
		iphone1quantity=driver.findElement(By.xpath("//*[@data-asin='B0BDK62PDX']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println("Watch quantity : "+iphone1quantity);
		//iphone1NameCart.contains("Apple iPhone 14 (128 GB) - Blue");
		//System.out.println("*****iPhone in cart validated successfully*****");
		int sumTotalItemsInt=Integer.parseInt(laptop2quantity)+ Integer.parseInt(watch3quantity)+ Integer.parseInt(iphone1quantity) ;
		sumTotalItems=String.valueOf(sumTotalItemsInt);
		//System.out.println(sumTotalItemsInt+" "+sumTotalItems);
	}

	public void removeWatch()
	{
		driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).click();
		driver.findElement(By.xpath("//div[@class='a-popover a-dropdown a-dropdown-common a-declarative']/div[1]//ul/li[2]")).click();
	}

	String itemCount3,cartSubtotal3,laptop3quantity,watch4quantity,iphone2quantity,sumTotalItems1;
	public void clickCartFinalCart4Item()
	{
		//validate total item=4
		itemCount3=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span")).getText();
		System.out.println(itemCount3);
		cartSubtotal3=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span[2]/span")).getText();
		System.out.println(cartSubtotal3);
		
		//get Laptop quantity and name validate
		laptop3quantity=driver.findElement(By.xpath("//*[@data-asin='"+dataasin+"']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println("Laptop quantity : "+laptop3quantity);
		
		//get watch quantity and name validate
		watch4quantity=driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println("watch quantity : "+watch4quantity);
		
		//get iPhone quantity and name validate
		iphone2quantity=driver.findElement(By.xpath("//*[@data-asin='B0BDK62PDX']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println("iphone quantity : "+iphone2quantity);
		
		int sumTotalItemsInt1=Integer.parseInt(laptop3quantity)+ Integer.parseInt(watch4quantity)+ Integer.parseInt(iphone2quantity) ;
		sumTotalItems1=String.valueOf(sumTotalItemsInt1);
		//System.out.println(sumTotalItemsInt1+" "+sumTotalItems1);
	}
	@AfterMethod
	public void closeBrowser()
	{
		CommonActions.waitOn();
		driver.quit();
	}
}
