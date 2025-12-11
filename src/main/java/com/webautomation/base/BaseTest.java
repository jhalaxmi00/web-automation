package com.webautomation.base;

import com.webautomation.config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class BaseTest {

    private static final ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();
    protected ConfigReader config = new ConfigReader();

    /**
     * Get thread-safe WebDriver instance
     */
    public WebDriver getDriver() {
        return driverThread.get();
    }

    /**
     * Initialize WebDriver based on 'browser' property
     */
    public void setUp() {
        String browser = ConfigReader.getProperty("browser", "chrome").toLowerCase();

        switch (browser) {
            case "chrome":
                driverThread.set(new ChromeDriver());
                break;
            case "firefox":
                driverThread.set(new FirefoxDriver());
                break;
            case "edge":
                driverThread.set(new EdgeDriver());
                break;
            default:
                System.out.println("Browser not supported! Launching Chrome by default.");
                driverThread.set(new ChromeDriver());
        }

        // Maximize and set implicit wait
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        Integer.parseInt(ConfigReader.getProperty("implicitWait", "10"))
                )
        );

        // Navigate to URL
        getDriver().get(ConfigReader.getProperty("url"));
    }

    /**
     * Close browser and remove WebDriver from ThreadLocal
     */
    public void tearDown() {
        if (getDriver() != null) {
            getDriver().quit();
            driverThread.remove();
        }
    }
}

//package com.webautomation.base;
//
//import com.webautomation.config.ConfigReader;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.edge.EdgeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
//
//import java.time.Duration;
//
//public class BaseTest {
//
//    public WebDriver getDriver() {
//        return driver;
//    }
//
//    protected WebDriver driver;
//
//    // Initialize WebDriver
//    public void setUp() {
//        String browser = ConfigReader.getProperty("browser", "chrome").toLowerCase();
//
//        switch (browser) {
//            case "chrome":
//                driver = new ChromeDriver();
//                break;
//            case "firefox":
//                driver = new FirefoxDriver();
//                break;
//            case "edge":
//                driver = new EdgeDriver();
//                break;
//            default:
//                System.out.println("Browser not supported! Launching Chrome by default.");
//                driver = new ChromeDriver();
//        }
//
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(
//                Duration.ofSeconds(
//                        Integer.parseInt(ConfigReader.getProperty("implicitWait", "10"))
//                )
//        );
//
//        // Navigate to the URL
//        driver.get(ConfigReader.getProperty("url"));
//    }
//
//    // Close the browser
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}
