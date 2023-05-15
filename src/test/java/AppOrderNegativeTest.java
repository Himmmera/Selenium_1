import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppOrderNegativeTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() { WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--headless");
        driver = new ChromeDriver (options);
        driver.get("http://localhost:9999/");
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }
    @Test
    public void shouldBeFailedIncorrectNameInput() {
        driver.findElement(By.xpath("[data-test-id = name] input")).sendKeys("Moris Michaella");
        driver.findElement(By.xpath("[data-test-id = phone] input")).sendKeys("+75555555555");
        driver.findElement(By.xpath("[data-test-id = agreement]")).click();
        driver.findElement(By.xpath("button")).click();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",
                driver.findElement(By.xpath("[data-test-id = name].input_invalid .input__sub")).getText().trim());

    }

    @Test
    public void shouldBeFailedIncorrectPhoneInput() {
        driver.findElement(By.cssSelector("[data-test-id = name] input")).clear();
        driver.findElement(By.cssSelector("[data-test-id = phone] input")).sendKeys("+75555555555");
        driver.findElement(By.cssSelector("[data-test-id = agreement]")).click();
        driver.findElement(By.className("button")).click();
        assertEquals("Поле обязательно для заполнения",
                driver.findElement(By.cssSelector("[data-test-id = name].input_invalid .input__sub")).getText().trim());

    }

    @Test
    public void shouldBeFailedIncorrectPhoneInput1() {
        driver.findElement(By.cssSelector("[data-test-id = name] input")).sendKeys("Ким Ирина Викторовна");
        driver.findElement(By.cssSelector("[data-test-id = phone] input")).sendKeys("+75555555555");
        driver.findElement(By.cssSelector("[data-test-id = agreement]")).click();
        driver.findElement(By.className("button")).click();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79999999999.",
                driver.findElement(By.cssSelector("[data-test-id = phone].input_invalid .input__sub")).getText().trim());

    }

    @Test
    void shouldBeFailedEmptyPhoneInput() {
        driver.findElement(By.cssSelector("[data-test-id = name] input")).sendKeys("Ким Ирина Викторовна");
        driver.findElement(By.cssSelector("[data-test-id = phone] input")).clear();
        driver.findElement(By.cssSelector("[data-test-id = agreement]")).click();
        driver.findElement(By.className("button.button")).click();
        assertEquals( "Поле обязательно для заполнения",
                driver.findElement(By.cssSelector("[data-test-id = phone].input_invalid .input__sub")).getText().trim());

    }

    @Test
    void shouldBeFailedUncheckBox() {
        driver.findElement(By.cssSelector("[data-test-id = name] input")).sendKeys("Ким Ирина Викторовна");
        driver.findElement(By.cssSelector("[data-test-id = phone] input")).sendKeys("+75555555555");
        driver.findElement(By.className("button.button")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test-id=agreement].input_invalid")).isDisplayed());

    }
}



