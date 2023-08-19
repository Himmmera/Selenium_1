package ru.netology;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.swing.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AppOrderPositiveTest {
    WebDriver driver;

    @AfterAll
    static void setUpAll() {
        WebDriverManager.chromedriver().setup();
        //  System.setProperty("WebDriver.chrome.driver", "./driver/chromedriver_win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");// комментируем headless если нужен визуальный просмотр действий
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSomethingTest() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Сидоров Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79856239080");
        driver.findElement(By.cssSelector("[data-test-id=agreement")).click();
        driver.findElement(By.tagName("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNameDashTest() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Сидоров-Прокофьев Иван");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79856239080");
        driver.findElement(By.cssSelector("[data-test-id=agreement")).click();
        driver.findElement(By.tagName("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success")).getText().trim();
        assertEquals(expected, actual);
    }

    @Test
    void shouldNameSpaceTest() {
        driver.get("http://localhost:9999/");
        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("жуков лаптев константин");
        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79856239080");
        driver.findElement(By.cssSelector("[data-test-id=agreement")).click();
        driver.findElement(By.tagName("button")).click();
        String expected = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        String actual = driver.findElement(By.cssSelector("[data-test-id=order-success")).getText().trim();
        assertEquals(expected, actual);
    }
}
