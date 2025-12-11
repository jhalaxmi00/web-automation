package com.webautomation.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ScreenshotUtil {

    private static WebDriver driver;


    public static String captureScreenshot(WebDriver driver, String testName) throws IOException {
        String filePath = "target/screenshots/" + testName + "_" + System.currentTimeMillis() + ".png";

        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.createDirectories(Paths.get("target/screenshots"));
            Files.copy(screenshot.toPath(), Paths.get(filePath));

        }catch (IOException e){
            e.printStackTrace();
        }

        return filePath;



    }



}
