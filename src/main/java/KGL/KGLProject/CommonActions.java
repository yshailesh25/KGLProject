package KGL.KGLProject;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class CommonActions extends BaseClass{

	//go and select window
	public static void selectOpenWindow()
	{
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
				//System.out.println(driver.switchTo().window(child_window).getTitle());
			}
		}
	}
	
	public static void selectUsingJavaScript(WebElement arguments)
	{
		//to handle elementclickinterceptedexception
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("arguments[0].click();", arguments);
	}
	
	public static void waitOn()
	{
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
