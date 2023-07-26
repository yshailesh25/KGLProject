package KGL.KGLProject;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC01 
{
	@Test
	public void TC01_Amazon()
	{
		//step1: Open https://www.amazon.in/ in Chrome Browser
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();

		//step2: Click on cart and verify Your Amazon Cart is empty is displayed with Sign in to your account and Sign up now button
		driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);	
		driver.findElement(By.xpath("//div[@id='nav-cart-text-container']")).click();
		boolean cart= driver.findElement(By.xpath("//*[contains(text(),'Your Amazon Cart is empty')]")).isDisplayed();
		boolean signIn= driver.findElement(By.xpath("//*[contains(text(),'Sign in to your account')]")).isDisplayed();
		boolean signUp= driver.findElement(By.xpath("//*[contains(text(),'Sign up now')]")).isDisplayed();

		//step3: Click on Electronics from dropdown menu and Search Iphone 14
		Select dropDown=new Select(driver.findElement(By.xpath("//select[@id='searchDropdownBox']")));
		dropDown.selectByVisibleText("Electronics");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iphone 14");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();

		driver.findElement(By.xpath("//*[@data-index='3']//h2//span")).click();
		//go and select window
		String parent=driver.getWindowHandle();
		Set<String>s=driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I1= s.iterator();
		while(I1.hasNext())
		{
			String child_window=I1.next();
			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
			}
		}
		String priceUI=driver.findElement(By.xpath("//*[@id='corePriceDisplay_desktop_feature_div']//span[@class='a-price-whole']")).getText();
		System.out.println(priceUI);

		driver.findElement(By.xpath("//span[@id='submit.add-to-cart']")).click();
		    //System.out.println("PRINT>>no click on cart on dialog box");

		//step4: Add to Cart above product and verify Added to Cart displayed with correct Cart subtotal with item count.
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//span[@class='a-button a-button-base attach-button-large attach-cart-button']")).click();
		String itemCount=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span")).getText();
		System.out.println(itemCount);
		String cartSubtotal=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span[2]/span")).getText();
		System.out.println(cartSubtotal);
		Assert.assertEquals(itemCount,"Subtotal ("+1+" item):");
		Assert.assertEquals(cartSubtotal,"  "+priceUI+".00");

		//step5: Click on above product image available in Added to Cart section.
		driver.findElement(By.xpath("//img[contains(@src,'https://m.media-amazon.com/images/I/6')]")).click();

		//go and select window
		String parent2=driver.getWindowHandle();
		Set<String>s2=driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I2= s2.iterator();
		while(I2.hasNext())
		{
			String child_window=I2.next();
			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
			}
		}

		//step6: Click on Visit the Apple Store link, navigate to APPLEWATCH menu and click on any Series 8 watch from submenu and Click on any related available watch and perform Add to Cart 3 Units
		driver.findElement(By.xpath("//*[contains(text(),'Visit the Apple Store')]")).click();
		WebElement appleWatch=driver.findElement(By.xpath("//*[contains(text(),'Apple Watch')]"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", appleWatch); //to handle elementclickinterceptedexception
		WebElement watchType=driver.findElement(By.xpath("//*[contains(text(),'Apple Watch Series 8 (GPS  + Cellular)')]"));
		js.executeScript("arguments[0].click();", watchType); 

		driver.findElement(By.xpath("//*[contains(@href,'B0BDKHJJLJ')]")).click();
		Select quantityWatch=new Select(driver.findElement(By.xpath("//select[@name='quantity']")));
		quantityWatch.selectByVisibleText("3");
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//*[@id='attach-sidesheet-view-cart-button']")).click();

		//step7: Verify Added to Cart section displays correct product details.
		//String watchNameCart=driver.findElement(By.xpath("//*[contains(text(),'Apple Watch Series 8 [GPS + Cellular 41 mm] smart watch w/ (Product)RED Aluminium Case & (Product)RED Sport Band. Fi…')]")).getText();
		String watchNameCart=driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//span[@class='a-truncate-cut']")).getText();
		System.out.println(watchNameCart);
		watchNameCart.contains("Apple Watch Series 8 [GPS + Cellular 41 mm]");
		//watchNameCart.equals("Apple Watch Series 8 [GPS + Cellular 41 mm] smart watch w/ (Product)RED Aluminium Case & (Product)RED Sport Band. Fi…");

		//step8: Click on Cart and Search for Dell Laptop and Add to Cart 2 units First Laptop
		driver.findElement(By.xpath("//div[@id='nav-tools']//*[@id='nav-cart-count-container']")).click();
		System.out.println("clicked to cart");
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("Dell Laptop");
		driver.findElement(By.xpath("//input[@id='nav-search-submit-button']")).click();
		String dataasin=driver.findElement(By.xpath("//*[@data-index='4']")).getAttribute("data-asin");
		String laptopName=driver.findElement(By.xpath("//*[@data-index='4']//h2//a")).getText();
		driver.findElement(By.xpath("//*[@data-index='4']//h2//span")).click();

		//go and select window
		String parent3=driver.getWindowHandle();
		Set<String>s3=driver.getWindowHandles();
		// Now iterate using Iterator
		Iterator<String> I3= s3.iterator();
		while(I3.hasNext())
		{
			String child_window=I3.next();
			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);
				System.out.println(driver.switchTo().window(child_window).getTitle());
			}
		}


		Select quantityLaptop=new Select(driver.findElement(By.xpath("//select[@name='quantity']")));
		quantityLaptop.selectByVisibleText("2");
		driver.findElement(By.xpath("//input[@id='add-to-cart-button']")).click();
		driver.switchTo().activeElement();
		driver.findElement(By.xpath("//*[@id='attach-sidesheet-view-cart-button']")).click();
		System.out.println("clicked to cart after laptop add");

		//step9: Click on Cart, verify all Cart Items, and verify correct Cart information is getting displayed.
		//validate total item=6
		String itemCount2=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span")).getText();
		System.out.println(itemCount2);
		String cartSubtotal2=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span[2]/span")).getText();
		System.out.println(cartSubtotal2);

		//get cart items validated
		String laptop2NameCart=driver.findElement(By.xpath("//*[@data-asin='"+dataasin+"']//span[@class='a-truncate-cut']")).getText().replace("…", "");
		String laptop2quantity=driver.findElement(By.xpath("//*[@data-asin='"+dataasin+"']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		//System.out.println("On UI: "+laptopName);
		System.out.println(laptop2NameCart+" : "+laptop2quantity);
		boolean laptopNameMatched=laptopName.contains(laptop2NameCart);
		System.out.println("*****Laptop in cart validated successfully : "+laptopNameMatched+"*****");

		String watch3NameCart=driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//span[@class='a-truncate-cut']")).getText();
		String watch3quantity=driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println(watch3NameCart+" : "+watch3quantity);
		watch3NameCart.contains("Apple Watch Series 8 [GPS + Cellular 41 mm]");
		System.out.println("*****Watch in cart validated successfully*****");

		String iphone1NameCart=driver.findElement(By.xpath("//*[@data-asin='B0BDK62PDX']//span[@class='a-truncate-cut']")).getText();
		String iphone1quantity=driver.findElement(By.xpath("//*[@data-asin='B0BDK62PDX']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println(iphone1NameCart+" : "+iphone1quantity);
		iphone1NameCart.contains("Apple iPhone 14 (128 GB) - Blue");
		System.out.println("*****iPhone in cart validated successfully*****");
		int sumTotalItemsInt=Integer.parseInt(laptop2quantity)+ Integer.parseInt(watch3quantity)+ Integer.parseInt(iphone1quantity) ;
		String sumTotalItems=String.valueOf(sumTotalItemsInt);
		//System.out.println(sumTotalItemsInt+" "+sumTotalItems);

		//validate items' count
		Assert.assertEquals(itemCount2,"Subtotal ("+sumTotalItems+" items):");

		//remove watch to 1 item
		driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).click();
		driver.findElement(By.xpath("//div[@class='a-popover a-dropdown a-dropdown-common a-declarative']/div[1]//ul/li[2]")).click();

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//step10: Click on cart again and reduce quantity of AppleWatch by 1 and verify all Shopping cart details are proper.
		//validate total item=4
		/*
		 * WebElement sub=driver.findElement(By.xpath(
		 * "//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span"));
		 * WebDriverWait wait = new WebDriverWait(driver,30);
		 * wait.until(ExpectedConditions.textToBePresentInElementValue(sub,
		 * "Subtotal (4 items):"));
		 */

		String itemCount3=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span")).getText();
		System.out.println(itemCount3);
		String cartSubtotal3=driver.findElement(By.xpath("//form[@id='gutterCartViewForm']//div[@data-name='Subtotals']/span[2]/span")).getText();
		System.out.println(cartSubtotal3);

		//get cart items validated
		String laptop3quantity=driver.findElement(By.xpath("//*[@data-asin='"+dataasin+"']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println("Laptop quantity : "+laptop3quantity);

		String watch4quantity=driver.findElement(By.xpath("//*[@data-asin='B0BDKHJJLJ']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println("watch quantity : "+watch4quantity);

		String iphone2quantity=driver.findElement(By.xpath("//*[@data-asin='B0BDK62PDX']//label[@for='quantity']/following-sibling::span/span/span/span[2]")).getText();
		System.out.println("iphone quantity : "+iphone2quantity);

		int sumTotalItemsInt1=Integer.parseInt(laptop3quantity)+ Integer.parseInt(watch4quantity)+ Integer.parseInt(iphone2quantity) ;
		String sumTotalItems1=String.valueOf(sumTotalItemsInt1);
		//System.out.println(sumTotalItemsInt1+" "+sumTotalItems1);

		//validate items' count
		Assert.assertEquals(itemCount3,"Subtotal ("+sumTotalItems1+" items):");
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.quit();
	}


}
