package Framework;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.mongodb.client.model.geojson.Point;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static java.time.Duration.ofSeconds;

public class HybridAppTesting extends capability {

	static AndroidDriver<AndroidElement> driver;

	@BeforeTest
	public void login() throws InterruptedException, IOException {

		Thread.sleep(8000);
		service = startserver();

		driver = capabilities(deviceName, apppackage, appActivity);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		Thread.sleep(8000);

		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Settings\"]")).click();
		driver.findElement(By.xpath("//*[@text='Sign in']")).click();
		driver.findElement(
				By.xpath("//android.widget.Button[@content-desc=\"Continue with Google\"]/android.view.ViewGroup"))
				.click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.id("com.google.android.gms:id/account_display_name")).click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		System.out.println("Logged in Successfully");

	}

	@Test(priority = 1)
	public void Navigating_to_Practice_Test_page() throws InterruptedException, IOException {

		driver.findElement(By.xpath("//*[@text='Search']")).click();
		driver.findElement(By.xpath("//*[@text='Math']")).click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Class 6 (Foundation)\"));").click();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@text='Addition and subtraction']")).click();
		driver.findElementByAndroidUIAutomator(
				"new UiScrollable(new UiSelector()).scrollIntoView(text(\"Add within 1000\"));").click();

		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@class='android.view.ViewGroup' and @index='5']")));

		driver.findElement(By.xpath("//*[@class='android.view.ViewGroup' and @index='5']")).click();
		driver.findElement(By.xpath("//android.widget.Button[@content-desc=\"Back\"]/android.widget.ImageView"))
				.click();

		System.out.println("Navigated to Practice Test page of Addition and subtraction Successfully");

	}

	@Test(priority = 2)
	public void Edit_My_Courses() throws InterruptedException, IOException {

		driver.findElement(By.xpath("//*[@text='Home']")).click();

		MobileElement element = (MobileElement) driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(10)"
						+ ".scrollIntoView(new UiSelector().text(\"Edit\"))"));
		element.click();

		Boolean choosecourse = driver.findElement(By.xpath("//*[@text='What courses can we help you learn?']"))
				.isDisplayed();
		Assert.assertTrue(choosecourse);

		WebElement we = driver.findElement(By.xpath("//*[@text='Arithmetic']"));
		we.click();

		if (we.isSelected() == true) {

			driver.findElement(By.xpath("//*[@text='Done']")).click();
		}

		else {
			driver.findElement(By.xpath("//*[@text='Arithmetic']")).click();
			driver.findElement(By.xpath("//*[@text='Done']")).click();
		}

		System.out.println("Edited My courses Successfully");

	}

	@Test(priority = 3)
	public void BookMark_Math_Course() throws InterruptedException, IOException {

		driver.findElement(By.xpath("//*[@text='Bookmarks']")).click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		if (!driver.findElements(By.xpath("//*[@text='Edit']")).isEmpty()) {

			driver.findElement(By.xpath("//*[@text='Edit']")).click();
			driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"Select bookmark\"]")).click();
			driver.findElement(By.xpath("//*[@text='Delete']")).click();

		}

		driver.findElement(By.xpath("//*[@text='Search']")).click();

		WebDriverWait wait1 = new WebDriverWait(driver, 30);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("(//android.widget.Button[@content-desc=\"Add Bookmark\"])[3]")));

		driver.findElement(By.xpath("(//android.widget.Button[@content-desc=\"Add Bookmark\"])[3]")).click();
		driver.findElement(By.xpath("	\r\n" + "//android.widget.Button[@content-desc=\"Bookmarks tab\"]\r\n" + ""))
				.click();

		Assert.assertTrue(driver.findElement(By.xpath("//*[@text='Addition and subtraction']")).isDisplayed());
		System.out.println("BookMarked Math Course Successfully");

	}

	@Test(priority = 4)
	public void Viwed_Biology_Video() throws InterruptedException, IOException {

		driver.findElement(By.xpath("//*[@text='Search']")).click();
		driver.findElement(By.xpath("//*[@text='Search']")).click();
		driver.findElement(By.xpath("//*[@text='Science']")).click();

		MobileElement element = (MobileElement) driver.findElement(
				MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setMaxSearchSwipes(10)"
						+ ".scrollIntoView(new UiSelector().text(\"High school biology\"))"));
		element.click();

		driver.findElement(By.xpath("//*[@text='Biology and the scientific method']")).click();
		driver.findElement(By.xpath("//*[@text='Biology overview']")).click();

		WebDriverWait wait1 = new WebDriverWait(driver, 50);
		wait1.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//*[@text='Overview of biology, the study of life.']")));

		String OverviewPage = driver.findElement(By.xpath("//*[@text='Overview of biology, the study of life.']"))
				.getText();
		Assert.assertEquals(OverviewPage, "Overview of biology, the study of life.");
		System.out.println("Viwed Biology Video Successfully");

	}

	@AfterTest
	public void SignOut() throws InterruptedException, IOException {

		driver.findElement(By.xpath("//*[@text='Home']")).click();
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Settings\"]")).click();
		driver.findElement(By.xpath("//*[@text='Sign out']")).click();
		driver.findElement(By.xpath("//*[@text='SIGN OUT']")).click();
		System.out.println("Signed out Successfully");
		service.stop();

	}

}
