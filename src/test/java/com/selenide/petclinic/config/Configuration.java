package com.selenide.petclinic.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Configuration {

    private static Configuration instance;

    private final String mainUrl;
    private String browser = System.getProperty("browser", "chrome");

    private Configuration() {
        String schema = System.getProperty("schema", "http");
        String host = System.getProperty("host", "localhost");
        String port = System.getProperty("port", "4200");
        String app = System.getProperty("app", "petclinic");
        mainUrl = schema.concat("://").concat(host).concat(":").concat(port).concat("/").concat(app);

    }

    public static Configuration getInstance() {
        if (instance == null){
            instance = new Configuration();
        }
        return instance;
    }

    public String getMainUrl(){
        return mainUrl;
    }

    public String getBrowser() {
        return browser;
    }
}
