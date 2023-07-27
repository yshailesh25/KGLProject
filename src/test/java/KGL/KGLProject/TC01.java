package KGL.KGLProject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC01 extends BaseClass
{
	@Test
	public void TC01_Amazon()
	{
		//step1: Open https://www.amazon.in/ in Chrome Browser
		//@BeforeMethod
		
		//step2: Click on cart and verify Your Amazon Cart is empty is displayed with Sign in to your account and Sign up now button	
		clickEmptyAmazonCart();
		Assert.assertEquals(emptyCart,true);
		Assert.assertEquals(signIn,true);
		Assert.assertEquals(signUp,true);
		
		//step3: Click on Electronics from dropdown menu and Search Iphone 14
		searchIphone14();
		//go and select open new window : use CommonActions
		CommonActions.selectOpenWindow();
		
		//step4: Add to Cart above product and verify Added to Cart displayed with correct Cart subtotal with item count.
		addToCartIphone14();
		clickCartForSubTotal();
		Assert.assertEquals(itemCount,"Subtotal ("+1+" item):");
		Assert.assertEquals(cartSubtotal,"  "+priceUI+".00");

		//step5: Click on above product image available in Added to Cart section.
		clickOnProduct();
		//go and select open new window : use CommonActions
		CommonActions.selectOpenWindow();

		//step6: Click on Visit the Apple Store link, navigate to APPLEWATCH menu and click on any Series 8 watch from submenu and Click on any related available watch and perform Add to Cart 3 Units
		add3AppleWatch();
		
		//step7: Verify Added to Cart section displays correct product details.
		getAddedWatch();
		Assert.assertTrue(watchNameCart.contains("Apple Watch Series 8 [GPS + Cellular 41 mm]"));
		
		//step8: Click on Cart and Search for Dell Laptop and Add to Cart 2 units First Laptop
		searchDellLaptop();
		//go and select open new window : use CommonActions
		CommonActions.selectOpenWindow();
		add2UnitLaptop();

		//step9: Click on Cart, verify all Cart Items, and verify correct Cart information is getting displayed.
		clickCartFinalCart6Item();
		Assert.assertTrue(laptopName.contains(laptop2NameCart));
		Assert.assertTrue(watch3NameCart.contains("Apple Watch Series 8 [GPS + Cellular 41 mm]"));
		Assert.assertTrue(iphone1NameCart.contains("Apple iPhone 14 (128 GB) - Blue"));
		//validate Total items' count
		Assert.assertEquals(itemCount2,"Subtotal ("+sumTotalItems+" items):","****[Failed]:6 items in Cart Validation****");
		//remove watch to 1 item
		removeWatch();
		CommonActions.waitOn();
		
		//step10: Click on cart again and reduce quantity of AppleWatch by 1 and verify all Shopping cart details are proper.
		clickCartFinalCart4Item();
		//validate Total items' count
		Assert.assertEquals(itemCount3,"Subtotal ("+sumTotalItems1+" items):","****[Failed]:4 items in Cart Validation****");
	}
}
