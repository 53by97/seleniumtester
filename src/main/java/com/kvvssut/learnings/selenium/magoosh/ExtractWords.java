/*package com.kvvssut.learnings.selenium.magoosh;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtractWords {

	static {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Workspace_Srimanta\\Installations\\chromedriver_win32\\chromedriver.exe");
	}

	private static final String FILE_PATH = "C:\\Workspace_Srimanta\\Outputs\\magoosh_gre_words\\";

	public static void main(String[] args) {

		WebDriver driver = null;

		try {
			driver = new ChromeDriver();

			login(driver);
			process(driver);
			logout(driver);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
	}

	private static void login(WebDriver driver) throws InterruptedException {
		driver.get("https://gre.magoosh.com/login");
		waitForLoad(driver);

		WebDriverWait wait0 = new WebDriverWait(driver, 15);
		wait0.until(ExpectedConditions.visibilityOfElementLocated(By.id("session_login")));

		driver.findElement(By.id("session_login")).sendKeys("srimant.sahu@gmail.com");
		driver.findElement(By.id("session_password")).sendKeys("53by97MG");

		driver.findElement(By.id("session_submit_action")).click();

		waitForLoad(driver);

		System.out.println("Successfully Logged In!");
	}

	public static synchronized void process(WebDriver driver) throws Exception {

		System.out.println("Started Extracting Data!\n");

		Thread.sleep(2000);

		driver.get("https://gre.magoosh.com/flashcards/vocabulary/decks");
		waitForLoad(driver);

		List<WebElement> decks = driver.findElements(By.className("flashcard-deck"));
		List<String> decknames = new ArrayList<String>(decks.size());
		for (int i = 1; i <= decks.size(); i++) {
			decknames.add(
					driver.findElement(By.cssSelector("body > div.container > div > div:nth-child(" + i + ") > a > h3"))
							.getText());
		}

		BufferedWriter writer = null;
		StringBuilder contents = null;
		int count;

		for (String deckname : decknames) {
			driver.get("https://gre.magoosh.com/flashcards/vocabulary/decks");
			waitForLoad(driver);

			driver.findElement(By.partialLinkText(deckname)).click();
			waitForLoad(driver);
			count = 0;

			try {
				writer = new BufferedWriter(new FileWriter(new File(FILE_PATH + deckname + ".txt")));
				contents = new StringBuilder();

				while (driver.findElement(By.partialLinkText("Click to see meaning")) != null) {

					if (count == 51) {
						break;
					}

					Thread.sleep(500);

					WebDriverWait wait0 = new WebDriverWait(driver, 15);
					wait0.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Click to see meaning")));

					Thread.sleep(2000);

					driver.findElement(By.partialLinkText("Click to see meaning")).click();
					waitForLoad(driver);

					contents.append(driver
							.findElement(By.cssSelector(
									"body > div.container > div.flashcard-container > div > div.back > div.flashcard-content > div.flashcard-word.flashcard-word-small"))
							.getText().toUpperCase());
					contents.append("\t\t-\t");

					contents.append(driver
							.findElement(By.cssSelector(
									"body > div.container > div.flashcard-container > div > div.back > div.flashcard-content > div.flashcard-text"))
							.getText());
					contents.append("\n");

					contents.append(driver
							.findElement(By.cssSelector(
									"body > div.container > div.flashcard-container > div > div.back > div.flashcard-content > div.flashcard-example"))
							.getText());
					contents.append("\n\n");

					driver.findElement(By.partialLinkText("I knew this word")).click();
					waitForLoad(driver);

					count++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (writer != null) {
					writer.write(contents.toString());
					writer.close();
				}
			}

		}

		System.out.println("\nCompleted Extraction of Data!");
	}

	private static void logout(WebDriver driver) {

		driver.get("http://gre.magoosh.com/logout");
		waitForLoad(driver);

		System.out.println("Successfully Logged Out!");

	}

	private static synchronized void waitForLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

}
*/