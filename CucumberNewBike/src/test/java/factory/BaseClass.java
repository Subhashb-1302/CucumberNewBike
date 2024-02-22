package factory;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BaseClass {
	static WebDriver driver;
	static Properties p;
	static Logger logger;
	
	public static WebDriver initializeBrowser() throws IOException {
		if(getProperties().getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();
			
			if(getProperties().getProperty("os").equalsIgnoreCase("window")) {
				capabilities.setPlatform(Platform.WINDOWS);
			} else if (getProperties().getProperty("os").equalsIgnoreCase("mac")) {
				capabilities.setPlatform(Platform.MAC);
			} else {
				System.out.println("No matching os found");
			}
			switch(getProperties().getProperty("browser").toLowerCase()){
				case "chrome":
					capabilities.setBrowserName("chrome");
					break;
				case "edge":
					capabilities.setBrowserName("MicrosoftEdge");
					break;
				default:
					System.out.println("No matching browser found");
			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}
			
		else if	(getProperties().getProperty("execution_env").equalsIgnoreCase("local")) {
			switch(getProperties().getProperty("browser").toLowerCase()) {
			case "chrome":
				ChromeOptions chromeOpt = new ChromeOptions();
				chromeOpt.addArguments("disable-notifications");
				driver=new ChromeDriver(chromeOpt);
				break;
			case "edge":
				EdgeOptions edgeOpt = new EdgeOptions();
				edgeOpt.addArguments("disable-notifications");
				driver=new EdgeDriver(edgeOpt);
				break;
			default:
				System.out.println("No matching browser found");
				driver=null;
		}
		}
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
			
			return driver;
		}
		
		public static WebDriver getDriver() {
			return driver;
		}
		
		public static Logger getLogger() {
			logger=LogManager.getLogger();
			return logger;
	}
	
	public static Properties getProperties() throws IOException {
		FileReader file = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
		p=new Properties();
		p.load(file);
		return p;
	}
}
