/*package com.kvvssut.learnings.selenium.dictionarydotcom;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExtractWordPronounciation {

	static {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Workspace_Srimanta\\Installations\\chromedriver_win32\\chromedriver.exe");
	}

	private static final String READ_FILE_PATH = "C:\\Workspace_Srimanta\\Outputs\\dictionary_dot_com\\Read_Decks\\";
	private static final String WRITE_FILE_PATH = "C:\\Workspace_Srimanta\\Outputs\\dictionary_dot_com\\Write_Decks\\";

	public static void main(String[] args) {

		WebDriver driver = null;

		try {
			driver = new ChromeDriver();

			process(driver);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}

	}

	private static void process(WebDriver driver) throws IOException {

		driver.get("http://www.dictionary.com/");
		waitForLoad(driver);

		String[] files = new File(READ_FILE_PATH).list();

		BufferedReader reader = null;
		BufferedWriter writer = null;
		int count;
		String readLine, word, pronounciation;

		for (String filename : files) {
			try {
				reader = new BufferedReader(new FileReader(READ_FILE_PATH + filename));
				writer = new BufferedWriter(new FileWriter(WRITE_FILE_PATH + filename));
				count = 0;
				readLine = null;

				while ((readLine = reader.readLine()) != null) {
					if (count % 3 == 0) {
						word = readLine.substring(0, readLine.indexOf('\t'));
						pronounciation = getPronounciation(driver, word);
						writer.write(word + "  " + pronounciation + "  -  "
								+ readLine.substring(readLine.lastIndexOf('\t') + 1) + "\n");
						System.out.println(word + "\t" + pronounciation + "\t-"
								+ readLine.substring(readLine.lastIndexOf('\t') + 1) + "\n");
					} else {
						writer.write(readLine + "\n");
					}
					count++;
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					reader.close();
				}
				if (writer != null) {
					writer.close();
				}
			}

		}

	}

	private static String getPronounciation(WebDriver driver, String word) throws InterruptedException {
		
		WebDriverWait wait0 = new WebDriverWait(driver, 15);
		wait0.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
		Thread.sleep(1000);

		driver.findElement(By.id("q")).clear();
		waitForLoad(driver);
		Thread.sleep(1000);
		
		WebDriverWait wait1 = new WebDriverWait(driver, 15);
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("q")));
		Thread.sleep(1000);

		driver.findElement(By.id("q")).sendKeys(word);
		driver.findElement(By.id("q")).sendKeys(Keys.ENTER);
		waitForLoad(driver);
		Thread.sleep(2000);

		WebElement rootWebElement = driver.findElement(By.cssSelector(
				"#source-luna > div:nth-child(1) > section > header > div.header-row.header-extras.pronounce.pronset > div:nth-child(1) > span.pron.spellpron"));
		String pronounciationText = rootWebElement.getText();
		StringBuilder pronounciation = new StringBuilder(pronounciationText);

		String text;
		int startInd;

		List<WebElement> allBoldChilds = rootWebElement.findElements(By.xpath(".//span[@class='dbox-bold']"));
		for (WebElement webElement : allBoldChilds) {
			text = webElement.getText();
			startInd = pronounciationText.indexOf(text);
			pronounciation.delete(startInd, startInd + text.length());
			pronounciation.insert(startInd, text.toUpperCase());
		}

		return pronounciation.toString();
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