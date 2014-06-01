import org.openqa.selenium.chrome.ChromeDriver
driver = {
	//TODO: Change this to pickup a system property not this dirty hard coding for my PC.
	System.setProperty('webdriver.chrome.driver', 'C:\\Tools\\chromedriver.exe')
	new ChromeDriver()
}
reportsDir = "target/geb-reports"