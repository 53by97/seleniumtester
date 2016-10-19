package com.kvvssut.learnings.selenium;

public class Cyclone {

	static {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Workspace_Srimanta\\Installations\\chromedriver_win32\\chromedriver.exe");
	}

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


		waitForLoad(driver);

		System.out.println("Successfully Logged In!");
	}

	public static synchronized void process(WebDriver driver) throws Exception {

		System.out.println("Started Extracting Data!\n");

		Thread.sleep(2000);

		driver.get("https://gre.magoosh.com/flashcards/vocabulary/decks");
		waitForLoad(driver);
		
		System.out.println("\nCompleted Extraction of Data!");
	}

	private static void logout(WebDriver driver) {

		driver.get("http://gre.magoosh.com/logout");
		waitForLoad(driver);

		System.out.println("Successfully Logged Out!");

	}

	private static synchronized void waitForLoad(WebDriver driver) {
		/*ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};*/
		/*WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);*/
	}

}
